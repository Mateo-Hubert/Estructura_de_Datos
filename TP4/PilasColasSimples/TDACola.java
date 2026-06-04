

package TP4.PilasColasSimples;
import TP4.Interfaz.Queue;
import TP4.Excepciones.*;
public class TDACola<E> implements Queue<E>{
    //Atributos de instancia
    Nodo<E> head;
    Nodo<E> tail;
    int size;    

    //Constructor
    public TDACola(){
        this.head = null;
        this.tail = null;
        size = 0;
    }
    public TDACola(Nodo<E> head, Nodo<E> tail){
        this.head = head;
        this.tail = tail;
        size = 0;
    }

    //Métodos
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public E front(){
        if (this.isEmpty()){
            throw new EmptyQueueException("No hay elementos en la cola");
        }
        return head.element();
    }

    public void enqueue(E element){
        
        Nodo<E> nNodo = new Nodo<E>(element);
        //Si está vacía tanto la la cabeza como el rabo se conectan nuevo nodo
        if(this.isEmpty()){
            head = nNodo;
            tail = nNodo;
        }
        else{
            //Si la pila no esta vacía el nuevo nodo será el nuevo rabo pero
            //Primero debo hacer que el anterior rabo apunte al nuevo 
            tail.setNext(nNodo);
            tail = nNodo;
        }
        size++;
    }
    public E dequeue(){
        if(isEmpty()){
            throw new EmptyQueueException("La cola está vacía");
        }
        // Si la cola solo tiene un elemento head = tail, por lo que
        // head = null es equivalente a head = head.getNext, con la 
        // diferencia de que en ese caso debo hacer nulo a tail manualmente
        Nodo<E> aux = head;
        if(this.size == 1){
            head = null;
            tail = null;
        }
        else{
            head = head.getNext();
        }
        this.size--;
        return aux.element();
    }
}
