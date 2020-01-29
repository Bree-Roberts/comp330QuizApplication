package server;

import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException, ParseException {
    Object obj = new JSONParser().parse(new FileReader("src/main/java/server/questions.json"));
    PrintStream out =
        new PrintStream(
            System
                .out); // Allows to pass an output to other methods. We can change where the output
                       // goes later. (System.out for now)
    Scanner in = new Scanner(System.in);
    Parser parser = new Parser(obj);
    Output output = new Output(out);

    WriteQues writeQues = new WriteQues();
    writeQues.promptUser(in, out);
    Map<String, String> trueFalse = parser.parseTrueFalse();
    Map<String, String> matching = parser.parseMatching();
    Map<String, Map<String, String>> multipleChoice = parser.parseMultipleChoice();

    // Ask the questions and record score
    int trueFalseScore = output.askTrueFalse(trueFalse, in);
    int matchingScore = output.askMatching(matching, in);
    int multipleChoiceScore = output.askMultipleChoice(multipleChoice, in);

    out.println(
        "You scored "
            + trueFalseScore
            + " out of "
            + trueFalse.size()
            + " on the True / False section.");
    out.println(
        "You scored " + matchingScore + " out of " + matching.size() + " on the Matching section.");
    out.println(
            "You scored " + multipleChoiceScore + " out of " + multipleChoice.size() + " on the Matching section.");
  }
}
