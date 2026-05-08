package TP5.Excepciones;

public class InvalidKeyException extends RuntimeException{
	public InvalidKeyException(String s) {
		super(s);
	}
}