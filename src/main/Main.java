package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import graph.KeywordGraph;
import graph.UndirectedGraph;
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
		KeywordGraph keywordGraph = new KeywordGraph(text);
		keywordGraph.drawEdges();
		keywordGraph.printHashMap();
		
		
	}

}