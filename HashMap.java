/* Name: Zihao Wang
 * Email: tonywang1997@brandeis.edu
 * Date: December 10 2017
 * PA 3 - This is Hashmap data structure 
 */
public class HashMap {
	private Entry[] map;
	int mapSize;
	int keySize;
	int rehashIndex;
	/**
	 * This is the constructor 
	 * 0(1)
	 */
	public HashMap() {
		this.mapSize=500;
		this.keySize=0;
		map= new Entry[this.mapSize];
		this.rehashIndex=1;
	}
	/**
	 * This method set an entry to the hashmap
	 * @param key represents GraphNode
	 * @param value represents the index to heap
	 * 0(1)
	 */
	public void set(GraphNode key, int value) {
		Entry node = new Entry(key,value);
		int hashValue= hashFuntion(key);
		if(map[hashValue]==null) {
			map[hashValue]=node;
			keySize++;
		} else if(map[hashValue].getKey().getId().equals(key.getId())){
				map[hashValue].setValue(value);
		} 
		if(((double)keySize/(double)mapSize)>0.5) {
			reHashing();//check if it needs rehashing 
			
		}
	}
	/**
	 * This method generates the index for certain key
	 * @param key represents the GraphNode
	 * @return an integer of  key's position in the hashMap
	 * O(1)
	 */
	public int hashFuntion(GraphNode key) {
		int sum =0;
		String ID = key.getId();
		for (int i=0; i<4;i++) {
			char c = ID.charAt(i+9);
			sum=sum+(int)c;
		}
		int hashValue= (sum*rehashIndex)%mapSize;
		boolean probe =true;
		int j= 0;
		while(probe) { 
			if(map[hashValue]==null || map[hashValue].getKey().getId().equals(key.getId())) {
				return hashValue;
			} else {
				hashValue=(hashValue+j*j)%mapSize;
				j++;
				probe=(hashValue<mapSize);
			}
		}
		return hashValue;
	}
	/**
	 * This method double the size of hashmap
	 * O(n)
	 */
	public void reHashing() {
		this.mapSize=2*this.mapSize;
		this.rehashIndex=2*this.rehashIndex;
		Entry[] temp = this.map;
		this.map=new Entry[this.mapSize];
		this.keySize=0;
		for (int i=0;i<temp.length;i++) {
			if (temp[i]!=null) {
				set(temp[i].getKey(),temp[i].getValue());
			}
		}
	}
	/**
	 * This method return the value of graphnode stored in the hashmap
	 * @param g represents the graphNode
	 * @return an integer that stores the position of graphnode in the heap
	 * O(1)
	 */
	public int getValue(GraphNode g) {
		if(hasKey(g)==1) {
			return map[hashFuntion(g)].getValue();
		} else {
			return -1;
		}
	}
	/**
	 * This method check if the hashmap contains a certain graphnode 
	 * @param g represents the graphnode that needs to be searched for
	 * @return a boolean whether it exists
	 * O(1)
	 */
	public int hasKey(GraphNode g) {
		if (map[hashFuntion(g)]==null) {
			return 0;
		} else if(map[hashFuntion(g)].getValue()==-1){
			return -1;
		} else {
			return 1;
		}
	}
	/**
	 * This is a method that returns the size of the hashmap
	 * @return the size of the hashmap
	 * O(1)
	 */
	public int size() {
		return this.mapSize;
	}
	
	
}
