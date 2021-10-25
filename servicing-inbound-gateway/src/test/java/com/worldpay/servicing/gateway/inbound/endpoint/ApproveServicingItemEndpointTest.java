package com.worldpay.servicing.gateway.inbound.endpoint;

import static com.worldpay.servicing.model.dto.enums.ServiceItemType.ADHOC_CHARGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.worldpay.servicing.core.service.ServiceItemService;
import com.worldpay.servicing.gateway.inbound.model.approvews.ApproveServicingItemRequest;
import com.worldpay.servicing.gateway.inbound.model.approvews.ApproveServicingItemResponse;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServiceItemStatus;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServiceItemType;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServicingItemRequest;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServicingItemRequest.CallInfo;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServicingItemRequest.CaseInformation;
import com.worldpay.servicing.gateway.inbound.model.approvews.ServicingItemRequest.ServiceItem;
import com.worldpay.servicing.model.dto.AdhocChargeDTO;
import com.worldpay.servicing.model.dto.CaseIdentifier;
import com.worldpay.servicing.model.dto.PaymentDTO;
import com.worldpay.servicing.model.dto.ServiceItemDTO;
import com.worldpay.servicing.testutils.SimpleEntityHelper;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@RunWith(MockitoJUnitRunner.class)
public class ApproveServicingItemEndpointTest {

  private static final String CASE_ID = "12345";

  private static final String ADMIN = "ADMIN";

  private static final String REJECTION_REASON = "Not enough privileges";

  private static final String RESPONSE_IS_NULL = "Response is null";

  private static final String STATUS_IS_NOT_500 = "Status is not 500";

  private static final String MESSAGE_IS_NOT_THE_EXPECTED_ONE = "Error message is not the expected one";

  private static final String STATUS_IS_NOT_200 = "Status is not 200";

  private static final String CASE_ID_IS_NOT_THE_EXPECTED_ONE = "Case id is not the expected one";

  private static final String ITEM_STATUS_DIFFERENT = "Service Item Status is different from APPROVED";

  private static final String APPROVAL_IS_NOT_ADMIN = "Approval is not ADMIN";

  private static final String APPROVAL_DATE_MUST_BE_SET = "Approval date must be set";

  private static XMLGregorianCalendar APPROVAL_DATE;

  private static CaseInformation CASE_INFO;

  private static CallInfo CALL_INFO;

  private static ServicingItemRequest SERVICING_ITEM_REQUEST;

  @Mock
  private ServiceItemService serviceItemService;

  @InjectMocks
  private ApproveServicingItemEndpoint approveServicingItemEndpoint;

  @BeforeClass
  public static void overallTestInit() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
    try {
      GregorianCalendar gc = new GregorianCalendar();
      gc.setTime(new Date());
      APPROVAL_DATE = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
    } catch (DatatypeConfigurationException exp) {
      Assert.fail("Could not initialize Approval Date");
    }

    CASE_INFO = new CaseInformation();
    CASE_INFO.setApprovedBy(ADMIN);
    CASE_INFO.setCaseId(CASE_ID);
    CALL_INFO = new CallInfo();
    CALL_INFO.setCallId("AAAAA");
    CALL_INFO.setCallType("ServiceItemApproval");
    CALL_INFO.setCreationDate(APPROVAL_DATE);

