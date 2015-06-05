/*  
 *  Date: 05/28/15
 *  Program Name: TestBasicAligner.java
 *  Program Description: TestBasicAligner.java conatins multiple tests on all methods (except private method matchAt)
 *   in an instantiated BasicAligner object. 
 */

import java.util.*;

public class TestBasicAligner{
	
public static void main(String [] args){

	String [] stringTest = new String [2];

	stringTest [0] = "ABCDBDADD";
	stringTest [1] = "DAC";

  //Instantiate BasicAligner object
	BasicAligner test2 = new BasicAligner(stringTest);

  //perform alignment to test remaining public methods
  test2.performAlignment();
  
  //Test getSequence method
  test2.getSequence(0); 
  if (test2.getSequence(0).equals(stringTest[0])) {
      System.out.println("Test 2 (getSequence): passed");
  } else {
      System.out.println("Test 2 (getSequence): FAILED");
  }

  //Test getOffset method      
  if (test2.getOffset(1) == 5) {
      System.out.println("Test 3 (getOffset): passed");
  } else {
      System.out.println("Test 3 (getOffset): FAILED");
  }
  //Test getNumErrors method
  if (test2.getNumErrors() == 1) {
      System.out.println("Test 4 (getNumErrors): passed");
  } else {
      System.out.println("Test 4 (getNumErrors): FAILED");
  }
}

}