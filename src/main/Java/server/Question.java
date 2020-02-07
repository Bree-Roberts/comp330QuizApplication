package server;

import java.util.ArrayList;

public interface Question {
  QTypes getType();

  String getStatement();

  ArrayList getAnswers();
}
