package TP2;
import java.util.Queue;
import java.util.LinkedList;
abstract class Problema2{
    public Queue<Integer> colaImpar(Queue<Integer> c){
        Queue<Integer> cI = new LinkedList<>();
        while (!(c.isEmpty())){
            if (c.peek() % 2 != 0) {
                cI.offer(c.poll()); 
            }
            else{
                c.poll();
            }
        }
        return cI;
    }
}