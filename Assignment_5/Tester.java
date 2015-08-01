public class Tester{
	
public static void traversal(TreeNode root){

	if(root != null){
	System.out.println(root.item.getWord());
	traversal(root.left);
	traversal(root.right);
	}

}

public static void main(String[] args) {
	

}






}