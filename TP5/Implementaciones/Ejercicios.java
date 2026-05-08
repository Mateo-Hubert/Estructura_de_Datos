package TP5.Implementaciones;

import TP5.Interfaz.*;
import java.util.Iterator;

@SuppressWarnings({"Uncheked", "Unused"})

public class Ejercicios<K,V> {
    
    //Métodos
//Ejercicio 1) a. Es O(n)
    public PositionList<Pair<Integer, Integer>> ejercicio1(TDAMapeo<Integer,Integer> m1, TDAMapeo<Integer,Integer> m2){
        
        TDALista<Pair<Integer, Integer>> lista = new TDALista<>();
        //For each con creación del iterador dentro -> O(n) + O(n) = O(n)
        for (Entry<Integer,Integer> e1 : m1.entries()){
        //Todas las líneas dentro del for son asignaciónes o cálculos simples
        // por lo que son O(1) por lo que no afectan al órden del for
            Integer v2 = m2.get(e1.getKey());
            if (v2 != null && !v2.equals(e1.getValue())){

                lista.addLast(new Pair<>(e1.getKey(), e1.getValue()));
                lista.addLast(new Pair<>(e1.getKey(), v2));
            }
        }
        //Return es O(1)
        return lista;
    }
    //Ejercicio 1) b. O(n) + O(1) + O(n) = O(n)
    public boolean ejercicio2(TDAMapeo<K,V> m1, TDAMapeo<K,V> m2){
        //Asignación: O(1)
        boolean aux = true;
        //keys = O(n)
        Iterator<K> it = m1.keys().iterator();
        //O(n)
        while(it.hasNext() && aux == true){
            K k1 = it.next();
            V value = m2.get(k1);
            if(value == null){
                aux = false;
            }
        }
        return aux;
    }
    //1) c. O(n) + O(n) = O(n). Se suman porque estan en secuencia
    // En lugar de anidados (cuyo caso sería O(n^2))
    public Dictionary<K,V> acomodar (Dictionary<K,V>  d){

        Diccionario<K,V> diccionario = new Diccionario<>();
        TDAMapeo<K,V> aux = new TDAMapeo<>();
        //For es O(n), aunque tenga d.entries que también es O(n)
        //Se ejecuta en secuencia por lo que queda O(n) + O(n) = O(n)
        for(Entry<K,V> e : d.entries()){
            aux.put(e.getKey(), e.getValue());
        }
        //Lo mismo que arriba
        for(Entry<K,V> e : aux.entries()){
            diccionario.insert(e.getKey(), e.getValue());
        }

        return diccionario;
    }
}
