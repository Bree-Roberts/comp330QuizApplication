package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Matching implements Question {
  private ArrayList<String> statements;
  private ArrayList<String> answers;
  private ArrayList<String> tempArray, tempArrayTwo;
  private ArrayList<Map.Entry<String, String>> questionKey;

  public Matching(ArrayList<Map.Entry<String, String>> questions) {
    statements = new ArrayList<>();
    answers = new ArrayList<>();
    questionKey = questions;
    tempArray = statements;
    for (Map.Entry<String, String> question : questions) {
      statements.add(question.getKey());
      answers.add(question.getValue());
    }
    Collections.shuffle(answers);
  }

  @Override
  public QTypes getType() {
    return QTypes.MATCHING;
  }

  @Override
  public String getStatement() {
    String temp = null;
    if (!tempArray.isEmpty()) {
      temp = tempArray.get(0);
      tempArray.remove(0);
    }
    return temp;
  }

  public String getNextStatement() {
    String temp = null;
    if (!tempArrayTwo.isEmpty()) {
      temp = tempArrayTwo.get(0);
      tempArrayTwo.remove(0);
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
