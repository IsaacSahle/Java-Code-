/**
* Student ID: V00816592
* Date: 07/17/15
* Program Name: StringStackRefBased.java 
* Program Description: Referenced base stack Implementation of Strings 
* @author Isaac Sahle
*/
public class StringStackRefBased implements StringStack{
	
static private	StringStackNode top;
	
	public StringStackRefBased(){
		top = null;
	}

    /**
    * Checks if stack is empty 
    * @return true if empty, false otherwise
    */
    public boolean isEmpty(){
    	return(top == null);
  	}

    /**
    * Computes height of stack (i.e the number of items in the stack)  
    * @return number of items in stack
    */
    public int height(){
    StringStackNode curr;
    int numItems = 0;

    for(curr = top; curr != null; curr = curr.next){
        numItems++;
    }
    return numItems;    
    }

    /**
    * Removes item at the top of the stack, if stack is empty,
    * StringStackException is thrown  
    * @return String item at top of the list 
    */
    public String pop() throws StringStackException{
    StringStackNode curr;

    //if list is empty throw exception
    if(isEmpty()){
    	throw new StringStackException("List is Empty");
    }

    //if at this point, at least one item to remove
    curr = top;
    top = top.next;
    return curr.word;

    }

    /**
    * Removes all items from the stack
    */
    public void popAll(){
    	top = null;
    }
 
    /**
    * Places a new item onto the top of the stack
    * @param item the string to be inserted into the list  
    */
    public void push(String item) throws StringStackException{
    StringStackNode insert = new StringStackNode(item);

    //RefBased therefore there will never be a issue pushing an item on the list 
    if(false){
        throw new StringStackException("List is full");
    }
    //insert at the top of the list 
    insert.next = top;
    top = insert;
    }

    /**
    * Just return the value currently at the top of the stack, leaving stack unchanged   
    * @return String item at top of the list 
    */
    public String peek() throws StringStackException{

        if(isEmpty()){
            throw new StringStackException("List is empty");
        }
        return (top.word);
    }

	public static void main(String[] args) {
		
        StringStackRefBased test = new StringStackRefBased();
        String one = "Isaac";
        String two = "Emily";
        String three = "Steve";
        
        //Test behavior after initializing stack
        if(test.isEmpty()){
        System.out.println("Test 1 (isEmpty): passed");
        }else{
        System.out.println("Test 1 (isEmpty): FAILED");
        }

        //Test behavior of height
        if(test.height() == 0){
        System.out.println("Test 2 (height): passed");
        }else{
        System.out.println("Test 2 (height): FAILED");
        }

        //Test behavior popping from empty list
        boolean compare = false;
        try{
        test.pop();
        }
        catch(StringStackException e) {
        compare = true;
        }
        if(compare){
        System.out.println("Test 3 (pop empty list): passed");
        }else{
        System.out.println("Test 3 (pop empty list): FAILED");
        }

        //Test behavior of peek
        compare = false;
        try{
        test.peek();    
        }
        catch(StringStackException e) {
        compare = true;   
        }
        if(compare){
        System.out.println("Test 4 (peek at empty list): passed");
        }else{
        System.out.println("Test 4 (peek at empty list): FAILED");
        }

        //Test behavior after pushing one item on list
        try{
        test.push(one);
        }
        catch(StringStackException e) {
        System.out.println(e);
        }
        if(!(test.isEmpty())){
        System.out.println("Test 5 (push one item): passed");
        }else{
        System.out.println("Test 5 (push one item): FAILED");
        }

        //Test behavior after pushing two more items on list
        try{
        test.push(two);
        test.push(three);
        }
        catch(StringStackException e) {
        System.out.println(e);
        }
        if(test.height() == 3){
        System.out.println("Test 6 (push two more items): passed");
        }else{
        System.out.println("Test 6 (push two more items): FAILED");
        }


        //Test behavior of pop
        compare = true;
        try{
        test.pop();    
        }
        catch(StringStackException e) {
        System.out.println(e);
        compare = false;
        }
        if(compare && test.height() == 2){
        System.out.println("Test 7 (pop item): passed");
        }else{
        System.out.println("Test 7 (pop item): FAILED");
        }

        //Test behavior of peek
        String temp = null;
        try{
        temp = test.peek();    
        }
        catch(StringStackException e) {
        System.out.println(e);
        }
        if(temp.equals("Emily")){
        System.out.println("Test 8 (peek at item): passed");
        }else{
        System.out.println("Test 8 (peek at item): FAILED");
        }
        
        //Test behavior of popAll
        test.popAll();
        if(test.isEmpty()){
        System.out.println("Test 9 (popAll items from list): passed");
        }else{
        System.out.println("Test 9 (popAll items from list): FAILED");
        }

	}


}

