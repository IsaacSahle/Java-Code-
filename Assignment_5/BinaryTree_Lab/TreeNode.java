public class TreeNode{
	
int data;
TreeNode lChild;
TreeNode rChild;

public TreeNode(int data){
	this.data = data;
	lChild = null;
	rChild = null;
}

public void setLeft(TreeNode set){
this.lChild = set;
}

public void setRight(TreeNode set){
this.rChild = set;
}

public void add(int insertData){

	if(insertData <= data){

	if(lChild == null){
		lChild = new TreeNode(insertData);
	}else{
		lChild.add(insertData);
	}

	}else{

		if(rChild == null){
			rChild = new TreeNode(insertData);
		}else{
			rChild.add(insertData);
		}
	}

}

}