package Práctica_Recuperatorio.Parcial2;

import TP5.Excepciones.*;
import TP5.Interfaz.*;
import TP5.Implementaciones.*;
import java.util.Iterator;


public class DiccionaroHashAbierto<K,V> extends Diccionario<K,V> {
    protected static final float fc=0.7f;
    protected PositionList<Entrada<K,V>>[] buckets;
    protected int n;
    protected int N;

    public boolean todas(K k, V v){
        if(this.isEmpty() || k == null){
            throw new InvalidKeyException("La clave es nula");
        }
        Iterator<Entry<K,V>> it = this.findAll(k).iterator();
        boolean aux = it.hasNext();
        while (it.hasNext() && aux){
        if(!it.next().getValue().equals(v)){
                aux = false;
            }
        }
        return aux;
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public Iterable<Entry<K,V>> findAll(K key){
        if(isEmpty() || key == null){
            throw new InvalidKeyException("Diccionrario vacío o llave nula");
        }
        int i = Math.abs(key.hashCode()) % N;
        PositionList<Entry<K,V>> all = new TDALista<>();
        for(Entry<K,V> e: buckets[i]){
            if(e.getKey().equals(key)){
                all.addLast(e);
            }
        }
        return all;
    }
}