package BST_A2;

public class BST_Node {
	  String data;
	  BST_Node left;
	  BST_Node right;
	  BST_Node parent;
	  
	  BST_Node(String data){ this.data=data; }

	  // --- used for testing  ----------------------------------------------
	  //
	  // leave these 3 methods in, as is

	  public String getData(){ return data; }
	  public BST_Node getLeft(){ return left; }
	  public BST_Node getRight(){ return right; }

	  // --- end used for testing -------------------------------------------

	  
	  // --- fill in these methods ------------------------------------------
	  //
	  // at the moment, they are stubs returning false 
	  // or some appropriate "fake" value
	  //
	  // you make them work properly
	  // add the meat of correct implementation logic to them

	  // you MAY change the signatures if you wish...
	  // make the take more or different parameters
	  // have them return different types
	  //
	  // you may use recursive or iterative implementations

	  
	  
	  
	  public boolean containsNode(String s) {
		  if (s.compareTo(data) < 0) {
			  if (left != null) {
				  return left.containsNode(s);
			  }
			  return false;
		  }
		  
		  if (s.compareTo(data) > 0) {
			  if (right != null) {
				  return right.containsNode(s);
			  }
			  return false;
		  }
		  
		  if (s.compareTo(data) == 0) {
			  return true;
		  } else {
			  return false;
		  }
	  }
	  
	  
	  public boolean insertNode(String s) {
		  if ((s.compareTo(data) < 0) && (getLeft() == null)) {
			  left = new BST_Node(s);
			  left.parent = this;
		  } else if ((s.compareTo(data) > 0) && (getRight() == null)) {
			  right = new BST_Node(s);
			  right.parent = this;
		  } else if (s.compareTo(data) < 0) {
			  left.insertNode(s);
		  } else if (s.compareTo(data) > 0) {
			  right.insertNode(s);
		  }
		  
		  return false;
	  }
	  
	  public boolean removeNode(String s, BST_Node parent) {
		  if (s.compareTo(data) > 0) {
			  if (right != null) {
				  return right.removeNode(s, this);
			  } else {
				  return false;
			  }
		  } else if (s.compareTo(data) < 0) {
			  if (left != null) {
				  return left.removeNode(s, this);
			  } else {
				  return false;
			  }
		  } else {
			  if ((left != null) && (right != null)) {
				  this.data = right.findMin().data;
				  right.removeNode(this.data, this);
			  } else if (parent.left == this) {
				  if (left == null) {
					  parent.left = right;
					  return true;
				  } else {
					  parent.left = left;
					  return true;
				  }
			  } else if (parent.right == this) {
				  if (left == null) {
					  parent.right = right;
					  return true;
				  } else {
					  parent.right = left;
					  return true;
				  }
			  }
		  }
		  
		  return true;
	  }
	  
	  public BST_Node findMin() {
		  if (getLeft() != null) {
			  return getLeft().findMin();
		  } else {
			  return this;
		  }
	  }
	  
	  public BST_Node findMax() {
		  if (getRight() != null) {
			  return getRight().findMax();
		  } else {
			  return this;
		  }
	  }
	  
	  public int getHeight() {
		  if ((this.getLeft() == null) && (this.getRight() == null)) {
			  return 1;
		  }
		  
		  if ((this.getLeft() == null) && (this.getRight() != null)) {
			  return this.getRight().getHeight() + 1;
		  }
		  
		  if ((this.getLeft() != null) && (this.getRight() == null)) {
			  return this.getLeft().getHeight() + 1;
		  }
		  
		  if (this.getRight().getHeight() >= this.getLeft().getHeight()) {
			  return this.getRight().getHeight() + 1;
		  } else {
			  return this.getLeft().getHeight() + 1;
		  }
	  }
	  
	  
	  

	  // --- end fill in these methods --------------------------------------


	  // --------------------------------------------------------------------
	  // you may add any other methods you want to get the job done
	  // --------------------------------------------------------------------
	  
	  public String toString(){
	    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
	            +",Right: "+((this.right!=null)?right.data:"null");
	  }
}
