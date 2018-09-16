package main;

import java.io.File;
import java.util.Scanner;
import graph.KeywordGraph;
import graph.PageRank;
import graph.SentenceGraph;
import graph.TextRank;
import graph.WeightedPageRank;
import process.ProcessText;
import Exceptions.InvalidSecondArgumentException;
public class Main {

	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		StringBuilder fileContents = new StringBuilder();
		Scanner readFile = null;
		
		try {


            Scanner input = new Scanner(System.in);

         

            readFile = new Scanner(inputFile);


            while (readFile.hasNextLine()) {
            	fileContents.append(readFile.nextLine());
            	/*
            	 * A space must be appended between each new line character or else the previous line character
            	 * Will appear next to the next new line character
            	 */
            	fileContents.append(" ");
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		ProcessText text = new ProcessText(fileContents.toString());
		
		KeywordGraph keywordGraph = new KeywordGraph(text);
		keywordGraph.drawEdges();
		
		PageRank pr = new PageRank(keywordGraph);
		pr.converge();
		
		SentenceGraph sentenceGraph = new SentenceGraph(text);
		sentenceGraph.drawEdges();
		
		WeightedPageRank wp = new WeightedPageRank(sentenceGraph);
		wp.converge();
		
		TextRank output = new TextRank();
		int flag = Integer.parseInt(args[2]);
		if(args[1].equals("k")) {
			if(flag > 1) throw new InvalidSecondArgumentException("Only 0 and 1 are valid arguments for this flag");
			output.getKeywords(keywordGraph, flag);
		}
		else if(args[1].equals("s")) {
			if(flag > 2) throw new InvalidSecondArgumentException("Only 0, 1, and 2 are valid arguments for this flag");
			output.getSentences(sentenceGraph, flag);
		}
		else {
			throw new InvalidSecondArgumentException("The only valid second argument is \"k\" or \"s\"");
		}
	}

}
