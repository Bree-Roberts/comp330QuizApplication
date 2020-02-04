package server;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

class Parser {
  private JSONObject obj;

  Parser(Object obj) {
    this.obj = (JSONObject) obj;
  }

  public ArrayList<TrueFalse> parseTrueFalse() {
    Map<String, String> m = (Map) obj.get("trueFalse");
    ArrayList<TrueFalse> trueFalse = new ArrayList<>();
    for (Map.Entry<String, String> entry : m.entrySet()) {
      trueFalse.add(new TrueFalse(entry.getKey(), entry.getValue()));
    }
    return trueFalse;
  }

  public ArrayList<Matching> parseMatching() {
    Map<String, Map<String, String>> m = (Map) obj.get("matching");
    ArrayList<Matching> matching = new ArrayList<>();
    for (Map.Entry<String, Map<String, String>> set : m.entrySet()) {
      matching.add(new Matching(new ArrayList<>(set.getValue().entrySet())));
    }
    return matching;
  }

  public ArrayList<MultipleChoice> parseMultipleChoice() {
    Map<String, Map<String, String>> m = (Map) obj.get("multipleChoice");
    ArrayList<MultipleChoice> multipleChoice = new ArrayList<>();
    for (Map.Entry<String, Map<String, String>> set : m.entrySet()) {
      multipleChoice.add(
          new MultipleChoice(set.getKey(), new ArrayList<>(set.getValue().entrySet())));
    }
    return multipleChoice;
  }
}
