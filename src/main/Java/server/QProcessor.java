package server;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class QProcessor {
  private ArrayList<TrueFalse> trueFalse;
  private ArrayList<Matching> matching;
  private ArrayList<MultipleChoice> multipleChoice;
  private static final ArrayList<Question> mixed = new ArrayList<>();
  private static int counter;

  public QProcessor(String fileName) throws IOException, ParseException {
    Object obj = new JSONParser().parse(new FileReader(fileName));
    Parser parser = new Parser(obj);
    trueFalse = parser.parseTrueFalse();
    matching = parser.parseMatching();
    multipleChoice = parser.parseMultipleChoice();

    mixed.addAll(parser.parseTrueFalse());
    mixed.addAll(parser.parseMatching());
    mixed.addAll(parser.parseMultipleChoice());
    Collections.shuffle(mixed);

    counter = 0;
  }

  public static Question getQuestion() {
    if (counter < mixed.size()) {
      counter++;
      return mixed.get(counter - 1);
    } else {
      return null;
    }
  }

  public static boolean checkAnswer(String userAnswer, Question question) {
    QTypes type = question.getType();
    if (QTypes.TRUE_FALSE.equals(type)) {
      return userAnswer.equalsIgnoreCase((String) question.getAnswers().get(0));
    } else if (QTypes.MATCHING.equals(type)) {
      Matching q = (Matching) question;
      for (Map.Entry<String, String> entry : q.getQuestionKey()) {
        if (entry.getKey().equalsIgnoreCase(q.getNextStatement())
            && entry.getValue().equals(userAnswer)) {
          return true;
        }
      }
      return false;
    } else if (QTypes.MULTIPLE_CHOICE.equals(type)) {
      MultipleChoice q = (MultipleChoice) question;
      for (Map.Entry<String, String> entry : q.getAnswers()) {
        if (userAnswer.equalsIgnoreCase(entry.getKey()) && entry.getValue().equals("C")) {
          return true;
        }
      }
      return false;
    } else {
      System.out.println("something went wrong - returning false");
      return false;
    }
  }
}
