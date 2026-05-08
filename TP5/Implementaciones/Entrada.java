package TP5.Implementaciones;
import TP5.Interfaz.Entry;

public class Entrada<K,V> implements Entry<K,V>{
    //Atributos
    protected K key;
    protected V value;

    //Constructor
    public Entrada(K key, V value){
        this.key = key;
        this.value = value;
    }

    //Métodos
    public void setKey(K key){
        this.key = key;
    }
    public K getKey(){
        return key;
    }
    public void setValue(V value){
        this.value = value;
    }
    public V getValue(){
        return value;
    }
}
