package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import process.ProcessText;

/*
 * Creates the graph with all the keywords from a piece of text using the appropriate synacthic filters
 */
public class KeywordGraph extends UndirectedGraph {
	//A number between 2 and 10 that defines the co-occurance
	private final int CO_OCCURANCE_SIZE = 2;
	private HashSet<String> syntacticFilter = new HashSet<String>();
	private ProcessText text;
	public KeywordGraph(ProcessText text) {
		super();
		this.text = text;
		syntacticFilter.add("JJ");
		syntacticFilter.add("JJR");
		syntacticFilter.add("JJS");
		syntacticFilter.add("NN");
		syntacticFilter.add("NNS");
		syntacticFilter.add("NNP");
		syntacticFilter.add("NNPS");
		int graphSize = this.createVertices();
		this.adjacencyMatrix = new int[graphSize][graphSize];
		//Put the valid syntactic filters for TextRank(only nouns and adjectives)
		// using Open NLP's part of speech codes
	
		
	}
	/*
	 * Create all of the vertices to be added to 
	 */
	private int createVertices() {
		String[] words = this.text.getTokenizedText();
		String[] tags = this.text.getPosTags();
		int currentNumberOfNodes = 0;
		Vertex textNode = null;
		for (int wordPos = 0; wordPos<words.length; wordPos++) {
				if(this.syntacticFilter.contains(tags[wordPos])) {
					textNode = new Vertex(words[wordPos]);
					if(!vertices.containsKey(textNode)) {
						vertices.put(textNode, currentNumberOfNodes);
						currentNumberOfNodes++;
				}
			}
		}
		return currentNumberOfNodes;
	}
	
	public void printHashMap() {
		for(Map.Entry<Vertex, Integer> m: vertices.entrySet()) {
			System.out.println("Node: " + m.getKey().getText() + " has value: " + m.getValue());
		}
	}
	
	/*
	 * Draws the edges between vertices on the graph
	 */
	public void drawEdges() {
		String[] words = this.text.getTokenizedText();
		String[] tags = this.text.getPosTags();
		
		for (int wordPos = 0; wordPos<words.length; wordPos++) {
			for (int searchWords = 0+wordPos; searchWords <= CO_OCCURANCE_SIZE+wordPos; searchWords++) {
				if(searchWords >= words.length) break; 
				if(syntacticFilter.contains(tags[searchWords])) {
					System.out.println(words[searchWords]);
					//draw an edge between nodes
					
				}
			}
			System.out.println("next...");
		}
		
	
		}
	
	
	}

