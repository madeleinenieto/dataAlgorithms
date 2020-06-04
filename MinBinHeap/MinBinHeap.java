package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	                                              //be null. This is ok! Just build out 
	                                              //from array[1]

	public MinBinHeap() {
	  this.array = new EntryPair[arraySize];
	  array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
	                                             //of child/parent computations...
	                                             //the book/animation page both do this.
	}
	    
	  //Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
	  return this.array;
	}
	
	private int parent(int i) {
		return (i / 2);
	}
	
	private int left(int i) {
		return (2 * i);
	}
	
	private int right(int i) {
		return (2 * i + 1);
	}
	
	private void up(int i) {
		int p = parent(i);
		
		if (array[i].getPriority() < array[p].getPriority()) {
			EntryPair t = array[p];
			array[p] = array[i];
			array[i] = t;
			
			if (p != 1) {
				up(p);
			}
		}
	}
	
	private void down(int i) {
		int s = left(i);
		
		if (s > 9999) {
			return;
		}
		
		if ((array[right(i)] != null) && (array[right(i)].getPriority() < array[s].getPriority())) {
			s = right(i);
		}
		
		if (array[i].getPriority() > array[s].getPriority()) {
			EntryPair t = array[s];
			array[s] = array[i];
			array[i] = t;
		}
		
		if ((left(s) < 9999) && (array[left(s)] != null)) {
			down(s);
		}
	}
	
	@Override
	public void insert(EntryPair entry) {
		array[size() + 1] = entry;
		up(size() + 1);
		size++;
	}
	
	@Override
	public void delMin() {
		if (array[1] != null) {
			if (array[2] == null ) {
				array[1] = null;
			}
			
			array[1] = array[size()];
			array[size()] = null;
			
			if (array[2] != null) {
				down(1);
			}
			
			size--;
		}
	}
	
	@Override
	public EntryPair getMin() {
		return array[1];
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void build(EntryPair[] entries) {
		int i = 0;
		
		while (i < entries.length && entries[i] != null) {
			array[i + 1] = entries[i];
			size++;
			i++;
		}
		
		for (int x = (size() / 2); x > 0; x--) {
			down(x);
		}
	}
}
