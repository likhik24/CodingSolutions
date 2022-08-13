package Graph;

import java.util.ArrayList;

public class ArticulationPoint {
    //articulation point is a vertex which when removed disconnects the graph
    //find discovery time of all nodes doing dfs discoverytime[nide]
   // then find lowest connecting node of each node i.e lowestnode[node]
    //if node 3 is connected to node 2, node 2 is connected to node1, lowest connecting node of node 3 is node 1

    //after this to find articluation point we find the condition for any node u,v
    // where u is parent of node v if L(V) >= D(u) lowestnode(child) >= discoverytime(parent)
   // then parent u is the articulation point
    int time = 0;
    public int[] lowestchildren;
    public void findArticulationPoint(ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[adjList.size()];
        int[] discTimes  = new int[visited.length];
        lowestchildren = new int[visited.length];
        boolean[] isAP = new boolean[visited.length];

        for(int i=0;i<visited.length;i++) {
            if(!visited[i]) {
                findDiscoveryTime(adjList, visited, i,  discTimes, -1, isAP);
            }
        }
        for (int u = 0; u < visited.length; u++)
            if (isAP[u] == true)
                System.out.print("Articulation point is " + u + " ");
        System.out.println();
    }

    public void findDiscoveryTime(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int source, int[] discTime, int parent, boolean[] isAP) {
        int children = 0;
        visited[source] = true;
        discTime[source] = lowestchildren[source] = ++time;
        System.out.println("discovery time of node " + source + " is " + discTime[source]);
        for(int neighbor: adjList.get(source)) {
            children++;
            if(neighbor == parent) continue;
           else if(visited[neighbor]) {
                lowestchildren[source] = Math.min(lowestchildren[neighbor], lowestchildren[source]);
            }
            else{
                findDiscoveryTime(adjList, visited, neighbor, discTime, source, isAP);
                if (parent != -1 && lowestchildren[neighbor] >= discTime[source]) {
                    isAP[source] = true;
                }
                lowestchildren[source] = Math.min(lowestchildren[neighbor], lowestchildren[source]);
            }

        }

        // If u is root of Graph.DFS tree and has two or more children.
        if (parent == -1 && children > 1)
            isAP[source] = true;

    }




}
