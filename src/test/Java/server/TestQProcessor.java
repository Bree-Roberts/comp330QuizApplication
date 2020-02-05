package server;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestQProcessor {
  private final QProcessor processor = new QProcessor("TestQProcessor.json");

  public TestQProcessor() throws IOException, ParseException {}

  @Test
  public void testGetQuestion() {
    assertNotNull(processor.getQuestion());
  }

  @Test
  public void testCheckAnswerTrueFalse() {
    TrueFalse q = new TrueFalse("this is true", "true");
    assertTrue(processor.checkAnswer("true", q, null));
  }

  @Test
  public void testCheckAnswerMatching() {
    Matching q =
        new Matching(
            new ArrayList<>(
                Arrays.asList(new MyEntry<>("red", "red"), new MyEntry<>("blue", "blue"))));
    assertTrue(processor.checkAnswer("red", q, "red"));
  }

  @Test
  public void testCheckAnswerMultipleChoice() {
    MultipleChoice q =
        new MultipleChoice(
            "this is a question",
            new ArrayList<>(
                Arrays.asList(
                    new MyEntry<>("A", "I"), new MyEntry<>("B", "C"), new MyEntry<>("C", "I"))));
    assertTrue(processor.checkAnswer("B", q, null));
  }
}
