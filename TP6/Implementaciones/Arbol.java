package TP6.Implementaciones;

import TP5.Excepciones.InvalidPositionException;
import TP5.Interfaz.Position;
import TP5.Excepciones.BoundaryViolationException;

import TP6.Interfaz.Tree;
import TP6.Excepciones.InvalidOperationException;
import TP6.Excepciones.EmptyTreeException;

@SuppressWarnings({"unchecked", "raw"})
public class Arbol<E> implements Tree<E>{
    //Atributo
    private int size;
    private TNodo<E> raiz;

    //Constructor
    public Arbol(E elem){
        this.raiz = new TNodo<>(elem);
        size = 0;
    }
    public Arbol(){
        raiz = null;
        size = 0;
    }

    //Métodos
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public void createRoot(E elem){
        if(raiz != null){
            throw new InvalidOperationException("El árbol ya posee una raiz");
        }
        raiz = new TNodo<>(elem);
    }
    public TNodo<E> root(){
        if(isEmpty()){
            throw new EmptyTreeException("El árbol no posee una raiz");
        }
        return raiz;
    }

    public Position<E> parent(Position<E> v){
        if(v == null){
            throw new InvalidPositionException("El árbol está vacío");
        }
        if(v == raiz){
            throw new BoundaryViolationException("El nodo no posee padre");
        }
        TNodo<E> aux = (TNodo<E>)v;
        return aux.getPadre();
    }


    public Position<E> addFirstChild(Position<E> p, E e){
        if (this.isEmpty() || p == null){
            throw new InvalidPositionException("El nodo no pertenece al árbol");
        }

        TNodo<E> padre = (TNodo<E>)p;
        TNodo<E> nuevoHijo = new TNodo<E>(e);
        padre.addHijo(nuevoHijo);
        
        size++;
        return nuevoHijo;
    }


}

