package Graph;

import java.util.*;
// You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.

//        The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph, and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.
//
//        To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti-1, xcnti], [xcnti, vi].
//
//        In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.
//
//        Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.
//
//
//
//        Example 1:
//
//
//        Input: edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
//        Output: 13
//        Explanation: The edge subdivisions are shown in the image above.
//        The nodes that are reachable are highlighted in yellow.
//        Example 2:
//
//        Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
//        Output: 23
//        Example 3:
//
//        Input: edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
//
//   Solution:
//        Approach 1: Dijkstra's
//        Intuition
//
//        Treating the original graph as a weighted, undirected graph, we can use Dijkstra's algorithm to find all reachable nodes in the original graph. However, this won't be enough to solve examples where subdivided edges are only used partially.
//
//        When we travel along an edge (in either direction), we can keep track of how much we use it. At the end, we want to know every node we reached in the original graph, plus the sum of the utilization of each edge.
//
//        Algorithm
//
//        We use Dijkstra's algorithm to find the shortest distance from our source to all targets. This is a textbook algorithm, refer to this link for more details.
//
//        Additionally, for each (directed) edge (node, nei), we'll keep track of how many "new" nodes (new from subdivision of the original edge) were used. At the end, we'll sum up the utilization of each edge.
//
//        Please see the inline comments for more details.


public class ReachableNodesInSubDividedGraph {

        public int reachableNodes(int[][] edges, int M, int N) {
            Map<Integer, Map<Integer, Integer>> graph = new HashMap();
            for (int[] edge: edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                graph.computeIfAbsent(u, x->new HashMap()).put(v, w);
                graph.computeIfAbsent(v, x->new HashMap()).put(u, w);
            }

            PriorityQueue<ANode> pq = new PriorityQueue<ANode>(
                    (a, b) -> Integer.compare(a.dist, b.dist));
            pq.offer(new ANode(0, 0));

            Map<Integer, Integer> dist = new HashMap();
            dist.put(0, 0);
            Map<Integer, Integer> used = new HashMap();
            int ans = 0;

            while (!pq.isEmpty()) {
                ANode anode = pq.poll();
                int node = anode.node;
                int d = anode.dist;

                if (d > dist.getOrDefault(node, 0)) continue;
                // Each node is only visited once.  We've reached
                // a node in our original graph.
                ans++;

                if (!graph.containsKey(node)) continue;
                for (int nei: graph.get(node).keySet()) {
                    // M - d is how much further we can walk from this node;
                    // weight is how many new nodes there are on this edge.
                    // v is the maximum utilization of this edge.
                    int weight = graph.get(node).get(nei);
                    int v = Math.min(weight, M - d);
                    used.put(N * node + nei, v);

                    // d2 is the total distance to reach 'nei' (neighbor) node
                    // in the original graph.
                    int d2 = d + weight + 1;
                    if (d2 < dist.getOrDefault(nei, M+1)) {
                        pq.offer(new ANode(nei, d2));
                        dist.put(nei, d2);
                    }
                }
            }

            // At the end, each edge (u, v, w) can be used with a maximum
            // of w new nodes: a max of used[u, v] nodes from one side,
            // and used[v, u] nodes from the other.
            // [We use the encoding (u, v) = u * N + v.]
            for (int[] edge: edges) {
                ans += Math.min(edge[2], used.getOrDefault(edge[0] * N + edge[1], 0) +
                        used.getOrDefault(edge[1] * N + edge[0], 0) );
            }

            return ans;
        }

    class ANode {
        int node, dist;

        ANode(int n, int d) {
            node = n;
            dist = d;
        }
    }
}
