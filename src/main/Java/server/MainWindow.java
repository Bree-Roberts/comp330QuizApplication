package server;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainWindow {

  private static BorderPane mainLayout = new BorderPane();
  private static VBox centerWindow = new VBox();
  private static Scene mainScene = new Scene(mainLayout, 900, 600);
  private static Text questionOutput = new Text();
  private static ScrollPane questionOutputWindow = new ScrollPane();
  private static Button test = new Button("Test");
  private static Label scoreBoard = new Label();

  public static Scene createMainWindow() {
    mainLayout.setTop(centerWindow);
    centerWindow.getChildren().addAll(test);
    questionOutputWindow.setContent(questionOutput);
    mainLayout.setCenter(questionOutputWindow);

    // Scoreboard UI
    mainLayout.setRight(scoreBoard);

    // UI formatting
    questionOutput.setFont(new Font(20));
    questionOutput.setWrappingWidth(620);

    // binds Label text
    scoreBoard.textProperty().bind(ScoreBoard.getScoreBoard());
    questionOutput.textProperty().bind(QuestionOutput.getQuestionOutputText());

    // binds test button (TEMP)
    test.setOnAction(e -> UserInputs.getNextQuestion());

    return mainScene;
  }

  public static BorderPane getGameWindow() {
    return mainLayout;
  }
}
