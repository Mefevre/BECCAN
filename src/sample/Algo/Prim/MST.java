package sample.Algo.Prim;
// A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
// The program is for adjacency matrix representation of the graph

import java.lang.*;

public class MST {
    // Number of vertices in the graph
    private static int V ;

    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }
    public void setV(int v)
    {
        this.V = v;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    int[][] printMST(int parent[], int graph[][])
    {
        int[][] tab = new int[V+1][V+1];
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
        {
            tab[parent[i]+1][(i+1)] = graph[i][parent[i]];
            System.out.println(parent[i]+1 + " - " + (i+1) + "\t" + graph[i][parent[i]]);
        }
        return tab;

    }

    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    public int[][] primMST(int[][] graph)
    {
        int[][] tab = new int[0][];

        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        return tab = printMST(parent, graph);
    }

    /*public static void main(String[] args)
    {
		/* Let us create the following graph
		2 3
		(0)--(1)--(2)
		| / \ |
		6| 8/ \5 |7
		| /	 \ |
		(3)-------(4)
			9		 *//*
        Prim t = new Prim();
        int graph[][] = new int[][] {
            { 0, 2, 3, 6, 5 },
            { 2, 0, 0, 0, 0 },
            { 3, 0, 0, 0, 0 },
            { 6, 0, 0, 0, 0 },
            { 5, 0, 0, 0, 0 }
           };

        // Print the solution
        t.primMST(graph);
    }*/
}
// This code is contributed by peziere ANOTNINN
