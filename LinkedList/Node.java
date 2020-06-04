package LinkedList_A1;

public class Node {
	public double data;
	public Node next;
	public Node prev;
	
	public Node(double data){
	    this.data=data;
	    this.next=null;
	    this.prev=null;
	}
	
	public String toString(){
	    return "data: "+data+"\thasNext: "+(next!=null)+"\t\thasPrev: "+(prev!=null);
	  }
	
	public boolean isNode(){ //testing purposes please do not touch!
	    return true;
	  }
	
	public double getData(){ //testing purposes please do not touch!
	    return data;
	  }
	
	public Node getNext(){ //testing purposes please do not touch
	    return next;
	  }
	
	public Node getPrev(){ //testing purposes please do not touch
	    return prev;
	  }
}
