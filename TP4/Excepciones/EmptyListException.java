package TP4.Excepciones;

public class EmptyListException extends RuntimeException{
	public EmptyListException(String msg) {
		super(msg);
	}
}