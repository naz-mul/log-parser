package me.nalam.logparser;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import me.nalam.logparser.constants.Extension;
import me.nalam.logparser.dao.Dao;
import me.nalam.logparser.dao.EventDao;
import me.nalam.logparser.entities.Event;
import me.nalam.logparser.strategy.LogProcessorStrategy;
import me.nalam.logparser.strategy.TextProcessor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogParser {
  private static final Logger log = LoggerFactory.getLogger(CustomLogParser.class);

  private static final Dao<Event> eventDao = new EventDao();

  public static void main(String... args) {
    try {
      log.info("Application is starting up with file {}", args);
      LogProcessorStrategy logProcessorStrategy = new LogProcessorStrategy();
      logProcessorStrategy.addProcessor(
          new TextProcessor(), Extension.TXT.name().toLowerCase(Locale.ENGLISH));

      while (true) {
        ArrayList<Event> logEvents = logProcessorStrategy.load(getAbsFilePath());
        if (!logEvents.isEmpty()) {
          for (Event event : logEvents) {
            saveEvent(event);
            log.debug("Successfully saved event with id as {}", event.getId());
          }
        }
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  private static String getAbsFilePath() {
    Scanner scanner = new Scanner(System.in);
    boolean keepAskingForInput = true;
    String absFilePath = "";
    while (keepAskingForInput) {
      System.out.println("Please input the absolute path of the logfile: ");
      absFilePath = scanner.nextLine();
      if (isValidPath(absFilePath)) keepAskingForInput = false;
    }
    return StringUtils.isNotEmpty(absFilePath) ? absFilePath.trim() : "";
  }

  private static boolean isValidPath(String path) {
    return StringUtils.isNotEmpty(path);
  }

  private static void saveEvent(Event event) {
    eventDao.save(event);
  }
}
