package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Matching implements Question {
  private ArrayList<String> statements;
  private ArrayList<String> answers;
  private ArrayList<Map.Entry<String, String>> questionKey;

  public Matching(ArrayList<Map.Entry<String, String>> questions) {
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
    String temp = null;
    if (!statements.isEmpty()) {
      temp = statements.get(0);
      statements.remove(0);
    }
    return temp;
  }

  @Override
  public ArrayList<String> getAnswers() {
    return answers;
  }

  public ArrayList<Map.Entry<String, String>> getQuestionKey() {
    return questionKey;
  }

  public int getStatementSize() {
    return statements.size();
  }
}
