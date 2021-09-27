package me.nalam.logparser.strategy;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogProcessorStrategy {
  private final Logger log = LoggerFactory.getLogger(LogProcessorStrategy.class);
  private final HashMap<String, LogProcessor> processors = new HashMap<>();

  public void addProcessor(LogProcessor processor, String filePath) {
    processors.put(filePath, processor);
  }

  @SuppressWarnings("unchecked")
  public <T> T load(String filePath) {
    LogProcessor processor = getValidProcessor(filePath);
    try {
      return (T) processor.load(filePath);
    } catch (ClassCastException e) {
      log.error("Invalid processor {}", filePath);
      throw new RuntimeException(e.getMessage(), e);
    } catch (Exception e) {
      log.error("Failed to load {} ", filePath);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private int containsExtension(String filePath) {
    final int lastIndexOf = filePath.lastIndexOf('.');
    int INVALID_EXTENSION = -1;
    if (lastIndexOf == INVALID_EXTENSION) {
      log.error("Invalid file extension {} ", filePath);
      throw new RuntimeException("File does not contain extension delimiter");
    }
    log.debug("Contains valid file extension {} ", filePath);
    return lastIndexOf;
  }

  private LogProcessor getValidProcessor(String filePath) {
    int indexContainingExtensionDelimiter = containsExtension(filePath);
    String extension = filePath.substring(indexContainingExtensionDelimiter + 1);
    LogProcessor processor = processors.get(extension);
    if (processor == null) {
      log.error("No processor was found for the extension .{} files", extension);
      throw new RuntimeException("No processor was found for the extension ." + extension);
    }
    log.debug("Using {} to process the logs", processor.getClass().getSimpleName());
    return processor;
  }
}
