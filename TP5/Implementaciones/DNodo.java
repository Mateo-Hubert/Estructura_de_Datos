package TP5.Implementaciones;
import TP5.Interfaz.Position;

class DNodo<E> implements Position<E> {
//Atributos
    private E element;
    private DNodo<E> next;
    private DNodo<E> prev;
//Constructores
public DNodo (E element){
    this.element = element;
}
public DNodo (E element, DNodo<E> next, DNodo<E> prev){
    this.element = element;
    this.next = next;
    this.prev = prev;
}
//Métodos
	public E element(){
        return element; //Es igual que un getElement, pero la implementación del Position pide que se llame así
    }
    public void setElement(E element){
        this.element = element;
    }
    public DNodo<E> getNext(){
        return next;
    }
    public void setNext(DNodo<E> next){
        this.next= next;
    }
    public DNodo<E> getPrev(){
        return prev;
    }
    public void setPrev(DNodo<E> prev){
        this.prev= prev;
    }
}
