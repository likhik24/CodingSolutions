package Graph;

import java.util.*;

//There is an undirected graph with n nodes, numbered from 0 to n - 1.
//
//        You are given a 0-indexed integer array scores of length n where scores[i] denotes the score of node i. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
//
//        A node sequence is valid if it meets the following conditions:
//
//        There is an edge connecting every pair of adjacent nodes in the sequence.
//        No node appears more than once in the sequence.
//        The score of a node sequence is defined as the sum of the scores of the nodes in the sequence.
//
//        Return the maximum score of a valid node sequence with a length of 4. If no such sequence exists, return -1.
//
//
//
//        Example 1:
//
//
//        Input: scores = [5,2,9,8,4], edges = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
//        Output: 24
//        Explanation: The figure above shows the graph and the chosen node sequence [0,1,2,3].
//        The score of the node sequence is 5 + 2 + 9 + 8 = 24.
//        It can be shown that no other node sequence has a score of more than 24.
//        Note that the sequences [3,1,2,0] and [1,0,2,3] are also valid and have a score of 24.
//        The sequence [0,3,2,4] is not valid since no edge connects nodes 0 and 3.
//        Example 2:
//
//
//        Input: scores = [9,20,6,4,11,12], edges = [[0,3],[5,3],[2,4],[1,3]]
//        Output: -1
public class MaximumScoreOfNodeSequence {
    //store nodes along with their neighbors mapped to highest indegree
    //and we need to  store top 3 neighbors for all nodes , so priorityqueue is the right choice when we want top k elements
    // u poll by storing in minheap format for each node and polling first element whenever the priorityqueue size is greater than 3
    class Node  implements Comparable<Node>{
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node a) {
            return a.weight-this.weight;
        }
    }
    int maxScore = Integer.MIN_VALUE;
    public int maximumScore(int[] scores, int[][] edges) {
        List<PriorityQueue<Node>> adjList = new ArrayList<>();
        // TreeMap<Integer, TreeSet<int[]>> indegreeMap = new TreeMap<>(Collections.reverseOrder());
        //  int[] indegree = new int[scores.length];
        for(int i=0;i<scores.length;i++)
            adjList.add(new PriorityQueue<>((a,b) -> Integer.compare(a.weight,b.weight)));
        for(int[] edge: edges)
        {

            adjList.get(edge[0]).add(new Node(edge[1], scores[edge[1]]));
            adjList.get(edge[1]).add(new Node(edge[0], scores[edge[0]]));
            if(adjList.get(edge[0]).size() > 3)
                adjList.get(edge[0]).poll();
            if(adjList.get(edge[1]).size() > 3)
                adjList.get(edge[1]).poll();
        }

        for(int[] edge: edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            for(Node n11: adjList.get(n1))
            {
                for(Node n22: adjList.get(n2)) {
                    if(n11.vertex != n22.vertex && n22.vertex != n1 && n11.vertex != n2 )
                        maxScore = Math.max(maxScore, scores[n1] + scores[n2] + n11.weight + n22.weight);
                }
            }
        }

        // for(int i=0;i<scores.length;i++)
        //     dfs(i, adjList, new HashSet<>(), 0, scores);

        return maxScore  == Integer.MIN_VALUE ? -1 : maxScore;
    }
}
