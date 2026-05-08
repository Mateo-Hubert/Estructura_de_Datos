package TP5.Implementaciones;
import java.util.Iterator;

import TP5.Excepciones.EmptyListException;
import TP5.Excepciones.InvalidPositionException;
import TP5.Excepciones.BoundaryViolationException;
import TP5.Interfaz.Position;
import TP5.Interfaz.PositionList;

public class TDALista<E> implements PositionList<E> {
    //Atributos
    protected int size;
    protected DNodo<E> header;
    protected DNodo<E> trailer;

    //Constructor
    public TDALista (){
        header = new DNodo<E>(null);
        trailer = new DNodo<E>(null);
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }
    //Metodos
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public Position<E> first(){
        if (isEmpty()){ //Si no hay nada rompe el flujo y lanza la excepción
            throw new EmptyListException("No hay elementos en la lista");
        }
        //Si hay al menos un elemento, devuelve el nodo siguiente a la cabeza
        return header.getNext();
    }
    public Position<E> last(){
        if (isEmpty()){ //Si no hay nada rompe el flujo y lanza la excepción
            throw new EmptyListException("No hay elementos en la lista");
        }
        //Si hay al menos un elemento, devuelve el nodo anterior al trailer
        return trailer.getPrev();
    }
    
	public Position<E> next(Position<E> p){
        DNodo<E> aux = checkPosition(p);
        if(aux.getNext() == trailer){
            throw new BoundaryViolationException("La posición no posee un elemento posterior");
        }
        return aux.getNext();
    }
    public Position<E> prev(Position<E> p){
        DNodo<E> aux = checkPosition(p);
        if(aux.getPrev() == header){
            throw new BoundaryViolationException("La posición no posee un elemento anterior");
        }
        return aux.getPrev();
    }
    public void addFirst(E element){
        DNodo<E> first = new DNodo<E>(element);
        first.setNext(header.getNext()); //El siguiente a nuestro nuevo primero será el antiguo primero
        first.setPrev(header);           //El previo a nuestro nuevo primero será obviamente la cabeza
        header.getNext().setPrev(first); //Reasigno el previo del antiguo primero a nuestro nuevo primero
        header.setNext(first);           //Resigno el siguiente de la cabeza a nuestro nuevo primero
        size++;
    }
    public void addLast(E element){
        DNodo<E> last = new DNodo<E>(element);
        last.setPrev(trailer.getPrev()); //El previo a last el antiguo último
        last.setNext(trailer);           //El siguiente a last obviamente el trailer
        trailer.getPrev().setNext(last); //Reasigno como siguiente del antiguo último a last
        trailer.setPrev(last);           //Resigno como previo del trailer a last
        size++;
    }
    public void addAfter(Position<E> p, E element){
        DNodo<E> aux = checkPosition(p);
        DNodo<E> nuevoNodo = new DNodo<E>(element);
        nuevoNodo.setNext(aux.getNext()); //El siguiente a nuestro nuevo nodo será el siguiente de p
        nuevoNodo.setPrev(aux);           //El previo a nuestro nuevo nodo será p
        aux.getNext().setPrev(nuevoNodo); //Reasigno nuevoNodo como previo del que anteriormente estaba luego de p 
        aux.setNext(nuevoNodo);           //Resigno nuevoNodoel como el siguiente de p
        size++;        
    }
    public void addBefore(Position<E> p, E element){
        DNodo<E> aux = checkPosition(p);
        DNodo<E> nuevoNodo = new DNodo<E>(element);
        nuevoNodo.setPrev(aux.getPrev()); //El previo de nuevoNodo es el que anteriormente estaba antes de P
        nuevoNodo.setNext(aux);           //El siguiente de nuevoNodo será p
        aux.getPrev().setNext(nuevoNodo); //Reasigno nuevoNodo como el siguiente del que anteriormente estaba previo a p 
        aux.setPrev(nuevoNodo);           //Resigno nuevoNodoel como el previo a p
        size++;        
    }
    public E remove(Position<E> p){
        DNodo<E> aux = checkPosition(p);
        E temp = p.element();
        aux.getPrev().setNext(aux.getNext()); //Asigno como siguiente del que está previo a p, al siguiente de p
        aux.getNext().setPrev(aux.getPrev()); //Asigno como previo del que está siguiente a p, al previo de p
        //Desacoplo aux para que quede huérfana y así se la lleve el garbage collector
        aux.setNext(null);
        aux.setPrev(null);
        aux.setElement(null);
        size--;
        return temp;
    }
    public E set(Position<E> p, E element){
        DNodo<E> aux = checkPosition(p);
        E temp = p.element();
        aux.setElement(element);
        return temp;
    }

    public Iterator<E> iterator(){
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions(){
        return new PositionIterable();
    }

    public void ejercicio2(E e1, E e2){
        if (size < 2){
            throw new InvalidPositionException("La lista no pose los suficientes elementos para este comando: " + size);
        }
        addAfter(first(), e1);
        addBefore(last(), e2);
    }

    public boolean inPositionList(PositionList<E> p, E e1){
        boolean esta = false;
        for(E elem : p){
            if(elem.equals(e1)){
                esta = true;
            }
        }
        return esta;
    }
    public Iterable<E> ejercicio5(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> aux = new TDALista<>();
        for (Position<E> e1 : l1.positions()){
            if (inPositionList(l2, e1.element())){
                for(Position<E> e2: l2.positions()){
                    if(e1.element().equals(e2.element())){
                        aux.addLast(e2.element());
                        l2.remove(e2);
                    }
                }
            }
        }
        return aux;
    }    

    //Métodos y Clases privadas
    /**
     * Verifica que la lista no sea vacía, que la posición apunte a un nodo de
     * la lista (por ende, que no esté vacía ni sea nula) y que no apunte ni 
     * a la cabeza ni al rabo
     * @throws InvalidPositionException si alguna de las condiciones no se cumple
     * @return el nodo de tipo DNodo<E> al que apunta p
     */
    private DNodo<E> checkPosition(Position<E> p){
        
        try{
            DNodo<E> aux = (DNodo<E>) p;
            if (isEmpty()){
                throw new InvalidPositionException("La lista está vacía");
            }
            if( aux == null || aux.element() == null || aux == header ||
                aux == trailer){ 
                throw new InvalidPositionException("Posición nula o vacía");
            }
            return aux;
        }
        catch( ClassCastException e ) {  
            throw new InvalidPositionException( "La posición no es un nodo de la lista"); 
        }
    }
    private class ElementIterator implements Iterator<E>{
        //Atrubutos
        private DNodo<E> cursor;
        
        //Constructor
        public ElementIterator(){
            cursor = header.getNext();
        }

        //Métodos
        public boolean hasNext(){
            return cursor != trailer;
        }
        public E next(){
            E aux= cursor.element();
            cursor = cursor.getNext();
            return aux;
        }
    }

    private class PositionIterable implements Iterable<Position<E>>{
        //Método
        public Iterator<Position<E>> iterator(){
            return new PositionIterator();
        }
    }
    private class PositionIterator implements Iterator<Position<E>>{
        //Atrubutos
        private DNodo<E> cursor;
        
        //Constructor
        public PositionIterator(){
            cursor = header.getNext();
        }

        //Métodos
        public boolean hasNext(){
            return cursor != trailer;
        }
        public Position<E> next(){
            Position<E> aux= cursor;
            cursor = cursor.getNext();
            return aux;
        }
    }
}
