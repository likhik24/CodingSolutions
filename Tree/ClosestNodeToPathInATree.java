package Tree;
import java.util.*;
/*
You are given a positive integer n representing the number of nodes in a tree, numbered from 0 to n - 1 (inclusive). You are also given a 2D integer array edges of length n - 1, where edges[i] = [node1i, node2i] denotes that there is a bidirectional edge connecting node1i and node2i in the tree.

You are given a 0-indexed integer array query of length m where query[i] = [starti, endi, nodei] means that for the ith query, you are tasked with finding the node on the path from starti to endi that is closest to nodei.

Return an integer array answer of length m, where answer[i] is the answer to the ith query.



Example 1:


Input: n = 7, edges = [[0,1],[0,2],[0,3],[1,4],[2,5],[2,6]], query = [[5,3,4],[5,3,6]]
Output: [0,2]
Explanation:
The path from node 5 to node 3 consists of the nodes 5, 2, 0, and 3.
The distance between node 4 and node 0 is 2.
Node 0 is the node on the path closest to node 4, so the answer to the first query is 0.
The distance between node 6 and node 2 is 1.
Node 2 is the node on the path closest to node 6, so the answer to the second query is 2.
Example 2:


Input: n = 3, edges = [[0,1],[1,2]], query = [[0,1,2]]
Output: [1]
 */
public class ClosestNodeToPathInATree {
    //            We use an adjacency list al to track neighbors. We also use an adjacency matrix am.

// After we populate al, we use dfs to fill am with the shortest distance from a given node to all other nodes. This takes O(n * n) time.

// Then, for each query, we use am to go straight for start to end - with each step, we pick one node that is closer to end.

// As we ride along the way, we track the closest node to a target.

    // O(n * n + m * n) - time complexity
    public void dfs(int source, int dest, int dist, ArrayList<ArrayList<Integer>> graph, int[][] mat) {
        mat[source][dest] = dist;
        ArrayList<Integer> adj = graph.get(dest);
        for(int v: adj) {
            if(mat[source][v] == -1){
                dfs(source,v, dist+1, graph,mat  );
            }
        }

    }

    public int[] closestDist(int[][] matrix, int[][] query, ArrayList<ArrayList<Integer>> graph) {
        int[] result = new int[query.length];
        for(int i=0;i<query.length;i++) {
            int start = query[i][0];
            int dest = query[i][1];
            int node = query[i][2];
            result[i] = findClosestNode(start, dest, node, start, matrix, graph);
        }

        return result;
    }

    public int findClosestNode(int start, int dest, int node, int result, int[][] matrix, ArrayList<ArrayList<Integer>> graph) {
        ArrayList<Integer> adj = graph.get(start);
        for(int i: adj) {
            if(matrix[i][dest] < matrix[start][dest]) {
                return findClosestNode(i, dest, node, matrix[i][node] < matrix[result][node] ? i : result , matrix, graph);
            }

        }
        return result;
    }

    public int[] closestNode(int n, int[][] edges, int[][] query) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++)
            graph.add(new ArrayList<Integer>());
        for(int[] edge:edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[][] mat = new int[n][n];
        for(int[] m: mat)
            Arrays.fill(m,-1);
        for(int i=0;i<n;i++)
            dfs(i, i, 0, graph, mat);

        return closestDist(mat, query, graph);
    }
}
