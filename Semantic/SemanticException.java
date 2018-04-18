package Semantic;

public class SemanticException extends Exception {

	public SemanticException(String msg, int l, int o){
		super("Line:" + l + "," + o + ": " + msg);
	}
}