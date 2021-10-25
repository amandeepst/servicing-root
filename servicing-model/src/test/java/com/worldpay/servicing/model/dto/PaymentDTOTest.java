package com.worldpay.servicing.model.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.worldpay.servicing.model.entity.Payment;
import com.worldpay.servicing.model.mapper.EntityToDtoMapper;
import com.worldpay.servicing.testutils.DefaultEntitiesMock;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RunWith(MockitoJUnitRunner.class)
public class PaymentDTOTest {

  private static final String MUST_NOT_BE_NULL = "must not be NULL";

  private static final String MUST_MATCH_REGEXP = "must be Y or N";

  private static final String PARENT_PAYMENT_ID = "123";

  private static final String PAYMENT_ID = "456";

  private static final String VALUE_TO_LONG = "12345678901234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890";

  private static final String THE_FIELD_MUST_BE_LESS_THAN = "field must be less than or equals";

  private static final String CHARACTERS = "characters";

  private static final String VALUE_OUT_OF_BOUNDS_20_DIGITS_0_DIGITS_EXPECTED = "must be an integer with maximum 20 digits";

  private static final String NOT_A_SINGLE_VIOLATION_MESSAGE = "Is not a single violation message";

  private static final String MESSAGE_IS_NOT_THE_ONE_EXPECTED = "The violation message is not the one expected";

  private static final String NOT_THE_PROPERLY_COLUMN_NAME = "Is not the properly column name";

  private static final String NO_CONSTRAINT_VIOLATION = "There is no constraint violation";

  private static ValidatorFactory validatorFactory;

  private static Validator validator;

  @BeforeClass
  public static void createValidator() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void close() {
    validatorFactory.close();
  }

  @Test
  public void testPaymentStatusIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaymentStatus(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "paymentStatus");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testPaymentClassIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaymentClass(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "paymentClass");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testTotalPaymentAmountIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setTotalPaymentAmount(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "totalPaymentAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testPartyIdentifierIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPartyIdentifier(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "partyIdentifier");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testPaymentChannelIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaymentChannel(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "paymentChannel");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testPaidAmountIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaidAmount(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "paidAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCurrencytIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setCurrency(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "currency");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testLineIdIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setLineId(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "lineId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAccountTypeIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setAccountType(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "accountType");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testBillIdIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setBillId(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "billId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAlternateBillIdIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setAlternateBillId(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "alternateBillId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testLegalCounterpartyIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setLegalCounterparty(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "legalCounterparty");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCaseIdentifierIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setCaseIdentifier(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "caseIdentifier");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCreatedByIsNull() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setCreatedBy(null);
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_NOT_BE_NULL,
        "createdBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testInvalidIlmArchiveFlag() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setIlmArchiveFlag("z");
    Boolean isConstraintViolation = validateConstraintViolation(paymentDTO, MUST_MATCH_REGEXP,
        "ilmArchiveFlag");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void shouldHaveNoViolations() {
    PaymentDTO paymentDTO = createDefaultPayment();
    Set<ConstraintViolation<PaymentDTO>> violations = validator.validate(paymentDTO);
    assertTrue("Violations list is empty", violations.isEmpty());
  }

  @Test
  public void setPaymentIdToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaymentId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "paymentId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setRejectionReasonToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setRejectionReason(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS, "rejectionReason");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setPaymentClassToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaymentClass(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS, "paymentClass");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setPaymentReferenceToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaymentReference(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 36 " + CHARACTERS, "paymentReference");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setPartyIdentifierToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPartyIdentifier(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "partyIdentifier");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setPaymentChannelToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaymentChannel(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS, "paymentChannel");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCurrencyToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setCurrency(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS, "currency");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setLatestBankingEntryEventToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setLatestBankingEntryEvent(new BigDecimal(VALUE_TO_LONG));
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, VALUE_OUT_OF_BOUNDS_20_DIGITS_0_DIGITS_EXPECTED, "latestBankingEntryEvent");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setLineIdToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setLineId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "lineId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAccountTypeToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setAccountType(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 4 " + CHARACTERS, "accountType");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setBillIdToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setBillId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 64 " + CHARACTERS, "billId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAlternateBillIdToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setAlternateBillId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS, "alternateBillId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setLegalCounterpartyToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setLegalCounterparty(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "legalCounterparty");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setBankingEntryStatusToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setBankingEntryStatus(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 15 " + CHARACTERS, "bankingEntryStatus");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setBankAcctRefToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setBankAcctRef(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 10 " + CHARACTERS, "bankAcctRef");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCaseIdentifierToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setCaseIdentifier(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "caseIdentifier");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCreatedByToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setCreatedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "createdBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setApprovedByToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setApprovedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "approvedBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAdjustmentIdToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setAdjustmentId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "adjustmentId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }


  @Test
  public void setParentPaymentIdToLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setParentPaymentId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "parentPaymentId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setLineAmountTooLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setLineAmount(new BigDecimal(99999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, "Settlement Line Value must have at most 11 digits and 2 decimals", "lineAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setUnpaidAmountTooLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setUnpaidAmount(new BigDecimal(99999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, "Unpaid Amount must have at most 11 digits and 2 decimals", "unpaidAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setPaidAmountTooLong() {
    PaymentDTO paymentDTO = createDefaultPayment();
    paymentDTO.setPaidAmount(new BigDecimal(99999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(
        paymentDTO, "Paid Amount must have at most 11 digits and 2 decimals", "paidAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  private Boolean validateConstraintViolation(
      PaymentDTO paymentDTO, String violationExpectedMsg, String columnName) {
    Set<ConstraintViolation<PaymentDTO>> violations = validator.validate(paymentDTO);
    assertEquals(NOT_A_SINGLE_VIOLATION_MESSAGE, violations.size(), 1);
    ConstraintViolation<PaymentDTO> violation = violations.iterator().next();
    assertTrue(MESSAGE_IS_NOT_THE_ONE_EXPECTED, violation.getMessage().contains(violationExpectedMsg));
    assertEquals(NOT_THE_PROPERLY_COLUMN_NAME, columnName, violation.getPropertyPath().toString());

    return Boolean.TRUE;
  }

  private PaymentDTO createDefaultPayment() {
    Payment payment = DefaultEntitiesMock.populatePayment(PARENT_PAYMENT_ID, null);
    PaymentDTO paymentDTO = EntityToDtoMapper.convertPaymentToDTO(new ModelMapper(), payment);
    paymentDTO.setPaymentId(PAYMENT_ID);
    return paymentDTO;
  }
}
