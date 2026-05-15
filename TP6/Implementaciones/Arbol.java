package TP6.Implementaciones;

import java.lang.Iterable;
import java.util.Iterator;

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
    public Position<E> root(){
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
        TNodo<E> anterior = (TNodo<E>)rb;
        TNodo<E> nuevo = null;
        Iterator<Position<TNodo<E>>> it = padre.getHijos().positions().iterator();
        while (it.hasNext() && nuevo == null){
            Position<TNodo<E>> aux = it.next();
            if(anterior == aux.element()){
                nuevo = new TNodo<>(e);
                padre.getHijos().addBefore(aux, nuevo);
                nuevo.setPadre(padre);
                size++;
            }
        }
        if(nuevo == null){//Nuevo no se creó porque rb no es hijo de p
            throw new InvalidPositionException("rb no es hijo de p");
        }

        return nuevo;
    }
    
    public Position<E> addAfter(Position<E> p, Position<E> rb, E e){
            if(p == null || rb == null || !(p instanceof TNodo<E>) || !(rb instanceof TNodo<E>)){
                throw new InvalidPositionException("El padre o el nodo son inválidos");
            }
            TNodo<E> padre = (TNodo<E>)p;
            TNodo<E> anterior = (TNodo<E>)rb;
            TNodo<E> nuevo = null;
            Iterator<Position<TNodo<E>>> it = padre.getHijos().positions().iterator();
            while (it.hasNext() && nuevo == null){
                Position<TNodo<E>> aux = it.next();
                if(anterior == aux.element()){
                    nuevo = new TNodo<>(e);
                    padre.getHijos().addAfter(aux, nuevo);
                    nuevo.setPadre(padre);
                    size++;
                }
            }
            if(nuevo == null){//Nuevo no se creó porque rb no es hijo de p
                throw new InvalidPositionException("rb no es hijo de p");
            }

            return nuevo;
        }

    public void removeExternalNode (Position<E> p){
        TNodo<E> posicion = checkPosition(p); //Si p no es un nodo, o el arbol está vacío lanza InvalidPositionException
        if (!posicion.getHijos().isEmpty()){ //Si p no es una hoja
            throw new InvalidPositionException("La posición no es válida");
        }
        
        if(posicion == root()){
            this.raiz = null;
        }
        else{
        //Para eliminar p, debo encontrar su posición en el árbol de la forma Position<TNodo<E>>
            TNodo<E> padre = posicion.getPadre();
            Iterator<Position<TNodo<E>>> it = padre.getHijos().positions().iterator();
            boolean eliminada = false;
            while (it.hasNext() && !eliminada){
                Position<TNodo<E>> aux = it.next();
                if(aux.element() == posicion){
                    padre.getHijos().remove(aux);
                    /**
                      * Idealmente en lugar de usar el remove dentro del while,
                      * debería guardar la posición y hacer el remove fuera para
                      * no arriesgarme a un: ConcurrentModificationException,
                      * tener en cuenta a futuro... Pero yo me olvidé xD
                      */
                    posicion.setPadre(null); //Solo para asegurarnos de que el GC haga lo suyo
                    posicion.setElement(null);//Idem
                    eliminada = true;
                }
            }
        }    
        size--;    
    }

    public void removeInternalNode (Position<E> p){
        TNodo<E> posicion = checkPNoRoot(p); //Si p no es un nodo, es una raíz o el arbol está vacío lanza InvalidPositionException
        if(posicion.getHijos().isEmpty()){ //Si el nodo no es un nodo interno
            throw new InvalidPositionException("La posición no corresponde a un nodo interno");
        }
        //Para eliminar p, debo encontrar su posición en el árbol de la forma Position<TNodo<E>>
        TNodo<E> padre = posicion.getPadre();
        Iterator<Position<TNodo<E>>> it = padre.getHijos().positions().iterator();
        boolean encontrada = false;
        Position<TNodo<E>> posicionP = null; //Posicion de p en forma Position<TNodo<E>> en lugar de Position<E>
        while (it.hasNext() && !encontrada){
                Position<TNodo<E>> aux = it.next();
                if(aux.element() == posicion){
                    posicionP = aux;
                    encontrada = true;
                }
            }
        for(TNodo<E> n : posicion.getHijos()){
            n.setPadre(padre);
            padre.getHijos().addBefore(posicionP, n);
        }
        padre.getHijos().remove(posicionP);
        posicion.setElement(null);
        posicion.setPadre(null);
        size--;
    }

    public void removeNode (Position<E> p){
        
        TNodo<E> posicion = checkPosition(p); //Si p no es un nodo, el arbol está vacío lanza InvalidPositionException
        if(posicion == root() && posicion.getHijos().size() > 1){
            throw new InvalidPositionException("La raiz tiene más de un hijo");
        }
        if(posicion.getHijos().isEmpty()){
            this.removeExternalNode(p);
        }
        else{
            if(p == this.root()){
                TNodo<E> hijoR = posicion.getHijos().first().element();
                this.raiz = hijoR;
                hijoR.setPadre(null);
                posicion.setElement(null);
                size--;
            }
            else{
                this.removeInternalNode(p);
            }
        }
    }

    public E replace(Position<E> v, E e){
        TNodo<E> posicion = checkPosition(v);
        E elemento = posicion.element();
        posicion.setElement(e);
        return elemento;
    }
    
    public Iterator<E> iterator(){
        return this.preorden(checkPosition(this.root()), new TDALista<>()).iterator();
    }

    public Iterable<Position<E>> positions(){
        return this.preorden(checkPosition(this.root()), new TDALista<>()).positions();
    }


    //Métodos privados

     /**
     * Chequea si el árbol está vacío y la posición es un nodo, 
     * en caso de que si lo sea, la convierte a Nodo y la retorna
     */
    private TNodo<E> checkPosition(Position<E> p){
        if(this.isEmpty()){ 
            throw new InvalidPositionException("El árbol está vacío");
        }
        if(p == null || !(p instanceof TNodo<E>)){ 
            throw new InvalidPositionException("La posición no es un nodo");
        }
        return (TNodo<E>)p;
    }
    
    /**
     * Chequea si el árbol está vacío y la posición es un nodo que no sea la raiz, 
     * y si puede, la convierte a Nodo y la retorna
     */
    private TNodo<E> checkPNoRoot(Position<E> p){
        if(this.isEmpty()){ 
            throw new InvalidPositionException("El árbol está vacío");
        }
        if(p == null || !(p instanceof TNodo<E>) || //Si p no es un nodo
           p == this.root()){ //Si p es la raíz
            throw new InvalidPositionException("La posición no es válida");
        }
        return (TNodo<E>)p;
    }
    /**
     * Dado un nodo, devuelve una lista en preorden de los elementos del todo el  
     * arbol que posee a dicho nodo como raiz. Se genera recursivamente 
     */
    private TDALista<E> preorden(TNodo<E> nodo, TDALista<E> lista){
        lista.addLast(nodo.element());
        if(!nodo.getHijos().isEmpty()){
            for(TNodo<E> hijo : nodo.getHijos()){
                preorden(hijo, lista);
            }
        }
        return lista;
    }
}

