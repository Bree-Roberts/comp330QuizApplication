package server;

import org.json.simple.parser.JSONParser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  // private Object obj =
  //  new JSONParser().parse(new FileReader("src/main/java/server/questions.json"));
  // Allows to pass an output to other methods. We can change where the output goes later.
  // (System.out for now)
  // private PrintStream out = new PrintStream(System.out);
  // private Scanner in = new Scanner(System.in);
  // private Parser parser = new Parser(obj);
  // private Output output = new Output(out);

  // UI Elements
  private BorderPane mainLayout = new BorderPane();
  private BorderPane titleLayout = new BorderPane();
  private VBox centerWindow = new VBox();
  private VBox titleSelect = new VBox();
  private Scene mainScene = new Scene(mainLayout, 900, 600);
  private Scene titleScreen = new Scene(titleLayout, 900, 600);
  private Label title = new Label();
  private Text questionOutput = new Text();
  private ScrollPane questionOutputWindow = new ScrollPane();
  private Button take = new Button("Take Quiz");
  private Button create = new Button("Create");
  private Button settings = new Button("Settings");
  private Button test = new Button("Test Score");
  private Button exit = new Button("Exit");
  private Label scoreBoard = new Label();

  public void start(Stage primaryStage) throws Exception {

    // Title Screen Elements
    title.setText("Quiz Game");
    title.setFont(new Font(48));
    titleLayout.setTop(title);
    titleLayout.setCenter(titleSelect);
    titleSelect.getChildren().addAll(take, create, settings, exit);

    // Quiz Elements
    primaryStage.setTitle("Quiz Game");
    mainLayout.setBottom(centerWindow);
    centerWindow.getChildren().addAll(test);
    questionOutputWindow.setContent(questionOutput);
    mainLayout.setCenter(questionOutputWindow);
    primaryStage.setScene(titleScreen);

    // Scoreboard UI
    mainLayout.setRight(scoreBoard);

    // UI formatting
    questionOutput.setFont(new Font(20));
    questionOutput.setWrappingWidth(620);

    // binds Label text
    scoreBoard.textProperty().bind(ScoreBoard.getScoreBoard());
    questionOutput.textProperty().bind(QuestionOutput.getQuestionOutputText());

    // Button Functions
    take.setOnAction(e -> primaryStage.setScene(mainScene));
    test.setOnAction(e -> ScoreBoard.addScore(10));

    // Parse the questions and answers
    // Map<String, String> trueFalse = parser.parseTrueFalse();
    // Map<String, String> matching = parser.parseMatching();

    // Ask the questions and record score
    //  int trueFalseScore = output.askTrueFalse(trueFalse, in);
    // int matchingScore = output.askMatching(matching, in);

    // out.println(
    //   "You scored "
    //     + trueFalseScore
    //   + " out of "
    //  + trueFalse.size()
    //  + " on the True / False section.");
    // out.println(
    //   "You scored " + matchingScore + " out of " + matching.size() + " on the Matching
    // section.");

    primaryStage.show();
  }
}
