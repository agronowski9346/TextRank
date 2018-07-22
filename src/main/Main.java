package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import graph.KeywordGraph;
import graph.PageRank;
import graph.SentenceGraph;
import graph.UndirectedGraph;
import graph.WeightedPageRank;
import process.ProcessText;

public class Main {

	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		BufferedReader br = null;
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
		//KeywordGraph keywordGraph = new KeywordGraph(text);
		//keywordGraph.drawEdges();
		//System.out.println(keywordGraph.toString());
		//PageRank pr = new PageRank(keywordGraph);
		//pr.converge();
		//keywordGraph.printHashMap();
		SentenceGraph sentenceGraph = new SentenceGraph(text);
		sentenceGraph.drawEdges();
		System.out.println(sentenceGraph);
		WeightedPageRank wp = new WeightedPageRank(sentenceGraph);
		//wp.test(sentenceGraph);
		//wp.score(2);
		wp.converge();
	}

}
