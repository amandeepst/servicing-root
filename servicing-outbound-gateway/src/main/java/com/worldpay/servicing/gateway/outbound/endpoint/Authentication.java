package com.worldpay.servicing.gateway.outbound.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = Authentication.AUTH_NS)
public class Authentication {

  public static final String AUTH_NS = "http://siebel.com/webservices";

  @XmlElement(namespace = AUTH_NS, name = "UsernameToken")
  private String username;

  @XmlElement(namespace = AUTH_NS, name = "PasswordText")
  private String password;

  @XmlElement(namespace = AUTH_NS, name = "SessionType")
  private String sessionType;

  public Authentication() {
  }

  public Authentication(String username, String password, String sessionType) {
    this.username = username;
    this.password = password;
    this.sessionType = sessionType;
  }

  public Authentication(String username, String password) {
    this(username, password, "None");
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSessionType() {
    return sessionType;
  }

  public void setSessionType(String sessionType) {
    this.sessionType = sessionType;
  }
}
