package server;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Parser {
  private JSONObject obj;

  public Parser(Object obj) {
    this.obj = (JSONObject) obj;
  }

  public Map<String, String> parseTrueFalse() {
    return ((Map) obj.get("trueFalse"));
  }

  public Map<String, String> parseMatching() {
    return (Map) obj.get("matching");
  }

  public Map<String, Map<String, String>> parseMultipleChoice() {
    return (Map) obj.get("multipleChoice");
  }
}
