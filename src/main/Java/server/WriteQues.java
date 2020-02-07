package server;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteQues {
  private final JSONObject jo = new JSONObject();
  private Map<String, String> trueFalseM;
  private Map<String, Map<String, String>> matchingM;
  private Map<String, Map<String, String>> multipleChoiceM;
  private String fileName;

  public WriteQues(String fileName) {
    trueFalseM = new LinkedHashMap<>();
    matchingM = new LinkedHashMap<>();
    multipleChoiceM = new LinkedHashMap<>();
    this.fileName = fileName;
  }

  public void writeTrueFalse(String statement, String answer) {
    trueFalseM.put(statement, answer);
  }

  public void writeMatching(String setName, ArrayList<Map.Entry<String, String>> questions) {
    Map<String, String> setMap = new LinkedHashMap<>();
    for (Map.Entry<String, String> entry : questions) {
      setMap.put(entry.getKey(), entry.getValue());
    }
    matchingM.put(setName, setMap);
  }

  public void writeMultipleChoice(String question, ArrayList<Map.Entry<String, String>> answers) {
    Map<String, String> answerMap = new LinkedHashMap<>();
    for (Map.Entry<String, String> answer : answers) {
      answerMap.put(answer.getKey(), answer.getValue());
    }
    multipleChoiceM.put(question, answerMap);
  }

  public void writeJSON() throws FileNotFoundException {
    jo.put("trueFalse", trueFalseM);
    jo.put("matching", matchingM);
    jo.put("multipleChoice", multipleChoiceM);
    PrintWriter pw = new PrintWriter(fileName);
    pw.write(jo.toJSONString());

    pw.flush();
    pw.close();
  }
}
