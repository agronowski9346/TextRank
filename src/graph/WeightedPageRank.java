package graph;

import java.util.Map.Entry;

public class WeightedPageRank extends PageRank {
	private SentenceGraph graph;
	public WeightedPageRank(SentenceGraph graph) {
		super();
		this.graph = graph;
	}
	
	@Override
	public double score(int vertex) {
		double summation = 0;
		Vertex currentVertex = this.graph.reverseVertex(vertex);
		//necessary to handle String in the form of 5 p.m. since the currentVertex will be null
		if(currentVertex == null) {
			this.graph.vertices.remove(currentVertex);
			return -1;
		}
		/*
		 * calculates the summation of all the Vertices "j" which point to the 
		 * current vertex i, which is determined by the adjacency matrix of the graph
		 */
		//this for loop represents the vertex of the weights we want to calculate
		for (int adjVertex = 0; adjVertex<this.graph.adjacencyMatrix[vertex].length; adjVertex++) {
			//if there is an edge between the vertex we want to calculate and its adjacent vertex
			if(this.graph.adjacencyMatrix[vertex][adjVertex] != null && this.graph.adjacencyMatrix[vertex][adjVertex] != 0) {
				for (Entry<Vertex, Integer> entry : this.graph.vertices.entrySet()) {
			        if (adjVertex == entry.getValue()) {
			        	summation += (entry.getKey().getScore()) * ((this.graph.adjacencyMatrix[entry.getValue()][vertex])/sumOutEdgeVertexWeights(entry.getValue()));
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
		
		//TODO error: WE COMPUTE THE CONVERGENCE BY USING THE SCORES OF SUCCESSFUL ITERATIONS, not the current
		//The must be two maps one for the first iteration and another one for the second
		int count = 0;
		while(!converge) {
			for(int i = 0; i<this.graph.adjacencyMatrix.length; i++) {
				currentScore = this.score(i);
				count++;
				//System.out.println("node " + i +" has score: " +currentScore);
				if((pastIterationScores.containsKey(i)) && (currentScore - pastIterationScores.get(i) < this.convergenceThreshold)) {
					//System.out.println("we lopped throught " + count + " iterations");
					return true;
				}
				pastIterationScores.put(i, currentScore);
				
				}
				
			}	
			
		return true;
		}
	
	}


