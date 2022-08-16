package Graph;
/*You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

        Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

        If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.



        Example 1:



        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
        Output: 0.25000
        Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
        Example 2:



        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
        Output: 0.30000
        Example 3:



        Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
        Output: 0.00000
        Explanation: There is no path between 0 and 2.
*/
import java.util.*;
import java.util.stream.IntStream;

public class PathWithMaximumProbability {

        class Node {
            double weight;
            int vertex;
            Node(int v, double w) {
                this.weight = w;
                this.vertex = v;
            }
        }
        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            IntStream.range(0, n).forEach(i->graph.add(new ArrayList<>()));
            int index=0;
            for(int[] edge: edges) {
                graph.get(edge[0]).add(new Node(edge[1], succProb[index]));
                graph.get(edge[1]).add(new Node(edge[0], succProb[index++]));
            }
            Queue<Integer> queue = new LinkedList<>();
            double[] p = new double[n];
            p[start] = 1d;
            queue.add(start);
            while(!queue.isEmpty()) {
                int curr = queue.poll();
                ArrayList<Node> adj = graph.get(curr);
                for(Node n1: adj) {
                    if(p[n1.vertex] < p[curr]*n1.weight) {
                        p[n1.vertex] = p[curr]*n1.weight;
                        queue.add(n1.vertex);
                    }

                }
            }
            return p[end];
        }


}
