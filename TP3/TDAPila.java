package TP3;
import java.util.EmptyStackException;

@SuppressWarnings("unchecked")
public class TDAPila<E> implements Stack<E>{
    //Atributos de instancia
    private E[] pila;
    private int cant;

    //Constructor
    public TDAPila(){
        pila = (E[]) new Object[10];
        cant = 0;
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
        return pila[cant-1];
    }
    public void push(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(this.pila.length == cant){
            this.reSize();
        }
        pila[cant++] = element;
    }
    public E pop(){
        if(this.isEmpty()){
            throw new EmptyStackException();
        }
        cant--;
        E temp = pila[cant];
        pila[cant] = null;
        return temp;
    }
    private void reSize(){
        E[] aux = (E[]) new Object[(pila.length)*2];
        for (int i = 0; i < cant; i++){
            aux[i] = this.pila[i];
        }
        this.pila = aux;
    }
}