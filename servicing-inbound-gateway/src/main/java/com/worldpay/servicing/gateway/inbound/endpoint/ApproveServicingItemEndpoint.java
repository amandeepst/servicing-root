package com.worldpay.servicing.gateway.inbound.endpoint;

import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_APPROVAL_ERROR;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_BILL_CORRECTION_APPROVAL_PAYMENT_DEBUG;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_MANUAL_APPROVAL_ADHOC_DEBUG;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_MANUAL_APPROVAL_ADJUSTMENT_DEBUG;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_MANUAL_APPROVAL_INFO;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_MANUAL_APPROVAL_PAYMENT_DEBUG;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_NO_ADHOC_WARN;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_NO_BILL_CORRECTIONS_WARN;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_139_NO_PAYMENT_WARN;
import static com.worldpay.servicing.common.logging.XmlLogHelper.marshallObjectIntoString;
import static com.worldpay.servicing.core.service.ServiceItemService.APPROVAL_SERVICE;
import static com.worldpay.servicing.model.dto.enums.ServiceItemType.ADHOC_CHARGE;
import static com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT;
import static com.worldpay.servicing.model.entity.enums.ServiceItemStatus.IN_PROGRESS;
import static com.worldpay.servicing.model.entity.enums.ServiceItemStatus.valueOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import com.worldpay.servicing.common.logging.LoggingMarker;
import com.worldpay.servicing.core.service.ServiceItemService;
import com.worldpay.servicing.gateway.inbound.model.approvews.ApproveServicingItemRequest;
import com.worldpay.servicing.gateway.inbound.model.approvews.ApproveServicingItemResponse;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServiceItemStatus;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServicingItemRequest;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServicingItemRequest.ServiceItem;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServicingItemResponse;
import com.worldpay.servicing.model.dto.AdhocChargeDTO;
import com.worldpay.servicing.model.dto.BillCorrectionDTO;
import com.worldpay.servicing.model.dto.EntityLifecycle;
import com.worldpay.servicing.model.dto.FinancialAdjustmentDTO;
import com.worldpay.servicing.model.dto.PaymentDTO;
import com.worldpay.servicing.model.dto.ServiceItemDTO;
import com.worldpay.servicing.model.dto.enums.ServiceItemType;
import com.worldpay.tpg.logging.TpgLogger;
import com.worldpay.tpg.logging.TpgLoggerFactory;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Date;
import java.util.List;

@Endpoint
@Transactional
@Monitored
public class ApproveServicingItemEndpoint {

  private static final TpgLogger LOGGER =
      TpgLoggerFactory.getTpgLogger(ApproveServicingItemEndpoint.class);

  private static final String NAMESPACE_URI =
      "http://model.inbound.gateway.servicing.worldpay.com/approvews";

  private static final String SERVICE_ITEM_FOR_CASE_ID = "The retrieved service item for case id: ";

  private static final String NOT_BE_NULL = " should not be null";

  private final ServiceItemService serviceItemService;

