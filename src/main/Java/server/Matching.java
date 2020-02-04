package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Matching implements Question {
  private ArrayList<String> statements;
  private ArrayList<String> answers;
  private ArrayList<Map.Entry<String, String>> questionKey;
  private int num;

  public Matching(ArrayList<Map.Entry<String, String>> questions) {
    num = 0;
    statements = new ArrayList<>();
    answers = new ArrayList<>();
    questionKey = questions;
    for (Map.Entry<String, String> question : questions) {
      statements.add(question.getKey());
      answers.add(question.getValue());
    }
    Collections.shuffle(answers);
  }

  @Override
  public Enum getType() {
    return QTypes.MATCHING;
  }

  @Override
  public String getStatement() {
    num++;
    return statements.get(num - 1);
  }

  @Override
  public ArrayList getAnswers() {
    return answers;
  }

  public ArrayList<Map.Entry<String, String>> getQuestionKey() {
    return questionKey;
  }
}
