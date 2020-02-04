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
        tfc++;
        return getRequestedQuestion(tfc, trueFalseEntries, "trueFalse");
      case MATCHING:
        mc++;
        return getRequestedQuestion(mc, matchingEntries, "matching");
      case MULTIPLE_CHOICE:
        mcc++;
        return getRequestedQuestion(mcc, matchingEntries, "multipleChoice");
      case RANDOM:
        switch ((int) (Math.random() * 3) + 1) {
          case 1:
            return getQuestion(QTypes.TRUE_FALSE);
          case 2:
            return getQuestion(QTypes.MATCHING);
          case 3:
            return getQuestion(QTypes.MULTIPLE_CHOICE);
        }
        return null;
      default:
        System.out.println("Enum error");
        break;
    }
    return null;
  }

  private Map.Entry getRequestedQuestion(int counter, ArrayList entries, String type) {
    if (counter - 1 < entries.size()) {
      return new MyEntry("trueFalse", entries.get(counter - 1));
    }
    return null;
  }
}
