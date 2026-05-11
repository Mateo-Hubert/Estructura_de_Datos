package TP6.Implementaciones;

import java.lang.Iterable;

import TP5.Excepciones.InvalidPositionException;
import TP5.Implementaciones.TDALista;
import TP5.Interfaz.Position;
import TP5.Interfaz.PositionList;
import TP5.Excepciones.BoundaryViolationException;

import TP6.Interfaz.Tree;
import TP6.Excepciones.InvalidOperationException;
import TP6.Excepciones.EmptyTreeException;

@SuppressWarnings({"unchecked", "rawtype"})
public class Arbol<E> implements Tree<E>{
    //Atributo
    private int size;
    private TNodo<E> raiz;

    //Constructor
    public Arbol(E elem){
        this.raiz = new TNodo<>(elem);
        size = 1;
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
        size++;
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
            throw new BoundaryViolationException("El nodo es una raiz, por lo que no posee padre");
        }
        TNodo<E> aux = (TNodo<E>)v;
        return aux.getPadre();
    }

    public Iterable<Position<E>> children (Position<E> v){
        if(v == null){
            throw new InvalidPositionException("El nodo pasado es nulo");
        }
        TNodo<E> aux = (TNodo<E>)v;
        
        //Debo usar una lista auxiliar porque Java no permite el polimorfismo dentro
        //De las llaves, Iterable<Positions<E>> == TDALista<Position<E>>, pero
        // Iterable<Position<E>> != Iterable<TNodo<E>>
        TDALista<Position<E>> childs = new TDALista<>();

        //aux.getHijos() devuelve PositionList<TNodo<E>>
        for(Position<E> p :aux.getHijos()){
            childs.addLast(p);
        }
        return childs;
    }
    
    public Iterable<Position<E>> childrenIA(Position<E> v) {
    if (v == null) {
        throw new InvalidPositionException("El nodo pasado es nulo");
    }
    
    TNodo<E> aux = (TNodo<E>) v;
    
    // Obtenemos la lista interna de hijos
    PositionList<TNodo<E>> listaHijos = aux.getHijos();

    // El doble casteo:
    // 1. (Iterable): Lo convertimos a tipo Iterable crudo ("perdemos" el <TNodo<E>>)
    // 2. (Iterable<Position<E>>): Lo convertimos al tipo que necesitamos
    return (Iterable<Position<E>>) (Iterable) listaHijos;
}

    public boolean isInternal(Position<E> v){
        if (v == null) {
            throw new InvalidPositionException("El nodo pasado es nulo");
        }
    
        TNodo<E> aux = (TNodo<E>) v;

        return !aux.getHijos().isEmpty();
    }
    public boolean isExternal(Position<E> v){
        if (v == null) {
            throw new InvalidPositionException("El nodo pasado es nulo");
        }
    
        TNodo<E> aux = (TNodo<E>) v;

        return aux.getHijos().isEmpty();
    }

    public boolean isRoot(Position<E> v){
        if (v == null) {
            throw new InvalidPositionException("El nodo pasado es nulo");
        }
    
        TNodo<E> aux = (TNodo<E>) v;

        return !this.isEmpty() && aux == this.root();
    }

	public Position<E> addFirstChild(Position<E> p, E e){
        if(this.isEmpty() || p == null){
            throw new InvalidPositionException("La posición es inválida y/o el árbol está vacío");
        }
        TNodo<E> padre = (TNodo<E>)p;
        TNodo<E> hijo = new TNodo<>(e);
        padre.getHijos().addFirst(hijo);

        return hijo;
    }

    public Position<E> addLastChild(Position<E> p, E e){
        if(this.isEmpty() || p == null){
            throw new InvalidPositionException("La posición es inválida y/o el árbol está vacío");
        }
        TNodo<E> padre = (TNodo<E>)p;
        TNodo<E> hijo = new TNodo<>(e);
        padre.getHijos().addLast(hijo);

        return hijo;
    }
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e){
        if(p == null || rb == null || !(p instanceof TNodo<E>) || !(rb instanceof TNodo<E>)){
            throw new InvalidPositionException("El padre o el nodo son inválidos");
        }
        TNodo<E> padre = (TNodo<E>)p;
        TNodo<E> previo = (TNodo<E>)rb;
        TNodo<E> nuevo = new TNodo<>(e);
        padre.getHijos().addBefore(rb, nuevo);        
        return nuevo;
    }


}

