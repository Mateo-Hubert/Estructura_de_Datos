package TP2;
import java.util.Queue;
//import java.util.LinkedList;
public abstract class Problema4<E> {
//Método
    public Integer maximo(Queue<Integer> c){
        int m;
        if(!c.isEmpty()){
            m = c.peek().intValue();
            for(Integer num : c){ 
            m = Math.max(m, num.intValue());
            }
        }
        else{
            m = 0;
        }
        return m;
    }
}
