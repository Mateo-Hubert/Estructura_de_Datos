package TP1;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TesterConjunto {
    //Declaración de datos
    Conjunto<Integer> c1;
    Conjunto<Integer> c2;
    Conjunto<String> c3;
    //Código que se ejuta siempre antes de un Test
    @BeforeEach
    public void setUp(){
        c1 = new ConjuntoArreglo<>(3);
        c2 = new ConjuntoArreglo<>(-1);
        c3 = new ConjuntoArreglo<>(3);
    }
    @Test
    public void Test1(){
        //Probando meter datos en c1 y c3, y el isEmpty
        assertTrue(c1.isEmpty());
        c1.put(1); c1.put(2); c1.put(3);
        assertFalse(c1.isEmpty());

        assertTrue(c2.isEmpty());

        assertTrue(c3.isEmpty());
        c3.put("Hola"); c3.put("Mundo");
        assertFalse(c3.isEmpty());

        //Probando los comandos size y capacity
        assertEquals(c1.size(), 3);
        assertEquals(c1.capacity(), 3);
        
        assertEquals(c2.size(), 0);
        assertEquals(c2.capacity(), 10);

        assertEquals(c3.size(), 2);
        assertEquals(c3.capacity(), 3);

        //Probando los get y los pertenece
        assertEquals(1, c1.get(0));;
        assertTrue(c3.get(1).equals("Mundo"));

        assertTrue(c1.pertenece(1));
        assertFalse(c2.pertenece(100));
        assertFalse(c3.pertenece("Mondongo"));

        //Creando un conjunto auxiliar
        Conjunto<Integer> c4;
        //Usando intersección primero sin valores en común
        c2.put(7);
        c4 = c1.interseccion(c2); 
        assertTrue(c4.isEmpty());
        //Luego habiendo un valor en común
        c2.put(2);
        c4 = c1.interseccion(c2);
        assertFalse(c4.isEmpty());
        assertEquals(1, c4.size());
        assertEquals(2, c4.get(0));
    }
}
