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
    assertNotNull(QProcessor.getQuestion());
  }

  @Test
  public void testCheckAnswerTrueFalse() {
    TrueFalse q = new TrueFalse("this is true", "true");
    assertTrue(QProcessor.checkAnswer("true", q));
  }

  @Test
  public void testCheckAnswerMatching() {
    Matching q =
        new Matching(
            "Match these to their definitions",
            new ArrayList<>(
                Arrays.asList(new MyEntry<>("red", "red"), new MyEntry<>("blue", "blue"))));
    assertTrue(QProcessor.checkAnswer("red", q));
  }

  @Test
  public void testCheckAnswerMultipleChoice() {
    MultipleChoice q =
        new MultipleChoice(
            "this is a question",
            new ArrayList<>(
                Arrays.asList(
                    new MyEntry<>("A", "I"), new MyEntry<>("B", "C"), new MyEntry<>("C", "I"))));
    assertTrue(QProcessor.checkAnswer("B", q));
  }
}
