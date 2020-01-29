package server;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteQues
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // creating JSONObject
        JSONObject jo = new JSONObject();

        // putting data to JSONObject
        jo.put("firstName", "John");
        jo.put("lastName", "Smith");
        jo.put("age", 25);

        // for address data, first create LinkedHashMap
        Map m = new LinkedHashMap(4);
        m.put("streetAddress", "21 2nd Street");
        m.put("city", "New York");
        m.put("state", "NY");
        m.put("postalCode", 10021);

        // putting address to JSONObject
        jo.put("address", m);

        // for phone numbers, first create JSONArray
        JSONArray ja = new JSONArray();

        m = new LinkedHashMap(2);
        m.put("type", "home");
        m.put("number", "212 555-1234");

        // adding map to list
        ja.add(m);

        m = new LinkedHashMap(2);
        m.put("type", "fax");
        m.put("number", "212 555-1234");

        // adding map to list
        ja.add(m);

        // putting phoneNumbers to JSONObject
        jo.put("phoneNumbers", ja);

        // writing JSON to file:"JSONExample.json" in cwd
        PrintWriter pw = new PrintWriter("JSONExample.json");
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }

    public void promptUser(Scanner in, PrintStream out) {
        JSONObject jo = new JSONObject();
        boolean done = false;
        ArrayList<String> options = new ArrayList<>(Arrays.asList("true false", "matching", "multiple choice"));
        out.println("Enter what kind of question to begin: True False, Matching, or Multiple Choice");
        String user = in.nextLine();
        while(!done) {
            while (!options.contains(user.toLowerCase()) && !user.equalsIgnoreCase("done")) {
                out.print("Please enter a valid response: ");
                user = in.nextLine();
            }
            if(user.equalsIgnoreCase("done")){
                break;
            } else if (user.equalsIgnoreCase("true false")) {
                createTrueFalse(in, out, jo);
            } else if (user.equalsIgnoreCase("matching")) {
                createMatching(in, out, jo);
            } else if (user.equalsIgnoreCase("multiple choice")) {
                createMultipleChoice(in, out, jo);
            } else {
                out.println("Just... How?");
            }
            out.println("If you are finished enter: Done. \nIf not, enter another question type: True False, Matching, or Multiple Choice.");
            user = in.nextLine();
            if(user.equalsIgnoreCase("done")) {
                done = true;
            }
        }

    }

    private void createTrueFalse(Scanner in, PrintStream out, JSONObject jo){
        Map m = new LinkedHashMap();
        boolean done = false;
        boolean valid = false;
        String statement;
        String answer;
        out.println("Please enter a statement: ");
        statement = in.nextLine();
        while(!done) {
            //TODO statement confirmation
            out.print("Is this statement true or false: ");
            while(!valid) {
                answer = in.nextLine();
                if(answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false")){
                    valid = true;
                    m.put(statement, answer);
                } else {
                    out.println("Please enter a valid response.");
                }
            }
            out.println("If you are finished enter: Done. \nOtherwise, please enter another statement: ");
            statement = in.nextLine();
            if(statement.equalsIgnoreCase("done")){
                done = true;
            }
            valid = false;
        }
        jo.put("trueFalse", m);
    }

    private void createMatching(Scanner in, PrintStream out, JSONObject jo){

    }

    private void createMultipleChoice(Scanner in, PrintStream out, JSONObject jo){

    }
}