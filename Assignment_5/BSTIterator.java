/**
 * Student ID: V00816592
 * Date: 07/31/15
 * Program Name: BSTIterator.java 
 * Program Description: Iterator used to traverse a BSTRefBased.java binary search tree 
 * @author Mike Zastre, UVic
 * @author Isaac Sahle, UVic
 * Part of CSC 115, Summer 2015, Assignment #5
 */
import java.util.LinkedList;

public class BSTIterator implements java.util.Iterator<WordRefs> {
    private BSTRefBased t;
    private WordRefs currentItem;
    private LinkedList<WordRefs> list;

    public BSTIterator(BSTRefBased t) {
        this.t = t;
        currentItem = null;
        list = new LinkedList<>();
        setInorder();
    }

    /**
    * Computes if list is not empty
    * @return true if the list is not empty, false otherwise   
    */
    public boolean hasNext() {
        return !(list.isEmpty());
    }
    /**
    * Retrieves next item in list, exception thrown if there is no item to retrieve   
    * @return Wordrefs item from list    
    */
    public WordRefs next() throws java.util.NoSuchElementException {
       return (list.remove());
    }
    /**
    * Throws exception, removal not allowed for this implementation       
    */
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    /**
    * Clears list and sets list items to the preorder traversal       
    */
    public void setPreorder() {
        list.clear();
        preorder(t.getRoot());
    }
    /**
    * Clears list and sets list items to the inorder traversal       
    */
    public void setInorder() {
        list.clear();
        inorder(t.getRoot());
    }
    /**
    * Clears list and sets list items to the postorder traversal       
    */
    public void setPostorder() {
        list.clear();
        postorder(t.getRoot());
    }
    /**
    * Preorder method has the actual access to data and 
    * adds items to list in preorder.
    * @param node the node which is the starting point of traversal       
    */
    private void preorder(TreeNode node) {

        if(node == null){
            return;
        }else{
           list.add(node.item);
           preorder(node.left);
           preorder(node.right);
        }

    }
    /**
    * Inorder method has the actual access to data and 
    * adds items to list inorder.
    * @param node the node which is the starting point of traversal       
    */
    private void inorder(TreeNode node) {
        
        if(node == null){
            return;
        }else{
           inorder(node.left);
           list.add(node.item);
           inorder(node.right);
        }

    }
    /**
    * Postorder method has the actual access to data and 
    * adds items to list in postorder.
    * @param node the node which is the starting point of traversal       
    */
    private void postorder(TreeNode node) {
    
        if(node == null){
            return;
        }else{
           postorder(node.left);
           postorder(node.right);
           list.add(node.item);        
        }

    }

public static void main(String[] args) {

        BSTRefBased t;
        BSTIterator it;
        boolean result;
        String message;

        message = "Test 1: set Inorder  -- ";
        t = new BSTRefBased();
        t.insert("humpty");
        t.insert("dumpty");
        it = new BSTIterator(t);
        result = it.next().getWord().equals("dumpty") && it.next().getWord().equals("humpty");
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 2: set Preorder  -- ";
        it.setPreorder();
        result = it.next().getWord().equals("humpty") && it.next().getWord().equals("dumpty");
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 3: set Postorder  -- ";
        t = new BSTRefBased();
        t.insert("humpty");
        t.insert("dumpty");
        t.insert("sat");
        it = new BSTIterator(t);
        it.setPostorder();
        result = it.next().getWord().equals("dumpty") && it.next().getWord().equals("sat") && it.next().getWord().equals("humpty");
        System.out.println(message + (result ? "passed" : "FAILED"));
    
}

}
