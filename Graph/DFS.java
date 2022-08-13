package Graph;

import java.util.*;
public class DFS {
    public void dfs(ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[adjList.size()];
         doDfs(adjList, 5, visited);
    }
    public void doDfs(ArrayList<ArrayList<Integer>> adjList, int source, boolean[] visited) {
        if(visited[source])
            return;
        visited[source] = true;
        System.out.println(source);
        for(Integer neighbor: adjList.get(source)) {
            if(!visited[neighbor])
                doDfs(adjList,neighbor, visited);
        }

    }



}
