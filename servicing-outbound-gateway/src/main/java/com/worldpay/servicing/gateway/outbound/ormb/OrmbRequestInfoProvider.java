package com.worldpay.servicing.gateway.outbound.ormb;

import java.util.Map;

public interface OrmbRequestInfoProvider<T> {

  Map<OrmbRequestParameter, String> getRequestParams(T entity);

  String getSpecificPath();

}
