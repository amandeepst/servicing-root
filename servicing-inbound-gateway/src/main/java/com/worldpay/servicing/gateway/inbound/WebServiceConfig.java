package com.worldpay.servicing.gateway.inbound;

import com.worldpay.servicing.core.CoreConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;
import java.util.Properties;

@EnableWs
@Configuration
@ComponentScan({"com.worldpay.servicing.gateway.inbound.endpoint","com.worldpay.servicing.gateway.inbound.service"})
@Import(CoreConfiguration.class)
public class WebServiceConfig extends WsConfigurerAdapter {

  private static final String SERVICING_APPROVAL_SERVICE_LOGIN =
      "servicing.approvalService.password";

  private static final String SERVICING_APPROVAL_SERVICE_USER = "servicing.approvalService.user";

  @Autowired
  private Environment env;

  @Bean
  public SimplePasswordValidationCallbackHandler securityCallbackHandler() {
    SimplePasswordValidationCallbackHandler callbackHandler;
    callbackHandler = new SimplePasswordValidationCallbackHandler();
    Properties users = new Properties();
    users.setProperty(env.getProperty(SERVICING_APPROVAL_SERVICE_USER),
        env.getProperty(SERVICING_APPROVAL_SERVICE_LOGIN));
    callbackHandler.setUsers(users);

    return callbackHandler;
  }

  @Bean
  public Wss4jSecurityInterceptor securityInterceptor() {
    Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
    securityInterceptor.setValidationActions("UsernameToken");
    securityInterceptor.setSkipValidationIfNoHeaderPresent(false);
    securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());

    return securityInterceptor;
  }

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    interceptors.add(securityInterceptor());
  }

  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/ws/*");
  }

  @Bean(name = "servicingItems")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema approveServicingItemsSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("ApproveServicingItemsPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition
        .setTargetNamespace("http://model.inbound.gateway.servicing.worldpay.com/approvews");
    wsdl11Definition.setSchema(approveServicingItemsSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema approveServicingItemsSchema() {
    return new SimpleXsdSchema(new ClassPathResource("ws/approve-item-request.xsd"));
  }
}
