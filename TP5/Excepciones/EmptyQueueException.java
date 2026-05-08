package TP5.Excepciones;
public class EmptyQueueException extends RuntimeException {
	public EmptyQueueException(String msg) {
		super(msg);
	}
}