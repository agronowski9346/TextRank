package pageRank;

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
		for (int )
	}
}
