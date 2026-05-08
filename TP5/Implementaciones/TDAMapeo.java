package TP5.Implementaciones;

import TP5.Interfaz.Map;
import TP5.Interfaz.Position;
import TP5.Interfaz.Entry;
import TP5.Excepciones.InvalidKeyException;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class TDAMapeo<K,V> implements Map<K,V>{
    //Atributos
    protected static final int N = 13;
    protected TDALista<Entrada<K,V>>[] l;
    protected int size;

    //Constructor
    public TDAMapeo(){
        l = new TDALista[N];
        for(int i = 0; i < N; i++){
            l[i] = new TDALista<Entrada<K,V>>();
        }
        size = 0;
    }
    //Métodos
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    //Get usando un for-each
    /** 
        public V get(K key){
            V aux = null;
            if (key == null){
                throw new InvalidKeyException("La llave es nula");
            }
            int bucket = this.hash(key);
            for (Entrada<K,V> e: l[bucket]){
                if(e.getKey().equals(key)){
                aux = e.getValue(); 
                }
            }
            return aux;
        }
    */
    //get usando un while
    public V get(K key){
        if (key == null){
            throw new InvalidKeyException("La llave es nula");
        }
        //Variables auxiliares
            V aux = null;

            //Busco en que bucket está el valor 
            int bucket = this.hash(key);
            
            //Creo un iterador para buscar dentro del bucket
            Iterator<Entrada<K,V>> it = l[bucket].iterator();
        
        //Bucle para recorrer el bucket
        while(it.hasNext() && aux == null){
            Entrada<K,V> e = it.next();
            if(e.getKey().equals(key)){
                aux = e.getValue();
            }
        }
        return aux;
    }

    public V put(K key, V value){
        if(key == null){
            throw new InvalidKeyException("Llave inválida");
        }
        //Variables auxilares 
        V valor = null;
        int bucket = hash(key);
        Iterator<Entrada<K,V>> it = l[bucket].iterator();

        // Misma lógica que en el get, solo que, si hay una entrada 
        // con la misma key, reemplazo su valor y devuelvo el viejo
        while(it.hasNext() && valor == null){
            Entrada<K,V> e = it.next();
            if(e.getKey().equals(key)){
                valor = e.getValue();
                e.setValue(value);          
            }
        }
        //Si no está en el bucket, la agrego
        if(valor == null){
            l[bucket].addLast(new Entrada<K,V>(key, value));
            size++;

        }
        return valor;
    }

    public V remove(K key){
        if (key == null){
            throw new InvalidKeyException("La llave es nula");
        }
        //Variables auxiliares
            V aux = null;
            int bucket = this.hash(key);
            Iterator<Position<Entrada<K,V>>> it = l[bucket].positions().iterator();
        
        //Bucle para recorrer el bucket
        while(it.hasNext() && aux == null){
            Position<Entrada<K,V>> e = it.next();
            if(e.element().getKey().equals(key)){
                aux = e.element().getValue();
                l[bucket].remove(e);
                size--;
            }
        }
        return aux;
    }
    //O(n)
    public Iterable<K> keys(){
        //O(1)
        TDALista<K> aux = new TDALista<>();
        //O(N), como N es una constante (13) queda O(1)
        for (int i = 0; i < N; i++){
            //O(1)
            Iterator<Position<Entrada<K,V>>> it = l[i].positions().iterator();
            //O(n)
            while(it.hasNext()){
                //O(1)
                Position<Entrada<K,V>> e = it.next();
                //O(1)
                aux.addLast(e.element().getKey());
            }
        }
        //O(1)
        return aux;
    }

    public Iterable<V> values(){
        TDALista<V> aux = new TDALista<>();
        for (int i = 0; i < N; i++){
            Iterator<Position<Entrada<K,V>>> it = l[i].positions().iterator();
            while(it.hasNext()){
                Position<Entrada<K,V>> e = it.next();
                aux.addLast(e.element().getValue());
            }
    }
    return aux;
    }
        
    public Iterable<Entry<K,V>> entries(){
        TDALista<Entry<K,V>> aux = new TDALista<>();
        for (int i = 0; i < N; i++){
            Iterator<Position<Entrada<K,V>>> it = l[i].positions().iterator();
            while(it.hasNext()){
                Position<Entrada<K,V>> e = it.next();
                aux.addLast(e.element());
            }
        }
        return aux;
    }

    //Métodos privados
    private int hash(K key){
        int llave = Math.abs(key.hashCode());
        return (llave % N);
    }
}