  @Autowired
  public ApproveServicingItemEndpoint(
      @Qualifier(APPROVAL_SERVICE) ServiceItemService serviceItemService) {
    this.serviceItemService = serviceItemService;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "approveServicingItemRequest")
  @ResponsePayload
  public ApproveServicingItemResponse approveServiceItem(
      @RequestPayload ApproveServicingItemRequest request) {

    ServicingItemResponse siResponse = new ServicingItemResponse();
    String caseId = request.getServicingItemRequest().getCaseInformation().getCaseId();
    LOGGER.info(SRV_139_MANUAL_APPROVAL_INFO,
        "Executing manual approval for service item belonging to case id {} and payload {} ", caseId,
        marshallObjectIntoString("com.worldpay.servicing.gateway.inbound.model.approvews", request));
    try {
      ServicingItemRequest serviceItemRequest = request.getServicingItemRequest();
      switch (serviceItemRequest.getServiceItem().getServiceType()) {
        case ADHOC_CHARGE:
          // take the caseId and update all charges
          LOGGER.debug(SRV_139_MANUAL_APPROVAL_ADHOC_DEBUG, "Executing approval for Adhoc Charges inside case {}", caseId);
          updateCharges(serviceItemRequest);
          break;

        case ADJUSTMENT:
          // update adjustment with adj_id = serviceid
          // update all payments having adj_id = serviceid
          LOGGER.debug(SRV_139_MANUAL_APPROVAL_ADJUSTMENT_DEBUG, "Executing approval for Adjustments inside case {}", caseId);
          updateAjustments(serviceItemRequest);
          break;

        case PAYMENT:
          // update all payments having parent_payment_id = serviceid
          LOGGER.debug(SRV_139_MANUAL_APPROVAL_PAYMENT_DEBUG, "Executing approval for Payments inside case {}", caseId);
          updatePayments(serviceItemRequest);
          break;

        case BILL_CORRECTION:
          // update all bill corrections having parent_payment_id = serviceid
          LOGGER.debug(SRV_139_BILL_CORRECTION_APPROVAL_PAYMENT_DEBUG, "Executing approval for Bill Corrections inside case {}", caseId);
          updateBillCorrections(serviceItemRequest);
          break;

      }

      siResponse.setId(request.getServicingItemRequest().getCaseInformation().getCaseId());
      siResponse.setRequestStatus(OK.toString());

    } catch (Exception e) {
      LOGGER.error(SRV_139_APPROVAL_ERROR, "Error encountered when approving item: {}",
          request.getServicingItemRequest().getCaseInformation().getCaseId(), e);
      siResponse.setId(request.getServicingItemRequest().getCaseInformation().getCaseId());
      siResponse.setRequestStatus(INTERNAL_SERVER_ERROR.toString());
      siResponse.setErrorMessage(e.getMessage());
    }

    ApproveServicingItemResponse response = new ApproveServicingItemResponse();
    response.setServicingItemResponse(siResponse);

    return response;
  }

  private void updateAjustments(ServicingItemRequest serviceItemRequest) {
    String adjustmentId = serviceItemRequest.getServiceItem().getServiceId();
    Assert.notNull(adjustmentId,
        "Please fill in the serviceId parameter in order to update adjustment status");
    String caseId = serviceItemRequest.getCaseInformation().getCaseId();
    ServiceItemDTO serviceItemDto =
        serviceItemService.loadByCaseIdAndType(caseId, ServiceItemType.FINANCIAL_ADJUSTMENT);
    Assert.notNull(serviceItemDto, SERVICE_ITEM_FOR_CASE_ID + caseId + NOT_BE_NULL);
    List<FinancialAdjustmentDTO> targetAdjustments = serviceItemDto.getServiceItem()
        .getFinancialAdjustments().stream()
        .filter(item -> adjustmentId.equals(item.getAdjustmentId())).collect(toList());
    ServiceItemDTO targetServiceItemDto = new ServiceItemDTO(serviceItemDto);
    if (targetAdjustments.isEmpty()) {
      LOGGER.warn(LoggingMarker.SRV_139_NO_ADJUSTMENT_WARN, "There are no Adjustments to modify inside case: " + caseId);
      return;
    } else {
      Assert.isTrue(targetAdjustments.size() == 1,
          "There should be a single Adjustment with id = " + adjustmentId);
      targetServiceItemDto.getServiceItem().setFinancialAdjustments(targetAdjustments);
      handleNewStatus(targetAdjustments, serviceItemRequest);
    }

    ServiceItemDTO serviceItemDtowithPayments = serviceItemService.loadByCaseIdAndType(caseId, PAYMENT);
    List<PaymentDTO> targetPayments = serviceItemDtowithPayments.getServiceItem().getPayments()
        .stream().filter(payment -> adjustmentId.equals(payment.getAdjustmentId()))
        .collect(toList());
    if (targetPayments.isEmpty()) {
      LOGGER.warn(SRV_139_NO_PAYMENT_WARN, "There are no Payments to modify inside case: " + caseId);
    } else {
      handleNewStatus(targetPayments, serviceItemRequest);
      targetServiceItemDto.getServiceItem().setPayments(targetPayments);
    }

    serviceItemService.updateServiceItem(targetServiceItemDto);
  }

