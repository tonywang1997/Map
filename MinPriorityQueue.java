/* Name: Zihao Wang
 * Email: tonywang1997@brandeis.edu
 * Date: December 10 2017
 * PA 3 - This is a prioirty queue data structure implemented by heap
 */
public class MinPriorityQueue {
	GraphNode[] heap;
	HashMap indexMap;
	int heapSize;
	/**
	 * This is the constructor 
	 * O(1)
	 */
	public MinPriorityQueue(){
		indexMap= new HashMap();
		heap= new GraphNode[indexMap.size()];
		heapSize=0;
	}
	/**
	 * This is method that returns the parent of an element in the heap
	 * @param index represents the position of the element
	 * @return the index of its parent 
	 * O(1)
	 */
	public int parent(int index) {
		return (index-1)/2;
	}
	/**
	 * This is method that returns the leftChild of an element in the heap
	 * @param index represents the position of the element
	 * @return the index of its leftChild 
	 * O(1)
	 */
	public int leftChild(int index) {
		return 2*index+1;
	}
	/**
	 * This is method that returns the rightChild of an element in the heap
	 * @param index represents the position of the element
	 * @return the index of its rightChild
	 * O(1)
	 */
	public int rightChild(int index) {
		return 2*index+2;
	}
	/**
	 * This is a method that either heapify up or down an element 
	 * @param g represents the graph node that needs heapify
	 * O(logn)
	 */
	public void heapify(GraphNode g) {
		int index= indexMap.getValue(g);
		//decide whether it needs heapify up or down
		if(heap[index].priority<heap[parent(index)].priority) {
			heapifyUp(g,index);
		} else {
			heapifyDown(g,index);
		}
	}
	/**
	 * This is a method that heapify up an element in the heap 
	 * @param g represents the graph node that needs heapify
	 * @param index represents the position of the element in the heap
	 * O(logn)
	 */
	public void heapifyUp(GraphNode g,int index) {
		while(index>0 && heap[index].priority<heap[parent(index)].priority) {
			indexMap.set(g, parent(index));
			indexMap.set(heap[parent(index)], index);
			GraphNode temp = heap[index];
			heap[index]=heap[parent(index)];
			heap[parent(index)]=temp;
			index=parent(index);
		}
	}
	/**
	 * This is a method that heapify down an element in the heap 
	 * @param g represents the graph node that needs heapify
	 * @param index represents the position of the element in the heap
	 * O(logn)
	 */
	public void heapifyDown(GraphNode g,int index) {
		int left=leftChild(index);
		int right = rightChild(index);
		int smallest;
		if (left<heapSize && heap[left].priority<heap[index].priority) {
			smallest=left;
		} else {
			smallest=index;
		}
		if (right<heapSize&&heap[right].priority<heap[smallest].priority) {
			smallest=right;
		}
		if (smallest!=index) {
			indexMap.set(g, smallest);
			indexMap.set(heap[smallest], index);
			GraphNode temp=heap[index];
			heap[index]=heap[smallest];
			heap[smallest]=temp;
			heapifyDown(g,smallest);
		}
	}
	/**
	 * This is a method that inserts a graph node in the heap
	 * @param g represents the graph node
	 * O(logn)
	 */
	public void insert(GraphNode g) {
		heap[heapSize]=g;
		indexMap.set(g, heapSize);
		heapSize++;
		rebalance(g);
	}
	/**
	 * This is a method that deletes an element in a heap
	 * O(logn)
	 */
	public void deleteMin(){
		if(heapSize==1) {
			heap[0]=null;
			heapSize--;
		} else {
			indexMap.set(heap[0], -1);
			heap[0]=heap[heapSize-1];
			heap[heapSize-1]=null;
			indexMap.set(heap[0], 0);
			heapSize--;
			rebalance(heap[0]);
		}
	}
	/**
	 * This is a method that rebalance the whole heap with a graphnode
	 * @param g represents the node that needs to be rebalanced 
	 * O(logn)
	 */
	public void rebalance(GraphNode g) {
		heapify(g);
	}
	/**
	 * This method delete and return the highest priority in the heap
	 * @return the highest priority element in the heap
	 * @throws Exception when there is no element in the heap
	 * 0(logn)
	 */
	public GraphNode pullHighestPriorityElement() throws Exception{
		if(isEmpty()) {
			throw new Exception("Unable to pull");
		} else {
			GraphNode delete = heap[0];
			deleteMin();
			return delete;
		}
	}
	/**
	 * This method check if the heap is empty
	 * @return a boolean whether this heap is empty
	 * O(1)
	 */
	public boolean isEmpty() {
		return heapSize==0;
	}
	/**
	 * This method check if the heap contains certain graph node
	 * @param g represents the graphnode
	 * @return a boolean whether a graphnode exists in the heap
	 * O(1)
	 */
	public boolean hasKey(GraphNode g) {
		if(indexMap.hasKey(g)==-1||indexMap.hasKey(g)==1) {
			return true;
		} else {
			return false;
		}
	}
}
