package TP2;
import java.util.Arrays;
import java.util.Stack;
@SuppressWarnings("unused")
class Persona{
//Atributos de instancia
    private String nombre;
    private double peso;
    private double altura;
//Constructor
    public Persona(String nombre, double peso, double altura){
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
    }
//Métodos
    public void invertir(Persona[] p){
        //Creo una pila auxiliar
        Stack<Persona> pila = new Stack<>();
        //"Envuelvo" temporalmente a p como una lista, y le asigno 
        // todos los elementos que almacena a la pila en el mismo 
        // orden
        pila.addAll(Arrays.asList(p));
        int pilaSize = pila.size();
        //Con un for tomo el último elemento de la pila y lo 
        //ingreso en p a la vez que se borra de la misma, 
        //haciendo que p se sobreescriba pero en el orden inverso
        for (int i = 0; i < pilaSize; i++){
            p[i] = pila.pop(); 
        }
    }
}