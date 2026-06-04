package Práctica_Recuperatorio.Parcial1;
//import TP5.Implementaciones.*;
import TP5.Interfaz.PositionList;
import TP4.Interfaz.Queue;
import TP4.PilasColasSimples.TDACola;
import TP3.Stack;
class Ejercicio3{
    public static Queue<Integer> filtrarYReordenar(PositionList<Integer> lista){
        Queue<Integer> cola = new TDACola<>();
        Stack<Integer> aux = new TDAPila<>();
        for(Integer i: lista){
            if((i % 2) == 0){
                cola.enqueue(i);
            }
            else{
                aux.push(i);
            }
        }
        while(!aux.isEmpty()){
            Integer elem = aux.pop();
            cola.enqueue(elem);
        }
        return cola;
    }
}