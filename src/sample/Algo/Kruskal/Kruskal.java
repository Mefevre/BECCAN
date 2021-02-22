package sample.Algo.Kruskal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Kruskal {

    /**
     * Détermine l'arbre couvrant de poids minimum (ARPM) à partir d'un graphe connexe non-orienté et pondéré.
     * <p>
     * Utilise l'algorithme de Kruskal
     * @see https://fr.wikipedia.org/wiki/Algorithme_de_Kruskal
     *
     * @param vertices graphe constitué d'un ensemble de points dans un plan à 2 dimensions.
     * @return arrêtes de l'arbre couvrant de poids minimum dans ce graphe.
     */
    static List<Edge> compute(List<Vertex> vertices) {

        // Calcule les arêtes et leur poids
        List<Edge> allEdges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                allEdges.add(new Edge(vertices.get(i), vertices.get(j)));
            }
        }

        // Tri par poids ascendant
        allEdges.sort(Comparator.comparingDouble(Edge::getWeight));

        // Applique l'algo de Kruskal
        List<Edge> graph = new ArrayList<>();
        int i = 0;
        while (graph.size() < vertices.size() - 1) {
            Edge edge = allEdges.get(i++);
            int id1 = edge.u.clusterId;
            int id2 = edge.v.clusterId;
            // L'arête est ajouté au compute si ses 2 sommets n'appartiennent pas au même réseau
            if (id1 != id2) {
                graph.add(edge);
                // Regroupe les sommets des 2 réseaux venant d'être reliés
                for (Vertex v : vertices)
                    if (v.clusterId == id2) {
                        v.clusterId = id1;
                    }
            }
        }

        return graph;
    }

    static class Vertex {

        static int NEX_ID = 0;

        private final int x;

        private final int y;

        private int clusterId = NEX_ID++;

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {

        private final Vertex u;

        private final Vertex v;

        private final double weight;

        Edge(Vertex v1, Vertex v2) {
            this.u = v1;
            this.v = v2;
            this.weight = Math.hypot(Math.abs(v1.x - v2.x), Math.abs(v1.y - v2.y));
        }

        double getWeight() {
            return weight;
        }
    }


    public static void main(String args[]) throws FileNotFoundException {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(0, 2));
        vertices.add(new Vertex(0, 0));
        vertices.add(new Vertex(1, 1));
        vertices.add(new Vertex(2, 1));
        vertices.add(new Vertex(3, 2));
        vertices.add(new Vertex(4, 2));
        vertices.add(new Vertex(3, 0));

        List<Edge> graph = Kruskal.compute(vertices);

        System.out.println(graph.stream().mapToDouble(Edge::getWeight).sum()); // 7.656854249492381
    }
}
