package me.nalam.logparser.models;

import java.util.Objects;
import me.nalam.logparser.constants.EventState;

public class LogEvent {
  private String id;
  private EventState state;
  private Long timestamp;

  public LogEvent() {}

  public LogEvent(String id, EventState state, Long timestamp) {
    this.id = id;
    this.state = state;
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EventState getState() {
    return state;
  }

  public void setState(EventState state) {
    this.state = state;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogEvent logEvent = (LogEvent) o;
    return id.equals(logEvent.id)
        && state == logEvent.state
        && Objects.equals(timestamp, logEvent.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, state, timestamp);
  }

  @Override
  public String toString() {
    return "Event{"
        + "id='"
        + id
        + '\''
        + ", state="
        + state
        + ", timestamp="
        + timestamp
        + '}';
  }
}
