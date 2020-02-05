package server;

import java.util.ArrayList;
import java.util.Arrays;

public class TrueFalse implements Question {
  private String statement;
  private String answer;

  public TrueFalse(String statement, String answer) {
    this.statement = statement;
    this.answer = answer;
  }

  @Override
  public Enum getType() {
    return QTypes.TRUE_FALSE;
  }

  @Override
  public String getStatement() {
    return statement;
  }

  @Override
  public ArrayList getAnswers() {
    return new ArrayList<String>(Arrays.asList(answer));
  }
}
