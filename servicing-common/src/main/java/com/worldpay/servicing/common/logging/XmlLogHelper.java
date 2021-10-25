package com.worldpay.servicing.common.logging;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public interface XmlLogHelper {

  static String marshallObjectIntoString(String namespace, Object input){
    StringWriter outputW = new StringWriter();

    if (namespace == null || input == null){
      outputW.write(String.format("Namespace %s and object %s should not be null", namespace, input));
    } else {
      try {
        JAXBContext jc = JAXBContext.newInstance(namespace);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(input, outputW);
      } catch (JAXBException e) {
        outputW.write("Could not marshall into a string");
      }
    }

    return outputW.toString();
  }

}
