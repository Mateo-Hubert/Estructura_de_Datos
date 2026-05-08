package TP4.Excepciones;

public class InvalidPositionException extends RuntimeException {
	public InvalidPositionException(String msg) {
		super(msg);
	}
}