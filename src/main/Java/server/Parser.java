package server;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

class Parser {
  private JSONObject obj;

  public Parser(Object obj) {
    this.obj = (JSONObject) obj;
  }

  Map parseTrueFalse() {
    return ((Map) obj.get("trueFalse"));
  }

  Map parseMatching() {
    return (Map) obj.get("matching");
  }

  Map parseMultipleChoice() {
    return (Map) obj.get("multipleChoice");
  }
}
