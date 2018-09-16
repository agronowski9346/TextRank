package graph;

import java.util.HashMap;
import java.util.Map.Entry;

/*
 * Class represents a generic undirected graph using an adjacency matrix
 * 
 */
public class UndirectedGraph {
	protected int[][] adjacencyMatrix;
	protected HashMap<Vertex, Integer> vertices = new HashMap<Vertex, Integer>();;
	
	//adds an edge between two vertices
	protected void addEdge(int to, int from) {
		adjacencyMatrix[to][from] = 1;
		adjacencyMatrix[from][to] = 1;
	}
	
	/*
	 * Reverses the lookup of the hashmap to get a certain vertex at its int position
	 */
	Vertex reverseVertex(int vertex) {
		for (Entry<Vertex, Integer> entry : this.vertices.entrySet()) {
			if(entry.getValue() == vertex) return entry.getKey();
		}
		return null;
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
