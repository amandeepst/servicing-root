package com.worldpay.servicing.common;

import com.worldpay.servicing.common.context.UserInfoFilter;
import com.worldpay.servicing.common.context.UserInfoStore;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Collections;

import javax.servlet.Filter;

@Configuration
@ComponentScan({"com.worldpay.servicing.common"})
public class CommonConfig {

  @Bean
  public Filter userInfoFilter() {
    return new UserInfoFilter();
  }

  @Bean
  public FilterRegistrationBean userInfoFilterRegistration() {
    FilterRegistrationBean result = new FilterRegistrationBean();
    result.setFilter(this.userInfoFilter());
    result.setUrlPatterns(Collections.singletonList("/*"));
    result.setName("User Info Store Filter");
    result.setOrder(1);
    return result;
  }

  @Bean(destroyMethod = "destroy")
  public ThreadLocalTargetSource threadLocalUserInfoStore() {
    ThreadLocalTargetSource result = new ThreadLocalTargetSource();
    result.setTargetBeanName("userInfoStore");
    return result;
  }

  @Primary
  @Bean(name = "proxiedThreadLocalTargetSource")
  public ProxyFactoryBean proxiedThreadLocalTargetSource(
      ThreadLocalTargetSource threadLocalTargetSource) {
    ProxyFactoryBean result = new ProxyFactoryBean();
    result.setTargetSource(threadLocalTargetSource);
    return result;
  }

  @Bean(name = "userInfoStore")
  @Scope(scopeName = "prototype")
  public UserInfoStore userInfoStore() {
    return new UserInfoStore();
  }
}
