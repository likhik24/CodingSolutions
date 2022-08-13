package Graph;

import java.util.*;

public class BiPartiteGraph {
    public boolean isBiPartite(ArrayList<ArrayList<Integer>> adjList) {
        int source= 0;
        String[] color = new String[adjList.size()];
        //color[source] = "Red";
        return bfs(source, adjList, color);
        // return true;
    }
    public boolean bfs(int source, ArrayList<ArrayList<Integer>> adjList, String[] color) {
      if(color[source] == "Red" || color[source] == "Blue")
          return false;
        color[source] = "Red";

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
               int curr = queue.poll();
                List<Integer> neighbors = adjList.get(curr);
                for(Integer nei: neighbors) {
                    if(color[nei] == color[curr]) {
                        System.out.println("Edge not fulfilling bipartite is " + curr + " to " + nei);
                        return false;
                    }
                    color[nei] = color[curr] == "Red" ? "Blue" : "Red";
                    queue.add(nei);
                }
            }
        }
        return true;
    }
}
