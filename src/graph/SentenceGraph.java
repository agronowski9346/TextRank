package graph;

import java.util.Map;

import process.ProcessText;

/*
 * Creates an undirected weighted graph showing the connection between sentences using similarity between sentences
 */
public class SentenceGraph extends UndirectedGraph {
	private ProcessText text;
	//contains all of the sentences in the text
	private String[] sentences;
	protected Double[][] adjacencyMatrix;
	public SentenceGraph(ProcessText text) {
		super();
		this.text = text;
		this.sentences = this.text.getFileContents().split("\\.");
		int graphSize = this.sentences.length - 1;
		this.adjacencyMatrix = new Double[graphSize][graphSize];
	}
	
	protected void addEdge(int to, int from, double weight) {
		this.adjacencyMatrix[to][from] = weight;
		this.adjacencyMatrix[from][to] = weight;
	}
	
	//returns the current weight at a vertex
	protected double getWeight(int vertex1, int vertex2) {
		return this.adjacencyMatrix[vertex1][vertex2];
	}
	
	private void createVertices() {
		for(int i = 0; i<this.sentences.length; i++) {
			this.vertices.put(new Vertex(this.sentences[i]), i);
		}
	}
	
	/*
	 * Computes the similarity between two sentences
	 * this method can be implemented many ways which will affect the summary that is created.
	 * This particular method computes the similarity score by dividing the number of identical tokens
	 * by the log of the sentence length
	 */
	private double similarityScore(int numberOfSimilarTokens, int sentenceLength1, int sentenceLength2) {
		return (numberOfSimilarTokens)/((Math.log(sentenceLength1) + Math.log(sentenceLength2)));
	}
	
	public void drawEdges() {
		this.createVertices();
		int countSimilarTokens = 0;
		int lengthOfSentence1 = 0;
		int lengthOfSentence2 = 0;
		//represent the sentences words
		String[] current = null;
		String[] other = null;
		//loops through sentences and compares them to the next sentences and comparing all of their respective words
		for(int currentSentence = 0; currentSentence < this.sentences.length; currentSentence++) {
			current = this.sentences[currentSentence].split("\\s+");
			for(int examineSentences = currentSentence + 1; examineSentences< this.sentences.length-1; examineSentences++) {
				other = this.sentences[examineSentences].split("\\s+");
				for(int currentSentenceWord = 0; currentSentenceWord<current.length; currentSentenceWord++) {
					lengthOfSentence1 = this.sentences[currentSentence].length();
					for(int examineSentencesWord = 0; examineSentencesWord<other.length; examineSentencesWord++) {
						if(current[currentSentenceWord].equals(other[examineSentencesWord])) {
							countSimilarTokens++;
						}
						
						lengthOfSentence2 = this.sentences[examineSentences].length();
					}
				}
				
				this.addEdge(currentSentence, examineSentences, this.similarityScore(countSimilarTokens, lengthOfSentence1, lengthOfSentence2));
				countSimilarTokens = 0;
				lengthOfSentence1 = 0;
				lengthOfSentence2 = 0;
			}
				
			}
			
	}
	
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
				if(this.adjacencyMatrix[i][j] != null)
				matrix.append(String.format("%.1f", this.adjacencyMatrix[i][j]) + " ");
				else
				matrix.append("NA "); //represents null
			}
			matrix.append("\n");
		}
		return matrix.toString();
	}
	
	public void printHashMap() {
		for(Map.Entry<Vertex, Integer> m: vertices.entrySet()) {
			System.out.println("Node: " + m.getKey().getText() + " has value graph value: " + m.getValue() + " and score value " + m.getKey().getScore());
		}
	}
}
