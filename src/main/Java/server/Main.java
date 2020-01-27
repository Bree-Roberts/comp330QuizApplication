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

import java.io.FileReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	private Object obj = new JSONParser().parse(new FileReader("src/main/java/server/questions.json"));
	private PrintStream out = new PrintStream(System.out); //Allows to pass an output to other methods. We can change where the output goes later. (System.out for now)
	private Scanner in = new Scanner(System.in);
	private Parser parser = new Parser(obj);
	private Output output = new Output(out);
	
	//UI Elements
	private BorderPane mainLayout = new BorderPane();
	private HBox centerWindow = new HBox();
	private Scene mainScene = new Scene(mainLayout, 900, 600);
	private Text questionOutput = new Text(); 
	private ScrollPane questionOutputWindow = new ScrollPane();
	
	private Label scoreBoard = new Label();
	
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Quiz Game");
		mainLayout.setCenter(centerWindow);
		mainLayout.setTop(scoreBoard);
		questionOutputWindow.setContent(questionOutput);
		
		//UI formatting
		questionOutput.setFont(new Font(20));
		questionOutput.setWrappingWidth(620);
		
		
		//binds Label text
		scoreBoard.textProperty().bind(ScoreBoard.getScoreBoard());
		questionOutput.textProperty().bind(QuestionOutput.getQuestionOutputText());
		
		//Parse the questions and answers
		Map<String, String> trueFalse = parser.parseTrueFalse();
		Map<String, String> matching = parser.parseMatching();

		//Ask the questions and record score
		int trueFalseScore = output.askTrueFalse(trueFalse, in);
		int matchingScore = output.askMatching(matching, in);

		out.println(
				"You scored "
						+ trueFalseScore
						+ " out of "
						+ trueFalse.size()
						+ " on the True / False section.");
		out.println(
				"You scored "
						+ matchingScore
						+ " out of "
						+ matching.size()
						+ " on the Matching section.");



	}
}


