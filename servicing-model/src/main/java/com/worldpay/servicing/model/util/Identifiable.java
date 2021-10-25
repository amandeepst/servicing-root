package com.worldpay.servicing.model.util;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {

  /**
   * Returns the internal id of the entity
   *
   * @return the internal id
   */
  T getId();
}
