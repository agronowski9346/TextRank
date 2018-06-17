package graph;

import java.util.HashSet;
import java.util.LinkedList;
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
		int graphSize = this.createNodes();
		this.text = text;
		this.adjacencyMatrix = new int[graphSize][graphSize];
		//Put the valid syntactic filters for TextRank(only nouns and adjectives)
		// using Open NLP's part of speech codes
		syntacticFilter.add("JJ");
		syntacticFilter.add("JJR");
		syntacticFilter.add("JJS");
		syntacticFilter.add("NN");
		syntacticFilter.add("NNS");
		syntacticFilter.add("NNP");
		syntacticFilter.add("NNPS");
		
	}
	
	private int createNodes() {
		String[] words = this.text.getTokenizedText();
		String[] tags = this.text.getPosTags();
		int currentNumberOfNodes = 0;
		Vertex textNode = null;
		for (int wordPos = 0; wordPos<words.length; wordPos++) {
			for (int searchWords = 0+wordPos; searchWords <= CO_OCCURANCE_SIZE+wordPos; searchWords++) {
				if(searchWords >= words.length) break; 
				if(syntacticFilter.contains(tags[searchWords])) {
					textNode = new Vertex(tags[searchWords]);
					if(!vertices.containsKey(textNode)) {
						vertices.put(textNode, currentNumberOfNodes);
						currentNumberOfNodes++;
					}
				}
			}
		}
		return currentNumberOfNodes;
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
					if(!vertices.containsKey(words[searchWords])) {
						Vertex textNode = new Vertex(tags[searchWords]);
						vertices.put(textNode, value)
					}
					System.out.println(words[searchWords]);
				}
			}
			System.out.println("next...");
		}
		
	
		}
	}

