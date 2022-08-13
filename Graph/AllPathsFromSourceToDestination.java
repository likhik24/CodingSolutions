package Graph;
//Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
//
//        At least one path exists from the source node to the destination node
//        If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
//        The number of possible paths from source to destination is a finite number.
//        Return true if and only if all roads from source lead to destination.
//
//
//
//        Example 1:
//
//
//        Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
//        Output: false
//        Explanation: It is possible to reach and get stuck on both node 1 and node 2.
//        Example 2:
//
//
//        Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
//        Output: false
//        Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
import java.util.*;
public class AllPathsFromSourceToDestination {
    enum Color {
        //WHITE,
        GRAY,
        GREEN
    }

        public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
            Color[] colors = new Color[n];
            //Arrays.fill(colors, Color.WHITE);
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            for(int i=0;i<n;i++)
                adjList.add(new ArrayList<>());
            for(int[] edge: edges)
                adjList.get(edge[0]).add(edge[1]);
            return dfs(adjList, source, destination, colors);

        }
        public boolean dfs(ArrayList<ArrayList<Integer>> adjList, int source, int destination, Color[] colors) {
            if( colors[source] == Color.GRAY)
                return false;
            if(adjList.get(source).isEmpty())
                return source == destination;
            colors[source] = Color.GRAY;

            boolean ans = true;
            for(int n: adjList.get(source)) {
                ans &= dfs(adjList, n, destination, colors);
                if(!ans)
                    return false;
            }
            colors[source]  = Color.GREEN;
            return ans;
        }

}
