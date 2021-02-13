/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.ArrayList;
import java.util.List;


public class Node implements Comparable<Node> {

    public String name;
    public List<EEDGE> adjacents = new ArrayList<EEDGE>();
    public List<EEDGE> revAdjacents = new ArrayList<EEDGE>();
    public Node previous;
    public PanelPlan.NodeFX circle;
    public double minDistance = Double.POSITIVE_INFINITY;
    public boolean visited, isArticulationPoint;
    public int visitTime = 0, lowTime = 0;
    public int Numero;

    public Node(String argName) {
        name = argName;
        visited = false;
    }

    public Node(String argName, PanelPlan.NodeFX c ,int num) {
        name = argName;
        circle = c;
        visited = false;
        Numero = num;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(minDistance, o.minDistance);
    }
}
