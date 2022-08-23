package Graph;
import java.util.*;

public class ShortestCycleInDirectedGraph {

    public List<Integer> getShortestPathInDirectedGraph(int[][] edges, int start){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        Map<Integer, Integer> parentMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        Set<Integer> visited = new HashSet<>();
        List<Integer> cycle = new ArrayList<>();
        while(!queue.isEmpty()) {
            int curr = queue.remove();
            if(visited.contains(curr))
                continue;
            visited.add(curr);
            for(int next : graph.getOrDefault(curr, new ArrayList<>())) {
                if(visited.contains(next)) {
                    // found cycle;
                    cycle.add(next);
                    while(parentMap.containsKey(curr)) {
                        cycle.add(curr);
                        curr = parentMap.get(curr);
                    }
                    cycle.add(curr);
                    return cycle;
                }
                else if(!parentMap.containsKey(next)) {
                    queue.add(next);

                    parentMap.put(next, curr);
                }
            }
        }
        return cycle;
    }
}
