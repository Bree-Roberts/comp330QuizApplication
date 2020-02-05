package server;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInputs {

  private static Question currentQuestion;

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
    CheckBox falseCheck = new CheckBox();
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
    int alternate = 0;
    int statementSize = ((Matching) currentQuestion).getStatementSize();
    for (int i = 0; i < statementSize; i++) {

      Label label = new Label(currentQuestion.getStatement());
      ComboBox<String> choices = new ComboBox<String>();

      for (int f = 0; f < currentQuestion.getAnswers().size(); f++) {
        choices.getItems().add((String) currentQuestion.getAnswers().get(f));
      }

      alternate++;

      if (alternate % 2 == 0) {
        left.getChildren().add(label);
        left.getChildren().add(choices);
      } else {
        right.getChildren().add(label);
        right.getChildren().add(choices);
      }
    }

    holder.getChildren().addAll(left, right, submit);
    MainWindow.getGameWindow().setBottom(holder);
  }

  public static void createMC() {
    HBox holder = new HBox();
    VBox left = new VBox();
    VBox right = new VBox();
    int alternate = 0;

    for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
      Button button = new Button(((MultipleChoice) currentQuestion).getAnswerStrings().get(i));

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
}
