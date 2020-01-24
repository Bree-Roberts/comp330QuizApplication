package server;

import java.io.FileReader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Parser {
  private JSONObject obj;

  public Parser() throws Exception {
    obj =
        (JSONObject) new JSONParser().parse(new FileReader("src/main/java/server/questions.json"));
  }

  public Map<String, String> getTrueFalse() {
    Map trueFalseQuestions = ((Map) obj.get("trueFalse"));
    return trueFalseQuestions;
  }
}
