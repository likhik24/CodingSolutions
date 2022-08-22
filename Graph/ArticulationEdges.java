package Graph;

import java.util.*;
public class ArticulationEdges {

    //articulation edge is an edge which when removed disconnects the graph
    // whenever lowestnode of neighbor > discoverytime[source]
    // this is a articulation edge as disconnecting edge source,i disconnects graph from all vertices after i
//    lowestnode means leastvaluechildren , if the children of neighbor are greater than value of discovertimeof source,
//    there is no backedge from neighbor to source which will make graph conencted when we remove source->neighbor edge


    int time=0;
    public List<List<Integer>> criticalConnections(int n, ArrayList<ArrayList<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] lowestNode = new int[n];
        int[] discoveryTime = new int[n];
        List<List<Integer>> adjList = new ArrayList<>();
//        for(int i=0;i<n;i++)
//            adjList.add(new ArrayList<>());
//        for(List<Integer> connect: connections) {
//            adjList.get(connect.get(0)).add(connect.get(1));
//            adjList.get(connect.get(1)).add(connect.get(0));
//        }
        // for(int i=0;i<n;i++) {
        dfs(connections, 0,-1, visited, result, lowestNode, discoveryTime);
        // }
        return result;
    }
    //{1,2} , {0,3}, {0,1}, {1} 1 2  3
    public void dfs(ArrayList<ArrayList<Integer>>  connections, int source, int parent, boolean[] visited, List<List<Integer>> result, int[] lowestNode,int[] discoveryTime) {
        if(parent == source)
            return;
        visited[source] = true;
        lowestNode[source] = discoveryTime[source] = ++time;
        int children = 0;
        for(int i: connections.get(source)) {
            if(visited[i] && parent == i)
                continue;
            else if(!visited[i]) {
                dfs(connections, i, source, visited, result, lowestNode, discoveryTime);
                if (lowestNode[i] > discoveryTime[source])
                    // whenever lowestnode of neighbor > discoverytime[source] this is a articulation edge as disconnecting edge source,i disconnects graph from all vertices after i

                    result.add(Arrays.asList(new Integer[]{source, i}));
            }

            lowestNode[source] = Math.min(lowestNode[i], lowestNode[source]);


        }

    }
}