package Práctica_Recuperatorio.Parcial2;
import TP5.Implementaciones.TDALista;
import TP5.Interfaz.PositionList;
import TP5.Interfaz.Position;
import TP5.Excepciones.InvalidPositionException;
import java.util.Iterator;

public class Ejercicio2 {
    class TNodo<E> implements Position<E>{
        //Atributos
        E element;
        TNodo<E> padre;
        PositionList<TNodo<E>> hijos;
        
        //Constructor
        public TNodo(E element){
            this.element = element;
            padre = null;
            hijos = new TDALista<>();
        }

        //Métodos
        public E element(){
            return element;
        }
        public void setElement(E e){
            element = e;
        }
        public TNodo<E> padre(){
            return padre;
        }
        public void setPadre(TNodo<E> p){
            padre = p;
        }
        public PositionList<TNodo<E>> hijos(){
            return hijos;
        }
        public void setHijo(TNodo<E> h){
            hijos.addLast(h);
        }
    }
    class Arbol<E>{
        TNodo<E> raiz;
        int cant;

        public Iterable<E> eliminarDescendientes(Position<E> p) throws InvalidPositionException{
            TNodo<E> t = checkPosition(p);
            TDALista<E> lista = new TDALista<>();

            return eliminador(t, lista);
        }
        public TDALista<E> eliminador(TNodo<E> t, TDALista<E> l){
            
            if(!(t.hijos().isEmpty())){
                for(TNodo<E> hijo: t.hijos()){
                    eliminador(hijo,l);
                    l.addLast(hijo.element());
                    this.removeExternalNode(hijo);
                }
            }
            return l;
        }
        public void removeExternalNode(Position<E> p){
            TNodo<E> t = checkPosition(p);
            if(!(t.hijos().isEmpty())){
                throw new InvalidPositionException("La posición no es una hoja");
            }
            t.setElement(null);
            if(t == raiz){
                raiz = null;
            }
            else{
                Position<TNodo<E>> pos = null;
                Iterator<Position<TNodo<E>>> it = t.padre().hijos().positions().iterator();
                while(it.hasNext() && pos == null){
                    Position<TNodo<E>> pt = it.next();
                    if(pt.element() == t){
                        pos = pt;
                    }
                }
                t.padre().hijos().remove(pos);
                t.setPadre(null);
            }
            cant--;
        }
        public TNodo<E> checkPosition(Position<E> p){
            if(this.cant == 0 || p == null || !(p instanceof TNodo<?>)){
                throw new InvalidPositionException("El árbol está vacío o la posición es nula");
            }
            return (TNodo<E>)p;
        }
    }
}
