package com.worldpay.servicing.model.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.worldpay.servicing.model.entity.BillCorrection;
import com.worldpay.servicing.model.entity.enums.BillCorrectionType;
import com.worldpay.servicing.model.entity.enums.BooleanFlag;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;
import com.worldpay.servicing.model.mapper.EntityToDtoMapper;
import com.worldpay.servicing.model.util.GenericBuilder;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RunWith(MockitoJUnitRunner.class)
public class BillCorrectionDTOTest {

  private static final String MUST_NOT_BE_NULL = "must not be NULL";

  private static final String MUST_MATCH_REGEXP = "must be Y or N";

  private static final String NO_CONSTRAINT_VIOLATION = "There is no constraint violation";

  private static final String CHARACTERS = "characters";

  private static final String NOT_A_SINGLE_VIOLATION_MESSAGE = "Is not a single violation message";

  private static final String MESSAGE_IS_NOT_THE_ONE_EXPECTED = "The violation message is not the one expected";

  private static final String NOT_THE_PROPERLY_COLUMN_NAME = "Is not the properly column name";

  private static final String VALUE_TO_LONG = "12345678901234567890123456789012345678901234567890"
      + "1234567890123456789012345678901234567890";

  private static final String THE_FIELD_MUST_BE_LESS_THAN = "field must be less than or equals";

  private static ValidatorFactory validatorFactory;

  private static Validator validator;

  private BillCorrectionDTO billCorrectionDTO;

  @BeforeClass
  public static void createValidator() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void close() {
    validatorFactory.close();
  }

  @Before
  public void setup() {
    billCorrectionDTO = createDefaultBillCorrection();
  }

  @Test
  public void testStatusIsNull() {
    billCorrectionDTO.setStatus(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "status");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testPartyIdIsNull() {
    billCorrectionDTO.setPartyId(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "partyId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setRejectionReasonTooLong() {
    billCorrectionDTO.setRejectionReason(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        billCorrectionDTO, THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS, "rejectionReason");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCorrectionTypeIsNull() {
    billCorrectionDTO.setType(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL, "type");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testCurrencytIsNull() {
    billCorrectionDTO.setCurrency(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "currency");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testUnpaidAmountIdIsNull() {
    billCorrectionDTO.setUnpaidAmount(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "unpaidAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAccountTypeIsNull() {
    billCorrectionDTO.setAccountType(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "accountType");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testBillIdIsNull() {
    billCorrectionDTO.setBillId(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "billId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testAlternateBillIdIsNull() {
    billCorrectionDTO.setAltBillId(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "altBillId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testLineIdIsNull() {
    billCorrectionDTO.setLineId(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "lineId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void testLatestBankingEntryEventIsNull() {
    billCorrectionDTO.setLatestBankingEntryEvent(null);
    Boolean isConstraintViolation = validateConstraintViolation(billCorrectionDTO, MUST_NOT_BE_NULL,
        "latestBankingEntryEvent");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setCreatedByTooLong() {
    BillCorrectionDTO billCorrectionDTO = createDefaultBillCorrection();
    billCorrectionDTO.setCreatedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        billCorrectionDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "createdBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setApprovedByTooLong() {
    BillCorrectionDTO billCorrectionDTO = createDefaultBillCorrection();
    billCorrectionDTO.setApprovedBy(VALUE_TO_LONG);
    Boolean isConstraintViolation = validateConstraintViolation(
        billCorrectionDTO, THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, "approvedBy");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setLineIdTooLong() {
    BillCorrectionDTO billCorrectionDTO = createDefaultBillCorrection();
    billCorrectionDTO.setLineId("123456789012345678");
    Boolean isConstraintViolation = validateConstraintViolation(
        billCorrectionDTO, THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, "lineId");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setLineAmountTooLong() {
    BillCorrectionDTO billCorrectionDTO = createDefaultBillCorrection();
    billCorrectionDTO.setLineAmount(new BigDecimal(99999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(
        billCorrectionDTO, "Settlement Line Value must have at most 11 digits and 2 decimals", "lineAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  @Test
  public void setUnpaidAmountTooLong() {
    BillCorrectionDTO billCorrectionDTO = createDefaultBillCorrection();
    billCorrectionDTO.setUnpaidAmount(new BigDecimal(99999999999999.9));
    Boolean isConstraintViolation = validateConstraintViolation(
        billCorrectionDTO, "Unpaid Amount must have at most 11 digits and 2 decimals", "unpaidAmount");
    assertTrue(NO_CONSTRAINT_VIOLATION, isConstraintViolation);
  }

  private BillCorrectionDTO createDefaultBillCorrection() {
    Date today = new Date();
    BillCorrection billCorrection = GenericBuilder.of(BillCorrection::new)
        .with(BillCorrection::setBillCorrId, "billcorrId")
        .with(BillCorrection::setUnpaidAmount, BigDecimal.TEN)
        .with(BillCorrection::setUploadDate, today)
        .with(BillCorrection::setReason, "Some reason here")
        .with(BillCorrection::setApprovalDate, today)
        .with(BillCorrection::setLineAmount, BigDecimal.TEN)
        .with(BillCorrection::setApprovedBy, "User1")
        .with(BillCorrection::setCaseId, "CASE1")
        .with(BillCorrection::setCreatedBy, "User2")
        .with(BillCorrection::setType, BillCorrectionType.CANCEL)
        .with(BillCorrection::setCurrency, "GBP")
        .with(BillCorrection::setIlmArchiveFlag, "Y")
        .with(BillCorrection::setIlmDate, today)
        .with(BillCorrection::setPartyId, "PT1")
        .with(BillCorrection::setStatus, ServiceItemStatus.APPROVED)
        .with(BillCorrection::setReuseDueDate, BooleanFlag.N)
        .with(BillCorrection::setRejectionReason, "reason")
        .with(BillCorrection::setParentBillCorrId, "parBilCorId")
        .with(BillCorrection::setPaidInvoice, BooleanFlag.Y)
        .with(BillCorrection::setBillId, "12")
        .with(BillCorrection::setAltBillId, "34")
        .with(BillCorrection::setLineId, "LINEID")
        .with(BillCorrection::setLatestBankingEntryEvent, BigDecimal.TEN)
        .with(BillCorrection::setAccountType, "CHRG")
        .build();

    BillCorrectionDTO billCorrectionDTO = EntityToDtoMapper
        .convertToDTO(new ModelMapper(), billCorrection, BillCorrectionDTO.class);

    return billCorrectionDTO;
  }

  private Boolean validateConstraintViolation(
      BillCorrectionDTO billCorrectionDTO, String violationExpectedMsg, String columnName) {
    Set<ConstraintViolation<BillCorrectionDTO>> violations = validator.validate(billCorrectionDTO);
    assertEquals(NOT_A_SINGLE_VIOLATION_MESSAGE, violations.size(), 1);
    ConstraintViolation<BillCorrectionDTO> violation = violations.iterator().next();
    assertTrue(MESSAGE_IS_NOT_THE_ONE_EXPECTED, violation.getMessage().contains(violationExpectedMsg));
    assertEquals(NOT_THE_PROPERLY_COLUMN_NAME, columnName, violation.getPropertyPath().toString());

    return Boolean.TRUE;
  }

}
