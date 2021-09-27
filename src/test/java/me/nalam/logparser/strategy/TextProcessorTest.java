package me.nalam.logparser.strategy;

import static me.nalam.logparser.utils.TestUtil.getAbsPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import me.nalam.logparser.constants.EventType;
import me.nalam.logparser.entities.Event;
import org.junit.Before;
import org.junit.Test;

public class TextProcessorTest {
  private LogProcessor processor;

  @Before
  public void setUp() throws Exception {
    processor = new TextProcessor();
  }

  @Test
  public void testLoad() throws Exception {
    ArrayList<Event> logEvents = (ArrayList<Event>) processor.load(getAbsPath("logfile.txt"));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 3);
    Event expectedEvent = new Event("scsmbstgrc", 8L, null, "null", true);
    Event event = logEvents.get(1);
    assertEquals(expectedEvent, event);
  }

  @Test
  public void testLoadApplicationLog() throws Exception {
    ArrayList<Event> logEvents = (ArrayList<Event>) processor.load(getAbsPath("logfile.txt"));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 3);
    Event expectedEvent = new Event("scsmbstgra", 4L, EventType.APPLICATION_LOG, "12345", false);
    Event event = logEvents.get(0);
    assertEquals(expectedEvent, event);
  }

  @Test(expected = JsonSyntaxException.class)
  public void testLoadInvalidJson() throws Exception {
    processor.load(getAbsPath("logfilebroken.txt"));
  }

  @Test(expected = JsonSyntaxException.class)
  public void testLoadTwoLogEventsInOneLine() throws Exception {
    processor.load(getAbsPath("logfiletwologs.txt"));
  }

  @Test()
  public void testLoadEmptyFile() throws Exception {
    ArrayList<Event> logEvents = (ArrayList<Event>) processor.load(getAbsPath("logfileempty.txt"));
    assertNotNull(logEvents);
    assertEquals(logEvents.size(), 0);
  }
}
