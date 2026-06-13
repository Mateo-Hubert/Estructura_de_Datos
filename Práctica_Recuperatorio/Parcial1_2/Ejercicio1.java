package Práctica_Recuperatorio.Parcial1_2;

import TP4.Interfaz.*;
import TP4.ListaDoble.*;
import TP5.Excepciones.EmptyListException;

/**
 * Siguiente parcial: **Ejercicio 1:**
 * Suponga que cuenta con la clase `ListaDE<E>` que implementa la interfaz `PositionList<E>` vista en clase. 
 * Esta clase implementa una lista doblemente enlazada con enlaces al primer y último elemento con nodos centinelas. 
 * Agregue un método a esta clase con la siguiente signatura: **public PositionList dividirLista(Position pos)**. 
 * Este método deberá modificar la lista receptora del mensaje de modo tal que desde la posición *pos* en adelante se eliminen 
 * todos sus nodos (*pos* inclusive). 
 * Los nodos eliminados deberán conformar una nueva lista que será la que retorne el método. 
 * Además, deberá lanzar la excepción correspondiente en caso de que la lista esté vacía. 
 * Requiere que el parámetro *pos* sea válido (que esté ligado y que la posición sea de la lista receptora del mensaje). 
 * Si utiliza otros métodos de la clase `ListaDE<E>` deberá implementarlos.
 */
public class Ejercicio1 {
    class ListaDE<E> extends TDALista<E>{
        protected DNodo<E> head;
        protected DNodo<E> tail;
        protected int size;
        public PositionList<E> dividirLista(Position<E> pos){
            if(isEmpty()){
                throw new EmptyListException("La lista se encuentra vacía");
            }
            PositionList<E> lista = new ListaDE<>();
            Position<E> iterador = pos;
            while(iterador != tail){
                lista.addLast(iterador.element());
                iterador = next(iterador);
                this.remove(prev(iterador));
            }
            return lista;
        }
        public boolean isEmpty() {
            return this.size == 0;
        }
        public void addLast(E e){
            DNodo<E> n = new DNodo<>(e);
            tail.getPrev().setNext(n);
            tail.setPrev(n);
            size++;
        }
        public Position<E> next(Position<E> p){
            return ((DNodo<E>)p).getNext();
        }
        public Position<E> prev(Position<E> p){
            return ((DNodo<E>)p).getPrev();
        }
        public E remove(Position<E> p){
            E e = p.element();
            DNodo<E> n = (DNodo<E>)(p);
            DNodo<E> siguiente = n.getNext();
            DNodo<E> previo = n.getPrev();
            siguiente.setPrev(previo);
            previo.setNext(siguiente);
            n.setElement(null);
            n.setNext(null);
            n.setPrev(null);
            size--;
            return e;
        }

    }
}
