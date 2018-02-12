import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pazuk on 08.02.2018.
 */

    // Plan:
    // #1.Check if main() method got any arguments from input at app start
    // #2.Scan and store data from input
    // #3.Check for matches between inputed data and arguments
    // #4.Print result

public class Greper {

    static boolean matchAlreadyFound;    // will be needed for case if match found
    static ArrayList<String> inputList;  // will be needed as container for inputing data
    static ArrayList<String> outputList; // container for filtered data


    public static void main(String[] args) {
        // implementation of the plan
        if(!haveArguments(args)) { // #1
            System.err.println("Error! Application should start with at least one argument");
        } else {
            scanInput(); // #2
            checkMatches(args); // #3
            printResult(outputList); // #4 - profit
        }
    }

    private static boolean haveArguments(String[] args) { // #1
        return args.length>0;
    }

    private static void scanInput() { // #2
        Scanner scanner=new Scanner(System.in);
        inputList =new ArrayList<>();
        int i=0;
        while(scanner.hasNextLine()) {
            inputList.add(scanner.nextLine()); // storing inputing lines in ArrayList
            if(inputList.get(i).isEmpty() || inputList.get(i).equals(" ")){
                break; // input stop after empty line
            } else {
                i++;
            }
        }
        scanner.close();
    }

    private static void checkMatches(String[] args) { // #3
        outputList=new ArrayList<>();
        Pattern pattern;
        Matcher matcher;
        int k=0;
        for(int i = 0; i< inputList.size(); i++) {
            matchAlreadyFound=false;
            String[] tempStrArr= inputList.get(i).split(" "); // tokenizing inputed lines into "words" by space delimiter
            for(int j=0; j<args.length; j++){
                pattern=Pattern.compile(args[j]+";?"); // compile pattern from every single word from arguments
                                                       // +
                                                       // in task examples there are ";" after every inputed line
                                                       // don't know, if it was really conceived, or it's just a
                                                       // decorative element of the visual representation of content
                                                       // of the sheet, but am gonna make it the same for checking for
                                                       // matches, if there is ";" after inputed line or not

                for(String iTempStrArr:tempStrArr){       // take every single word from inputed line
                    matcher=pattern.matcher(iTempStrArr); //
                    if(matcher.matches()) {               // and and check for the match with pattern
                        matchAlreadyFound = true;
                        outputList.add(k, inputList.get(i)); // if there is at least one (first) match in line-take the
                        k++;                                 // whole this line and add it to outputList ArrayList
                        break;
                    }
                }
                if(matchAlreadyFound) {
                    break;
                }
            }
        }
    }

    private static void printResult(ArrayList<String> outputList) { // #4
        for(String iStringList : outputList) {
            System.out.println(iStringList);
        }
    }
}
