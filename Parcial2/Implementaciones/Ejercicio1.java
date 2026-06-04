package Parcial2.Implementaciones;

import Parcial2.Interfaces.BinaryTree;
import TP4.Excepciones.InvalidPositionException;
import TP5.Interfaz.*;
import TP5.Excepciones.*;
import TP5.Implementaciones.*;

public class Ejercicio1<K, V>{
    protected int size;
    protected final static int N = 13;
    protected TDALista<Entrada<K,V>> l[];
    //O(n/N) ya que en el peor de los casos solo recorrerá un bucket entero
    public int todas(K key){
        int contador = 0;
        int bucket = this.bucket(key);
        //O(n/N)
        //Supongo que la lista de buckets se llama l
        for(Entry<K, V> e : l[bucket]){
            if(e.getKey().equals(key)){
                contador++;
            }
        }
        return contador;
    }
    private int bucket(K key){
        if (key == null){
            throw new InvalidKeyException("La Key no es válida");
        }
        int llave = Math.abs(key.hashCode());
        return llave % N;
    }
}


class Ejercicio2<E>{
    public TDAMapeo<Character, Integer> eliminarHojas(BinaryTree<Character> arbol, Position<Character> p){
        if(p == null){
            throw new InvalidPositionException("Posición inválida"); 
        }
        TDAMapeo<Character, Integer> mp = new TDAMapeo<>();
        return posOrden(arbol, p, mp);
    }
    private TDAMapeo<Character, Integer> posOrden(BinaryTree<Character> arbol, Position<Character> p, TDAMapeo<Character, Integer> mp){
        if(arbol.hasLeft(p)){
            posOrden(arbol, arbol.left(p),mp);
        }
        if(arbol.hasRight(p)){
            posOrden(arbol, arbol.right(p), mp);
            }
        if(!arbol.hasLeft(p) && !arbol.hasRight(p)){
                if(mp.get(p.element()) == null){
                    mp.put(p.element(), 1);
                }
                else{
                    mp.put(p.element(), mp.get(p.element())+1);
                }
                arbol.removeNode(p);
            }
        
        return mp;
    }   
}