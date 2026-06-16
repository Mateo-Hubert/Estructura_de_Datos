package Práctica_Recuperatorio.Parcial1_3;

import TP4.ListaDoble.TDALista;
import TP4.Interfaz.PositionList;

/**
 * **Ejercicio 2:**
 * Escriba en Java un método tal que dadas dos listas genéricas L1 y L2 retorne una lista L3 con 
 * los elementos que se encuentren tanto en L1 como en L2. 
 * Asuma que cuenta con la implementación del TDALista completa. 
 * Compare los elementos por equivalencia.
 */
public class Ejercicio2<E>{
    //Nota: asumo que ninguna de las dos listas tiene elementos repetidos, de lo contrario,
    public PositionList<E> l1Enl2(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> l3 = new TDALista<>();
        for(E e : l1){
            for(E e2 : l2){
                if(e.equals(e2)){
                    l3.addLast(e);
                }
            }
        }
        return l3;
    }
}