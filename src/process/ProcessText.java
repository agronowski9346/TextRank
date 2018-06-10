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
	private final String POS_MODEL_LOCATION = "src\\apache-opennlp-1.8.4-bin\\apache-opennlp-1.8.4\\nlpPOSModel\\en-pos-maxent.bin";
	public ProcessText(String fileContents) {
		this.setFileContents(fileContents);
		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
		this.tokenizedText = simpleTokenizer.tokenize(fileContents);
	}
	
	//assigns part of speech tags to the text
	public void POStag() {
		try {
		POSModel model =  new POSModelLoader().load(new File(POS_MODEL_LOCATION));
		POSTaggerME tagger = new POSTaggerME(model);
		String[] tags = tagger.tag(this.tokenizedText);
		for(String test: tags) System.out.println(test);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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
