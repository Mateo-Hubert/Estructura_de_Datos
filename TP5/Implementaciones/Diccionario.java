package TP5.Implementaciones;

import TP5.Excepciones.InvalidKeyException;
import TP5.Excepciones.InvalidEntryException;
import TP5.Interfaz.*;
import java.util.Iterator;


@SuppressWarnings("unchecked")
public class Diccionario<K,V> implements Dictionary<K,V>{
    //Atributos
    protected int size;
    protected final static int N = 13;
    protected TDALista<Entrada<K,V>> l[];

    //Constructor:
    public Diccionario(){
        l = new TDALista[N];
        for(int i = 0; i < N; i++){
            l[i] = new TDALista<Entrada<K,V>>();
        }
        size = 0;
    }

    //Métodos:
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public Entry<K,V> find(K key){
        if (key == null){
            throw new InvalidKeyException("No se permiten keys nulas");
        }
        Entrada<K,V> entrada = null;
        Iterator<Entrada<K,V>> it = l[bucket(key)].iterator();

        while(it.hasNext() && entrada == null){
            Entrada<K,V> e = it.next();
            if (e.getKey().equals(key)){
                entrada = e;
            }
        }
        return entrada;
    }
    public Iterable<Entry<K,V>> findAll(K key){
        if (key == null){
            throw new InvalidKeyException("No se permiten keys nulas");
        }
        TDALista<Entry<K,V>> all = new TDALista<>();

        for (Entry<K,V> e : l[bucket(key)]){
            if(e.getKey().equals(key)){
                all.addLast(e);
            }
        }
        return all;
    }
    public Entry<K,V> insert(K key, V value){
        if (key == null){
            throw new InvalidKeyException("No se permiten keys nulas");
        }
        Entrada<K,V> e = new Entrada<>(key, value);
        l[bucket(key)].addLast(e);
        size++;
        return e;
    }
	public Entry<K,V> remove(Entry<K,V> e){
        if(e == null){
            throw new InvalidEntryException("No se pueden remover entrada nulas");
        }
        Entrada<K,V> entrada = null;
        Iterator<Position<Entrada<K,V>>> it = l[bucket(e.getKey())].positions().iterator();

        while(it.hasNext() && entrada == null){
            Position<Entrada<K,V>> aux = it.next();
            if(aux.element().getKey().equals(e.getKey()) &&
                aux.element().getValue().equals(e.getValue())){
                entrada = aux.element();
                l[bucket(e.getKey())].remove(aux);
                size--;
            }
        }
        if(entrada == null){
            throw new InvalidEntryException("La entrada no pertenece al diccionario");
        }
        return entrada;
    }
    public Iterable<Entry<K,V>> entries(){
        TDALista<Entry<K,V>> lista = new TDALista<>();
        for(int i = 0; i < l.length; i++){
            for(Entry<K,V> e :l[i]){
                lista.addLast(e);
            }
        }
        return lista;
    }
    //Punto 5) a. O(n)
    Iterable<Entry<K,V>> eliminarTodas(K c,V v) {
        if (c == null){
            throw new InvalidKeyException("La key es inválida");
        }
        TDALista<Entry<K,V>> lista = new TDALista<>();
        for(Position<Entrada<K,V>> e : l[bucket(c)].positions()){
            if(e.element().getKey().equals(c) && e.element().getValue().equals(v)){
                lista.addLast(e.element());
                l[bucket(c)].remove(e);
                size--;
            }
        }
        return lista;
    }

    //Métodos privados
    private int bucket(K key){
        int aux = Math.abs(key.hashCode());
        return aux % N;
    }
}
