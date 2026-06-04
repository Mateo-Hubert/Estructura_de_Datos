package TP6.Implementaciones;

import java.util.Iterator;
import TP5.Excepciones.BoundaryViolationException;
import TP5.Excepciones.InvalidPositionException;
import TP5.Implementaciones.TDALista;
import TP5.Interfaz.Position;
import TP5.Interfaz.PositionList;
import TP6.Excepciones.EmptyTreeException;
import TP6.Excepciones.InvalidOperationException;
import TP6.Interfaz.BinaryTree;

public class ArbolBinario<E> implements BinaryTree<E>{
    //Atributos
    int size;
    BNodo<E> root;

    //Constructor
    public ArbolBinario(BNodo<E> root){
        this.root = null;
        this.size = 0;
    }

    //Métodos
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Position<E> root(){
        if(this.isEmpty()){
            throw new EmptyTreeException("El árbol está vacío");
        }
        return root;
    }

    public Position<E> parent(Position<E> v){
        BNodo<E> posicion = check(v);
        if(isRoot(v)){
            throw new BoundaryViolationException("La posición es una raiz");
        }
        BNodo<E> padre = posicion.getPadre();
        return padre;
    }
    public Iterable<Position<E>> children(Position<E> v){
        BNodo<E> posicion = check(v);
        PositionList<Position<E>> lista = new TDALista<>();
        lista.addFirst(posicion.getIzquierdo());
        lista.addLast(posicion.getDerecho());
        return lista;
    }

    public boolean isInternal(Position<E> v){
        BNodo<E> posicion = check(v);
        return posicion.getIzquierdo() != null || posicion.getDerecho()!= null;
    }

    public boolean isExternal(Position<E> v){
        check(v);
        return !isInternal(v);
    }

    public boolean isRoot(Position<E> v){
        BNodo<E> posicion = check(v);
        return posicion.getPadre() == null;
    }

    

    public Position<E> left(Position<E> v){
        BNodo<E> posicion = check(v);
        if(!hasLeft(v)){
            throw new BoundaryViolationException("La posición no tiene hijo izquierdo");
        }
        return posicion.getIzquierdo();
    }

    public Position<E> right(Position<E> v){
        BNodo<E> posicion = check(v);
        if(!hasRight(v)){
            throw new BoundaryViolationException("La posición no tiene hijo Derecho");
        }
        return posicion.getDerecho();
    }

    public boolean hasLeft(Position<E> v){
        BNodo<E> posicion = check(v);
        return posicion.getIzquierdo() != null;
    }
    
    public boolean hasRight(Position<E> v){
        BNodo<E> posicion = check(v);
        return posicion.getDerecho() != null;
    }

    public Position<E> addLeft(Position<E> v, E r){
        if(isEmpty()){
            throw new InvalidPositionException("El árbol se encuentra vacío");
        }
        if(hasLeft(v)){
            throw new InvalidOperationException("La posición ya tiene un hijo Izquierdo");
        }
        BNodo<E> hI = new BNodo<E>(r);
        check(v).setIzquierdo(hI); 
        hI.setPadre(check(v)); 
        size++; 
        return hI;
    }

    public Position<E> addRight(Position<E> v, E r){
        if(isEmpty()){
            throw new InvalidPositionException("El árbol se encuentra vacío");
        }
        if(hasRight(v)){
            throw new InvalidOperationException("La posición ya tiene un hijo Derecho");
        }
        BNodo<E> hD = new BNodo<E>(r);
        check(v).setDerecho(hD);  
        hD.setPadre(check(v));
        size++;  
        return hD;
    }

    public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2){
        BNodo<E> posicion = check(v);
        if(isEmpty() || !isExternal(v)){
            throw new InvalidPositionException("El árbol está vacío o la posición es inválida");
        }
        BNodo<E> hI = (BNodo<E>)T1.root();
        BNodo<E> hD = (BNodo<E>)T2.root();
        posicion.setIzquierdo(hI);
        hI.setPadre(posicion);
        posicion.setDerecho(hD);
        hD.setPadre(posicion);
        size += T1.size() + T2.size(); 
    }

    public E replace(Position<E> v, E e){
        BNodo<E> nodo = check(v);
        E aux = nodo.element();
        nodo.setElement(e);
        return aux;
    }

    public Iterator<E> iterator(){
        PositionList<E> lista = new TDALista<>();
        return preOrden(lista, this.root()).iterator();
    }
    //Está mal, hay que hacer un preOrden específico para las positions 
    public Iterable<Position<E>> positions(){
        PositionList<E> lista = new TDALista<>();
        return preOrden(lista, this.root()).positions();
    }

    //Métodos y Clases privadas
    private BNodo<E> check(Position<E> p){
        if(p == null || p.element() == null || !(p instanceof BNodo<?>)){
            throw new InvalidPositionException("La posición no es válida");
        }
        return (BNodo<E>)p;
    }

    private PositionList<E> preOrden(PositionList<E> l, Position<E> p){
        BNodo<E> posicion = check(p);
        l.addLast(p.element());
        if(hasLeft(p)){
            preOrden(l, posicion.getIzquierdo());
        }
        if(hasRight(p)){
            preOrden(l, posicion.getDerecho());
        }
        return l;
    }
}
