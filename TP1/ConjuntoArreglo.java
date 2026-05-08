package TP1;
@SuppressWarnings("unchecked")
class ConjuntoArreglo<E> implements Conjunto<E>{
//Atributos de instancia
    private int capacity;
    private int cant;
    private E[] conjunto;
//Constructor
    public ConjuntoArreglo (int capacity){
        //Chequeo que capacidad no sea negativo o 0
        if (capacity > 0) {
            this.capacity = capacity;}
        else {this.capacity = 10;
        }
        //Inicializo el conjunto
        this.conjunto = (E[]) new Object[this.capacity];
        this.cant = 0;
    }
//Métodos
    public int size(){
        return cant;
    }
    public int capacity(){
        return capacity;
    }
    public boolean isEmpty(){
        return (cant == 0);
    }
    public E get(int i){
        return conjunto[i];
    }
    public void put (E elem){
        conjunto[cant++] = elem;
    }
    public boolean pertenece(E elem){
        //Creo una variable auxiliar
        boolean p = false;
        //Si elem no es nulo y cant no está vacío recorro todo 
        //conjunto para ver si hay algún elemento igual a elem
        if (elem != null && cant != 0){
            for (int i = 0; i < (cant) && !p; i++){
                p = this.get(i).equals(elem);
            }
        }
        return p;
    }
    //Versión recursiva (pésima opción, la normal es infinitamente mejor)
    public boolean pRecursivo (E elem, int i){
        boolean p = false;
        if (i < this.size() && this.get(i).equals(elem)){
        p = true;
        }
        else{ 
            if(i + 1 < this.size()){
                p = pRecursivo(elem, i+1);
            }
        }
        return p;
    }
    //Fin
    public Conjunto<E> interseccion(Conjunto<E> c) {
        //Creo un nuevo conjunto
        Conjunto<E> Nconjunto = new ConjuntoArreglo<E>(this.capacity());
        //Verifico que c no esté vacío ya que la interseccion entre
        //un conjunto común y un conjunto vacío da como resultado 
        //un conjunto vacío
        if (!c.isEmpty()){
            for(int i = 0; i < this.size(); i++){
                //si hay un elemento tanto en c como en este conjunto
                //lo agrego al conjunto nuevo
                if (c.pertenece(this.get(i))){
                    Nconjunto.put(this.get(i));
                }
            }
        }
        return Nconjunto;
    }
}