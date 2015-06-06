/* 
* Node for Linked List, containing 
* a task object and a next reference
*/
class TaskListNodeAlt {
    Task task;
    TaskListNodeAlt next;

    TaskListNodeAlt(Task task) {
        this.task = task;
        this.next = null;
    }    
}
