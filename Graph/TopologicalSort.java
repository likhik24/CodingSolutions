package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

// Time complexity: O(E+v)
public class TopologicalSort {
    public void topologicalSort(ArrayList<ArrayList<Integer>> adjList) {
        int[] indegree = new int[adjList.size()];
       Deque<Integer> queue = new LinkedList<>();
        for(int i=0;i<adjList.size();i++) {
            ArrayList<Integer> neighbor = adjList.get(i);

            for(int n:neighbor)
                indegree[n]++;

        }
        for(int i=0;i<indegree.length;i++) {
            if(indegree[i] == 0)
                queue.add(i);

        }

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.println(curr);
            ArrayList<Integer> neighbor = adjList.get(curr);
            for(int n:neighbor) {
                indegree[n]--;
                if(indegree[n] == 0 )
                    queue.add(n);
            }
        }
    }

}
