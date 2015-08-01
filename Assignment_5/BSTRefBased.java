/**
* Student ID: V00816592
* Date: 07/31/15
* Program Name: BSTRefBased.java 
* Program Description: Referenced based binary search tree implementation of
* WordRefs.java nodes which contain strings 
* @author Isaac Sahle
*/
import java.util.Iterator;

public class BSTRefBased extends AbstractBinaryTree implements Iterable<WordRefs>{
    
    private TreeNode root; 


    public BSTRefBased() {
        root = null;
    }


    public BSTRefBased(WordRefs item, AbstractBinaryTree left, AbstractBinaryTree right){
        root = new TreeNode(item, null, null);
        if (left != null) {
            attachLeftSubtree(left);
        }

        if (right != null) {
            attachRightSubtree(right);
        }
    }

    /**
    * Checks if the tree is empty(i.e. if the root is null)
    * @return true if tree is empty, false otherwise  
    */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
    * Removes all nodes from tree (i.e. sets the root to null)  
    */
    public void makeEmpty() {
        root = null;
    }

    /**
    * Gets node at the root of the tree
    * @return the root node of the tree    
    */
    protected TreeNode getRoot() {
        return root;
    }

    /**
    * Sets the root of the tree to node passed in 
    * @param r TreeNode to be set as root  
    */
    protected void setRoot(TreeNode r) {
        this.root = r;
    }

    /**
    * Gets the item contained in the root node
    * @return Wordrefs item within root node   
    */
    public WordRefs getRootItem() throws TreeException {
        if (root == null) {
            throw new TreeException("getRootItem() on empty tree");
        }

        return root.item;
    }

    /**
    * Sets the root nodes item to the WordRefs item passed in 
    * @param item WordRefs item to be set as the roots item 
    */
    public void setRootItem(WordRefs item) {
        if (root == null) {
            root = new TreeNode(item);
        } else {
            root.item = item;
        }
    }

    /**
    * Creates a new node and adds to the left child of an existing tree.
    * This only occurs if the tree is not empty and the tree does not currently
    * have a left child
    * @param item the item to be added as the left child of existing tree   
    */
    public void attachLeft(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachLeft to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new TreeException("attachLeft to occupied left child");
        }
        //attach left node 
        root.left = new TreeNode(item, null, null);

