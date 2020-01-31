package server;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Output {
  private PrintStream out;
  private Map<String, String> trueFalse, matching;
  private Map<String, Map<String, String>> multipleChoice;
  private int tfc, mc, mcc;

  public Output(String fileName) throws IOException, ParseException {
    this.out = out;
    Object obj = new JSONParser().parse(new FileReader(fileName));
    Parser parser = new Parser(obj);
    tfc = 0;
    trueFalse = parser.parseTrueFalse();
    mc = 0;
    matching = parser.parseMatching();
    mcc = 0;
    multipleChoice = parser.parseMultipleChoice();
  }

  public Map.Entry getQuestion(QTypes type) {
    switch (type) {
      case TRUE_FALSE:
        return getTrueFalse();
      case MATCHING:
        return getMatching();
      case MULTIPLE_CHOICE:
        return getMultipleChoice();
      case RANDOM:
        return null;
      default:
        System.out.println("Enum error");
        break;
    }
    return null;
  }

  private Map.Entry<String, Map.Entry<String, String>> getTrueFalse() {
    ArrayList<Map.Entry<String, String>> entries = new ArrayList<>(trueFalse.entrySet());
    if (tfc < entries.size()) {
      tfc++;
      return new MyEntry<>("trueFalse", entries.get(tfc - 1));
    }
    return null;
  }

  private Map.Entry<String, Map.Entry<ArrayList<String>, ArrayList<String>>> getMatching() {
    // TODO add support for multiple matching sections
    ArrayList<String> statements = new ArrayList<>(matching.keySet());
    ArrayList<String> answers = new ArrayList<>(matching.values());
    return new MyEntry<>("matching", new MyEntry<>(statements, answers));
  }

  private Map.Entry<String, Map.Entry<String, Map<String, String>>> getMultipleChoice() {
    ArrayList<Map.Entry<String, Map<String, String>>> entries =
        new ArrayList<>(multipleChoice.entrySet());
    if (mcc < entries.size()) {
      mcc++;
      return new MyEntry<>("multipleChoice", entries.get(mcc - 1));
    }
    return null;
  }
}
