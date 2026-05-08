package TP5.Excepciones;

public class InvalidEntryException extends RuntimeException {
	public InvalidEntryException (String s) {
		super(s);
	}
}