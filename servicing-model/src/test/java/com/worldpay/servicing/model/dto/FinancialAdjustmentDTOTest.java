package com.worldpay.servicing.model.dto;

import static com.worldpay.servicing.model.mapper.EntityToDtoMapper.convertToDTO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.worldpay.servicing.model.entity.FinancialAdjustment;
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
public class FinancialAdjustmentDTOTest {

  private static final String MUST_NOT_BE_NULL = "must not be NULL";

  private static final String MUST_MATCH_REGEXP = "must be Y or N";

  private static final String ADJUSTMENT_ID = "123";

  private static final String VALUE_TO_LONG = "12345678901234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890";

  private static final String THE_FIELD_MUST_BE_LESS_THAN = "field must be less than or equals";

  private static final String CHARACTERS = "characters";

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
  public void testStatusIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setStatus(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "status");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAdjustmentClassIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentClass(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "adjustmentClass");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAdjustmentCodeIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentCode(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "adjustmentCode");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAdjustmentTypeIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentType(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "adjustmentType");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testImmediateBillFlagIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setImmediateBillFlag(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "immediateBillFlag");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testFastestRouteFlagIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setFastestRouteFlag(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "fastestRouteFlag");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCaseIdentifierIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setCaseIdentifier(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "caseIdentifier");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCreatedByIsNull() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setCreatedBy(null);
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_NOT_BE_NULL,
        "createdBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testInvalidImmediateBillFlag() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setImmediateBillFlag("z");
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_MATCH_REGEXP,
        "immediateBillFlag");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testInvalidFastestRouteFlag() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setFastestRouteFlag("z");
    Boolean isConstraintViolation = validateConstraintViolation(adjustmentDTO, MUST_MATCH_REGEXP,
        "fastestRouteFlag");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void shouldHaveNoViolations() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    Set<ConstraintViolation<FinancialAdjustmentDTO>> violations = validator.validate(adjustmentDTO);
    assertTrue("Violations list is empty", violations.isEmpty());
  }

  @Test
  public void setAdjustmentIdTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "adjustmentId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setRejectionReasonTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setRejectionReason(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS, "rejectionReason");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAdjustmentClassTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentClass(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS, "adjustmentClass");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAdjustmentCodeTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentCode(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 8 " + CHARACTERS, "adjustmentCode");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAdjustmentTypeTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentType(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 8 " + CHARACTERS, "adjustmentType");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAdjustmentReasonTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentReason(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 15 " + CHARACTERS, "adjustmentReason");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAdjustmentCauseTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setAdjustmentCause(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 15 " + CHARACTERS, "adjustmentCause");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setFromPartyIdTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setFromPartyId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "fromPartyId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setFromCurrencyTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setFromCurrency(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS, "fromCurrency");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setFromLegalCounterpartyTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setFromLegalCounterparty(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "fromLegalCounterparty");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setToPartyIdTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setToPartyId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "toPartyId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setToCurrencyTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setToCurrency(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS, "toCurrency");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setToLegalCounterpartyTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setToLegalCounterparty(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "toLegalCounterparty");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setPaymentNarrativeTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setPaymentNarrative(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 18 " + CHARACTERS, "paymentNarrative");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCaseIdentifierTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setCaseIdentifier(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "caseIdentifier");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCreatedByTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setCreatedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "createdBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setApprovedByTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setApprovedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "approvedBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setFromAmountByTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setFromAmount(new BigDecimal(9999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, "From Amount must have at most 11 digits and 2 decimals", "fromAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setToAmountByTooLong() {
    FinancialAdjustmentDTO adjustmentDTO = createDefaultFinantialAdjustment();
    adjustmentDTO.setToAmount(new BigDecimal(9999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(
        adjustmentDTO, "To Amount must have at most 11 digits and 2 decimals", "toAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  private FinancialAdjustmentDTO createDefaultFinantialAdjustment() {
    FinancialAdjustment adjustment = DefaultEntitiesMock.populateAdjustment();
    FinancialAdjustmentDTO adjustmentDTO = convertToDTO(new ModelMapper(), adjustment, FinancialAdjustmentDTO.class);
    adjustmentDTO.setAdjustmentId(ADJUSTMENT_ID);
    return adjustmentDTO;
  }

  private Boolean validateConstraintViolation(
      FinancialAdjustmentDTO adjustmentDTO, String violationExpectedMsg, String columnName) {
    Set<ConstraintViolation<FinancialAdjustmentDTO>> violations = validator.validate(adjustmentDTO);
    assertEquals(NOT_A_SINGLE_VIOLATION_MESSAGE, violations.size(), 1);
    ConstraintViolation<FinancialAdjustmentDTO> violation = violations.iterator().next();
    assertTrue(MESSAGE_IS_NOT_THE_ONE_EXPECTED, violation.getMessage().contains(violationExpectedMsg));
    assertEquals(NOT_THE_PROPERLY_COLUMN_NAME, columnName, violation.getPropertyPath().toString());

    return Boolean.TRUE;
  }
}
