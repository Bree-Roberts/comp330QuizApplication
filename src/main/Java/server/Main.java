package server;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    Parser parser = new Parser();
    Map<String, String> trueFalse = parser.getTrueFalse();
    Scanner in = new Scanner(System.in);
    int trueFalseScore = printTrueFalse(trueFalse, in);

    System.out.println(
        "You scored "
            + trueFalseScore
            + " out of "
            + trueFalse.size()
            + " on the True / False section.");
  }

  private static int printTrueFalse(Map<String, String> ques, Scanner in) {
    String answer;
    int score = 0;
    boolean valid;
    System.out.println("Enter True or False: ");
    Iterator<Map.Entry<String, String>> itr = ques.entrySet().iterator();
    while (itr.hasNext()) {
      valid = false;
      Map.Entry<String, String> pair = itr.next();
      System.out.print(pair.getKey() + ": ");
      answer = in.nextLine();
      while (!valid) {
        if (answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false")) {
          if (answer.equalsIgnoreCase(pair.getValue())) {
            System.out.println("Correct!");
            score++;
          } else {
            System.out.println("Incorrect");
          }
          valid = true;
        } else {
          System.out.println("Please enter a valid response.");
          answer = in.nextLine();
        }
      }
    }
    return score;
  }
}
