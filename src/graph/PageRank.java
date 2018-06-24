package graph;

import graph.UndirectedGraph;

/*
 * applies PageRank on undirected unweighted and weighted graphs.
 */
public class PageRank {
	//value of the dampening factor usually set to 0.85
	private final double d = 0.85;
	private UndirectedGraph graph;
	
	public PageRank(UndirectedGraph graph) {
		this.graph = graph;
	}
	public double score(int vertex) {
		double scoreAtInput = 0;
		scoreAtInput = (1-d) + 
		/*
		 * calculates the summation of all the Vertices "j" which point to the 
		 * current vertex i, which is determined by the adjacency matrix of the graph
		 */
		for (int jContainedInCurrentVertex; jContaiedInCurrentVertex<this.graph.)
	}
}
