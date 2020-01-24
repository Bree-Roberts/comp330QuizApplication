package server;

import java.io.PrintStream;
import java.util.*;

public class Output {

    PrintStream out;

    public Output(PrintStream out) {
        this.out = out;
    }

    public int askTrueFalse(Map<String, String> ques, Scanner in) {
        String answer;
        int score = 0;
        boolean valid;
        out.println("Enter True or False: ");
        Iterator<Map.Entry<String, String>> itr = ques.entrySet().iterator();
        while (itr.hasNext()) {
            valid = false;
            Map.Entry<String, String> pair = itr.next();
            out.print(pair.getKey() + ": ");
            answer = in.nextLine();
            while (!valid) {
                if (answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false")) {
                    if (answer.equalsIgnoreCase(pair.getValue())) {
                        out.println("Correct!");
                        score++;
                    } else {
                        out.println("Incorrect");
                    }
                    valid = true;
                } else {
                    out.println("Please enter a valid response.");
                    answer = in.nextLine();
                }
            }
        }
        return score;
    }

    public int askMatching(Map<String, String> ques, Scanner in) {
        String answer;
        int score = 0;
        boolean valid;
        ArrayList<String> words = new ArrayList<>(ques.size());
        ArrayList<String> descriptions = new ArrayList<>(ques.size());
        for(Map.Entry<String, String> pair : ques.entrySet()){
            words.add(pair.getKey());
            descriptions.add(pair.getValue());
        }
        Collections.shuffle(descriptions);

        out.println("Your words are: " + words.toString());
        out.println("Match them to the following.");

        for (String description : descriptions) {
            out.print(description + " : ");
            valid = false;
            while(!valid) {
                answer = in.nextLine();
                if (!ques.containsKey(answer)) {
                    out.println("Please enter a valid response.");
                } else if (ques.get(answer).equalsIgnoreCase(description)) {
                    out.println("Correct!");
                    score++;
                    valid = true;
                } else {
                    out.println("Incorrect");
                    valid = true;
                }
            }
        }

        return score;
    }
}
