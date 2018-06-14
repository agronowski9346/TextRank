package process;

import java.io.File;
import java.io.FileNotFoundException;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.cmdline.postag.POSTaggerTool;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class ProcessText {
	private String fileContents;
	private String[] tokenizedText;
	private String[] posTags;
	private final String POS_MODEL_LOCATION = "src\\apache-opennlp-1.8.4-bin\\apache-opennlp-1.8.4\\nlpPOSModel\\en-pos-maxent.bin";
	public ProcessText(String fileContents) {
		this.setFileContents(fileContents);
		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
		this.tokenizedText = simpleTokenizer.tokenize(fileContents);
		this.posTag();
	}
	
	//assigns part of speech tags to the text
	private void posTag() {
		try {
		POSModel model =  new POSModelLoader().load(new File(POS_MODEL_LOCATION));
		POSTaggerME tagger = new POSTaggerME(model);
		setPosTags(tagger.tag(this.tokenizedText));
		/*
		 * prints out words and their appropriate POS TAGs
		for(int i = 0; i<this.tokenizedText.length; i++) {
			System.out.println(tokenizedText[i] + " POS is: " + tags[i]);
		}
		*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] getTokenizedText() {
		return tokenizedText;
	}

	public void setTokenizedText(String[] tokenizedText) {
		this.tokenizedText = tokenizedText;
	}

	public String getPOS_MODEL_LOCATION() {
		return POS_MODEL_LOCATION;
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

	public String[] getPosTags() {
		return posTags;
	}

	public void setPosTags(String[] posTags) {
		this.posTags = posTags;
	}
	
}
