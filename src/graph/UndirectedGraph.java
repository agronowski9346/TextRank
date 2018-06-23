package graph;

import java.util.HashMap;
import java.util.LinkedList;

/*
 * Class represents a generic undirected graph using an adjacency matrix
 * 
 */
public class UndirectedGraph {
	protected int[][] adjacencyMatrix;
	protected HashMap<Vertex, Integer> vertices = new HashMap<Vertex, Integer>();;
	
	//adds an edge between two vertices
	public void addEdge(int to, int from) {
		adjacencyMatrix[to][from] = 1;
		adjacencyMatrix[from][to] = 1;
	}
	
	//Performs a BreadthFirstSearch on the graph starting from a position in the matrix
	public void breadthFirstSearch(int startVertex) {
		//LinkedList<T> queue = new LinkedList<T>();
		int sizeOfAdjacencyList = this.adjacencyMatrix.length;
		boolean[] visitedVertices= new boolean[sizeOfAdjacencyList];
	//	queue.offer(this.adjacencyMatrix[positionX][positionY]);
		visitedVertices[startVertex] = true;
		
		//System.out.println(vertices.get(queue.poll()));
		//loop through each vertex adjacent to the startVertex and keep going
		
	}
	
	/*
	 * Prints out a nicely formatted look of the adjacency matrix
	 */
	public String toString() {
		StringBuilder matrix = new StringBuilder();
		for(int i = 0; i<this.adjacencyMatrix.length; i++) {
			//truncate the space depending on the digits so higher digit numbers don't push the output over
			if(i<=9) matrix.append("Index: " + i + "      ");
			else if(i<=10) matrix.append("Index: " + i + "     ");
			else {
				matrix.append("Index: " + i + "     ");
			}
			for(int j = 0; j<this.adjacencyMatrix[i].length; j++) {
				matrix.append(this.adjacencyMatrix[i][j]);
			}
			matrix.append("\n");
		}
		return matrix.toString();
	}
}
