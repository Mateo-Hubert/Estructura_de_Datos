package TP1;
public interface Conjunto<E> {
    int size();
    int capacity();
    boolean isEmpty();
    E get(int i);
    void put(E elem);
    boolean pertenece(E elem);
    Conjunto<E> interseccion(Conjunto<E> c);

    
}