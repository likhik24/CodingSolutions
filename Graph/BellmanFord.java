package Graph;


import java.sql.Time;
import java.util.*;

class Edge {
    int dest;
    int weight;
    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

//Helps to find negative cycles within a directed graph
//also find shortest distance between nodes which have negative weights

//    How to handle a disconnected graph (If the cycle is not reachable from the source)?
//        The below algorithm and program might not work if the given graph is disconnected. It works when all vertices are reachable from source vertex 0.
//        To handle disconnected graphs, we can repeat the process for vertices for which distance is infinite.
// Time complexity: O(VE)
public class BellmanFord {
    public boolean isNegativeCycle(int[] weights, int[][] edges, int vertices) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i=0;i<vertices;i++)
            graph.add(new ArrayList<Edge>());
        for(int i=0;i<weights.length;i++) {
            int[] edge = edges[i];
            graph.get(edge[0]).add(new Edge(edge[1], weights[i]));
        }

        int[] distances = findShortestPathFromSource(0,graph, edges, weights);
        for(int j=0;j<edges.length;j++) {
            int src = edges[j][0];
            int dest = edges[j][1];
            int weight = weights[j];
            if(distances[src] != Integer.MAX_VALUE && distances[src]+weight < distances[dest])
            {
                System.out.println("there is a negative cycle between " + src + " and " + dest);
                return true;
            }
        }
        return false;
    }

    public int[] findShortestPathFromSource(int source,  ArrayList<ArrayList<Edge>> graph, int[][] edges, int[] weights) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0; // assuming 0 as source;

        // Relax all edges |Vertices| - 1 times.
        // A simple shortest path from src to any
        // other vertex can have at-most |Vertices| - 1
        // edges

        for(int i=0;i<graph.size()-1;i++) {
            for(int j=0;j<edges.length;j++) {
                int src = edges[j][0];
                int dest = edges[j][1];
                int weight = weights[j];
                if(dist[src] != Integer.MAX_VALUE && dist[src]+weight < dist[dest])
                    dist[dest] = dist[src]+weight;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        BellmanFord graphAlgo = new BellmanFord();
        int[] weights = {-1,4,3,2,-2,5,1,-3};
                int[][] edges = {{0,1},{0,2},{1,2},{1,3},{1,4},{3,2},{3,1},{4,3}};
               System.out.println(graphAlgo.isNegativeCycle(weights, edges, 5));
    }
}
