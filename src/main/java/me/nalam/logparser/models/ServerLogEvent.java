package me.nalam.logparser.models;

import java.util.Objects;
import me.nalam.logparser.constants.EventType;

public class ServerLogEvent extends LogEvent {
  private EventType type;
  private String host;

  public ServerLogEvent() {
  }

  public ServerLogEvent(EventType type, String host) {
    this.type = type;
    this.host = host;
  }

  public EventType getType() {
    return type;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ServerLogEvent that = (ServerLogEvent) o;
    return type == that.type && host.equals(that.host);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), type, host);
  }

  @Override
  public String toString() {
    return "ServerEvent{" +
        "type=" + type +
        ", host='" + host + '\'' +
        '}';
  }
}
