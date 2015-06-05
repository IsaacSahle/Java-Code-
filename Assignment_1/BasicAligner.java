/*  
 *  Date: 05/28/15
 *  Program Name: BasicAligner.java
 *  Program Description: BasicAligner.java implements the aliger interface,
 *   and provides methods which assist in finding best character match up for two strings.
 *   It also contains a main method with tests on private method matchAt  
 */

import java.util.*;

public class BasicAligner implements Aligner{
	   
    private String [] sequence;
    private char [][] asCharacters;
    private int numErrors;
    private int alignmentIndex;

    /**
     *  Initializes sequence array, numErrors, and alignmentIndex
     * creates and stores values from sequence, in a two dimensional char array
     *  @param sequence array carries the strings for comparison 
     */
    public BasicAligner(String[] sequence) {

      this.sequence = sequence;    
      asCharacters = new char[sequence.length][];
      
      for(int x = 0; x < sequence.length; x++){
        asCharacters[x] = sequence[x].toCharArray();
      } 

      numErrors = -1;
      alignmentIndex = -1;

    }

    /**  
     *returns number of mismatched characters in the comparison of the strings
     *  @param firstI, secondI contain index values for cooresponding strings 
     *  
     *  @return the number of mismatched characters  
     */

    private int matchAt(int firstI, int secondI){
        
      numErrors = 0; 
      
      //Loop through and compare second string characters to the first    
      for(int i = 0; i < sequence[1].length();i++){

        //if characters dont match up increase number of errors
        if(asCharacters[0][i + firstI] != asCharacters[1][i]){
            numErrors++;
        }

        }

    return numErrors;

    }        

    /**
     *  Compares strings while keeping track of the match greatest index with the
     * least amount of errors, the smallest number of errors, and returns the index      
     */
    public void performAlignment() { 

      int errors = asCharacters[1].length;
      alignmentIndex = 0;

      //Loop and complete multiple comparisons; store smallest number or errors and the corresponding index
      //If there is the same number of errors the rightmost index will be stored   
      for(int y = 0; y <= (asCharacters[0].length)-(asCharacters[1].length); y++){

        if(matchAt(y,0) <= errors){
          errors = matchAt(y,0);
            
            if(y > alignmentIndex){
              alignmentIndex = y;
            }
        }

      }
       numErrors = errors;
    }
    
    /** 
     * Returns the index of offset based on the index value of 0 for first string
     * or 1 for the second string  
     * @param sequenceNumber index of the sequence string for
     *        which the offset is requested
     * @return number of characters from the start of the alignment
     *         at which the sequence string lines up       
     */
    public int getOffset(int sequenceNumber) {

      int temp = -1;

      //Checks to see that the alignment has been performed, and an index was set 
      if(alignmentIndex == -1){
        System.out.println("Alignment has not been performed: No index set");
        return -1;            
      }

      //Try block present to make sure an illegal index not passed in (anything other than 0 or 1) 
      try{
  
        if(sequence[sequenceNumber].equals(sequence[0])){
          temp = 0;
        }
        else{
          temp = alignmentIndex;
        }

        }
        //catch block with custom message 
        catch(ArrayIndexOutOfBoundsException e){
        System.out.println("INDEX OUT OF BOUNDS");    
        }      

        return temp;
    }

    /**
     *  Returns the string corresponding to the index passed in to the sequence array 
     *  @param sequenceNumber takes values of 0 for the first string or 1 for the second   
     *
     *  @return a string according to which index was passed in       
     */
    public String getSequence(int sequenceNumber) {
      String temp = "";

      //Try block present to make sure an illegal index not passed in (anything other than 0 or 1)      
      try{
        temp = sequence[sequenceNumber];
         }
        
        //catch block with custom message 
        catch(ArrayIndexOutOfBoundsException e){
        System.out.println("INDEX OUT OF BOUNDS");    
        }  
        return temp;         
    }

    /**
     *  returns the number of errors for best alignment 
     *  @return the number of errors for the best alignment between the strings
     */
    public int getNumErrors() {
      
      //Checks if alignment has been performed and number of errors are set, else return -1  
      if(numErrors == -1){
        System.out.println("Alignment has not been performed: number of errors has  not been set");
        return numErrors;            
      }
      else{
        return numErrors;
      } 
    }

    public static void main(String [] args){

      String [] stringTest = new String [2];
      
      stringTest [0] = "RTHFDTGRFTRFF";
      stringTest [1] = "RFF";
      
      //Instantiate BasicAligner for testing
      BasicAligner test1  = new BasicAligner(stringTest);
      
      //Test matchAt method  
      if(test1.matchAt(0,0) == 2){
          System.out.println("Test 1 part 1 (matchAt): passed");  
      } else{
          System.out.println("Test 1 part 1 (matchAt): FAILED");      
      }

      if(test1.matchAt(3,0) == 3){
          System.out.println("Test 1 part 2 (matchAt): passed");  
      } else{
          System.out.println("Test 1 part 2 (matchAt): FAILED");      
      }

      if(test1.matchAt(10,0) == 0){
          System.out.println("Test 1 part 3 (matchAt): passed");  
      } else{
          System.out.println("Test 1 part 3 (matchAt): FAILED");      
      }
      
    }

}