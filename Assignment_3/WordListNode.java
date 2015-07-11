/**
 *  Date: 06/30/15
 *  Program Name: WordListNode.java
 *  Program Description: nodes created to implement
 * 	WordList linked list 
 */
public class WordListNode{
	
String word;
WordListNode next;

//constructor sets word
public WordListNode(String word){
	this.word = word;
}

/**
* Compares two strings contained in WordListNode objects
* @param compare a string to compare 
* @return true if strings are the same false otherwise
*/
public boolean compare(String compare){

	if((this.word).equals(compare)){
		return true;
	}else {
		return false;
	}
}

}