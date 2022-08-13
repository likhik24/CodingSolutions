package Graph;

import java.util.*;
// You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
//
//        You are given two arrays redEdges and blueEdges where:
//
//        redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
//        blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
//        Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
//
//
//
//        Example 1:
//
//        Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
//        Output: [0,1,-1]
//        Example 2:
//
//        Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
//        Output: [0,1,-1]

class Noded {
    HashMap<String, HashSet<Integer>> adjMap;
    int value;
    String color;

    Noded() {
        adjMap = new HashMap<>();
        adjMap.put("Red", new HashSet<>());
        adjMap.put("Blue", new HashSet<>());

    }

}
public class ShortestPathWithAlternatingColors {

        // g1-> graph with red edges
        // g2-> graph with blue edges
        List<Integer> g1[], g2[];
        int[] dist1, dist2, ans;
        int MX = (int) 2e9;

        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            dist1 = new int[n];
            dist2 = new int[n];
            g1=new ArrayList[n];
            g2=new ArrayList[n];
            ans=new int[n];
            for (int i=0;i<n;i++){
                g1[i]=new ArrayList<>();
                g2[i]=new ArrayList<>();
                dist1[i]=MX;
                dist2[i]=MX;
                ans[i]=MX;
            }
            for (int i=0;i<redEdges.length;i++){
                int u=redEdges[i][0];
                int v=redEdges[i][1];
                g1[u].add(v);
            }
            for (int i=0;i<blueEdges.length;i++){
                int u=blueEdges[i][0];
                int v=blueEdges[i][1];
                g2[u].add(v);
            }
            dist1[0]=0;
            dist2[0]=0;
            dfs(0,true);
            dfs(0,false);
            for (int i=0;i<n;i++){
                ans[i]=Math.min(dist1[i], dist2[i]);
                if (ans[i]==MX) ans[i]=-1;
            }
            return ans;
        }
        public void dfs(int u, boolean flag) {
            if (flag) {
                for (int v: g1[u]) {
                    if (dist1[v]>dist2[u]+1){
                        dist1[v]=dist2[u]+1;
                        dfs(v,!flag);
                    }
                }
            } else {
                for (int v: g2[u]) {
                    if (dist2[v]>dist1[u]+1){
                        dist2[v]=dist1[u]+1;
                        dfs(v,!flag);
                    }
                }
            }
        }

     public int[] shortestAlternatingPathsBFS(int n, int[][] redEdges, int[][] blueEdges) {
         List<Noded> adjList = new ArrayList<>();
          int distance[] = new int[n];
         Arrays.fill(distance, Integer.MAX_VALUE);
         for(int i=0;i<n;i++) {
             adjList.add(new Noded());
         }

         for(int[] edge: redEdges) {
             adjList.get(edge[0]).adjMap.get("Red").add(edge[1]);
         }
         for(int[] edge: blueEdges) {
             adjList.get(edge[0]).adjMap.get("Blue").add(edge[1]);
         }
         distance[0] = 0; // source cannot have alternating edge to itself
         shortestPathsBFS(adjList, distance, 0, "Red");
         shortestPathsBFS(adjList, distance, 0, "Blue");


         for(int i=0;i<distance.length;i++) {
             if(distance[i] == Integer.MAX_VALUE)
                 distance[i] = -1;
         }
         return distance;
     }

     public void shortestPathsBFS(List<Noded> adjList, int[] distance, int source, String color) {
         Queue<Integer> queue = new LinkedList<>();
        Map<String, Set<Integer>> visitedVia = new HashMap<>(){{
             put("Red", new HashSet<>());  // nodes visited via Red Edge
             put("Blue", new HashSet<>());  // nodes visited via Blue Edge
         }};

         visitedVia.get(color).add(source);
         queue.add(source);
         distance[source] = 0;
         int distances = 0;
         while(!queue.isEmpty()) {
             int size = queue.size();
             distances++;
              color = color == "Red" ? "Blue": "Red";

             for(int i=0;i<size;i++) {
                 Integer curr = queue.poll();
                 Noded neighbors = adjList.get(curr);
                 for(Integer nei: neighbors.adjMap.get(color)) {
                    if(visitedVia.get(color).contains(nei))
                        continue;
                    if(distance[nei] == Integer.MAX_VALUE || distance[nei] > distances)
                        distance[nei] = distances;
                    queue.add(nei);
                 }
             }
         }
     }

  public static void main(String[] args) {
     ShortestPathWithAlternatingColors path = new ShortestPathWithAlternatingColors();
     //path.shortestAlternatingPaths()
  }
}
