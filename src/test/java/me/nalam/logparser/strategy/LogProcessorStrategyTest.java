package me.nalam.logparser.strategy;

import static me.nalam.logparser.utils.TestUtil.getAbsPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Locale;
import me.nalam.logparser.constants.EventType;
import me.nalam.logparser.constants.Extension;
import me.nalam.logparser.entities.Event;
import org.junit.Before;
import org.junit.Test;

public class LogProcessorStrategyTest {
  LogProcessorStrategy logProcessorStrategy = new LogProcessorStrategy();

  @Before
  public void setUp() throws Exception {
    logProcessorStrategy.addProcessor(
        new TextProcessor(), Extension.TXT.name().toLowerCase(Locale.ENGLISH));
  }

  @Test
  public void testLoad() throws Exception {
    ArrayList<Event> logEvents = logProcessorStrategy.load(getAbsPath("logfile.txt"));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 3);
    Event expectedEvent = new Event("scsmbstgra", 4L, EventType.APPLICATION_LOG, "12345", false);
    Event event = logEvents.get(0);
    assertEquals(expectedEvent, event);
  }

  @Test(expected = RuntimeException.class)
  public void testLoadNoExtension() throws Exception {
    ArrayList<Event> logEvents = logProcessorStrategy.load(getAbsPath("logfiletxt"));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 0);
  }

  @Test(expected = RuntimeException.class)
  public void testLoadEmpty() throws Exception {
    ArrayList<Event> logEvents = logProcessorStrategy.load(getAbsPath(""));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 0);
  }

  @Test(expected = RuntimeException.class)
  public void testLoadNull() throws Exception {
    ArrayList<Event> logEvents = logProcessorStrategy.load(getAbsPath(null));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 0);
  }

  @Test(expected = RuntimeException.class)
  public void testLoadSpaces() throws Exception {
    ArrayList<Event> logEvents = logProcessorStrategy.load(getAbsPath("   "));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 0);
  }

  @Test(expected = RuntimeException.class)
  public void testLoadUnsupportedFileType() throws Exception {
    ArrayList<Event> logEvents = logProcessorStrategy.load(getAbsPath("logfile.json"));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 0);
  }
}
