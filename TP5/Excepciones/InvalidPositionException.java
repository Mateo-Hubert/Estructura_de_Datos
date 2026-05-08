package TP5.Excepciones;

public class InvalidPositionException extends RuntimeException {
	public InvalidPositionException(String msg) {
		super(msg);
	}
}