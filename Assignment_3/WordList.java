/**  
 *  Date: 06/30/15
 *  Program Name: WordList.java
 *  Program Description: contians methods to construct a linked list
 * of WordListNodes  
 */
public class WordList{
	
private WordListNode head;

 public WordList(){
 	head = null;
 }

/**
* Inserts node at the head of the list
*/
public void insertHead(String w){
	//create new node to insert 
	WordListNode insert = new WordListNode(w); 
	if(head == null){
		//if list is empty insert at head
		head = insert;
	}else{
		//insert at head
		insert.next = head;
		head = insert;
	}
}

/**
* Removes node from the head of list
*/
public void removeHead(){

   	try{
   		//Remove from head 
       	head = head.next;
    }        
    //Catch block in case the list is empty
    catch(NullPointerException e){
    	//System.out.println("Test passed: LIST EMPTY");   
    }

	}

/**
* Checks specific string is in list
* @return true if string is in list false otherwise 
*/
public boolean contains(String w){

	WordListNode curr = head;
	//search to see if string w matches any in the list
	while(curr != null){

		if(curr.compare(w)){
			return true;
		}
		curr = curr.next;
	}	
		return false;
	}
/**
* Checks if list is empty
* @return true if empty false otherwise
*/
public boolean isEmpty(){
	return (head == null);
}

/**
* Computes length of linked list
* @return length of list
*/
public int getLength(){
		
	int numItems = 0;
	//traverse through list and count
	for(WordListNode curr=head;curr != null;curr=curr.next){
		numItems++;
	}
	return numItems;
	}

/**
* Retrieves string at position index (i.e. index = 0 is first item in list)
* @return string at list position index
*/
public String retrieve(int index){

	WordListNode curr = head;
	int count = 0;
	//if illegal index return null
	if(index < 0 || index >= getLength()){
		return null;
	}
	//if we reach this point legal index has been passed
	while(curr != null){

		//break when correct position found
		if(count == index){
			break;
		}
			curr = curr.next;
			count++;
	}
		return curr.word;
}

public static void main(String[] args) {

	WordList list = new WordList();
	String result; 
	// Test behavior of isEmpty
       if (list.isEmpty()) {
           System.out.println("Test 1 (isEmpty): passed");
       } else {
           System.out.println("Test 1 (isEmpty): FAILED");
       }
    // Test behavior of length
       if (list.getLength() == 0) {
           System.out.println("Test 2 (length): passed");
       } else {
           System.out.println("Test 2 (length): FAILED");
       }

    // Test behavior of length after inserting a single node
       	String one = "Hello";
        list.insertHead(one);
       if (list.getLength() == 1) {
           System.out.println("Test 3 (length): passed");
       } else {
           System.out.println("Test 3 (length): FAILED");
       }

    // Test behavior of retrieve of the single node
         result = list.retrieve(0);
       if (result.equals(one)) {
           System.out.println("Test 4 (retrieve): passed");
       } else {
            System.out.println("Test 4 (retrieve): FAILED");
       }

    // Test behavior when inserting two more nodes
    // to head
       String two = "giraffe";
       String three = "cheetah";
       list.insertHead(two);
       list.insertHead(three);
       if(list.retrieve(0).equals(three) && list.retrieve(1).equals(two) && list.retrieve(2).equals(one)){
		System.out.println("Test 5 (insert): passed");
       }else{
       	System.out.println("Test 5 (insert): FAILED");
       }

   //Test behavior when removing node from head
       list.removeHead();
   	   if(list.retrieve(0).equals(two)){
		System.out.println("Test 6 (removeHead): passed");
   	   }else{
		System.out.println("Test 6 (removeHead): FAILED");
   	   }


  	//Test behavior when retrieving from index < 0
   	   if(list.retrieve(-100) == null){
		System.out.println("Test 7 (retrieve item for index < 0): passed");
   	   }else{
		System.out.println("Test 7 (retrieve item for index < 0): FAILED");
   	   } 

  	//Test behavior when retrieving something not in list
   	   if(list.retrieve(3) == null){
		System.out.println("Test 8 (retrieve item not in list): passed");
   	   }else{
		System.out.println("Test 8 (retrieve item not in list): FAILED");
   	   }   	   
    
}


}