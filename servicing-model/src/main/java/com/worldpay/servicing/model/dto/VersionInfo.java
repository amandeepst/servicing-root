package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionInfo {

  private String buildVersion;

  private String buildTime;

  @JsonProperty("buildVersion")
  public String getBuildVersion() {
    return buildVersion;
  }

  @JsonProperty("git.build.version")
  public void setBuildVersion(String buildVersion) {
    this.buildVersion = buildVersion;
  }

  @JsonProperty("buildTime")
  public String getBuildTime() {
    return buildTime;
  }

  @JsonProperty("git.build.time")
  public void setBuildTime(String buildTime) {
    this.buildTime = buildTime;
  }
}
