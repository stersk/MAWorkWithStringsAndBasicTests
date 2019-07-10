package com.mainacad.model;

public class ConnectionInfo implements Comparable<ConnectionInfo>{
  private User user;
  private Integer sessionId;
  private String userIp;
  private Long time;

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public ConnectionInfo(User user, Integer sessionId, String userIp, Long time) {
    this.user = user;
    this.sessionId = sessionId;
    this.userIp = userIp;
    this.time = time;
  }

  public ConnectionInfo() {
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getSessionId() {
    return sessionId;
  }

  public void setSessionId(Integer sessionId) {
    this.sessionId = sessionId;
  }

  public String getUserIp() {
    return userIp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ConnectionInfo that = (ConnectionInfo) o;

    if (user != null ? !user.equals(that.user) : that.user != null) return false;
    if (sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null) return false;
    if (userIp != null ? !userIp.equals(that.userIp) : that.userIp != null) return false;
    return time != null ? time.equals(that.time) : that.time == null;
  }

  @Override
  public int hashCode() {
    int result = user != null ? user.hashCode() : 0;
    result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
    result = 31 * result + (userIp != null ? userIp.hashCode() : 0);
    result = 31 * result + (time != null ? time.hashCode() : 0);
    return result;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }

  @Override
  public int compareTo(ConnectionInfo connectionInfo) {
    if (this.time > connectionInfo.time) {
      return 1;
    } else if (this.time < connectionInfo.time) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return time + " " + sessionId + " " + userIp + " " + user.getLogin() + " " +
            user.getPassword() + " " + user.getId();
  }
}
