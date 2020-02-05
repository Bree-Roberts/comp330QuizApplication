package server;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Output {
  private ArrayList trueFalseEntries;
  private ArrayList matchingEntries;
  private ArrayList multipleChoiceEntries;
  private int tfc, mc, mcc;

  public Output(String fileName) throws IOException, ParseException {
    Object obj = new JSONParser().parse(new FileReader(fileName));
    Parser parser = new Parser(obj);
    tfc = 0;
    trueFalseEntries = new ArrayList<Map.Entry<String, String>>(parser.parseTrueFalse().entrySet());
    mc = 0;
    matchingEntries =
        new ArrayList<Map.Entry<String, Map<String, String>>>(parser.parseMatching().entrySet());
    mcc = 0;
    multipleChoiceEntries =
        new ArrayList<Map.Entry<String, Map<String, String>>>(
            parser.parseMultipleChoice().entrySet());
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
        switch ((int) (Math.random() * 3) + 1) {
          case 1:
            return getTrueFalse();
          case 2:
            return getMatching();
          case 3:
            return getMultipleChoice();
        }
        return null;
      default:
        System.out.println("Enum error");
        break;
    }
    return null;
  }

  private Map.Entry<String, Map.Entry<String, String>> getTrueFalse() {
    if (tfc < trueFalseEntries.size()) {
      tfc++;
      return new MyEntry("trueFalse", trueFalseEntries.get(tfc - 1));
    }
    return null;
  }

  private Map.Entry<String, Map.Entry<ArrayList<String>, ArrayList<String>>> getMatching() {
    // TODO add support for multiple matching sections
    if (mc < matchingEntries.size()) {
      mc++;
      System.out.println(matchingEntries);
      return new MyEntry("matching", matchingEntries.get(mc - 1));
    }
    return null;
  }

  private Map.Entry<String, Map.Entry<String, Map<String, String>>> getMultipleChoice() {
    if (mcc < multipleChoiceEntries.size()) {
      mcc++;
      return new MyEntry("multipleChoice", multipleChoiceEntries.get(mcc - 1));
    }
    return null;
  }
}
