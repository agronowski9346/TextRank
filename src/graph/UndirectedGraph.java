package graph;

import java.util.HashMap;
import java.util.LinkedList;

/*
 * Class represents a generic undirected graph using an adjacency matrix
 * 
 */
public class UndirectedGraph<T> {
	private int[][] adjacencyMatrix;
	private HashMap<Integer, String> vertices;
	
	public UndirectedGraph(int size) {
		adjacencyMatrix = new int[size][size];
		vertices = new HashMap<Integer, String>();
	}
	
	//adds an edge between two vertices
	public void addEdge(int to, int from) {
		adjacencyMatrix[to][from] = 1;
		adjacencyMatrix[from][to] = 1;
	}
	
	//Performs a BreadthFirstSearch on the graph starting from a position in the matrix
	public void breadthFirstSearch(int startVertex) {
		LinkedList<T> queue = new LinkedList<T>();
		int sizeOfAdjacencyList = this.adjacencyMatrix.length;
		boolean[] visitedVertices= new boolean[sizeOfAdjacencyList];
	//	queue.offer(this.adjacencyMatrix[positionX][positionY]);
		visitedVertices[startVertex] = true;
		
		System.out.println(vertices.get(queue.poll()));
		//loop through each vertex adjacent to the startVertex and keep going
		
	}
}
