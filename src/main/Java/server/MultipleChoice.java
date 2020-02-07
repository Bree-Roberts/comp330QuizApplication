package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class MultipleChoice implements Question {
  private String statement;
  private ArrayList<Map.Entry<String, String>> answers;

  public MultipleChoice(String statement, ArrayList<Map.Entry<String, String>> answers) {
    this.statement = statement;
    this.answers = answers;
  }

  @Override
  public Enum getType() {
    return QTypes.MULTIPLE_CHOICE;
  }

  @Override
  public String getStatement() {
    return statement;
  }

  public ArrayList<String> getAnswerStrings() {
    ArrayList<String> answerStrings = new ArrayList<>();
    for (Map.Entry<String, String> entry : answers) {
      answerStrings.add(entry.getKey());
    }

    Collections.shuffle(answerStrings);
    return answerStrings;
  }

  @Override
  public ArrayList<Map.Entry<String, String>> getAnswers() {
    return answers;
  }
}
