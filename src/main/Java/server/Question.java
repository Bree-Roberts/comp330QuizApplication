package server;

import java.util.ArrayList;

public interface Question {
  Enum getType();

  String getStatement();

  ArrayList getAnswers();
}
