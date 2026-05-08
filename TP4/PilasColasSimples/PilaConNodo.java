package TP4.PilasColasSimples;

import java.util.EmptyStackException;
import TP4.Interfaz.Stack;
class PilaConNodo<E> implements Stack<E>{
    //Atributos de instancia
    private Nodo<E> head;
    private int cant;

    //Constructor
    public PilaConNodo(){
        this.head = null;
        this.cant = 0;
    }
    public PilaConNodo(Nodo<E> head){
        this.head = head;
        this.cant = 0;
    }

    //Métodos

    public int size(){
        return cant;
    }
    public boolean isEmpty(){
        return cant == 0;
    }
    public E top(){
        if (this.isEmpty()){
            throw new EmptyStackException();
        }
        return head.element();
    }
    public void push(E element){
        if(element == null){
            throw new NullPointerException("No se puede insertar un objeto nulo en una pila");
        }
        //Creo un nodo auxiliar y actualizo la cabeza
        Nodo<E> nNodo = head;
        head = new Nodo<E>(element);
        //Conecto el nuevo tope con el auxiliar y actualizo el cant
        head.setNext(nNodo);
        cant++;
    }
    public E pop(){
        if(this.isEmpty()){
            throw new EmptyStackException();
        }
        //Guardo el elemento del head, actualizo el tope y devuelvo el elemento 
        E aux = head.element();
        head = head.getNext();
        cant--;
        return aux;
    }
}