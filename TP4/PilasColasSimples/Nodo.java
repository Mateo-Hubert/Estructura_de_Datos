package TP4.PilasColasSimples;
import TP4.Interfaz.Position;

class Nodo<E> implements Position<E> {
//Atributos
    private E elemento;
    private Nodo<E> siguiente;
//Constructor
public Nodo (E elemento){
    this.elemento = elemento;
}
//Métodos
	public E element(){
        return elemento;
    }
    public void setElement(E elemento){
        this.elemento = elemento;
    }
    public Nodo<E> getNext(){
        return siguiente;
    }
    public void setNext(Nodo<E> siguiente){
        this.siguiente = siguiente;
    }
    
}
