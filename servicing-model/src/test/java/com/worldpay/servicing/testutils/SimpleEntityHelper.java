package com.worldpay.servicing.testutils;

import static com.worldpay.servicing.model.entity.enums.ServiceItemStatus.NOT_APPROVED;

import com.worldpay.servicing.model.dto.AdhocChargeDTO;
import com.worldpay.servicing.model.dto.BillCorrectionDTO;
import com.worldpay.servicing.model.dto.FinancialAdjustmentDTO;
import com.worldpay.servicing.model.dto.PaymentDTO;
import com.worldpay.servicing.model.entity.AdhocCharge;
import com.worldpay.servicing.model.entity.BillCorrection;
import com.worldpay.servicing.model.entity.FinancialAdjustment;
import com.worldpay.servicing.model.entity.Payment;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;
import com.worldpay.servicing.model.mapper.EntityToDtoMapper;
import com.worldpay.servicing.model.util.GenericBuilder;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SimpleEntityHelper {

  private static final ModelMapper modelMapper = new ModelMapper();

  public static List<Payment> createPaymentsList(int iterations) {
    List<Payment> newPayments = new ArrayList<>();
    for (int i = 0; i < iterations; i++) {
      newPayments.add(ModelUtils.PaymentBuilder.newPayment().build());
    }
    return newPayments;
  }

  public static List<FinancialAdjustment> createAdjustmentsList(int iterations) {
    List<FinancialAdjustment> newAdjustments = new ArrayList<>();
    for (int i = 0; i < iterations; i++) {
      newAdjustments.add(ModelUtils.FinancialAdjustmentBuilder.newAdjustment().build());
    }
    return newAdjustments;
  }

  public static List<FinancialAdjustmentDTO> createAdjustmentDTOsList(int iterations) {
    return createAdjustmentDTOsList(iterations, false);
  }

  public static List<FinancialAdjustmentDTO> createAdjustmentDTOsList(int iterations, boolean fillId) {
    return createAdjustmentsList(iterations).stream()
        .map(item -> {
          FinancialAdjustmentDTO fadto = EntityToDtoMapper.convertToDTO(modelMapper, item, FinancialAdjustmentDTO.class);
          fadto.setAdjustmentId(fillId ? UUID.randomUUID().toString() : null);
          return fadto;
        })
        .collect(Collectors.toList());
  }

  public static List<PaymentDTO> createPaymentDTOsList(int iterations) {
    return createPaymentDTOsList(iterations, false);
  }

  public static List<PaymentDTO> createPaymentDTOsList(int iterations, boolean fillId) {
    return createPaymentsList(iterations).stream()
        .map(item -> {
          PaymentDTO pdto = EntityToDtoMapper.convertPaymentToDTO(modelMapper, item);
          pdto.setPaymentStatus(NOT_APPROVED);
          if (fillId) {
            pdto.setPaymentId(UUID.randomUUID().toString());
          }
          return pdto;
        })
        .collect(Collectors.toList());
  }

  public static List<AdhocCharge> createChargesList(int iterations) {
    List<AdhocCharge> newCharges = new ArrayList<>();
    for (int i = 0; i < iterations; i++) {
      newCharges.add(ModelUtils.AdhocChargeBuilder.newAdhocCharge().build());
    }

    return newCharges;
  }

  public static List<AdhocChargeDTO> createChargeDTOsList(int iterations) {
    return createChargeDTOsList(iterations, false);
  }

  public static List<AdhocChargeDTO> createChargeDTOsList(int iterations, boolean fillId) {
    return createChargesList(iterations).stream().map(item -> {
      AdhocChargeDTO charge = EntityToDtoMapper.convertToDTO(modelMapper, item, AdhocChargeDTO.class);
      charge.setStatus(NOT_APPROVED);
      if (fillId) {
        charge.setAdhocChargeId(UUID.randomUUID().toString());
      }
      return charge;
    }).collect(Collectors.toList());
  }

  public static List<BillCorrectionDTO> createBillCorrectionDTOsList(int iterations, boolean fillId) {
    return createBillCorrectionList(iterations).stream()
        .map(item -> {
          BillCorrectionDTO b = EntityToDtoMapper.convertToDTO(modelMapper, item, BillCorrectionDTO.class);
          b.setBillCorrId(fillId ? UUID.randomUUID().toString() : null);
          return b;
        })
        .collect(Collectors.toList());
  }

  public static List<BillCorrection> createBillCorrectionList(int iterations) {
    List<BillCorrection> newCorrections = new ArrayList<>();
    for (int i = 0; i < iterations; i++) {
      newCorrections.add(GenericBuilder.of(BillCorrection::new).build());
    }
    return newCorrections;
  }
}
