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
	private LinkedList<String> coOccuranceQueue;
	private HashSet<String> syntacticFilter;
	public KeywordGraph(int size) {
		super(size);
		coOccuranceQueue = new LinkedList<String>();
		syntacticFilter = new HashSet<String>();
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
	public void createGraph(ProcessText text) {
		for(int searchStringSize = 0; searchStringSize<this.CO_OCCURANCE_SIZE; searchStringSize++) {
			this.coOccuranceQueue.offer(text.getTokenizedText()[searchStringSize]);
		}
		
		
		//String to be added to the queue
		int currentString;
		StringBuilder potentialVertex = new StringBuilder();
		for(int currentWord = CO_OCCURANCE_SIZE-1; currentWord<text.getTokenizedText().length; currentWord++) {
			
				currentString = currentWord + 1;
				if (currentString < text.getTokenizedText().length) this.coOccuranceQueue.offer(text.getTokenizedText()[currentString]);
				
		
			potentialVertex.append(this.coOccuranceQueue.poll());
			if(syntacticFilter.contains(potentialVertex.toString())) {
				//draw an edge
			}
			//reset the StringBuilder
			potentialVertex.setLength(0);
			currentString = 0;
		}
	}
}
