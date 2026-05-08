package TP5.Excepciones;

public class EmptyListException extends RuntimeException{
	public EmptyListException(String msg) {
		super(msg);
	}
}