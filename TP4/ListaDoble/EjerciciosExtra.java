/*package ListaDoble;
import Interfaz.Position;
import Interfaz.PositionList;

public class EjerciciosExtra<E> {

    public boolean inPositionList(PositionList<E> p, E e1){
        boolean esta = false;
        for(E elem : p){
            if(elem.equals(e1)){
                esta = true;
            }
        }
        return esta;
    }
    public int cantPositionList(PositionList<E> p, E e1){
        int cont = 0;
        for(E elem : p){
            if(elem.equals(e1)){
                cont++;
            }
        }
        return cont;
    }
    public boolean atLeastNTimes(PositionList<E> p, E x, int n){
        int cont = 0;
        for(E elem : p){
            if(elem == x){
                cont++;
            }
        }
        return cont >= n;
    }

    public PositionList<E> doublePositionList(PositionList<E> I){
        PositionList<E> p = new TDALista<>();
            for(E elem : I){
                p.addLast(elem);
                p.addLast(elem);
            }
        return p;
    }
    public Iterable<E> ejercicio5(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> aux = new TDALista<>();
        for (Position<E> e1 : l1.positions()){
            if (inPositionList(l2, e1.element())){
                aux.addLast(e1.element());
                l2.remove(e1);
            }
        }
        return aux;
    }



public static void main(String[] args) {
    TDALista<Character> l1 = new TDALista<>();
    TDALista<Character> l2 = new TDALista<>();

    l1.addLast('A');
    l1.addLast('B');
    l1.addLast('C');

    l2.addLast('B');
    l2.addLast('D');
    l2.addLast('B');
    l2.addLast('E');
    l2.addLast('A');

    System.out.println("l2 antes:");
    for (Character c : l2) System.out.print(c + " ");
    System.out.println();

    // Llamada al método - ajustá según dónde lo tengas
    Iterable<Character> eliminados = ejercicio5(l1, l2);

    System.out.println("l2 después:");
    for (Character c : l2) System.out.print(c + " ");
    System.out.println();

    System.out.println("Eliminados:");
    for (Character c : eliminados) System.out.print(c + " ");
    System.out.println();
}
}
*/