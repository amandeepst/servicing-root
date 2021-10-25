package com.worldpay.servicing.gateway.outbound.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldpay.servicing.gateway.outbound.WebServiceClientConfig;
import com.worldpay.servicing.gateway.outbound.model.PartyChildren;
import com.worldpay.servicing.model.dto.CallInfo;
import com.worldpay.servicing.model.dto.ormb.BalanceItem;
import com.worldpay.servicing.model.dto.ormb.OpenBill;
import com.worldpay.servicing.model.mapper.EntityToDtoMapper;

import org.springframework.http.HttpStatus;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestUtils {

  private static final Jaxb2Marshaller MARSHALLER = new WebServiceClientConfig().marshaller();

  public static <T> List<T> readObjects(
      ClassLoader classLoader, ObjectMapper jacksonMapper, String fileName) throws Exception {
    return jacksonMapper.readValue(
        new File(classLoader.getResource(fileName).getFile()), new TypeReference<List<T>>() {
        });
  }

  public static File loadFile(ClassLoader classLoader, String fileName) throws Exception {
    return new File(classLoader.getResource(fileName).getFile());
  }

  public static Jaxb2Marshaller loadMarshaller() {
    return MARSHALLER;
  }

  public static List<OpenBill> createOpenBills() {
    OpenBill openBill1 = new OpenBill();
    openBill1.setBillid("A100001");
    openBill1.setLineId("1");
    openBill1.setAccountDescription("Charging");
    openBill1.setAccountType("CHRG");
    openBill1.setAlternateBillId("100001");
    openBill1.setBankingEntryStatus("RELEASED");
    openBill1.setBillAmount(new BigDecimal("190.00"));
    openBill1.setBillDate(new Date());
    openBill1.setCurrency("GBP");
    openBill1.setDueDate(new Date());
    openBill1.setLatestBankingEntryEvent("100001");
    openBill1.setLegalCounterparty("PO1100000001");
    openBill1.setLegalCounterpartyDescription("Worldpay (UK) Limited");
    openBill1.setLineAmount(new BigDecimal("190.00"));
    openBill1.setPartyId("PO4000258437");
    openBill1.setUnpaidAmount(new BigDecimal("190.00"));

    OpenBill openBill2 = new OpenBill();
    openBill2.setBillid("A100002");
    openBill2.setLineId("2");
    openBill2.setAccountDescription("Charging");
    openBill2.setAccountType("CHRG");
    openBill2.setAlternateBillId("100002");
    openBill2.setBankingEntryStatus("RELEASED");
    openBill2.setBillAmount(new BigDecimal("100.00"));
    openBill2.setBillDate(new Date());
    openBill2.setCurrency("GBP");
    openBill2.setDueDate(new Date());
    openBill2.setLatestBankingEntryEvent("100002");
    openBill2.setLegalCounterparty("PO1100000001");
    openBill2.setLegalCounterpartyDescription("Worldpay (UK) Limited");
    openBill2.setLineAmount(new BigDecimal("100.00"));
    openBill2.setPartyId("PO4000258437");
    openBill2.setUnpaidAmount(new BigDecimal("100.00"));
    return Arrays.asList(openBill1, openBill2);
  }

  public static BalanceItem createBalanceItem(String partyId, String legalCounterparty,
      String legalCounterpartyShortCode, String legalCounterpartyDescription, String currency,
      String accountType, String accountBalance, String subAccountType,
      String subAccountDescription, String subAccountBalance, String uploadDate) {
    BalanceItem balanceItem = new BalanceItem();
    balanceItem.setPartyId(partyId);
    balanceItem.setLegalCounterparty(legalCounterparty);
    balanceItem.setLegalCounterpartyShortCode(legalCounterpartyShortCode);
    balanceItem.setLegalCounterpartyDescription(legalCounterpartyDescription);
    balanceItem.setCurrency(currency);
    balanceItem.setAccountType(accountType);
    balanceItem.setAccountBalance(accountBalance);
    balanceItem.setSubAccountType(subAccountType);
    balanceItem.setSubAccountDescription(subAccountDescription);
    balanceItem.setSubAccountBalance(subAccountBalance);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    balanceItem.setUploadDate(Date.from(LocalDateTime.parse(uploadDate, formatter).toInstant(
        ZoneOffset.UTC)));
    return balanceItem;
  }

  public static PartyChildren createPartyChildren() {
    PartyChildren partyChildren = new PartyChildren();
    partyChildren.setPartyId("PO4000258437");
    partyChildren.setChildren(Arrays.asList("PO4000258438", "PO4000258439"));
    return partyChildren;
  }

  public static CallInfo createCallInfo(String caseId) {
    CallInfo callInfo = new CallInfo();
    callInfo.setCallId(caseId);
    callInfo.setCallStatus(Integer.toString(HttpStatus.OK.value()));
    callInfo.setCallType("GET");
    callInfo.setCallDescription("GET with Body");
    callInfo.setCreationDate(EntityToDtoMapper.serializeDate(new Date()));
    return callInfo;
  }
}
