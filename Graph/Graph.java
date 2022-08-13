package Graph;

import java.util.*;
public class Graph {
    private int V;

    // Adjacency List as ArrayList of ArrayList's
    public ArrayList<ArrayList<Integer> > adj;

    // Constructor
    public Graph(int v) {
        V = v;
        adj = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }

    // Function to add an edge into the graph
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
    }
}