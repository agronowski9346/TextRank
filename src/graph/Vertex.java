package graph;

public class Vertex {
	private int score = 1;
	private String value;
	public Vertex(String value) {
		this.value = value;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getText() {
		return value;
	}
	public void setText(String text) {
		this.value = text;
	}
	
	//methods defined for checking in the hashmap for values
	@Override
	public boolean equals(Object o) {
		Vertex vertex = (Vertex)o;
		return this.getText().equalsIgnoreCase(vertex.getText());
	}
	@Override
	public int hashCode() {
		return this.getText().length();
	}
}
