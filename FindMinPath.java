/* Name: Zihao Wang
 * Email: tonywang1997@brandeis.edu
 * Date: December 10 2017
 * PA 3 - This is a class that calculate the shortest path from one place to another in a graph
 */
import java.io.*;
public class FindMinPath {
	/**
	 * This is the main method
	 * @param args
	 * @throws Exception
	 * O(nlogn)
	 */
	public static void main(String[] args) throws Exception{
		MinPriorityQueue queue = new MinPriorityQueue();
		GraphWrapper gw = new GraphWrapper(false);
		GraphNode home=gw.getHome();
		home.priority=0;
		queue.insert(home);
		boolean find=false;
		while(!queue.isEmpty()) {
			GraphNode curr =queue.pullHighestPriorityElement();
			if(curr.isGoalNode()) {
				PrintStream output= new PrintStream("answer.txt");
				print(curr, output);
				find=true;
				return;
			} 
			if(curr.hasEast()) {
				queue = path(queue, curr,curr.getEast(),curr.getEastWeight(),"East");
			}
			if(curr.hasSouth()) {
				queue = path(queue, curr,curr.getSouth(),curr.getSouthWeight(),"South");
			} 
			if(curr.hasWest()) {
				queue = path(queue, curr,curr.getWest(),curr.getWestWeight(),"West");
			} 
			if(curr.hasNorth()) {
				queue = path(queue, curr,curr.getNorth(),curr.getNorthWeight(),"North");
			}
		}
		if(!find) {
			System.out.println("There is no path");
		}

	}
	/**
	 * This method prints out a path from source to certain graphnode 
	 * @param g represents the graphnode
	 * @param output represents the exporting document
	 * @throws FileNotFoundException
	 * O(n)
	 */
	public static void print(GraphNode g, PrintStream output ) throws FileNotFoundException {
		 if(g!=null && g.previousNode!=null) {
			 print(g.previousNode,output);
			 output.println(g.previousDirection);
		 } 
	}
	/**
	 * This method generate a path from one node to another
	 * @param queue represents the current queue
	 * @param curr represents the current graphnode 
	 * @param neighbor represents the graphnode that will go through 
	 * @param weight represents the distance between two nodes
	 * @param direction represents the direction form curr to neighbor
	 * @return the new queue with the neighbor inserted 
	 */
	public static MinPriorityQueue path(MinPriorityQueue queue, GraphNode curr, GraphNode neighbor, int weight,String direction) {
		int x = curr.priority+weight;
		if(queue.hasKey(neighbor)) {
			if (x<neighbor.priority) {
				neighbor.priority=x;
				neighbor.previousNode=curr;
				neighbor.previousDirection=direction;
				queue.rebalance(neighbor);
			}
		} else {
			neighbor.priority=x;
			neighbor.previousNode=curr;
			neighbor.previousDirection=direction;
			queue.insert(neighbor);
		}
		return queue;
	}

}
