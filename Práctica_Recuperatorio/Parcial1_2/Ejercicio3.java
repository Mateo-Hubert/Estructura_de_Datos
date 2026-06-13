package Práctica_Recuperatorio.Parcial1_2;

import TP5.Implementaciones.*;
import TP5.Interfaz.*;

/**
 * Ejercicio 3:
 *Escriba en Java un método que reciba una lista de caracteres y retorne un diccionario con los caracteres que están en 
 *la lista y las posiciones en los que aparecen. Si la lista se encuentra vacía, se deberá retornar un diccionario vacío. 
 *Su signatura debe ser public Dictionary<Character,Position<Characters>> caracteresYposiciones(PositionList<Characters> l). 
 *Resuelva el problema en función de los TDAs Lista con Posición y Diccionario.
 */
public class Ejercicio3 {
    public Dictionary<Character,Position<Character>> caracteresYposiciones(PositionList<Character> l){
        Dictionary<Character, Position<Character>> d = new Diccionario<>();
        for(Position<Character> p : l.positions()){
            d.insert(p.element(), p);
        }
        return d;
    }
}
