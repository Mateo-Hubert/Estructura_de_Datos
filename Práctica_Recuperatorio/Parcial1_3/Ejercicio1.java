package Práctica_Recuperatorio.Parcial1_3;

import TP4.Excepciones.EmptyListException;
import TP4.Excepciones.InvalidPositionException;
import TP4.ListaDoble.DNodo;
import TP4.Interfaz.Position;


public class Ejercicio1<E> {
    protected DNodo<E> head;
    protected DNodo<E> tail;
    protected int cantElements;

    public int duplicarElem(E elem) throws EmptyListException{
        if(this.isEmpty()){
            throw new EmptyListException("La lista se encuentra vacía");
        }
        int cant = 0;
        DNodo<E> it = head;
        while(it != tail){
            it = it.getNext();
            if(it.element().equals(elem)){
                this.addAfter(it, elem);
                cant++; 
                it = it.getNext(); //para saltarnos el elemento que acabamos de crear
            }
        }
        return cant;
    }
    public boolean isEmpty(){
        return cantElements == 0;
    }
    public void addAfter(Position<E> p, E e){
        if(e == null || p == null || !(p instanceof DNodo<?>)){
            throw new InvalidPositionException("La posición o el nodo no son válidas");
        }
        DNodo<E> posicion = (DNodo<E>)p;
        DNodo<E> n = new DNodo<>(e);
        n.setNext(posicion.getNext());
        n.setPrev(posicion);
        posicion.getNext().setPrev(n);
        posicion.setNext(n);
        cantElements++;
    }
}
