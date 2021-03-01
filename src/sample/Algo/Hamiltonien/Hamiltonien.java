package sample.Algo.Hamiltonien;

import javafx.scene.control.TextArea;
import java.util.Arrays;

public class Hamiltonien {
    private int V, pathCount;
    private int[] path;
    private int[][] graph;
    private TextArea affiche;
    private boolean semiHelerien;

    public Hamiltonien (int[][] matrice, TextArea textArea)
    {
        for (int i=0; i<matrice.length; i++)
        {
            semiHelerien = false;
            affiche =  textArea;
            V = matrice.length;
            path = new int[V];
            graph = new int[V][V];

            Arrays.fill(path, -1);

            for (int ii = 0; ii<V; ii++)
            {
                for (int jj = 0; jj<V; jj++)
                {
                    graph[ii][jj] = matrice[ii][jj];
                }
            }

            path[0] = i;
            pathCount = 1;
            String resu = solve(i, i);
            affiche.appendText(resu);
            if (resu != "a\n")
            {
                display();
            }
        }
        System.out.println("fin");
    }

    /** function to find paths recursively **/
    public String solve(int vertex, int init)
    {
        /** solution **/
        if (graph[vertex][init] == 1 && pathCount == V)
            return "Ce graph possède un cycle Hamiltonien";
        /** all vertices selected but last vertex not linked to 0 **/
        if (pathCount == V) {
            semiHelerien = true;
            return "Ce graph possède un chemin Hamiltonien";
        }
        String retour = "a\n";
        for (int v = 0; v < V; v++)
        {
            /** if connected **/
            if (graph[vertex][v] == 1 )
            {
                /** add to path **/
                path[pathCount++] = v;
                /** remove connection **/
                graph[vertex][v] = 0;
                graph[v][vertex] = 0;

                /** if vertex not already selected  solve recursively **/
                if (!isPresent(v))
                    retour = solve(v, init);

                if (retour != "a\n")
                {
                    return retour;
                }
                /** restore connection **/
                graph[vertex][v] = 1;
                graph[v][vertex] = 1;
                /** remove path **/
                path[--pathCount] = -1;
            }
        }
        return retour;
    }
    /** function to check if path is already selected **/
    public boolean isPresent(int v)
    {
        for (int i = 0; i < pathCount - 1; i++)
            if (path[i] == v)
                return true;
        return false;
    }
    /** display solution **/
    public void display()
    {
        affiche.appendText("\nLe chemin est de l'ordre : ");
        if (semiHelerien)
        {
            for (int i = 0; i < V; i++)
                affiche.appendText(path[i]+1 +" ");
        } else {
            for (int i = 0; i <= V; i++)
                affiche.appendText(path[i % V] + 1 + " ");
        }
        affiche.appendText("\n");
    }
}
