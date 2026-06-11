package Práctica_Recuperatorio.Parcial2;

import TP5.Implementaciones.*;
import TP5.Interfaz.*;
import TP6.Interfaz.*;
import TP6.Implementaciones.*;

class Ejercicio3{
class ArbolB extends ArbolBinario<String>{
    
    @SuppressWarnings("unchecked")
    public Map<String,Integer> analisis(BinaryTree<String> b){
        TDAMapeo<String,Integer> m = new TDAMapeo<String,Integer>();
        m.put("Pares", 0);
        m.put("Impares", 0);
        m.put("Operadores", 0);
        return mapeo(m,b, b.root());
    }
    private Map<String, Integer> mapeo(Map<String, Integer> m, BinaryTree<String> b, Position<String> p){
        if (p != null){
            if(b.hasLeft(p)){
                mapeo(m,b,b.left(p));
            }
            if (p.element().equals("+") || p.element().equals("-") || p.element().equals("*") || p.element().equals("/")){
                m.put("Operadores", m.get("Operadores")+1);
            }
            else{
                int i = Integer.parseInt(p.element());
                if (i % 2 == 0) {
                    m.put("Pares", m.get("Pares")+1);
                }
                else{
                    m.put("Impares", m.get("Impares")+1);
                }
            }
            if(b.hasRight(p)){
                mapeo(m,b,b.right(p));
            }
        }
        return m;
    }
}
}