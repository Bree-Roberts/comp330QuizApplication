package server;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOutput {
  private final String fileName = "Test.json";

  @Test
  public void testGetQuestion() throws IOException, ParseException {
    Output o = new Output(fileName);
    Map.Entry test1 = o.getQuestion(QTypes.TRUE_FALSE);
    Map.Entry test2 = o.getQuestion(QTypes.MATCHING);
    Map.Entry test3 = o.getQuestion(QTypes.MULTIPLE_CHOICE);
    // Map.Entry test4 = o.getQuestion(QTypes.RANDOM);
    assertNotNull(test1);
    assertNotNull(test2);
    assertNotNull(test3);
    // assertNotNull(test4);
  }

  @Test
  public void testGetRequestedQuestion() throws IOException, ParseException {
    Output o = new Output(fileName);
    // Test 1
    Map.Entry<String, Map.Entry<String, String>> entry1 = o.getQuestion(QTypes.TRUE_FALSE);
    assertEquals("trueFalse", entry1.getKey());
    assertEquals("this is true", entry1.getValue().getKey());
    assertEquals("true", entry1.getValue().getValue());

    // Test 2
    Map.Entry<String, Map.Entry<String, String>> entry2 = o.getQuestion(QTypes.TRUE_FALSE);
    assertEquals("trueFalse", entry2.getKey());
    assertEquals("this is false", entry2.getValue().getKey());
    assertEquals("false", entry2.getValue().getValue());

    // Test null
    Map.Entry entryNull = o.getQuestion(QTypes.TRUE_FALSE);
    assertNull(entryNull);
  }
}
