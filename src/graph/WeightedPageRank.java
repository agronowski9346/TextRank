package graph;

import java.util.Map.Entry;

public class WeightedPageRank extends PageRank {
	private SentenceGraph graph;
	public WeightedPageRank(SentenceGraph graph) {
		super();
		this.graph = graph;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double score(int vertex) {
		double summation = 0;
		double scoreAtVertex;
		Vertex currentVertex = this.graph.reverseVertex(vertex);
		/*
		 * calculates the summation of all the Vertices "j" which point to the 
		 * current vertex i, which is determined by the adjacency matrix of the graph
		 */
		for (int adjVertex = 0; adjVertex<this.graph.adjacencyMatrix[vertex].length; adjVertex++) {
			//if there is an edge between nodes
			if(this.graph.adjacencyMatrix[vertex][adjVertex] != null && this.graph.adjacencyMatrix[vertex][adjVertex] != 0) {
				for (Entry<Vertex, Integer> entry : this.graph.vertices.entrySet()) {
			        if (adjVertex == entry.getValue()) {
		//	        	summation += entry.getKey().getScore() * (1.0/this.countOutDegree(entry.getValue()));
			        	summation += entry.getKey().getScore() * ((this.graph.adjacencyMatrix[entry.getValue()][vertex])/sumOutEdgeVertexWeights(entry.getValue()));
			        	break;
			        }
			    }
			}
		}
		currentVertex.setScore(((1-d)+(d*summation)));
		return currentVertex.getScore();
	}
	
	/*
	 * sums the weights of a vertex out edges
	 * For example: sumOutEdgeVertexWeights(2) would sum the outedges of vertex 2 (we call j) whose outedges are represented by k
	 * and whose weights are represented by Weight[j][k]
	 */
	private double sumOutEdgeVertexWeights(int j) {
		double weightSum = 0;
		for(int k = 0; k<this.graph.adjacencyMatrix.length; k++) {
			if(this.graph.adjacencyMatrix[j][k] != null && this.graph.adjacencyMatrix[j][k] != 0) {
				weightSum += this.graph.adjacencyMatrix[j][k];
			}
		}
		return weightSum;
	}
	
	public void test(SentenceGraph s) {
		System.out.println(s.adjacencyMatrix);
	}
	
	public boolean converge() {
		boolean converge = false;
		double currentScore;
		//TESTING
		
		//for(int j = 0; j<15; j++) {
		for(int i = 0; i<this.graph.adjacencyMatrix.length; i++) {
			currentScore = this.score(i);
			//System.out.println("node: " + i + " has score " + currentScore + " at iteration " + j);
			pastIterationScores.put(i, currentScore);
			}
		//}
		
		//TODO error: WE COMPUTE THE CONVERGENCE BY USING THE SCORES OF SUCCESSFUL ITERATIONS, not the current
		//The must be two maps one for the first iteration and another one for the second
		int count = 0;
		while(!converge) {
			for(int i = 0; i<this.graph.adjacencyMatrix.length; i++) {
				currentScore = this.score(i);
				count++;
				//System.out.println("node " + i +" has score: " +currentScore);
				if(currentScore - pastIterationScores.get(i) < this.convergenceThreshold) {
					System.out.println("we lopped throught " + count + " iterations");
					return true;
				}
				pastIterationScores.put(i, currentScore);
				
				}
				
			}	
			
		return true;
		}
	
	}


