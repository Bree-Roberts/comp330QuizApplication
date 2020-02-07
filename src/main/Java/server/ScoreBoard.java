package server;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class ScoreBoard {

  private static int score = 0;
  private static StringProperty scoreBoard = new SimpleStringProperty();

  public static void setScore(int newScore) {
    score = newScore;
  }

  public static int getScore() {
    return score;
  }

  public static void addScore(int points) {
    score += points;
    getScoreBoard();
  }

  public static StringProperty getScoreBoard() {
    scoreBoard.setValue("Score: " + getScore());
    return scoreBoard;
  }
}
