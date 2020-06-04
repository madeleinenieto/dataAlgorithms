package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;
	
	public BST(){ size=0; root=null; }
	
	@Override
	//used for testing, please leave as is
	public BST_Node getRoot(){ return root; }

	@Override
	public boolean insert(String s) {
		if (root != null) {
			root.insertNode(s);
			size++;
			return true;
		} else if (root == null) {
			root = new BST_Node(s);
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(String s) {
		if (this.empty()) {
			return false;
		}
		
		if (s.equals(root.data)) {
			BST_Node hold = new BST_Node(s);
			hold.left = root;
			boolean out = root.removeNode(s, hold);
			root = hold.left;
			size--;
			return out;
		}
		
		if (root.removeNode(s,  null)) {
			size--;
		}
		
		return root.removeNode(s,  null);
	}

	@Override
	public String findMin() {
		if (root == null) {
			return null;
		} else {
			return root.findMin().getData();
		}
	}

	@Override
	public String findMax() {
		if (root == null) {
			return null;
		} else {
			return root.findMax().getData();
		}
	}

	@Override
	public boolean empty() {
		if ((size == 0) && (root == null)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean contains(String s) {
		if (root == null) {
			return false;
		} else {
			return root.containsNode(s);
		}
	}

	@Override
	public int size() {
		if (root == null) {
			return 0;
		}
		return size;
	}

	@Override
	public int height() {
		if (root == null) {
			return -1;
		} else {
			return root.getHeight() - 1;
		}
	}
}