        return;
    } 

    /**
    * Attaches the passed in tree as the left subtree
    * This only occurs if this tree is not empty and there 
    * is currently no left child 
    * @param left the tree to be added as the left child of existing tree 
    */
    public void attachLeftSubtree(AbstractBinaryTree left) {
        if (isEmpty()) {
            throw new TreeException("attachLeftSubtree to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new 
                TreeException("attachLeftSubtree to occupied right child");
        }
        //attach left subtree
        root.left = left.getRoot();
        left.makeEmpty();

        return;    
    }

    /**
    * Creates a new node and adds to the right child of an existing tree.
    * This only occurs if the tree is not empty and the tree does not currently
    * have a right child
    * @param item the item to be added as the right child of existing tree   
    */
    public void attachRight(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachRight to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new TreeException("attachRight to occupied right child");
        }
        //attach right node
        root.right = new TreeNode(item, null, null);

        return;
    } 

    /**
    * Attaches the passed in tree as the right subtree.
    * This only occurs if this tree is not empty and there is currently no 
    * right child 
    * @param right the tree to be added as the right child of existing tree 
    */
    public void attachRightSubtree(AbstractBinaryTree right) {
        if (isEmpty()) {
            throw new TreeException("attachRightSubtree to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new 
                TreeException("attachRightSubtree to occupied right child");
        }
        //attach right subtree
        root.right = right.getRoot();
        right.makeEmpty();

        return;
    }

    /**
    * If the existing tree is not empty, a temporary tree is created,
    * existing trees left subtree is detached, and stored in temporary
    * tree. The temporary tree is then returned  
    * @return the detached left subtree 
    */
    public AbstractBinaryTree detachLeftSubtree() throws TreeException {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }
        //create and set root of temporary tree to left subtree
        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.left);
        //detach left subtree
        root.left = null;

        return result;
    }

    /**
    * If the existing tree is not empty, a temporary tree is created,
    * existing trees right subtree is detached, and stored in temporary
    * tree. The temporary tree is then returned  
    * @return the detached right subtree 
    */
    public AbstractBinaryTree detachRightSubtree() throws TreeException {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }
        //create and set root of temporary tree to right subtree
        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.right);
        //detach right subtree
        root.right = null;

        return result;
    }

    /**
    * Inserts a word into the tree, following appropriate
    * binary search tree rules (i.e. remaining tree after insertion is a bst)  
    * @param word word to be inserted into the tree 
    */
    public void insert(String word) {
        if(!(isEmpty())){
           root = insertItem(root,word);  
        }else{
            root = new TreeNode(new WordRefs(word));
        }
    }

    /**
    * The method which atually has access to the data and searches
    * for appropriate position for insertion. The insert method calls 
    * insertItem to actually perform insertion.   
    * @param r the current root node 
    * @param word the word to be inserted 
    * @return TreeNode which is new root for the bst  
    */
    protected TreeNode insertItem(TreeNode r, String word) {

        //if item is greater, search right for correct insertion spot
        if(word.compareTo(r.item.getWord()) >= 0){
            
            //if no right node, insertion spot found
            if(r.right == null){
                r.right = new TreeNode(new WordRefs(word));
            }else{
            //search right 
                insertItem(r.right,word);
            }
        }else{
            //if no left node, insertion spot found 
            if(r.left == null){
                r.left = new TreeNode(new WordRefs(word));
            }else{
            //search left    
                insertItem(r.left,word);
            }
        }

        return r; 

    }

    /**
    * Retrieves an item from the binary search tree.
    * Remaining list remains the same  
    * @param word the word to search for 
    * @return WordRefs object containing the desired word, and null if item is not in list   
    */
    public WordRefs retrieve(String word) {
        if(!(isEmpty())){
            TreeNode foundItem = retrieveItem(root,word);
            if(foundItem == null){
                return null;
            }else{
                return foundItem.item;
            }  
        }else{
           return null; 
        }
    }   

    /**
    * The method which atually has access to the data and searches
    * for appropriate item for retrieval. The retrieve method calls 
    * retrieveItem to actually perform retrieval.
    * @param r current root node
    * @param word the word for rerieval   
    * @return TreeNode contianing desired WordRefs object, null if node is not in list 
    */
    protected TreeNode retrieveItem(TreeNode r, String word) {
        TreeNode item;
        //if item is not in list, return null. 
        //otherwise, search left and right accordingly until item is found and return node
        if(r == null){
            item = null;
        }else if(word.compareTo(r.item.getWord()) == 0){
            item = r;
        }else if(word.compareTo(r.item.getWord()) >= 0){
            item = retrieveItem(r.right,word);
        }else{
            item = retrieveItem(r.left,word);
        }
        return item;
    }

    /**
    * Deletes item from the binary search tree. Remaining
    * tree follows the laws of the bst
    * @param word the word to be deleted 
    */
    public void delete(String word) {
        if(!(isEmpty())){
           root = deleteItem(root,word);
        }else{
            return;
        }
    }

    /**
    * The method which atually has access to the data and searches
    * for appropriate item for deletion. The delete method calls 
    * deleteItem to actually perform deletion.
    * @param r current root node
    * @param word the word for deletion   
    * @return TreeNode which is the new root node for the new bst
    */
    protected TreeNode deleteItem(TreeNode r, String word) {
        TreeNode newRoot;
        TreeNode newLeft;
        TreeNode newRight;
        //locate item for deletion accordingly 
        if(r == null){
            return null;
        }else if(word.compareTo(r.item.getWord()) == 0){
             newRoot = deleteNode(r);
             return newRoot;   
        }else if(word.compareTo(r.item.getWord()) >= 0){
            newRight = deleteItem(r.right,word);
            r.right = newRight;
            return r;
        }else{
            newLeft = deleteItem(r.left,word);
            r.left = newLeft;
            return r;
        }
        
    }

    /**
    * Support method called by deleteItem once item for deletion has been located.
    * deleteNode assesses which of the three cases of deletion must occur and reacts
    * accordingly  
    * @param node the node to be deleted    
    * @return TreeNode which is the new root node once node is deleted  
    */
    protected TreeNode deleteNode(TreeNode node) {
       
    TreeNode replacement;
    TreeNode replacementRight;
    //case1: node is a leaf
    // return null 
    if(node.left == null && node.right == null){
        return null;
    //case2: node has one child, left or right
    //return child which is not null    
    }else if(node.left == null || node.right == null){

        if(node.left == null){
            return node.right;
        }else{
            return node.left;
        }
    //case3: node has two children
    //find and delete leftmost node in right child of node, replace nodes item with found item     
    }else{
        replacement = findLeftMost(node.right);
        replacementRight = deleteLeftMost(node.right);
        node.item = replacement.item;
        node.right = replacementRight;
        return node;
    }


    }

    /**
    * Support method for deleteNode which locates the left most node given a starting point(root node) 
    * @param node root node to start searching from   
    * @return TreeNode the left most node from passed in root  
    */
    protected TreeNode findLeftMost(TreeNode node) {
        if(node.left == null){
            return node;
        }else{
            return(findLeftMost(node.left));
        }

    }
    
    /**
    * Support method for deleteNode which locates the left most node given a starting point(root node),
    * and deletes this node.  
    * @param node root node to start searching from   
    * @return TreeNode the replacement root for the deleted node  
    */
    protected TreeNode deleteLeftMost(TreeNode node) {
       TreeNode replacement; 
 
        if(node.left == null){
            return node.right;
        }else{
         replacement = deleteLeftMost(node.left);
         node.left = replacement;
         return node;
        }
    }

    /**
    * Creates a new instance of a iterator to traverse the current instance of 
    * a bst   
    * @return Instance of a BSTIterator   
    */
    public Iterator<WordRefs> iterator() {
        return new BSTIterator(this);
    }


    public static void main(String args[]) {
        BSTRefBased t;
        AbstractBinaryTree tt;
        int i;
        boolean result;
        String message;

        message = "Test 1: inserting 'humpty' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            result = t.getRootItem().getWord().equals("humpty");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 2: inserting 'humpty', 'dumpty', 'sat' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
            result = t.getRootItem().getWord().equals("humpty");
            tt = t.detachLeftSubtree();
            result &= tt.getRootItem().getWord().equals("dumpty");
            tt = t.detachRightSubtree();
            result &= tt.getRootItem().getWord().equals("sat");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 3: retrieving 'humpty' -- ";
        t = new BSTRefBased();
        t.insert("humpty");
        result = t.retrieve("humpty").getWord().equals("humpty");
        System.out.println(message + (result ? "passed" : "FAILED"));


        message = "Test 4: retrieving from leaf node -- ";
        t = new BSTRefBased();
    try{t.insert("humpty");
        t.insert("dumpty");
        t.insert("sat");
        t.insert("on");
        t.delete("on");
        result = t.detachRightSubtree().detachLeftSubtree().getRoot() == null; 
    }catch(Exception e){
        result = false;
    }
        System.out.println(message + (result ? "passed" : "FAILED"));


        message = "Test 5: retrieving form empty list -- ";
        t = new BSTRefBased();
        result = t.retrieve("") == null;
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 6: deleting from empty list -- ";
        t = new BSTRefBased();
        t.delete("");
        result = t.isEmpty();
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 7: deleting 'humpty' -- ";
        t = new BSTRefBased();
        t.insert("humpty");
        t.delete("humpty");
        result = t.isEmpty();
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 8: deleting node with two children -- ";
        t = new BSTRefBased();
    try{t.insert("humpty");
        t.insert("dumpty");
        t.insert("sat");
        t.delete("humpty");
        result = t.getRootItem().getWord().equals("sat") && t.detachLeftSubtree().getRootItem().getWord().equals("dumpty"); 
        }catch(Exception e){
        result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
    }
} 
