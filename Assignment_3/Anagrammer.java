/**  
 *  Date: 06/30/15
 *  Program Name: Anagrammer.java
 *  Program Description: Anagrammer class contains methods to compute and print to
 * console all possible anagrams from inputed dictionary phrase 
 * and maximum number of words in anagram
 */
public class Anagrammer{
	
private WordList anagramWords;	
private String [] dictionary;
private AlphabetStats phraseO;
private int count;
private int maxWords; 

/* Constructor initializes all instance variables and 
 * discards all words in dictionary that are not possible 
 * anagrams of the phase passed in
 */
public Anagrammer(String dictionary[], String phrase, int maxWords){

	this.count = 0;
	this.maxWords = maxWords;
	this.phraseO = new AlphabetStats(phrase);
	this.anagramWords = new WordList();

	//Indicate how many possible anagrams in the dictionary 
	int size = 0;
	AlphabetStats temp1;
	for(int x = 0; x < dictionary.length; x++){

		temp1 = new AlphabetStats(dictionary[x]);

		if(phraseO.contains(temp1)){
			size++;
		}
	}

	//Create an appropriate size array and insert anagram possibilities
	this.dictionary = new String [size];
	AlphabetStats temp2; 
	int index = 0;	

	for(int x = 0; x < dictionary.length; x++){
		
		temp2 = new AlphabetStats(dictionary[x]);
		
		if(phraseO.contains(temp2)){
			this.dictionary[index] = dictionary[x];
			index++; 
		}

	}

}

/** 
 * Calls findAnagram algorith to generate 
 * anagrams and display them as well as the 
 * the number of anagrams generated  
 */
public void generate(){
	findAnagram(anagramWords,phraseO);
}

/** 
 * Recursive backtracking algorithm which 
 * finds all the possible anagrams and prints
 * each out to console
 * @param words a linked list that temporarily stores current anagram to be printed
 * @param phraseStats the phrase that anagrams will be made from
 */ 
private void findAnagram(WordList words, AlphabetStats phraseStats){

	String [] originalDictionary;
	String dummy = "";
	AlphabetStats test = new AlphabetStats(dummy);	

	//first base case:if the phrase is empty(i.e. anagram was found) print
	//to console and return
	if(phraseStats.isEmpty()){		
		for(int i = 0; i < words.getLength(); i++){
			System.out.print(words.retrieve(i) + " ");
		}
		count++;
		System.out.println();
		return;
	}
	//Second base case:if subtracting the next phrase results in a negative
	//number, return
	if(!(phraseStats.subtract(test))){ 
		return;
	}
	//Third base case: if the words in the anagram exceeds the max words allowed
	if(words.getLength() >= maxWords){
		return;
	}else{
		
		AlphabetStats temp;
		for(int i = 0; i < dictionary.length; i++){
			//create AlphabetStats object to subtract from our phrase
			temp = new AlphabetStats(dictionary[i]);
			phraseStats.subtract(temp);
			//insert word subtracted into linked list 		
			words.insertHead(dictionary[i]);
			//recursive call to findAnagram
			findAnagram(words,phraseStats);
			//once we returned from recursive call add temp back to phrase and 
			//remove word from linked list 
			phraseStats.add(temp);
			words.removeHead();
		}
	}
}

public static void main(String[] args) {
	
	String [] dictionary = new String[7];
	dictionary[0] = "i";
	dictionary[1] = "s";
	dictionary[2] = "aa";
	dictionary[3] = "ac";
	dictionary[4] = "c";
	dictionary[5] = "sa";
	dictionary[6] = "iauishaifa";
	String phrase = "isaac";
	int maxWords = 3;

	Anagrammer test = new Anagrammer(dictionary,phrase,maxWords);

	//Test if constructor properyly discards words in dictionary that are not possibilities of anagrams
       if (test.dictionary.length == 6) {
           System.out.println("Test 1 (dictionary refining): passed");
       } else {
           System.out.println("Test 1 (dictionary refining): FAILED");
       }
    //Test if linked list is initialized properly   
       if (test.anagramWords.getLength() == 0) {
           System.out.println("Test 2 (linked list initialized): passed");
       } else {
           System.out.println("Test 2 (linked list initialized): FAILED");
       }

   //Test if instance variable phraseO has the correct letter frequency of the original phrase    
       AlphabetStats p = new AlphabetStats("isaac");
       if (test.phraseO.contains(p)){
           System.out.println("Test 3 (phrase object initialized): passed");
       } else {
           System.out.println("Test 3 (phrase object initialized): FAILED");
       }
       
	//Test if generate computes all anagrams and prints n! ways
       	System.out.println();
       	test.generate();
       	System.out.println();
       if (test.count == 6) {
           System.out.println("Test 4 (computing possible anagrams): passed");
       } else {
           System.out.println("Test 4 (computing possible anagrams): FAILED");
       }
    //Test if changing maxWord creates different anagrams
       int m = 2;
        Anagrammer test1 = new Anagrammer(dictionary,phrase,m);
        System.out.println();
       	test1.generate();
       	System.out.println();
       if (test1.count == 0) {
           System.out.println("Test 5 (computing possible anagrams with different maxWords): passed");
       } else {
       		System.out.println(test.count);
           System.out.println("Test 5 (computing possible anagrams with different maxWords): FAILED");
	   }
	//Test generate anagrams on a empty dictionary  
       String [] dictionary2 = new String [3];
       for(int i = 0; i<dictionary2.length;i++){
       	dictionary2[i] = "";
       }
       Anagrammer test2 = new Anagrammer(dictionary2,phrase,maxWords);
       test2.generate();
       if (test2.count == 0) {
           System.out.println("Test 6 (computing anagram on empty dictionary): passed");
       } else {
           System.out.println("Test 6 (computing anagram on empty dictionary): FAILED");
       }

	//Test generate on dictionary with no possible anagrams 
	   String [] dictionary3 = new String [3];
	   dictionary3[0] = "ahjdsga";
	   dictionary3[1] = "ajhksah";
	   dictionary3[2] = "laflafhl";
       Anagrammer test3 = new Anagrammer(dictionary3,phrase,maxWords);
       test3.generate();
       if (test3.count == 0) {
           System.out.println("Test 7 (computing anagram on dictionary with no possible anagrams): passed");
       } else {
           System.out.println("Test 7 (computing anagram on dictionary with no possible anagrams): FAILED");
       }       

	//Test generate on dictionary with all the same words
	   String [] dictionary4 = new String [3];
	   dictionary4[0] = "sac";
	   dictionary4[1] = "sac";
	   dictionary4[2] = "sac";
       Anagrammer test4 = new Anagrammer(dictionary4,phrase,maxWords);
	 
       if (test4.count == 0) {
           System.out.println("Test 8 (computing anagram on dictionary with same possible anagrams): passed");
       } else {
           System.out.println("Test 8 (computing anagram on dictionary with same possible anagrams): FAILED");
       }  	

}

}