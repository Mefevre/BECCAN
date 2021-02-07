package sample.Other;

import java.util.Scanner;

public class BellmanFord
{
    BellmanFord bellmanford;

    private int distances[];
    private int numberofvertices;
    public int tabdistance[][];

    public static final int MAX_VALUE = 999;

    public BellmanFord(int numberofvertices)
    {
        this.numberofvertices = numberofvertices;
        distances = new int[numberofvertices + 1];
        tabdistance = new int[numberofvertices][numberofvertices ];
    }

    public int[][] BellmanFordEvaluation(int source, int adjacencymatrix[][])
    {
        int Etat =0;
        for (int node = 1; node <= numberofvertices; node++)
        {
            distances[node] = MAX_VALUE;
        }

        distances[source] = 0;
        for (int node = 1; node <= numberofvertices - 1; node++)
        {
            for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
            {
                for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
                {
                    if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE)
                    {
                        if (distances[destinationnode] > distances[sourcenode]
                                + adjacencymatrix[sourcenode][destinationnode])
                            distances[destinationnode] = distances[sourcenode]
                                    + adjacencymatrix[sourcenode][destinationnode];
                    }
                }
            }
        }

        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
        {
            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
            {
                if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE)
                {
                    if (distances[destinationnode] > distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode])
                    {
                        System.out.println("The Graph contains negative egde cycle");
                        Etat = 1;
                    }



                }
            }
        }

        for (int vertex = 1; vertex <= numberofvertices; vertex++) //Affichage Finale  remplipr tableau
        {
            System.out.println("distance of source  " + source + " to "
                    + vertex + " is " + distances[vertex]);
            tabdistance[source-1][vertex-1] = distances[vertex];
            if (Etat == 1)
            {
                tabdistance[source-1][vertex-1] = 884;
            }
        }
        return tabdistance;


    }

    public static void main(String... arg)
    {
        int numberofvertices = 0;
        int source = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices");
        numberofvertices = scanner.nextInt();

        int adjacencymatrix[][] = new int[numberofvertices + 1][numberofvertices + 1];
        System.out.println("Enter the adjacency matrix");
        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
        {
            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
            {
                adjacencymatrix[sourcenode][destinationnode] = scanner.nextInt();// ICI
                if (sourcenode == destinationnode)
                {
                    adjacencymatrix[sourcenode][destinationnode] = 0;
                    continue;
                }
                if (adjacencymatrix[sourcenode][destinationnode] == 0)
                {
                    adjacencymatrix[sourcenode][destinationnode] = MAX_VALUE;
                }
            }
        }
        //ICI
        System.out.println("Enter the source vertex [1 -- "+numberofvertices+"]");
        source = scanner.nextInt();

        BellmanFord bellmanford = new BellmanFord(numberofvertices);
        bellmanford.BellmanFordEvaluation(source, adjacencymatrix);
        scanner.close();

    }

}


        /*
        $javac BellmanFord.java
        $java BellmanFord
        Enter the number of vertices
        6
        Enter the adjacency matrix
        0 4 0 0 -1 0
        0 0 -1 0 -2 0
        0 0 0 0 0 0
        0 0 0 0 0 0
        0 0 0 -5 0 3
        0 0 0 0 0 0

        Enter the source vertex
        1

        distance of source  1 to 1 is 0
        distance of source  1 to 2 is 4
        distance of source  1 to 3 is 3
        distance of source  1 to 4 is -6
        distance of source  1 to 5 is -1
        distance of source  1 to 6 is 2
        */