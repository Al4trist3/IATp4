import java.util.*;

class Nodo {
    String nombre;
    int peso;
    List<Nodo> vecinos;

    Nodo(String nombre, int peso) {
        this.nombre = nombre;
        this.peso = peso;
        this.vecinos = new ArrayList<>();
    }

    void agregarVecino(Nodo nodo) {
        vecinos.add(nodo);
    }
}

public class BusquedaExhaustiva {
    static List<String> camino = new ArrayList<>();

    public static void busquedaProfundidad(Nodo nodo, String objetivo) {
        camino.add(nodo.nombre);
        if (nodo.nombre.equals(objetivo)) {
            System.out.println("Camino encontrado: " + camino);
            return;
        }
        for (Nodo vecino : nodo.vecinos) {
            if (!camino.contains(vecino.nombre)) {
                busquedaProfundidad(vecino, objetivo);
            }
        }
        camino.remove(camino.size() - 1);
    }

    public static void main(String[] args) {
        Nodo A = new Nodo("A", 7);
        Nodo B = new Nodo("B", 6);
        Nodo C = new Nodo("C", 6);
        Nodo D = new Nodo("D", 4);
        Nodo E = new Nodo("E", 5);
        Nodo G = new Nodo("G", 3);
        Nodo H = new Nodo("H", 2);
        Nodo K = new Nodo("K", 3);
        Nodo L = new Nodo("L", 2);
        Nodo T = new Nodo("T", 0);

        // Conexiones
        A.agregarVecino(B);
        A.agregarVecino(C);
        B.agregarVecino(D);
        B.agregarVecino(E);
        C.agregarVecino(E);
        D.agregarVecino(G);
        D.agregarVecino(H);
        E.agregarVecino(H);
        G.agregarVecino(K);
        H.agregarVecino(K);
        H.agregarVecino(L);
        K.agregarVecino(T);
        L.agregarVecino(T);

        busquedaProfundidad(A, "T");
    }
}
