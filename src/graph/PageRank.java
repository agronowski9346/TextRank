package graph;

import java.util.HashMap;
import java.util.Map.Entry;

import graph.UndirectedGraph;

/*
 * applies PageRank on undirected unweighted and weighted graphs.
 */
public class PageRank {
	//value of the dampening factor usually set to 0.85
	protected final double d = 0.85;
	//value usually set to 0.0001
	protected final double convergenceThreshold = 0.0001;
	protected UndirectedGraph graph;
	//represents pastiterations of PageRank
	protected HashMap<Integer, Double> pastIterationScores = new HashMap<Integer, Double>();
	public PageRank(UndirectedGraph graph) {
		this.graph = graph;
	}
	public PageRank() {
		
	}
	/*
	 * sets and returns the score at a given vertex after PageRank has been applied on it
	 */
	public double score(int vertex) {
		double summation = 0;
		Vertex currentVertex = this.graph.reverseVertex(vertex);
		/*
		 * calculates the summation of all the Vertices "j" which point to the 
		 * current vertex i, which is determined by the adjacency matrix of the graph
		 */
		for (int adjVertex = 0; adjVertex<this.graph.adjacencyMatrix[vertex].length; adjVertex++) {
			if(this.graph.adjacencyMatrix[vertex][adjVertex] == 1) {
				for (Entry<Vertex, Integer> entry : this.graph.vertices.entrySet()) {
			        if (adjVertex == entry.getValue()) {
			        	summation += entry.getKey().getScore() * (1.0/this.countOutDegree(entry.getValue()));
			        	break;
			        }
			    }
			}
		}
		currentVertex.setScore(((1-d)+(d*summation)));
		return currentVertex.getScore();
	}
	/*
	 * iterates PageRank's scoring algorithm until convergence
	 */
	public boolean converge() {
		boolean converge = false;
		double currentScore;
		
		//TODO error: WE COMPUTE THE CONVERGENCE BY USING THE SCORES OF SUCCESSFUL ITERATIONS, not the current
		//The must be two maps one for the first iteration and another one for the second
		
		while(!converge) {
			for(int i = 0; i<this.graph.adjacencyMatrix.length; i++) {
				currentScore = this.score(i);
				//System.out.println("node " + i +" has score: " +currentScore);
				if((pastIterationScores.containsKey(i)) && (currentScore - pastIterationScores.get(i) < this.convergenceThreshold)) {
					return true;
				}
				pastIterationScores.put(i, currentScore);
				
				}
				
			}	
			
		return true;
		}
	
	/*
	 * Counts the out degree of a vertex
	 */
	private int countOutDegree(int vertex) {
		int count = 0;
		for(int i = 0; i<this.graph.adjacencyMatrix.length; i++) {
			if(this.graph.adjacencyMatrix[vertex][i] == 1) count++;
		}
		return count;
	}
}
