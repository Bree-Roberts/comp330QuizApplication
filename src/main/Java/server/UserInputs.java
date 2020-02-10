package server;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserInputs {

  private static Question currentQuestion;
  private static ArrayList<ComboBox<String>> matchList = new ArrayList<ComboBox<String>>();

  public static void getNextQuestion() {

    if (QProcessor.mixedHasNext()) {
      currentQuestion = QProcessor.getQuestion();
      QuestionOutput.setQuestionOutputText(currentQuestion.getStatement());
      if (currentQuestion.getType() == QTypes.TRUE_FALSE) {
        createTF();
      } else if (currentQuestion.getType() == QTypes.MATCHING) {
        createMatch();

      } else if (currentQuestion.getType() == QTypes.MULTIPLE_CHOICE) {
        createMC();
      }
    } else Platform.exit();
  }

  public static void createTF() {
    HBox holder = new HBox();
    CheckBox trueCheck = new CheckBox();
    trueCheck.setOnAction(e -> answerQuestion(QProcessor.checkAnswer("true", currentQuestion)));
    CheckBox falseCheck = new CheckBox();
    falseCheck.setOnAction(e -> answerQuestion(QProcessor.checkAnswer("false", currentQuestion)));
    Label trueLabel = new Label("True");
    Label falseLabel = new Label("False");
    holder.getChildren().addAll(trueCheck, trueLabel, falseCheck, falseLabel);
    MainWindow.getGameWindow().setBottom(holder);

    // formating
    holder.setPrefHeight(200);
    holder.setAlignment(Pos.CENTER);
    trueCheck.setMinSize(20, 20);
    falseCheck.setMinSize(20, 20);
    trueLabel.setMinSize(20, 40);
    trueLabel.setFont(new Font(24));
    falseLabel.setFont(new Font(24));
    falseLabel.setMinSize(20, 40);
  }

  public static void createMatch() {
    HBox holder = new HBox();
    HBox left = new HBox();
    HBox right = new HBox();
    VBox leftStatements = new VBox();
    VBox leftChoices = new VBox();
    VBox rightStatements = new VBox();
    VBox rightChoices = new VBox();

    Button submit = new Button("Submit");
    int alternate = 0;
    int matchingKeySize = ((Matching) currentQuestion).getMatchingKeySize();

    submit.setOnAction(e -> submitMatching());
    matchList.clear();

    for (int i = 0; i < matchingKeySize; i++) {

      Label label = new Label(((Matching) currentQuestion).getNextMatchingKey());

      // UI formatting
      label.setMinSize(20, 40);
      label.setFont(new Font(24));

      ComboBox<String> choices = new ComboBox<String>();
      matchList.add(choices);

      for (int f = 0; f < currentQuestion.getAnswers().size(); f++) {
        choices.getItems().add((String) currentQuestion.getAnswers().get(f));
      }
      if (alternate % 2 == 0) {
        leftStatements.getChildren().add(label);
        leftChoices.getChildren().add(choices);
      } else {
        rightStatements.getChildren().add(label);
        rightChoices.getChildren().add(choices);
      }
      alternate++;
    }
    left.getChildren().addAll(leftStatements, leftChoices);
    right.getChildren().addAll(rightStatements, rightChoices);
    holder.getChildren().addAll(left, right, submit);
    MainWindow.getGameWindow().setBottom(holder);

    // UI formating
    holder.setPrefHeight(200);
    holder.setAlignment(Pos.CENTER);
    left.setAlignment(Pos.BASELINE_RIGHT);
    right.setAlignment(Pos.BASELINE_LEFT);
    right.setSpacing(10);
  }

  public static void createMC() {
    HBox holder = new HBox();
    VBox left = new VBox();
    VBox right = new VBox();
    int alternate = 0;
    ArrayList<String> temp = new ArrayList<String>();
    temp = ((MultipleChoice) currentQuestion).getAnswerStrings();

    for (int i = 0; i < temp.size(); i++) {
      Button button = new Button(temp.get(i));
      button.setOnAction(
          e -> answerQuestion(QProcessor.checkAnswer(button.getText(), currentQuestion)));

      // UI Formating
      button.setMinSize(50, 50);

      if (alternate % 2 == 0) {
        left.getChildren().add(button);
      } else {
        right.getChildren().add(button);
      }
      alternate++;
    }

    holder.getChildren().addAll(left, right);
    MainWindow.getGameWindow().setBottom(holder);

    // UI Formatting
    holder.setPrefHeight(200);
    holder.setAlignment(Pos.CENTER);
    left.setAlignment(Pos.BASELINE_RIGHT);
    right.setAlignment(Pos.BASELINE_LEFT);
    right.setSpacing(10);
    left.setSpacing(10);
  }

  public static void answerQuestion(boolean answer) {
    MainWindow.getGameWindow().setBottom(null);
    if (answer) {
      MainWindow.setCorrect();
      ScoreBoard.addScore(10);
    } else {
      MainWindow.setIncorrect();
    }
    getNextQuestion();
  }

  public static void submitMatching() {
    ArrayList<Boolean> temp = new ArrayList<Boolean>();

    while (!matchList.isEmpty()) {
      temp.add(QProcessor.checkAnswer(matchList.get(0).getValue(), currentQuestion));
      matchList.remove(0);
    }
    QProcessor.resetCounter();
    if (temp.contains(false)) {
      answerQuestion(false);
    } else answerQuestion(true);
  }
}
