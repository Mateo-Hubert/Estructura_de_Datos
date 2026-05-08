package TP5.Excepciones;

public class BoundaryViolationException extends RuntimeException {
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}