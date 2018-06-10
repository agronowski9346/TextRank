package process;

import opennlp.tools.cmdline.postag.POSTaggerTool;
import opennlp.tools.tokenize.SimpleTokenizer;

public class ProcessText {
	private String fileContents;
	private String[] tokenizedText;
	public ProcessText(String fileContents) {
		this.setFileContents(fileContents);
		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
		this.tokenizedText = simpleTokenizer.tokenize(fileContents);
		
	}
	
	//assigns part of speech tags to the text
	public void POStag() {
		POSTaggerTool tool = new POSTaggerTool();
		System.out.println(tool.);
	}
	
	public String getFileContents() {
		return fileContents;
	}
	public void setFileContents(String fileContents) {
		this.fileContents = fileContents;
	}
	
	
	
	public void print() {
		for(String s: this.tokenizedText) {
			System.out.println(s);
		}
	}
	
}
