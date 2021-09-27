package me.nalam.logparser.strategy;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import me.nalam.logparser.entities.Event;
import me.nalam.logparser.models.ServerLogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextProcessor implements LogProcessor {

  private final Logger log = LoggerFactory.getLogger(TextProcessor.class);
  private final HashMap<String, ServerLogEvent> hashMap = new HashMap<>();
  private final ArrayList<Event> events = new ArrayList<>();

  @Override
  public Object load(String absFilePath) throws Exception {
    try (BufferedReader reader = new BufferedReader(new FileReader(absFilePath))) {
      Gson gson = new Gson();
      reader
          .lines()
          .forEach(
              line -> {
                log.debug("Current line from the file is {} ", line);
                ServerLogEvent currentLog = gson.fromJson(line, ServerLogEvent.class);
                if (hashMap.containsKey(currentLog.getId())) {
                  ServerLogEvent previousLog = hashMap.get(currentLog.getId());
                  currentLog.setTimestamp(
                      Math.abs(currentLog.getTimestamp() - previousLog.getTimestamp()));
                  log.info("The event {} duration is {}ms", currentLog.getId(), currentLog.getTimestamp());
                  events.add(new Event(currentLog.getId(), currentLog.getTimestamp(), currentLog.getType(), currentLog.getHost(), currentLog.getTimestamp() > 4));
                }
                hashMap.put(currentLog.getId(), currentLog);
              });

    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    return events;
  }
}
