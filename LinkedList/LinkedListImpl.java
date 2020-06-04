package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel;
	int count;
	Node curr;
	Node new_node;
	
	public LinkedListImpl() {
		sentinel = new Node(0);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}
	
	public Node getRoot() {
	    return sentinel;
	  }
	
	public boolean insert(double elt, int index) {
		curr = sentinel;
		
		if ((index < 0) || (index > size())) {
	    	return false;
	    }
	    
	    for (int i = 0; i <= size(); i++) {
	    	curr = curr.next;
	    	
	    	if (i == index) {
	    		new_node = new Node(elt);
	    		curr.prev.next = new_node;
	    		new_node.prev = curr.prev;
	    		curr.prev = new_node;
	    		new_node.next = curr;
	    	}
	    }
	    count++;
	    return true;
	}
	
	public boolean remove(int index) {
		if ((index < 0) || (index > size() - 1)) {
	    	return false;
	    }
		
		get(index);
		curr.next.prev = curr.prev;
		curr.prev.next = curr.next;
		count--;
		return true;
	}
	
	public double get(int index) {
		curr = sentinel;
		
		if ((index < 0) || (index > size() - 1)) {
	    	return Double.NaN;
	    }
		
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		
		return curr.getData();
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		count = 0;
	}
}
