package com.worldpay.servicing.model.mapper;

import com.worldpay.servicing.model.dto.PaymentDTO;
import com.worldpay.servicing.model.entity.FinancialAdjustment;
import com.worldpay.servicing.model.entity.Payment;

import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class EntityToDtoMapper<E, D> {

  private static final String DATE_FORMAT = "dd-MMM-yy HH.mm.ss";

  private EntityToDtoMapper() {
  }

  public static PaymentDTO convertPaymentToDTO(ModelMapper modelMapper, Payment payment) {
    PaymentDTO paymentDTO = modelMapper.map(payment, PaymentDTO.class);
    paymentDTO.setAdjustmentId(
        Optional.ofNullable(payment.getFinancialAdjustment())
            .map(FinancialAdjustment::getAdjustmentId)
            .orElse(null));
    return paymentDTO;
  }

  public static Payment convertPaymentToEntity(
      ModelMapper modelMapper, PaymentDTO paymentDTO, FinancialAdjustment adjustment) {
    Payment payment = modelMapper.map(paymentDTO, Payment.class);
    payment.setFinancialAdjustment(adjustment);
    return payment;
  }

  public static <E, D> D convertToDTO(
      ModelMapper modelMapper, E entity, Class<D> dtoClass) {
    return modelMapper.map(entity, dtoClass);
  }

  public static <E, D> E convertToEntity(
      ModelMapper modelMapper, D dto, Class<E> entityClass) {
    return modelMapper.map(dto, entityClass);
  }

  public static String serializeDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    return sdf.format(date).toUpperCase();
  }

}
