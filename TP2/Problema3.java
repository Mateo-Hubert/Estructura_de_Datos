package TP2;
import java.util.Stack;
public abstract class Problema3<E> {
    public Stack<E> intercalar(Stack<E> p1, Stack<E> p2){
        Stack<E> p3 = new Stack<>();
        //Mientras alguna de las dos pilas no esté vacía, el
        //bucle continua
        while ((!p1.empty()) || (!p2.empty())){
            //Si p1 no está vacía, mete el último elemento de
            //la pila p1 en la pila p3
            if(!p1.empty()){
                p3.push(p1.pop());

                }
            //Lo mismo pero con p2 en lugar de p1
            if(!p2.empty()){
                p3.push(p2.pop());

                }
            }
        return p3;
    }
}
