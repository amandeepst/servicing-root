package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailedVersionInfo extends VersionInfo {

  private String buildHost;

  private String buildUser;

  private String lastCommitId;

  private String lastCommitMessage;

  private String lastCommitTime;

  private Integer totalCommits;

  @JsonProperty("buildHost")
  public String getBuildHost() {
    return buildHost;
  }

  @JsonProperty("git.build.host")
  public void setBuildHost(String buildHost) {
    this.buildHost = buildHost;
  }

  @JsonProperty("buildUser")
  public String getBuildUser() {
    return buildUser;
  }

  @JsonProperty("git.build.user.name")
  public void setBuildUser(String buildUser) {
    this.buildUser = buildUser;
  }

  @JsonProperty("lastCommitId")
  public String getLastCommitId() {
    return lastCommitId;
  }

  @JsonProperty("git.commit.id")
  public void setLastCommitId(String lastCommitId) {
    this.lastCommitId = lastCommitId;
  }

  @JsonProperty("lastCommitMessage")
  public String getLastCommitMessage() {
    return lastCommitMessage;
  }

  @JsonProperty("git.commit.message.short")
  public void setLastCommitMessage(String lastCommitMessage) {
    this.lastCommitMessage = lastCommitMessage;
  }

  @JsonProperty("lastCommitTime")
  public String getLastCommitTime() {
    return lastCommitTime;
  }

  @JsonProperty("git.commit.time")
  public void setLastCommitTime(String lastCommitTime) {
    this.lastCommitTime = lastCommitTime;
  }

  @JsonProperty("totalCommits")
  public Integer getTotalCommits() {
    return totalCommits;
  }

  @JsonProperty("git.total.commit.count")
  public void setTotalCommits(Integer totalCommits) {
    this.totalCommits = totalCommits;
  }
}
