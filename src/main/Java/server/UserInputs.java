package server;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInputs {

  private static Question currentQuestion;
  private static ArrayList<ComboBox<String>> matchList = new ArrayList<ComboBox<String>>();

  public static void getNextQuestion() {
    currentQuestion = QProcessor.getQuestion();
    QuestionOutput.setQuestionOutputText(currentQuestion.getStatement());

    if (currentQuestion.getType() == QTypes.TRUE_FALSE) {
      createTF();
    } else if (currentQuestion.getType() == QTypes.MATCHING) {
      createMatch();

    } else if (currentQuestion.getType() == QTypes.MULTIPLE_CHOICE) {
      createMC();
    }
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
  }

  public static void createMatch() {
    HBox holder = new HBox();
    VBox left = new VBox();
    VBox right = new VBox();
    Button submit = new Button("Submit");
    submit.setOnAction(e -> submitMatching());
    int matchingKeySize = ((Matching) currentQuestion).getMatchingKeySize();

    matchList.clear();

    for (int i = 0; i < matchingKeySize; i++) {

      Label label = new Label(((Matching) currentQuestion).getNextMatchingKey());
      ComboBox<String> choices = new ComboBox<String>();
      matchList.add(choices);

      for (int f = 0; f < currentQuestion.getAnswers().size(); f++) {
        choices.getItems().add((String) currentQuestion.getAnswers().get(f));
      }

      left.getChildren().add(label);
      right.getChildren().add(choices);
    }

    holder.getChildren().addAll(left, right, submit);
    MainWindow.getGameWindow().setBottom(holder);
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

      if (alternate % 2 == 0) {
        left.getChildren().add(button);
      } else {
        right.getChildren().add(button);
      }
      alternate++;
    }

    holder.getChildren().addAll(left, right);
    MainWindow.getGameWindow().setBottom(holder);
  }

  public static void answerQuestion(boolean answer) {
    MainWindow.getGameWindow().setBottom(null);
    if (answer) {
      QuestionOutput.addQuestionOutputText("Correct!");
      ScoreBoard.addScore(10);
    } else {
      QuestionOutput.addQuestionOutputText("Incorrect!");
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