    SERVICING_ITEM_REQUEST = new ServicingItemRequest();
    SERVICING_ITEM_REQUEST.setCaseInformation(CASE_INFO);
    SERVICING_ITEM_REQUEST.setCallInfo(CALL_INFO);
  }

  @Test
  public void testApproveServicingItemWithEmptyAdjustmentId()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.ADJUSTMENT);
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_500, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(MESSAGE_IS_NOT_THE_EXPECTED_ONE,
        "Please fill in the serviceId parameter in order to update adjustment status",
        response.getServicingItemResponse().getErrorMessage());

    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveAdjustmentServicingItemWithInvalidCaseId()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.ADJUSTMENT);
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    siRequest.getServiceItem().setServiceId("1100");
    request.setServicingItemRequest(siRequest);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT)))
        .thenReturn(null);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_500, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(MESSAGE_IS_NOT_THE_EXPECTED_ONE,
        "The retrieved service item for case id: 12345 should not be null",
        response.getServicingItemResponse().getErrorMessage());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithNoAdjustments() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.ADJUSTMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT)))
        .thenReturn(createServiceItemDto(
            com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT, 0));

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithTwoAdjustments() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.ADJUSTMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT, 2);
    dto.getServiceItem().getFinancialAdjustments().forEach(item -> item.setAdjustmentId("1100"));
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT)))
        .thenReturn(dto);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_500, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(MESSAGE_IS_NOT_THE_EXPECTED_ONE,
        "There should be a single Adjustment with id = 1100",
        response.getServicingItemResponse().getErrorMessage());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithOneAdjustmentAndNoPayments()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.ADJUSTMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT, 1);
    dto.getServiceItem().getFinancialAdjustments().forEach(item -> item.setAdjustmentId("1100"));
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT)))
        .thenReturn(dto);
    ServiceItemDTO dtoPayments = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT, 0);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(dtoPayments);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT));
    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verify(serviceItemService, times(1)).updateServiceItem(any(ServiceItemDTO.class));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithOneAdjustmentAndUnusablePayments()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.ADJUSTMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT, 1);
    dto.getServiceItem().getFinancialAdjustments().forEach(item -> item.setAdjustmentId("1100"));
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT)))
        .thenReturn(dto);
    ServiceItemDTO dtoPayments = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT, 2);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(dtoPayments);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT));
    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verify(serviceItemService, times(1)).updateServiceItem(any(ServiceItemDTO.class));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithOneAdjustmentAndUsablePayments()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.ADJUSTMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT, 1);
    dto.getServiceItem().getFinancialAdjustments().forEach(item -> item.setAdjustmentId("1100"));
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT)))
        .thenReturn(dto);
    ServiceItemDTO dtoPayments = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT, 2);
    dtoPayments.getServiceItem().getPayments().forEach(item -> item.setAdjustmentId("1100"));

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(dtoPayments);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());
    assertEquals("FinancialAdjustments size is not 1", 1, dto.getServiceItem().getFinancialAdjustments().size());
    assertEquals("Payments size should be 0", 0, dto.getServiceItem().getPayments().size());
    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.FINANCIAL_ADJUSTMENT));
    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verify(serviceItemService, times(1)).updateServiceItem(any(ServiceItemDTO.class));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithEmptyParentPaymentId()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.PAYMENT);
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_500, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(MESSAGE_IS_NOT_THE_EXPECTED_ONE,
        "Please fill in the serviceId parameter in order to update payments status",
        response.getServicingItemResponse().getErrorMessage());

    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithInvalidPaymentCaseId()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.PAYMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(null);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_500, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(MESSAGE_IS_NOT_THE_EXPECTED_ONE,
        "The retrieved service item for case id: 12345 should not be null",
        response.getServicingItemResponse().getErrorMessage());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithNoPayments() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.PAYMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(createServiceItemDto(
            com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT, 0));

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithUnusablePayments() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.PAYMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);
    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT, 3);
    dto.getServiceItem().getPayments().forEach(item -> item.setParentPaymentId("2200"));
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(dto);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithUsablePayments() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.PAYMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);
    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT, 3);
    dto.getServiceItem().getPayments().forEach(item -> item.setParentPaymentId("1100"));
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(dto);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    List<PaymentDTO> payments = dto.getServiceItem().getPayments();
    payments.forEach(payment -> {
      assertEquals(ITEM_STATUS_DIFFERENT,
          com.worldpay.servicing.model.entity.enums.ServiceItemStatus.APPROVED,
          payment.getPaymentStatus());
      assertEquals(APPROVAL_IS_NOT_ADMIN, ADMIN, payment.getApprovedBy());
      assertNotNull(APPROVAL_DATE_MUST_BE_SET, payment.getApprovalDate());
    });

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verify(serviceItemService, times(1)).updateServiceItem(any(ServiceItemDTO.class));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithSomeUsablePayments()
      throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.PAYMENT);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);
    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT, 6);
    dto.getServiceItem().getPayments().forEach(item -> {
      if (dto.getServiceItem().getPayments().indexOf(item) <= 2) {
        item.setParentPaymentId("1100");
      } else {
        item.setParentPaymentId("2200");
      }
    });
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT)))
        .thenReturn(dto);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    List<PaymentDTO> payments = dto.getServiceItem().getPayments();
    payments.forEach(payment -> {
      if (payments.indexOf(payment) <= 2) {
        assertEquals(ITEM_STATUS_DIFFERENT,
            com.worldpay.servicing.model.entity.enums.ServiceItemStatus.APPROVED,
            payment.getPaymentStatus());
        assertEquals(APPROVAL_IS_NOT_ADMIN, ADMIN, payment.getApprovedBy());
        assertNotNull(APPROVAL_DATE_MUST_BE_SET, payment.getApprovalDate());
      } else {
        assertEquals(ITEM_STATUS_DIFFERENT,
            com.worldpay.servicing.model.entity.enums.ServiceItemStatus.NOT_APPROVED,
            payment.getPaymentStatus());
        assertNull("Approved By is not null", payment.getApprovedBy());
        assertNull("Approved Date is not null", payment.getApprovalDate());
        assertNull("Rejection Reason is not null", payment.getRejectionReason());
      }

    });

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.PAYMENT));
    verify(serviceItemService, times(1)).updateServiceItem(any(ServiceItemDTO.class));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithNoCharges() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateAdhocRequest(ServiceItemStatus.APPROVED);
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(ADHOC_CHARGE)))
        .thenReturn(createServiceItemDto(
            ADHOC_CHARGE, 0));

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(ADHOC_CHARGE));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveSuccessfulServicingItemWithCharges() {

    ServicingItemRequest siRequest = generateAdhocRequest(ServiceItemStatus.APPROVED);
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ServiceItemDTO siDto = createServiceItemDto(ADHOC_CHARGE, 3);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID), eq(ADHOC_CHARGE))).thenReturn(siDto);

    ServicingItemRequest.ServiceItem serviceItem = siRequest.getServiceItem();
    serviceItem.setServiceId(siDto.getServiceItem().getAdhocCharges().get(0).getAdhocChargeId());
    serviceItem.setServiceType(ServiceItemType.ADHOC_CHARGE);
    siRequest.setServiceItem(serviceItem);

    ApproveServicingItemResponse response = approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    List<AdhocChargeDTO> charges = siDto.getServiceItem().getAdhocCharges();
    charges.forEach(charge -> {
      assertEquals(ITEM_STATUS_DIFFERENT,
          com.worldpay.servicing.model.entity.enums.ServiceItemStatus.APPROVED, charge.getStatus());
      assertEquals(APPROVAL_IS_NOT_ADMIN, ADMIN, charge.getApprovedBy());
      assertNotNull(APPROVAL_DATE_MUST_BE_SET, charge.getApprovalDate());
    });

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(ADHOC_CHARGE));
    verify(serviceItemService, times(1))
        .updateServiceItem(eq(siDto));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveRejectedServicingItemWithCharges() {

    ServicingItemRequest siRequest = generateAdhocRequest(ServiceItemStatus.REJECTED);
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ServiceItemDTO siDto = createServiceItemDto(ADHOC_CHARGE, 3);
    ServicingItemRequest.ServiceItem serviceItem = siRequest.getServiceItem();
    serviceItem.setServiceId(siDto.getServiceItem().getAdhocCharges().get(0).getAdhocChargeId());
    serviceItem.setServiceType(ServiceItemType.ADHOC_CHARGE);
    siRequest.setServiceItem(serviceItem);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID), eq(ADHOC_CHARGE))).thenReturn(siDto);

    ApproveServicingItemResponse response = approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    List<AdhocChargeDTO> charges = siDto.getServiceItem().getAdhocCharges();
    charges.forEach(charge -> {
      assertEquals(ITEM_STATUS_DIFFERENT,
          com.worldpay.servicing.model.entity.enums.ServiceItemStatus.REJECTED,
          charge.getStatus());
      assertEquals(APPROVAL_IS_NOT_ADMIN, ADMIN, charge.getApprovedBy());
      assertNotNull(APPROVAL_DATE_MUST_BE_SET, charge.getApprovalDate());
      assertEquals("Rejection reason is not the expected one", REJECTION_REASON,
          charge.getRejectionReason());
    });

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(ADHOC_CHARGE));
    verify(serviceItemService, times(1))
        .updateServiceItem(eq(siDto));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithInvalidCaseId() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateAdhocRequest(ServiceItemStatus.APPROVED);
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(ADHOC_CHARGE)))
        .thenReturn(null);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_500, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(MESSAGE_IS_NOT_THE_EXPECTED_ONE,
        "The retrieved service item for case id: 12345 should not be null",
        response.getServicingItemResponse().getErrorMessage());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(ADHOC_CHARGE));
    verifyNoMoreInteractions(serviceItemService);
  }

  @Test
  public void testApproveServicingItemWithOneBillCorrection() throws DatatypeConfigurationException {

    ServicingItemRequest siRequest = generateRequest(ServiceItemStatus.APPROVED, ServiceItemType.BILL_CORRECTION);
    siRequest.getServiceItem().setServiceId("1100");
    ApproveServicingItemRequest request = new ApproveServicingItemRequest();
    request.setServicingItemRequest(siRequest);

    ServiceItemDTO dto = createServiceItemDto(
        com.worldpay.servicing.model.dto.enums.ServiceItemType.BILL_CORRECTION, 1);
    dto.getServiceItem().getBillCorrections().forEach(item -> item.setBillCorrId("1100"));
    when(serviceItemService.loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.BILL_CORRECTION)))
        .thenReturn(dto);

    ApproveServicingItemResponse response =
        approveServicingItemEndpoint.approveServiceItem(request);

    assertNotNull(RESPONSE_IS_NULL, response);
    assertEquals(STATUS_IS_NOT_200, HttpStatus.OK.toString(),
        response.getServicingItemResponse().getRequestStatus());
    assertEquals(CASE_ID_IS_NOT_THE_EXPECTED_ONE, CASE_ID,
        response.getServicingItemResponse().getId());

    verify(serviceItemService, times(1)).loadByCaseIdAndType(eq(CASE_ID),
        eq(com.worldpay.servicing.model.dto.enums.ServiceItemType.BILL_CORRECTION));
    verify(serviceItemService, times(1)).updateServiceItem(dto);
  }

  private ServiceItemDTO createServiceItemDto(
      com.worldpay.servicing.model.dto.enums.ServiceItemType type, int itemIterations) {
    ServiceItemDTO sr = new ServiceItemDTO();
    com.worldpay.servicing.model.dto.CallInfo ci = new com.worldpay.servicing.model.dto.CallInfo();
    ci.setCallDescription("ApproveServiceItems");
    ci.setCallId("AAAAAAA");
    ci.setCallType("ApproveServiceItems");
    ci.setCreationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    sr.setCallInfo(ci);
    CaseIdentifier cid = new CaseIdentifier(CASE_ID);
    sr.setCaseIdentifier(cid);

    com.worldpay.servicing.model.dto.ServiceItem srvit =
        new com.worldpay.servicing.model.dto.ServiceItem();

    switch (type) {
      case ADHOC_CHARGE: {
        srvit.setAdhocCharges(SimpleEntityHelper.createChargeDTOsList(itemIterations, true));
        break;
      }
      case FINANCIAL_ADJUSTMENT: {
        srvit.setFinancialAdjustments(
            SimpleEntityHelper.createAdjustmentDTOsList(itemIterations, true));
        break;
      }
      case PAYMENT: {
        srvit.setPayments(SimpleEntityHelper.createPaymentDTOsList(itemIterations, true));
        break;
      }
      case BILL_CORRECTION: {
        srvit.setBillCorrections(SimpleEntityHelper.createBillCorrectionDTOsList(itemIterations, true));
        break;
      }

    }

    sr.setServiceItem(srvit);
    return sr;
  }

  private ServicingItemRequest generateAdhocRequest(ServiceItemStatus status) {

    ServiceItem serviceItem = createServiceItem(status, ServiceItemType.ADHOC_CHARGE);

    SERVICING_ITEM_REQUEST.setServiceItem(serviceItem);

    return SERVICING_ITEM_REQUEST;
  }

  private ServiceItem createServiceItem(ServiceItemStatus status, ServiceItemType sType) {
    ServiceItem serviceItem = new ServiceItem();
    serviceItem.setApprovalDecisionDate(APPROVAL_DATE);
    serviceItem.setServiceId(null);
    serviceItem.setServiceType(sType);
    serviceItem.setStatus(status);

    if (ServiceItemStatus.REJECTED == status) {
      serviceItem.setRejectionReason(REJECTION_REASON);
    }

    return serviceItem;
  }

  private ServicingItemRequest generateRequest(ServiceItemStatus status, ServiceItemType type)
      throws DatatypeConfigurationException {
    ServiceItem serviceItem = createServiceItem(status, type);
    SERVICING_ITEM_REQUEST.setServiceItem(serviceItem);
    return SERVICING_ITEM_REQUEST;
  }

}
