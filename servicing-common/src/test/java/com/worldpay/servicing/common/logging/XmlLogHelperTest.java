package com.worldpay.servicing.common.logging;

import static junit.framework.TestCase.assertEquals;

import com.worldpay.servicing.common.logging.model.Point;
import org.junit.Test;

public class XmlLogHelperTest {

  @Test
  public void whenNamespaceOrInputAreNullExceptionIsThrown() {
    Object object = new Object();
    assertEquals("Namespace null and object " + object.toString() + " should not be null",
        XmlLogHelper.marshallObjectIntoString(null, object));
    assertEquals("Namespace test and object null should not be null", XmlLogHelper.marshallObjectIntoString("test", null));
    assertEquals("Namespace null and object null should not be null", XmlLogHelper.marshallObjectIntoString(null, null));
  }

  @Test
  public void whenExceptionIsThrownThenGenerealMessageIsReturned() {
    assertEquals("Should be Could not marshall into a string", "Could not marshall into a string",
        XmlLogHelper.marshallObjectIntoString("test", new Object()));
  }

  @Test
  public void whenNamespaceisWrongThenGenerealMessageIsReturned() {
    assertEquals("Should be Could not marshall into a string", "Could not marshall into a string",
        XmlLogHelper.marshallObjectIntoString("com.worldpay.servicing.common.logging", createPoint(2,2)));
  }

  @Test
  public void whenNamespaceAndObjectAreOkThanCorrectMessageIsReturned() {
    Point point = createPoint(1, 3);

    String expectedMessage = "<point><x>1</x><y>3</y></point>";

    assertEquals("Should be equal", expectedMessage,
        XmlLogHelper.marshallObjectIntoString("com.worldpay.servicing.common.logging.model", point));
  }

  private Point createPoint(int x, int y){
    Point point = new Point();
    point.setX(1);
    point.setY(3);

    return point;
  }
}
