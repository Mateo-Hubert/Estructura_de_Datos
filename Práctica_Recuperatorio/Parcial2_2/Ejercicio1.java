package Práctica_Recuperatorio.Parcial2_2;

import TP5.Implementaciones.*;
import TP5.Interfaz.*;

/**
 * Suponga que cuenta con la clase Diccionario<K,V> que implementa la interfaz Dictionary<K,V> vista en clase. 
 * Esta clase implementa un diccionario utilizando hash abierto. 
 * Agregue un método a esta clase con la siguiente signatura: public int cantEntradas(K key, V value). 
 * Este método deberá contar la cantidad de entradas con clave key que hay en el diccionario. 
 * Comparar claves por equivalencia. 
 * Si utiliza otros métodos de la clase Diccionario<K,V> deberá implementarlos.
 */
class Ejercicio1 {
    class D<K,V> extends Diccionario<K,V>{
        protected PositionList<Entrada<K,V>>[] a;
        protected int n;
        protected int N;
        protected static final float fc = 0.5f;
        //En promedio es orden (n/N) que tiende a O(1) 
        public int cantEntradas(K key, V value){
            if(key == null){
                throw new NullPointerException();
            }
            int cant = 0;
            int bucket = Math.abs(key.hashCode()) % N;

            for(Entrada<K,V> e : a[bucket]){
                if(e.getValue() == value){
                    cant++;
                }
            }
            return cant;
        }
    }
}