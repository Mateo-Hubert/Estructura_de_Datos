package TP6.Implementaciones;
import TP5.Interfaz.Position;
public class BNodo<E> implements Position<E> {
    //Atributos de intancia
    E element;
    BNodo<E> padre;
    BNodo<E> hijoIzquierdo;
    BNodo<E> hijoDerecho;
    
    //Constructor
    public BNodo(E element){
        this.element = element;
        this.padre = null;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
    public BNodo(E element, BNodo<E> padre, BNodo<E> hijoIzquierdo, BNodo<E> hijoDerecho){
        this.element = element;
        this.padre = padre;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    //Métodos
    public E element(){
        return this.element;
    }
    public void setElement(E element){
        this.element = element;
    }
    public BNodo<E> getPadre(){
        return padre;
    }
    public void setPadre(BNodo<E> padre){
        this.padre = padre;
    }
    public BNodo<E> getIzquierdo(){
        return hijoIzquierdo;
    }
    public void setIzquierdo(BNodo<E> hijoIzquierdo){
        this.hijoIzquierdo = hijoIzquierdo;
    }
    public BNodo<E> getDerecho(){
        return hijoDerecho;
    }
    public void setDerecho(BNodo<E> hijoDerecho){
        this.hijoDerecho = hijoDerecho;
    }
}
