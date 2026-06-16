package Práctica_Recuperatorio.Parcial1_3;

import TP3.TDAPila;
import TP3.Stack;

/**
 * **Ejercicio 3:**
 * Escriba en Java un método llamado "eliminarDePila" tal que, dados como parámetros una pila de 
 * caracteres y un caracter, retorne otra pila de caracteres con los elementos en el mismo orden 
 * sin el caracter pasado por parámetro. 
 * Para resolver este ejercicio asuma que cuenta con la implementación del TDAPila completa.
 */
public class Ejercicio3 {
    public Stack<Character> eliminarDePila(Stack<Character> p, char c){
        Stack<Character> aux = new TDAPila<>();
        while(!p.isEmpty()){ //Doy vuelta la pila con una auxiliar
            aux.push(p.pop());
        }
        while(!aux.isEmpty()){
            if(aux.top().equals(c)){ //Revisa el elemento sin popearlo
                aux.pop(); //Popea sin guardar el elemento (eliminándolo)
            }
            else{
                p.push(aux.pop());//Popea guardando el elemento en la posición original aproximáda
            }
        }
        return p;
    }
    //Opción 2 si quiero dejar p intacta
    public Stack<Character> eliminarDePila2(Stack<Character> p, char c){
        Stack<Character> aux = new TDAPila<>();
        while(!p.isEmpty()){ //Doy vuelta la pila con una auxiliar
            aux.push(p.pop());
        }
        Stack<Character> pila = new TDAPila<>(); //Otra pila auxiliar para que al final p no resulte modificada
        while(!aux.isEmpty()){
            p.push(aux.top()); //Devuelvo los elementos a la pila sin modificar aux
            if(aux.top().equals(c)){ //Revisa el elemento sin popearlo
                aux.pop(); //Popea sin guardar el elemento (eliminándolo)
            }
            else{
                pila.push(aux.pop());//Popea guardando el elemento en la posición original aproximáda
            }
        }

        return pila;
    }
}
