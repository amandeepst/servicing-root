package com.worldpay.servicing.model.dto;

import static com.worldpay.servicing.model.mapper.EntityToDtoMapper.convertToDTO;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.worldpay.servicing.model.dto.AdhocChargeDTO.PciValidationAware;
import com.worldpay.servicing.model.dto.AdhocChargeDTO.SdsValidationAware;
import com.worldpay.servicing.model.entity.AdhocCharge;
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
public class AdhocChargeDTOTest {

  private static final String MUST_NOT_BE_NULL = "must not be NULL";

  private static final String MUST_MATCH_REGEXP = "must be Y or N";

  private static final String VALUE_TO_LONG = "123456789012345678901234567890"
      + "1234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890";

  private static final String THE_FIELD_MUST_BE_LESS_THAN = "field must be less than or equals";

  private static final String THE_FIELD_MUST_BE_GREATER_THAN = " field must be greater than or equals";

  private static final String THE_FIELD_MUST_BE_POSITIVE_DECIMAL = " field must be a positive number with maximum 2 decimals";

  private static final String CHARACTERS = "characters";

  private static final String NOT_A_SINGLE_VIOLATION_MESSAGE = "Is not a single violation message";

  private static final String MESSAGE_IS_NOT_THE_ONE_EXPECTED = "The violation message is not the one expected";

  private static final String NOT_THE_PROPERLY_COLUMN_NAME = "Is not the properly column name";

  private static final String NO_CONSTRAINT_VIOLATION = "There is no constraint violation";

  private static ValidatorFactory validatorFactory;

  private static Validator validator;

  private static Class[] defaultValidationGroups = {SdsValidationAware.class, PciValidationAware.class};

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
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setStatus(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "status", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testPartyIdIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setPartyId(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "partyId", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testLegalCounterpartyIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setLegalCounterparty(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "legalCounterparty", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testChargeTypeIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setChargeType(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "chargeType", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testQuantityIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setQuantity(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "quantity", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCaseIdentifierIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setCaseIdentifier(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "caseIdentifier", SdsValidationAware.class);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testQuantityIsZero() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setQuantity(ZERO);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, THE_FIELD_MUST_BE_GREATER_THAN + " 1",
        "quantity", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testQuantityIsNegative() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setQuantity(TEN.negate());
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, THE_FIELD_MUST_BE_GREATER_THAN + " 1",
        "quantity", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAmountIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setAmount(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "amount", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAmountIsZero() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setAmount(ZERO);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, THE_FIELD_MUST_BE_POSITIVE_DECIMAL,
        "amount", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAmountIsNegative() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setAmount(TEN.negate());
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, THE_FIELD_MUST_BE_POSITIVE_DECIMAL,
        "amount", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAmountIsBiggerThan11Digits() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setAmount(new BigDecimal(9999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, "Amount must have at most 11 digits and 2 decimals",
        "amount", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testImmediateBillFlagIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setImmediateBillFlag(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "immediateBillFlag", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCreatedByIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setCreatedBy(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "createdBy", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCurrencyIsNull() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setCurrency(null);
    Boolean isConstraintViolation = validateConstraintViolation(adhocChargeDTO, MUST_NOT_BE_NULL,
        "currency", PciValidationAware.class);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void shouldHaveNoViolations() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    Set<ConstraintViolation<AdhocChargeDTO>> violations = validator.validate(adhocChargeDTO);
    assertTrue("Violations list is empty", violations.isEmpty());
  }

  @Test
  public void setAdhocChargeId() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setAdhocChargeId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "adhocChargeId", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setRejectionReasonTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setRejectionReason(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS, "rejectionReason", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setPartyIdTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setPartyId(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "partyId", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setChargeTypeTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setChargeType(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 8 " + CHARACTERS, "chargeType", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setLegalCounterpartyTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setLegalCounterparty(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "legalCounterparty", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCurrencyTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setCurrency(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS, "currency", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setAdhocChargeReasonTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    String reason = "12345678901234567890123456789012345678901234567890A";
    adhocChargeDTO.setAdhocChargeReason(reason);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "adhocChargeReason", defaultValidationGroups);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCaseIdentifierTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setCaseIdentifier(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "caseIdentifier", SdsValidationAware.class);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCreatedByTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setCreatedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "createdBy", SdsValidationAware.class);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setApprovedByTooLong() {
    AdhocChargeDTO adhocChargeDTO = createDefaultAdhocCharge();
    adhocChargeDTO.setApprovedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        adhocChargeDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "approvedBy", SdsValidationAware.class);
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  private Boolean validateConstraintViolation(
      AdhocChargeDTO adhocChargeDTO, String violationExpectedMsg, String columnName, Class<?>... validationGroups) {
    Set<ConstraintViolation<AdhocChargeDTO>> violations = validator.validate(adhocChargeDTO, validationGroups);
    assertEquals(NOT_A_SINGLE_VIOLATION_MESSAGE, violations.size(), 1);
    ConstraintViolation<AdhocChargeDTO> violation = violations.iterator().next();
    assertTrue(MESSAGE_IS_NOT_THE_ONE_EXPECTED, violation.getMessage().contains(violationExpectedMsg));
    assertEquals(NOT_THE_PROPERLY_COLUMN_NAME, columnName, violation.getPropertyPath().toString());

    return Boolean.TRUE;
  }

  private AdhocChargeDTO createDefaultAdhocCharge() {
    AdhocCharge adhocCharge = DefaultEntitiesMock.populateAdhocCharge(null, null);
    AdhocChargeDTO adhocChargeDTO = convertToDTO(new ModelMapper(), adhocCharge, AdhocChargeDTO.class);
    adhocChargeDTO.setAdhocChargeId("456");
    return adhocChargeDTO;
  }
}
