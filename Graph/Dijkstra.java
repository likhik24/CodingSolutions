package Graph;

import java.util.*;

public class Dijkstra {
    static class Node implements Comparable<Node>{
        int v;
        int distance;
        Node(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }
    @Override
    public int compareTo( Node b) {
        return this.distance - b.distance;
    }
    }

    public static int[] dijkstra(int vertices, ArrayList<ArrayList<ArrayList<Integer> > > adj, int source) {

      PriorityQueue<Node> queue = new PriorityQueue<>();
      queue.add(new Node(source, 0));
      int[] distances = new int[vertices];
      Arrays.fill(distances, Integer.MAX_VALUE);
      distances[source] = 0;
    HashSet<Integer> visitedSet = new HashSet<>();
 
      while(!queue.isEmpty()) {
        Node curr = queue.poll();
        if(visitedSet.contains(curr.v))
        continue;
        visitedSet.add(curr.v);

        ArrayList<ArrayList<Integer>> edges = adj.get(curr.v);
        for(ArrayList<Integer> edge: edges) {
            int vertex=  edge.get(0);
            int distance = edge.get(1);
            if(distances[vertex] > distance+distances[curr.v])
              { 
                distances[vertex] = distance+distances[curr.v];
                
              }
              queue.add(new Node(vertex, distances[vertex]));

        }
      }
      return distances;



    }

    public static void main(String[] args)
    {
        ArrayList<ArrayList<ArrayList<Integer> > > adj
                = new ArrayList<>();
        HashMap<Integer, ArrayList<ArrayList<Integer> > >
                map = new HashMap<>();

        int V = 6;
        int E = 5;
        int[] u = { 0, 0, 1, 2, 4 };
        int[] v = { 3, 5, 4, 5, 5 };
        int[] w = { 9, 4, 4, 10, 3 };

        for (int i = 0; i < E; i++) {
            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(v[i]);
            edge.add(w[i]);

            ArrayList<ArrayList<Integer> > adjList;
            if (!map.containsKey(u[i])) {
                adjList = new ArrayList<>();
            }
            else {
                adjList = map.get(u[i]);
            }
            adjList.add(edge);
            map.put(u[i], adjList);

            ArrayList<Integer> edge2 = new ArrayList<>();
            edge2.add(u[i]);
            edge2.add(w[i]);

            ArrayList<ArrayList<Integer> > adjList2;
            if (!map.containsKey(v[i])) {
                adjList2 = new ArrayList<>();
            }
            else {
                adjList2 = map.get(v[i]);
            }
            adjList2.add(edge2);
            map.put(v[i], adjList2);
        }

        for (int i = 0; i < V; i++) {
            if (map.containsKey(i)) {
                adj.add(map.get(i));
            }
            else {
                adj.add(null);
            }
        }
        int S = 1;

        // Input sample
        //[0 [[3, 9], [5, 4]],
        // 1 [[4, 4]],
        // 2 [[5, 10]],
        // 3 [[0, 9]],
        // 4 [[1, 4], [5, 3]],
        // 5 [[0, 4], [2, 10], [4, 3]]
        //]
        int[] result
                = dijkstra(
                V, adj, S);
        System.out.println(Arrays.toString(result));
    }

}
