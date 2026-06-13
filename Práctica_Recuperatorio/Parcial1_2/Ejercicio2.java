package Práctica_Recuperatorio.Parcial1_2;

import TP3.*;
import TP4.Interfaz.Queue;

/**
 * Ejercicio 2:
Implemente un método tal que dada una pila de caracteres p y una cola de caracteres q, agregue los elementos de q en p en su parte de abajo. 
Resuelva este ejercicio en términos de los TDA pila y cola. Por ejemplo:
 */
public class Ejercicio2<E> {
    public void fusion(Stack<E> p, Queue<E> q){
        Stack<E> s = new TDAPila<>();
        while(!p.isEmpty()){
            s.push(p.pop());
        }
        while(!q.isEmpty()){
            p.push(q.dequeue());
        }
        while(!s.isEmpty()){
            p.push(s.pop());
        }
    }
}
