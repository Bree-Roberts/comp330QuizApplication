package server;

import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    Object obj = new JSONParser().parse(new FileReader("src/main/java/server/questions.json"));
    PrintStream out = new PrintStream(System.out);

    Parser parser = new Parser(obj);
    Output output = new Output(out);

    Map<String, String> trueFalse = parser.parseTrueFalse();
    Map<String, String> matching = parser.parseMatching();
    Scanner in = new Scanner(System.in);

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
