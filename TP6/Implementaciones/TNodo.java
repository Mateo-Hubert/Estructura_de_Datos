package TP6.Implementaciones;

import TP5.Interfaz.Position;
import TP5.Interfaz.PositionList;
import TP5.Implementaciones.TDALista;

public class TNodo<E> implements Position<E>{
    //Atributos
    protected E elemento;
    protected PositionList<TNodo<E>> hijos;
    protected TNodo<E> padre;

    //Constructor
    public TNodo(E elemento, TNodo<E> padre){
        this.elemento = elemento;
        this.padre = padre;
        this.hijos = new TDALista<>();
        
    }
    public TNodo(E elemento){
        this.elemento = elemento;
        this.padre = null;
        this.hijos = new TDALista<>();
    }

    //Métodos
    public E element(){
        return elemento;
    }
    public void setElement(E elemento){
        this.elemento = elemento;
    }

    public TNodo<E> getPadre(){
        return padre;
    }
    public void setPadre(TNodo<E> padre){
        this.padre = padre;
    }

    public void addHijo(TNodo<E> hijo){
        hijos.addFirst(hijo);
    }
    public PositionList<TNodo<E>> getHijos(){
        return hijos;
    }
    
}
