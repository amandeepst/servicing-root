package com.worldpay.servicing.model.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class BillIdSerializer extends StdSerializer<String> {

  protected BillIdSerializer() {
    super(String.class);
  }

  @Override
  public void serialize(String value, JsonGenerator generator, SerializerProvider provider)
      throws IOException {
    generator.writeString(value.replaceAll("<","&lt;"));
  }

}
