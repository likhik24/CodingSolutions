package Arrays;
import java.util.*;

//You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
//
//        For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
//        You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
//
//        Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
//
//
//
//        Example 1:
//
//        Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//        Output: 2
//        Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
//        Example 2:
//
//        Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//        Output: -1
public class BusRoutes {
        class Pair{
            int x;
            int y;
            Pair(int x, int y) {
                this.x = x;
                this.y = y ;
            }
        }
        HashSet<Integer> sources = new HashSet<>();
        HashSet<Integer> targets = new HashSet<>();
        HashSet<Integer> seen= new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if(source == target)
                return 0;
            List<List<Integer>> adjList = new ArrayList<>();
            for(int i=0;i<routes.length;i++) {
                adjList.add(new ArrayList<>());
            }
            for(int i=0;i<routes.length;i++) {
                for(int j=1;j<routes.length;j++) {
                    if(isIntersecting(routes, i, j)) {
                        adjList.get(i).add(j);
                        adjList.get(j).add(i);
                    }
                }
                if(Arrays.binarySearch(routes[i], source) >= 0) {
                    if(!sources.contains(i)) queue.add(new Pair(i,0));
                    sources.add(i);

                }
                if(Arrays.binarySearch(routes[i], target) >= 0)
                    targets.add(i);
            }

            while(!queue.isEmpty()) {
                Pair curr = queue.poll();
                if(targets.contains(curr.x))
                    return curr.y+1;
                if(seen.contains(curr.x))
                    continue;
                seen.add(curr.x);
                for(Integer adj: adjList.get(curr.x)) {
                    if(!seen.contains(adj))
                        queue.add(new Pair(adj, curr.y+1));
                }
            }
            return -1;
        }


        public boolean isIntersecting(int[][] routes, int i, int j) {
            for(int k=0;k<routes[i].length;k++) {
                for(int m=0;m<routes[j].length;m++) {
                    if(routes[i][k] == routes[j][m])
                        return true;
                }
            }
            return false;
        }
}
