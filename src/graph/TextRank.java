package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TextRank {
	
	/*
	 * prints the top keywords to describe the text in a sorted order according to their scores
	 * flag of 0 represents that we take all keywords with their scores printed next to them
	 * flag of 1 represents that we take all keywords without their scores printed next to them
	 */
	public void getKeywords(KeywordGraph keywordGraph, int flag) {
		List<Vertex> keyWordsByScore = new ArrayList<Vertex>(keywordGraph.vertices.keySet());
		 Collections.sort(keyWordsByScore, new Comparator<Vertex>() {

		        public int compare(Vertex v1, Vertex v2) {
		          	if(v1.getScore() < v2.getScore()) return 1;
		          	else if(v2.getScore() < v1.getScore()) return -1;
		          	return 0;
		        }
		    });
		if(flag == 0) printAllKeywordsWithScore(keyWordsByScore);
		else if(flag == 1) printAllKeywordsWithoutScore(keyWordsByScore);
		else System.out.println("something went wrong with the keyword flag");
	}
	
	public void printAllKeywordsWithScore(List<Vertex> keyWordsByScore) {
		 for(Vertex v: keyWordsByScore) {
			 System.out.println("Score: " + v.getScore() + ": " + v.getText());
		 }
	}
	
	public void printAllKeywordsWithoutScore(List<Vertex> keyWordsByScore) {
		for(Vertex v: keyWordsByScore) {
			 System.out.println(v.getText());
		 }
	}
	
	/*
	 * prints the top sentences to describe the text in a sorted order according to their scores
	 * flag of 0 represents that we take all sentences with their scores printed next to them
	 * flag of 1 represents that we take all sentences without their scores printed next to them
	 * flag of 2 has just the top 100 words
	 */
	public void getSentences(SentenceGraph sentenceGraph, int flag) {
		List<Vertex> sentencesByScore = new ArrayList<Vertex>(sentenceGraph.vertices.keySet());
		 Collections.sort(sentencesByScore, new Comparator<Vertex>() {

		        public int compare(Vertex v1, Vertex v2) {
		          	if(v1.getScore() < v2.getScore()) return 1;
		          	else if(v2.getScore() < v1.getScore()) return -1;
		          	return 0;
		        }
		    });
		
		 if(flag == 0) printAllSentencesWithScores(sentencesByScore);
		 else if(flag == 1) printAllSentencesWithoutScores(sentencesByScore);
		 else if(flag == 2) oneHundreedWordSummary(sentencesByScore);
		 else System.out.println("something went wrong with the sentence flag");
	}
	
	public void printAllSentencesWithScores(List<Vertex> sentencesByScore) {
		 for(Vertex v: sentencesByScore) {
			 System.out.println("Score: " + v.getScore() + ": " + v.getText());
		 }
	}
	
	public void printAllSentencesWithoutScores(List<Vertex> sentencesByScore) {
		 for(Vertex v: sentencesByScore) {
			 System.out.println(v.getText());
		 }
	}
	
	/*
	 * prints the 100 word summary of the text
	 */
	public void oneHundreedWordSummary(List<Vertex> sentencesByScore) {
		int wordsLength = 100;
		for(Vertex v: sentencesByScore) {
			if(wordsLength - v.getText().length() > 0) {
				System.out.println(v.getText());
				wordsLength -= v.getText().length();
			}
			else {
				String[] splited = v.getText().split("\\s+");
				for(int i = 0; i<splited.length; i++) {
					if(wordsLength == 0) break;
					System.out.print(splited[i] + " ");
					wordsLength--;
				}
			}
			 
		 }
	}
}
