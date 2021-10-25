package com.worldpay.servicing.model.mapper;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.worldpay.servicing.model.dto.AdhocChargeDTO;
import com.worldpay.servicing.model.dto.BillCorrectionDTO;
import com.worldpay.servicing.model.dto.FinancialAdjustmentDTO;
import com.worldpay.servicing.model.dto.PaymentDTO;
import com.worldpay.servicing.model.entity.AdhocCharge;
import com.worldpay.servicing.model.entity.BillCorrection;
import com.worldpay.servicing.model.entity.Currency;
import com.worldpay.servicing.model.entity.FinancialAdjustment;
import com.worldpay.servicing.model.entity.Payment;
import com.worldpay.servicing.model.entity.Service;
import com.worldpay.servicing.model.entity.ServiceClassifier;
import com.worldpay.servicing.model.entity.ServicingAdminRule;
import com.worldpay.servicing.model.util.GenericBuilder;
import com.worldpay.servicing.testutils.ModelUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class EntityToDtoMapperTest {


  private static final String TEST_PAYMENT_ID = "paymentid";

  private static final String TEST_ADHOCCHG_ID = "adhocchgid";

  private static final String TEST_ADJUSTMENT_ID = "adjustmentid";

  private static final String TEST_BILL_CORRECTION_ID = "billCorrectionId";

  private static final String DATE_FORMAT = "dd-MMM-yy HH.mm.ss";

  @Mock
  ModelMapper modelMapper;

  private Payment payment;

  private AdhocCharge adhocChg;

  private Currency currency;

  private Service service;

  private ServiceClassifier serviceClassifier;

  private ServicingAdminRule servicingAdminRule;

  private FinancialAdjustment adjustment;

  private PaymentDTO paymentDto;

  private AdhocChargeDTO adhocChargeDTO;

  private FinancialAdjustmentDTO adjustmentDTO;

  private BillCorrection billCorrection;

  private BillCorrectionDTO billCorrectionDTO;

  @Before
  public void init() {
    initMocks(this);

    payment =
        ModelUtils.PaymentBuilder.newPayment()
            .paymentId(TEST_PAYMENT_ID)
            .build();

    adhocChg =
        ModelUtils.AdhocChargeBuilder.newAdhocCharge()
            .adhocChargeId(TEST_ADHOCCHG_ID)
            .build();

    adjustment =
        ModelUtils.FinancialAdjustmentBuilder.newAdjustment()
            .adjustmentId(TEST_ADJUSTMENT_ID)
            .build();

    billCorrection = GenericBuilder.of(BillCorrection::new)
        .with(BillCorrection::setBillCorrId, TEST_BILL_CORRECTION_ID)
        .build();

    currency = new Currency();
    currency.setCurrencyId("GBP");
    currency.setCurrencyName("Pound Sterling");
    currency.setDecimalPositions(2);

    service = new Service();
    service.setServiceId("ADJUSTMENT");
    service.setServiceName("Financial Adjustents");

    serviceClassifier = new ServiceClassifier();
    serviceClassifier.setServiceClassId("INV");
    serviceClassifier.setServiceClassName("Invoice Adjustment");
    serviceClassifier.setService(service);

    servicingAdminRule = new ServicingAdminRule();
    servicingAdminRule.setService(service);
    servicingAdminRule.setServiceClassifier(serviceClassifier);
    servicingAdminRule.setServiceTypeCode("AADDTXN");

    paymentDto = new ModelMapper().map(payment, PaymentDTO.class);
    adhocChargeDTO = new ModelMapper().map(adhocChg, AdhocChargeDTO.class);
    adjustmentDTO = new ModelMapper().map(adjustment, FinancialAdjustmentDTO.class);
    billCorrectionDTO = new ModelMapper().map(billCorrection, BillCorrectionDTO.class);

    when(modelMapper.map(payment, PaymentDTO.class)).thenReturn(paymentDto);
    when(modelMapper.map(adhocChg, AdhocChargeDTO.class)).thenReturn(adhocChargeDTO);
    when(modelMapper.map(adjustment, FinancialAdjustmentDTO.class)).thenReturn(adjustmentDTO);
    when(modelMapper.map(billCorrection, BillCorrectionDTO.class)).thenReturn(billCorrectionDTO);

    when(modelMapper.map(paymentDto, Payment.class)).thenReturn(payment);
    when(modelMapper.map(adhocChargeDTO, AdhocCharge.class)).thenReturn(adhocChg);
    when(modelMapper.map(adjustmentDTO, FinancialAdjustment.class)).thenReturn(adjustment);
    when(modelMapper.map(billCorrectionDTO, BillCorrection.class)).thenReturn(billCorrection);

  }

  @Test
  public void testConstructorIsPrivate()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<EntityToDtoMapper> constructor = EntityToDtoMapper.class.getDeclaredConstructor();
    assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test
  public void testConvertPaymentToDTO() {
    PaymentDTO paymentDTOConv = EntityToDtoMapper.convertPaymentToDTO(modelMapper, payment);
    assertEquals("PaymentDTO is not the same as expected", paymentDTOConv.getPaymentId(),
        paymentDto.getPaymentId());
  }

  @Test
  public void testConvertPaymentToEntity() {
    Payment paymentConv = EntityToDtoMapper
        .convertPaymentToEntity(modelMapper, paymentDto, adjustment);
    assertEquals("Payment is not the same as expected", paymentConv.getPaymentId(),
        payment.getPaymentId());

  }

  @Test
  public void testConvertAdjustmentToDTO() {
    FinancialAdjustmentDTO adjustmentDTOConv = EntityToDtoMapper.convertToDTO(modelMapper, adjustment, FinancialAdjustmentDTO.class);
    assertEquals("AdjustmentDTO is not the same as expected", adjustmentDTOConv.getAdjustmentId(),
        adjustmentDTO.getAdjustmentId());
  }

  @Test
  public void testConvertAdjustmentToEntity() {
    FinancialAdjustment adjustmentConv = EntityToDtoMapper.convertToEntity(modelMapper, adjustmentDTO, FinancialAdjustment.class);
    assertEquals("Adjustment is not the same as expected", adjustmentConv.getAdjustmentId(),
        adjustment.getAdjustmentId());
  }

  @Test
  public void testConvertAdhocChargeToDTO() {
    AdhocChargeDTO adhocChargeDTOConv = EntityToDtoMapper.convertToDTO(modelMapper, adhocChg, AdhocChargeDTO.class);
    assertEquals("AdhocChargeDTO is not the same as expected",
        adhocChargeDTOConv.getAdhocChargeId(), adhocChargeDTO.getAdhocChargeId());
  }

  @Test
  public void testConvertBillCorrectionToEntity() {
    BillCorrection billCorrectionConv = EntityToDtoMapper.convertToEntity(modelMapper, billCorrectionDTO, BillCorrection.class);
    assertEquals("BillCorrection is not the same as expected", billCorrectionConv.getBillCorrId(),
        billCorrection.getBillCorrId());
  }

  @Test
  public void testConvertBillCorrectionToDTO() {
    BillCorrectionDTO billCorrectionDTOConv = EntityToDtoMapper.convertToDTO(modelMapper, billCorrection, BillCorrectionDTO.class);
    assertEquals("BillCorrectionDTO is not the same as expected",
        billCorrectionDTOConv.getBillCorrId(), billCorrectionDTO.getBillCorrId());
  }

  @Test
  public void testConvertAdhocChargeToEntity() {
    AdhocCharge adhocChargeConv = EntityToDtoMapper.convertToEntity(modelMapper, adhocChargeDTO, AdhocCharge.class);
    assertEquals("AdhocCharge is not the same as expected", adhocChargeConv.getAdhocChargeId(),
        adhocChg.getAdhocChargeId());
  }

  @Test
  public void testSerializeDate() {
    String expectedDate = new SimpleDateFormat(DATE_FORMAT).format(new Date()).toUpperCase();
    String returnedDate = EntityToDtoMapper.serializeDate(new Date());
    assertEquals("Date format is not the same", expectedDate, returnedDate);
  }


}
