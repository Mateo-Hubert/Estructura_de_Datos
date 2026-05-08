package TP5.Implementaciones;
//import TP5.Implementaciones.TDALista;
class Main{
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
/* 
    System.out.println("l2 antes:");
    for (Character c : l2) System.out.print(c + " ");
    System.out.println();

    // Llamada al método - ajustá según dónde lo tengas
    Iterable<Character> eliminados = l2.ejercicio5(l1, l2);

    System.out.println("l2 después:");
    for (Character c : l2) System.out.print(c + " ");
    System.out.println();

    System.out.println("Eliminados:");
    for (Character c : eliminados) System.out.print(c + " ");
    System.out.println();
    */
}
}