/**
* Student ID: V00816592
* Date: 07/17/15
* Program Name: Extra.java 
* Program Description: Extra contains algorithm which runs infixcalc with all of the provided 
* testcases from testcases.txt and compares
* computed output against the expected results (results.txt)
* @Author Isaac Sahle 
*/

//Sample command-line input .. java Extra --input testcases.txt --output results.txt

import java.io.*;
import java.util.*;

public class Extra{

	
public static void main(String[] args) {

	String infileName1 = null;
    String infileName2 = null;	
	boolean passed = true;		

try{
    //process command line arguments 
    for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--input")) {
                infileName1 = args[i+1];
            } else if (args[i].equals("--output")) {
                infileName2 = args[i+1];
            }
        }
             if (infileName1 == null) {
                System.out.println("Usage: please provide the first input filename");
            }

            if (infileName2 == null) {
                System.out.println("Usage: please provide the second input filename");
            }

            if (infileName1 == null || infileName2 == null) {
                System.exit(1);
            }

            // Processing can begin, file names have been set 

            Scanner infileScanner1  = new Scanner(new File(infileName1));
            Scanner infileScanner2 = new Scanner(new File(infileName2));
            InfixCalc result = new InfixCalc();

            //While the files still have text, compare each string to each other 
            while (infileScanner1.hasNext() && infileScanner2.hasNext()) {

                String s1 = infileScanner1.nextLine();
                String s2 = infileScanner2.nextLine();
                if(result.evaluateExpression(s1).equals("")){
                    if(!(s2.equals("<syntax error>")) && !(s2.equals("<unbalanced ()>")) && !(s2.equals("<noninteger>"))){
                        passed = false;
                        break;
                    }
                }else if(!(result.evaluateExpression(s1).equals(s2))){
                    System.out.println(result.evaluateExpression(s1));
                    passed = false;
                    break;
                }
            }
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Probably not enough arguments provided.\n");
            System.exit(1);
        }
        System.out.println("Results are the same: " + passed);
    }

}