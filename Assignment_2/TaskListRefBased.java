public class TaskListRefBased implements TaskList{
	
	public TaskListNodeAlt head;

    public TaskListRefBased() {
    	this.head = null;
    }

    /** 
     * Computes the number of elements currently in list 
     * @return number greater than or equal to 0 corresponding
     * to number of items in the list 
     */    
    public int getLength() {

      int numItems = 0;

      for(TaskListNodeAlt curr = head; curr != null; curr = curr.next){
      	numItems++;
      }

      return numItems;
    }

    /** 
     * Checks whether or not the list is empty 
     * @return whether or not the list contains tasks
     */
    public boolean isEmpty() {
        return(getLength() == 0);
    }
    
    /**
     * If the list has at least one task, then the task at
     * the head is removed from the list, and this task
     * is returned. If there are no items in the list,
     * then the value returned is null.
     * @return a Task object corresponding to the task at the
     * head of the list; possibly null.
     */
    public Task removeHead() {

        //Task at the head to be returned
        Task result;
        TaskListNodeAlt curr;

        try{
            result = head.task;
            
            curr = head;
            head = curr.next;
            curr.next = null;
            curr = null; 

            return result;
        }

        catch(NullPointerException e){
         System.out.println("ERROR: LIST EMPTY");   
         return null;
        }
      
    }
    
    /**
     * If there are no items in the list, the value of
     * of null is returned.
     *
     * If the list has at least one task, then the list
     * is searched for a task with the same priority and
     * number as t. When found, this task is removed from
     * the list, and t is returned; otherwise the value
     * of null is returned.
     * @param t Task to be removed from the list.
     * @return a Task object corresponding the the task at the
     * head of the list; possibly null.
     */
    public Task remove(Task t) {

        //Set references to beginning of the list
        TaskListNodeAlt curr = head;
        TaskListNodeAlt prev = null; 

        //check if list is empty 
        if(curr == null){
           return null;
        }

        //Loop through till the end of the list: stop if task is found
        while(curr.next != null){

            if(t.priority != curr.task.priority || t.number != curr.task.number){
                prev = curr;
                curr = curr.next;
            }
            else{
                break;
            }
        }

        //Double check loop broke due to the right task being found
        if(t.priority != curr.task.priority || t.number != curr.task.number){
            return null;            
        }

        //If removing a value at the beginning of the list
        if(prev == null){
            head = curr.next;
            return curr.task;
        }
        //If removing from anywhere other than beginning of list
        else{
            prev.next = curr.next;
        }

        return curr.task;
    }

    /** 
     * Accepts a task to be inserted into the list.
     * Inserts the task t in order from highest t.priority
     * nearest to the head. 
     * Also: No two tasks in the list may have the same
     * task number.
     * @param t task to be placed into the task list
     */
    public void insert(Task t) {

        TaskListNodeAlt curr = head;
        TaskListNodeAlt prev = null;
        TaskListNodeAlt insert = new TaskListNodeAlt(t);

        //If the list is empty insert first 
        if(curr == null){
            head = insert;
            return;
        }

        //check if task attempted to insert, has the same number as a task currently in list 
        while(curr != null){

            if(t.number == curr.task.number){
                return;                
            }
            prev = curr;
            curr = curr.next;
        }

        //At this point it is safe to insert the task 
        curr = head;
        prev = null;

        while(curr.next != null){

        if(t.priority <= curr.task.priority){
            prev = curr;
            curr = curr.next;
            }
        else{
            break; 
        }

        }

        if(prev == null && t.priority <= curr.task.priority){
            curr.next = insert;
            return;
        }

        if (prev == null && t.priority > curr.task.priority) {
            insert.next = curr;
            head = insert;
            return; 
        }

        if(curr.next == null && t.priority <= curr.task.priority){
            curr.next = insert;
            return;
        }
        else{
            insert.next = curr;
            prev.next = insert;
        }

    }

    /**
     * Takes an integer value indicate that the ith task
     * in the list is to be returned. When i is 0, the first
     * task is returned, when i is 1, the second task is
     * returned, etc. If there is no such task i, then null
     * is returned. The list is not modified by this operation.
     * @param i indicates the number of task from the start of
     * of the list which will be the task returned
     * @return contents of the ith task in the list; possibly null
     */ 
    public Task retrieve(int i) {
    
    TaskListNodeAlt curr = head;
    int count = 0;

    if(i < 0 || head == null || i >= getLength()){
        return null;
    }

    while(curr!= null){

        if(count != i){
            curr = curr.next;
        }
        else{
            break;
        }
        count++;
    }
    return curr.task;
    }

}