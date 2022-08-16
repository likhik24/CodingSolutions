package Graph;
import java.util.*;

/*Residual Graph of a flow network is a graph which indicates additional possible flow. If there is a path from source to sink in residual graph, then it is possible to add flow. Every edge of a residual graph has a value called residual capacity which is equal to original capacity of the edge minus current flow. Residual capacity is basically the current capacity of the edge.

        residual graph is graph which contains residual capacity of all edges  which is original capacity of edge- current flow in the edge
        initially all edges flow is 0
        so residual graph is orginal capacity of the graph, then we do bfs from nodes to update residual capacity of the graph by updating parent and from source to t find the maximum path flow subtract this from all existing paths form source to sink then again to bfs on residual graph to further ge tminimum flow

maximum flow is obtained from minimum cut of the graph in ford fulkerson

//find max flow between source and sink nodes n d graph,  start doing bfs from source in residual graph and keep updating parent until we connect to target, any edge greater than flow 0 can be used to connect to target ,
// we keep adding to queue  the nodes that are in path and flow greater than 0 to be visited, after each bfs we backtrack the path from target to source using parent array and update pathflow to be minimum of any paths ,
// then decrement pathflow for all parents in the path from source to bfs, we keep doing bfs until we cant find a root to target as all flow in residual graph to target is 0
 this is Edmonds-Karp Algorithm. The idea of Edmonds-Karp is to use BFS in Ford Fulkerson implementation as BFS always picks a path with minimum number of edges.
  When BFS is used, the worst case time complexity can be reduced to O(VE2). The above implementation uses adjacency matrix representation though where BFS takes O(V2) time,
  the time complexity of the above implementation is O(EV3) (Refer CLRS book for proof of time complexity)

        */

public class FordFulkersonAlgorithm {

    public boolean bfs(int[][] residGraph, int source, int target, int[] parent) {
        boolean[] visited = new boolean[residGraph.length];
        Arrays.fill(visited,false);
        parent[source] = -1;
        visited[source] = true;
        Deque<Integer> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            int curr = queue.pollFirst();
            int[] adjacent = residGraph[curr];
            for(int i=0;i<adjacent.length;i++) {
                if( !visited[i] && residGraph[curr][i] > 0) {
                    if(i == target) {
                        parent[i] = curr;
                        return true;
                    } else {
                        parent[i] = curr;
                        visited[i] = true;
                        queue.add(i);
                    }
                }

            }
        }
        return false;
    }

    public int fordFulkerson(int[][] matrix, int source, int target) {
        int[] parent = new int[matrix.length];
        int[][] residGraph = new int[matrix.length][matrix[0].length];
        int maxFlow = 0;
        for(int i=0;i< residGraph.length;i++)
        {
            for(int j=0;j<residGraph[0].length;j++) {
                residGraph[i][j] = matrix[i][j];
            }
        }

        while(bfs(residGraph, source, target, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            int u,v;
            for (v=target; v!=source ; v=parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, residGraph[u][v]);
            }
            for (v=target; v!=source ; v=parent[v]) {
                u = parent[v];
                residGraph[u][v] -= pathFlow;
                residGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }

        return maxFlow;
    }
    public static void main(String[] args)
            throws java.lang.Exception
    {
        // Let us create a graph shown in the above example
        int graph[][] = new int[][] {
                { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 },
                { 0, 4, 0, 0, 14, 0 },  { 0, 0, 9, 0, 0, 20 },
                { 0, 0, 0, 7, 0, 4 },   { 0, 0, 0, 0, 0, 0 }
        };
        FordFulkersonAlgorithm m = new FordFulkersonAlgorithm();

        System.out.println("The maximum possible flow is "
                + m.fordFulkerson(graph, 0, 5));
    }
}
