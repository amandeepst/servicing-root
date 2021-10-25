package com.worldpay.servicing.common.context;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UserInfoFilter implements Filter {

  private static final String LOGIN_HEADER_NAME = "Login-Name";

  private static final String POSITION_HEADER_NAME = "Position-Name";

  private static final String JWT_HEADER_NAME = "X-WP-Jwt";

  private static final String CORRELATION_ID_HEADER = "X-WP-Diagnostic-CorrelationId";

  @Autowired
  private UserInfoStore userInfoStore;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // Do nothing
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String positionName = request.getHeader(POSITION_HEADER_NAME);
    String loginName = request.getHeader(LOGIN_HEADER_NAME);
    String jwtName = request.getHeader(JWT_HEADER_NAME);
    String correlationId = request.getHeader(CORRELATION_ID_HEADER);
    try {
      this.userInfoStore.setPositionName(positionName);
      this.userInfoStore.setLoginName(loginName);
      this.userInfoStore.setJwtName(jwtName);
      this.userInfoStore.setCorrelationId(correlationId);
      chain.doFilter(servletRequest, servletResponse);
    } finally {
      this.userInfoStore.clear();
    }
  }

  @Override
  public void destroy() {
    // Do nothing
  }
}
