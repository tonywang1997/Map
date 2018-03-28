/* Name: Zihao Wang
 * Email: tonywang1997@brandeis.edu
 * Date: December 10 2017
 * PA 3 - This is a entry class that stores GraphNode and its value
 */
public class Entry {
	private GraphNode key;
	private int value;
	/**
	 * This is a constructor
	 * @param key represents GraphNode
	 * @param value represents value
	 * O(1)
	 */
	public Entry(GraphNode key, int value) {
		this.key=key;
		this.value=value;
	}
	/**
	 * This method returns the key
	 * @return the GraphNode stored in Entry
	 * O(1)
	 */
	public GraphNode getKey() {
		return this.key;
	}
	/**
	 * This method return the value 
	 * @return the value stored in Entry
	 * O(1)
	 */
	public int getValue() {
		return this.value;
	}
	/**
	 * This method set the key of the Entry
	 * @param key represents the GraphNode
	 * O(1)
	 */
	public void setKey(GraphNode key) {
		this.key=key;
	}
	/**
	 * This method set the value of the  
	 * @param value represents the value of the Entry
	 * O(1)
	 */
	public void setValue(int value) {
		this.value=value;
	}
}
