/**
* Student ID: V00816592
* Date: 07/31/15
* Program Name: Extra.java 
* Program Description: Extra contains algorithm which runs WordReferences with all of the provided 
* testcases from tests.txt and compares
* computed output against the expected results (results.txt)
* @Author Isaac Sahle 
*/

//Sample command-line input .. java Extra --input tests/in01.txt --output tests/out01.txt

import java.io.*;
import java.util.*;

public class Extra{

    public static BSTRefBased rawRefs(String filename) {
        BSTRefBased result = new BSTRefBased();
        try {
            Scanner scanner = new Scanner(new File(filename));
            int lineNum = 1;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                line = line.trim();
                line = line.replaceAll("\\p{Punct}+", "");
                if (line.equals("")) {
                    continue;
                }
                String words[] = line.split("\\s+");
                for (String w : words) {
                    String ww = w.toLowerCase();
                    WordRefs wf = result.retrieve(ww);
                    if (wf == null) {
                        result.insert(ww);
                        wf = result.retrieve(ww);
                    }
                    wf.addLine(lineNum);
                }
                lineNum++;
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            System.exit(1);
        } catch (NullPointerException npe) {
            System.err.println("One of the tree routines created a " +
                "null pointer exception");
            System.exit(2);
        }
        return result;
    }	


    public static void filterRefs(BSTRefBased tree, String words[]) {
        for (String w : words) {
            tree.delete(w);
        }
    }

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
            String exclude[] = {"a", "the", "i", "it", "is"};
            BSTRefBased result = rawRefs(infileName1);
            filterRefs(result,exclude);   

            //for each WordRefs in the bst, compare each string and count to expected output    
            for (WordRefs wf : result) {
            if(!(wf.toString().equals(infileScanner2.nextLine()))){
                passed  = false;
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