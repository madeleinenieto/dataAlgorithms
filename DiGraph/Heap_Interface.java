package DiGraph_A5;

public interface Heap_Interface {
	// ADT operations
	  void insert(EntryPair entry);
	  void delMin();
	  EntryPair getMin();
	  int size();
	  void build(EntryPair [] entries);
	  EntryPair[] getHeap();
}
