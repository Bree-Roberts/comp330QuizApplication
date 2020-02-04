package server;

import java.util.ArrayList;
import java.util.Map;

public class MultipleChoice implements Question {
  private String statement;
  private ArrayList answers;

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

  @Override
  public ArrayList<Map.Entry<String, String>> getAnswers() {
    return answers;
  }
}
