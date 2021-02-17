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
        semiHelerien = false;
        affiche =  textArea;
        V = matrice.length;
        path = new int[V];

        Arrays.fill(path, -1);
        graph = matrice;
        try
        {
            path[0] = 0;
            pathCount = 1;
            solve(0);
            affiche.setText("Le Graph n'est pas Hamiltonien.");
        }
        catch (Exception e)
        {
            affiche.setText(e.getMessage());
            display();
        }
    }

    /** Function to find cycle **/
    /*public void findHamiltonianCycle(int[][] g)
    {
        V = g.length;
        path = new int[V];

        Arrays.fill(path, -1);
        graph = g;
        try
        {
            path[0] = 0;
            pathCount = 1;
            solve(0);
            System.out.println("No solution");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            display();
        }
    }*/
    /** function to find paths recursively **/
    public void solve(int vertex) throws Exception
    {
        /** solution **/
        if (graph[vertex][0] == 1 && pathCount == V)
            throw new Exception("Ce graph possède un cycle Hamiltonien");
        /** all vertices selected but last vertex not linked to 0 **/
        if (pathCount == V) {
            semiHelerien = true;
            throw new Exception("Ce graph possède un chemin Hamiltonien");
        }

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
                    solve(v);

                /** restore connection **/
                graph[vertex][v] = 1;
                graph[v][vertex] = 1;
                /** remove path **/
                path[--pathCount] = -1;
            }
        }
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
            for (int i = 0; i <= V; i++)
                affiche.appendText(path[i]+1 +" ");
        } else {
            for (int i = 0; i <= V; i++)
                affiche.appendText(path[i % V] + 1 + " ");
        }
    }
}
