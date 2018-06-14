package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import process.ProcessText;

public class Main {

	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		BufferedReader br = null;
		StringBuilder fileContents = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(inputFile));
			while(br.read() !=-1) {
				fileContents.append(br.readLine());
			}
			ProcessText text = new ProcessText(fileContents.toString());
		} catch (FileNotFoundException e) {
			System.out.println("no file found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