  private void updatePayments(ServicingItemRequest serviceItemRequest) {
    String parentPaymentId = serviceItemRequest.getServiceItem().getServiceId();
    Assert.notNull(parentPaymentId,
        "Please fill in the serviceId parameter in order to update payments status");
    String caseId = serviceItemRequest.getCaseInformation().getCaseId();
    ServiceItemDTO serviceItemDto =
        serviceItemService.loadByCaseIdAndType(caseId, PAYMENT);
    Assert.notNull(serviceItemDto, SERVICE_ITEM_FOR_CASE_ID + caseId + NOT_BE_NULL);
    List<PaymentDTO> targetPayments = serviceItemDto.getServiceItem().getPayments().stream()
        .filter(payment -> parentPaymentId.equals(payment.getParentPaymentId()))
        .collect(toList());
    if (targetPayments.isEmpty()) {
      LOGGER.warn(SRV_139_NO_PAYMENT_WARN, "There are no Payments to modify inside case: " + caseId);
    } else {
      handleNewStatus(targetPayments, serviceItemRequest);
      ServiceItemDTO modifiedServiceItem = new ServiceItemDTO(serviceItemDto);
      modifiedServiceItem.getServiceItem().setPayments(targetPayments);
      serviceItemService.updateServiceItem(modifiedServiceItem);
    }
  }

  private void updateCharges(ServicingItemRequest serviceItemRequest) {
    String caseId = serviceItemRequest.getCaseInformation().getCaseId();
    ServiceItemDTO serviceItemDto = serviceItemService.loadByCaseIdAndType(caseId, ADHOC_CHARGE);
    Assert.notNull(serviceItemDto, SERVICE_ITEM_FOR_CASE_ID + caseId + NOT_BE_NULL);
    if (serviceItemDto.getServiceItem().getAdhocCharges().isEmpty()) {
      LOGGER.warn(SRV_139_NO_ADHOC_WARN, "There are no AdhocCharges to modify inside case: " + caseId);
    } else {
      List<AdhocChargeDTO> inProgressCharges = serviceItemDto.getServiceItem().getAdhocCharges().stream()
          .filter(adhocCharge -> IN_PROGRESS == adhocCharge.getStatus()).collect(toList());
      serviceItemDto.getServiceItem().setAdhocCharges(inProgressCharges);
      handleNewStatus(serviceItemDto.getServiceItem().getAdhocCharges(), serviceItemRequest);
      serviceItemService.updateServiceItem(serviceItemDto);
    }
  }

  private void updateBillCorrections(ServicingItemRequest serviceItemRequest) {
    String caseId = serviceItemRequest.getCaseInformation().getCaseId();
    ServiceItemDTO serviceItemDto =
        serviceItemService.loadByCaseIdAndType(caseId, ServiceItemType.BILL_CORRECTION);
    Assert.notNull(serviceItemDto, SERVICE_ITEM_FOR_CASE_ID + caseId + NOT_BE_NULL);
    if (serviceItemDto.getServiceItem().getBillCorrections().isEmpty()) {
      LOGGER.warn(SRV_139_NO_BILL_CORRECTIONS_WARN, "There are no BillCorrections to modify inside case: " + caseId);
    } else {
      String serviceId = serviceItemRequest.getServiceItem().getServiceId();
      List<BillCorrectionDTO> filteredCorrections = serviceItemDto.getServiceItem().getBillCorrections().stream()
          .filter(billCorr -> serviceId.equals(billCorr.getParentBillCorrId())).collect(
              toList());
      handleNewStatus(filteredCorrections, serviceItemRequest);
      serviceItemDto.getServiceItem().getBillCorrections().clear();
      serviceItemDto.getServiceItem().getBillCorrections().addAll(filteredCorrections);

      serviceItemService.updateServiceItem(serviceItemDto);
    }
  }

  private void handleNewStatus(List<? extends EntityLifecycle> entities,
      ServicingItemRequest originalRequest) {
    ServiceItem serviceItem = originalRequest.getServiceItem();
    ServiceItemStatus newStatus = serviceItem.getStatus();
    Date currentDate = new Date();
    if (newStatus != null) {
      entities.forEach(entity -> {
        if (ServiceItemStatus.APPROVED == newStatus || ServiceItemStatus.REJECTED == newStatus) {
          entity.setApprovalDate(currentDate);
          entity.setApprovedBy(originalRequest.getCaseInformation().getApprovedBy());
        }
        entity.setStatus(valueOf(newStatus.name()));
        entity.setRejectionReason(serviceItem.getRejectionReason());
      });
    }
  }
}
