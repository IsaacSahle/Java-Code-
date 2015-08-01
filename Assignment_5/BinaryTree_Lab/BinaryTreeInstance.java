public class BinaryTreeInstance{

static TreeNode root;


public BinaryTreeInstance(){
	root = null;
}
public BinaryTreeInstance(TreeNode root){
	this.root = root;
}


public int countNode(TreeNode tree){

int count = 1;

if(tree.lChild != null){
	count += countNode(tree.lChild);
}
if(tree.rChild != null){
	count += countNode(tree.rChild);
}

return count;

}

public int countLeafNode(TreeNode tree){

if(tree == null){
	return 0;
}
if(tree.lChild == null && tree.rChild == null){
	return 1;
}else{
	return(countLeafNode(tree.lChild) + countLeafNode(tree.rChild));
}

}

public static void add(int data){

	if(root != null){
		root.add(data);
	}else{
		root = new TreeNode(data);
	}

}


public static void main(String[] args) {

TreeNode root = new TreeNode(6);
TreeNode c1 = new TreeNode(5);	
TreeNode c2 = new TreeNode(4);

root.setLeft(c1);
root.setRight(c2);

TreeNode c3 = new TreeNode(3);
TreeNode c4 = new TreeNode(2);
TreeNode c5 = new TreeNode(1);

c1.setLeft(c3);
c1.setRight(c4);
c3.setLeft(c5);

BinaryTreeInstance test = new BinaryTreeInstance(root);

test.add(0);

System.out.println(test.countNode(root));

BTreePrinter cool = new BTreePrinter();

cool.printNode(root);

}




}