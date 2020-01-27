package server;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuestionOutput {

	private static StringProperty questionOutputText = new SimpleStringProperty();
	
	public static StringProperty getQuestionOutputText() {
		return questionOutputText;
	}
	
	public static void setQuestionOutputText(String text) {
		questionOutputText.setValue(text);
	}
	
}
