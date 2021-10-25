package com.worldpay.servicing.gateway.outbound.endpoint;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

public class HeaderHandler implements WebServiceMessageCallback {

  private Authentication authentication;

  private String soapAction;

  public HeaderHandler(Authentication authentication, String soapAction) {
    this.authentication = authentication;
    this.soapAction = soapAction;
  }

  @Override
  public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
    // set the SOAP action
    ((SoapMessage) message).setSoapAction(soapAction);

    // set the AUTH headers
    SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();

    try {
      JAXBContext context = JAXBContext.newInstance(Authentication.class);

      Marshaller marshaller = context.createMarshaller();
      marshaller.marshal(authentication, soapHeader.getResult());

    } catch (JAXBException e) {
      throw new IOException("Error while marshalling authentication.", e);
    }
  }
}
