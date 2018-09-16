package Exceptions;

@SuppressWarnings("serial")
public class InvalidSecondArgumentException extends RuntimeException {
	public InvalidSecondArgumentException(String s) {
		super(s);
	}
}
