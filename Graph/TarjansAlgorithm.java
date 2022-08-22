package Graph;


import java.util.*;

//Algorithm for finding number of strongly connected components using dfs in O(V+E) time complexity

// Get the connected components of this graph. If two indexes
// have the same value for sccs array then they're in the same SCC.
//we start at node which is not visited and keep adding other nodes to same scc until we find the source of curr scc, we stop and update lowest_id of all neighbors
public class TarjansAlgorithm {
    int id=0;
    int sccCount = 0;
    int[] sccs;

    public int findStronglyConnectedComponents(ArrayList<ArrayList<Integer>> adjList ) {
        //taking source as 0
        TreeSet<Integer> visited = new TreeSet<>();
        int count = 0;
        int[] ids = sccs = new int[adjList.size()];


        int[] lowest_id = new int[adjList.size()]; //no.of.nodes
        for(int i=0;i<lowest_id.length;i++)
            lowest_id[i] = i;

        for(int i=0;i<ids.length;i++)ids[i] = -1; // marks all nodes as unvisited

        for(int i=0;i<adjList.size();i++) {
            if(ids[i] == -1) { //keep visiting nodes that are not visited
                dfs(visited, 0,ids, lowest_id, adjList, new Stack<>());
                }
    }
        System.out.println("length is" + sccCount);
        for(int j=0;j<lowest_id.length;j++)
            System.out.println("lowest id of " + j + "is " + lowest_id[j]);

        return sccCount;
    }

    public void dfs(TreeSet<Integer> visited, int source, int[] ids, int[] lowest_id,ArrayList<ArrayList<Integer>> adjList, Stack<Integer> currVisited) {

        visited.add(source);
        lowest_id[source] = ids[source] = id++; //to increment lowest_id and id of source to time at which it is visited and increment time
        currVisited.push(source); //0 1 2

        for(Integer neigh: adjList.get(source)) {
            if(ids[neigh] == -1) // the neighbor is not yet visited
            {
                  dfs(visited, neigh,ids, lowest_id, adjList, currVisited);

            }
            if(visited.contains(neigh)) //once neighbor is visited we update lowest_id of source
                lowest_id[source] = Math.min(lowest_id[neigh], lowest_id[source] );

        }
        // On recursive callback, if we're at the root node (start of SCC)
        // empty the seen stack until back to root.
        //lowest_id will be lowest for the node for which we started scc on , so lowest_id[node] will be equal to ids only for the start node
        if(lowest_id[source] == ids[source]) {
            for(int node=currVisited.pop(); ; node=currVisited.pop()) {
                visited.remove(node);
                sccs[node] = sccCount;
                if (node == source) break;
            }
            sccCount++;
            }
        }




}
