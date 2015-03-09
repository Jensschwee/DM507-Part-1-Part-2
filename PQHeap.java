import java.lang.Math;

public class PQHeap implements PQ {
	
	private Element[] heap;
	private int heapsize;

	/**
	 * Creates a new priority queue of a given size
	 */
	public PQHeap(int maxElms) {
		heapsize = 0;
		heap = new Element[maxElms];		
	}	

	/**
	 *	Inserts the Element e into the heap
	 */
	@Override
    public void insert(Element e) {
    	heapsize++;
    	heap[heapsize-1] = e;
    	decreaseKey(heapsize-1);
    }

    /**
	 * 	Removes and returns the Element in the heap
	 * 	with the lowest key.
	 */
	@Override
	public Element extractMin() {
		Element min;

		min = heap[0];
		heap[0] = heap[heapsize-1];
		heapsize--;
		minheapify(0);
		return min;
	}

    /**
     *	Decreases the value of the element at i's key to k.
     */
    private void decreaseKey(int i) {
    	Element swap;    	
    	
    	if (i != 0) {
    		if (heap[parent(i)].key > heap[i].key) {
    			swap = heap[i];
    			heap[i] = heap[parent(i)];
    			heap[parent(i)] = swap;
    			decreaseKey(parent(i));
    		}
    	}
    }

    private void minheapify(int i) {
    	int smallest;    	
    	int left = left(i);
    	int right = right(i);
    	Element swap;

    	if (left <= heapsize && heap[left].key < heap[i].key)
    		smallest = left;
    	else
    		smallest = i;
    	if (right <= heapsize && heap[right].key < heap[smallest].key)
    		smallest = right;
    	if (smallest != i) {
    		swap = heap[i];
    		heap[i] = heap[smallest];
    		heap[smallest] = swap;
    		minheapify(smallest);
    	}

    	
    }
    
    private int parent(int i) { return (int) Math.floor(i/2); }
    private int left(int i) { return i*2; }
    private int right(int i) { return (i*2)+1; }

    @Override
    public String toString() {
    	String out = "Key | Data \n";
    	for(Element e : heap)
    		out += "" + e.key + " | " + e.data + "\n";    		
    	
    	return out;
    }
}