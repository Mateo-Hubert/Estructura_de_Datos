package Práctica_Recuperatorio.Parcial2_2;

import TP6.Implementaciones.*;
import TP4.Excepciones.InvalidPositionException;
import TP5.Implementaciones.TDALista;
import TP5.Interfaz.Position;
import TP5.Interfaz.PositionList;

/**
 * Ejercicio2
 * a) Implemente la clase `TNodo<E>` para representar un nodo de un árbol general tal como se vio en clase. 
 * Implemente sólo la signatura, atributos y constructor/es.
 * b) Suponga que cuenta con la clase `Arbol<E>` que implementa la interfaz `Tree<E>` vista en clase. 
 * Escriba un método tal que reciba una posición p y un rótulo r y agregue a todas las posiciones del 
 * subárbol determinado por p un nuevo hijo al final con rótulo r. Cuenta con total acceso a la estructura. 
 * Si utiliza otros métodos del TDAArbol deberá implementarlos.
   public class Arbol<E> implements Tree<E>{
    protected TNodo<E> raiz;
    protected int cantElems;
    ...}
 * c) Indique el orden del tiempo de ejecución de su solución. Justifique adecuadamente, puede hacerlo en lenguaje coloquial.
 */

public class Ejercicio2 {
    class TNodo<E> implements Position<E>{
        E element;
        TNodo<E> padre;
        PositionList<TNodo<E>> hijos;

        public TNodo(E element){
            this.element = element;
            padre = null;
            hijos = new TDALista<>();
        }

        public E element(){
            return element;
        }
        public void setElement(E element){
        }
        public TNodo<E> padre(){
            return padre;
        }
        public void setPadre(TNodo<E> padre){
        }
        public PositionList<TNodo<E>> hijos(){
            return hijos;
        }
    }

    class A<E> extends Arbol<E>{
        protected TNodo raiz;
        protected int cantElems;
        // Este método es de orden O(n) ya que en el peor de los casos, p = raíz por lo que el subarbol 
        // es precisamente el mismo arbol original
        public void addSubArbol(Position<E> p, E r){
            if(p == null || !(p instanceof TNodo<?>)){
                throw new InvalidPositionException("La posición no es válida");
            }
            TNodo<E> n = (TNodo<E>)p;
            if(!(n.hijos().isEmpty())){
                for(TNodo<E> t: n.hijos()){
                    addSubArbol(t, r);
                }
            }
            TNodo<E> h = new TNodo<>(r);
            h.setPadre(n);
            n.hijos().addLast(h);
            cantElems++;
        }
    }
}
