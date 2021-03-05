package sample.Algo.Hamiltonien;

import javafx.scene.control.TextArea;
import java.util.Arrays;

public class Hamiltonien {
    private int V, pathCount;
    private int[][] path;
    private int[][] graph;
    private TextArea affiche;
    private boolean hamiltonien;

    public Hamiltonien (int[][] matrice, TextArea textArea)
    {

        affiche =  textArea;
        V = matrice.length;
        path = new int[V][V+1];
        for (int ii=0; ii<V; ii++)
        {
            Arrays.fill(path[ii], -1);
        }
        hamiltonien = false;

        for (int i=0; i<matrice.length; i++)
        {
            graph = new int[V][V];


            for (int ii = 0; ii<V; ii++)
            {
                for (int jj = 0; jj<V; jj++)
                {
                    graph[ii][jj] = matrice[ii][jj];
                }
            }

            path[i][0] = i;
            pathCount = 1;
            path[i][V] = solve(i, i);

            if (path[i][V] != 0)
            {
                hamiltonien = true;
            }
        }
        display();
        System.out.println("fin");
    }

    /** function to find paths recursively **/
    public int solve(int vertex, int init)
    {
        /** solution **/
        if (graph[vertex][init] == 1 && pathCount == V)
            return 2;
        /** all vertices selected but last vertex not linked to 0 **/
        if (pathCount == V) {
            return 1;
        }
        int retour = 0;
        for (int v = 0; v < V; v++)
        {
            /** if connected **/
            if (graph[vertex][v] == 1 )
            {
                /** add to path **/
                path[init][pathCount++] = v;
                /** remove connection **/
                graph[vertex][v] = 0;
                //graph[v][vertex] = 0;

                /** if vertex not already selected  solve recursively **/
                if (!isPresent(v, init))
                    retour = solve(v, init);

                if (retour != 0)
                {
                    return retour;
                }
                /** restore connection **/
                graph[vertex][v] = 1;
                //graph[v][vertex] = 1;
                /** remove path **/
                path[init][--pathCount] = -1;
            }
        }
        return retour;
    }
    /** function to check if path is already selected **/
    public boolean isPresent(int v, int init)
    {
        for (int i = 0; i < pathCount - 1; i++)
            if (path[init][i] == v)
                return true;
        return false;
    }
    /** display solution **/
    public void display()
    {
        if (hamiltonien)
        {
            boolean cycle = false;
            if (path[0][V] == 2) {
                cycle = true;
                affiche.setText("Le graph possède un cycle hamiltonien.");
                affiche.appendText("\nLe cycle est de l'ordre : ");
            } else {
                affiche.setText("Le graph possède des chemins hamiltoniens.");
                affiche.appendText("\nLes chemins sont de l'ordre : ");
            }


            if (!cycle) {
                for (int i = 0; i < V; i++)
                {
                    if (path[i][V] == 1)
                    {
                        affiche.appendText("\n   en partant du sommet " + (i + 1) + " : ");
                        for (int j = 0; j < V; j++) {
                            affiche.appendText(path[i][j] + 1 + " ");
                        }
                    }
                }
            } else {
                for (int i = 0; i <= V; i++)
                    affiche.appendText(path[0][i % V] + 1 + " ");
            }
        } else {
            affiche.setText("Le graph n'est pas hamiltonien.");
        }
        affiche.appendText("\n");
    }
}
