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

class Estado implements Comparable<Estado> {
    Nodo nodo;
    int costoTotal;
    Estado padre; // Para rastrear el camino

    Estado(Nodo nodo, int costoTotal, Estado padre) {
        this.nodo = nodo;
        this.costoTotal = costoTotal;
        this.padre = padre;
    }

    @Override
    public int compareTo(Estado otro) {
        return Integer.compare(this.costoTotal, otro.costoTotal);
    }
}

public class BusquedaHeuristica {
    public static void busquedaAEstrella(Nodo inicio, String objetivo) {
        PriorityQueue<Estado> frontera = new PriorityQueue<>();
        Set<String> explorados = new HashSet<>();
        frontera.add(new Estado(inicio, inicio.peso, null));

        while (!frontera.isEmpty()) {
            Estado estadoActual = frontera.poll();

            if (estadoActual.nodo.nombre.equals(objetivo)) {
                // Se encontró el camino
                List<String> camino = new ArrayList<>();
                while (estadoActual != null) {
                    camino.add(estadoActual.nodo.nombre);
                    estadoActual = estadoActual.padre;
                }
                Collections.reverse(camino);
                System.out.println("Camino encontrado: " + camino);
                return;
            }

            explorados.add(estadoActual.nodo.nombre);

            for (Nodo vecino : estadoActual.nodo.vecinos) {
                if (!explorados.contains(vecino.nombre)) {
                    int costoTotal = estadoActual.costoTotal + vecino.peso;
                    frontera.add(new Estado(vecino, costoTotal, estadoActual));
                }
            }
        }

        System.out.println("No se encontró un camino al objetivo.");
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

        busquedaAEstrella(A, "T");
    }
}
