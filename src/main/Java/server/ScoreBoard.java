package server;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScoreBoard {

  private static int score;
  private static StringProperty scoreBoard = new SimpleStringProperty();

  public static void setScore(int newScore) {
    score = newScore;
  }

  public static int getScore() {
    return score;
  }

  public static void addScore(int points) {
    score += points;
  }

  public static StringProperty getScoreBoard() {
    scoreBoard.setValue("Score: " + getScore());
    return scoreBoard;
  }
}
