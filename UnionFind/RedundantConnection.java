package UnionFind;

import java.util.*;
// In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

//        The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
//
//        The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.
//
//        Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
//
//
//
//        Example 1:
//
//
//        Input: edges = [[1,2],[1,3],[2,3]]
//        Output: [2,3]
//        Example 2:
//
//
//        Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
//        Output: [4,1]
public class RedundantConnection {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        int[] indegree = new int[n+1];
        for(int[] edge:edges) {
            indegree[edge[1]]++;
        }
        Stack<Integer> vec = new Stack<>();
        for(int i=0;i<n;i++) {
            if(indegree[edges[i][1]] == 2) {
                vec.push(i);
            }
        }
        // 1. if there is any vertices that have indegree == 2
        //    then we could delete one edge and find if we can build a valid tree after deletion
        //    And we choose first one since we use the stack.
        if(!vec.isEmpty()) {
            if(isTreeAfterRemoveEdge(edges, vec.peek())) {
                return edges[vec.pop()];
            }
            vec.pop();
            return edges[vec.pop()];
        }else{
            //2. if no vertice have indegree equals to 2.
            //   then there must be a cycle, so just simply iterate edge and use UnionFind to find extra edge.
            for(int i=0;i<n;i++) {
                int[] edge = edges[i];
                if(uf.isConnected(edge[0], edge[1])) {
                    return edges[i];
                }else{
                    uf.union(edge[0], edge[1]);
                }
            }
        }
        return new int[2];
    }
    public boolean isTreeAfterRemoveEdge(int[][] edges, int deleteEdgeIndex) {
        UnionFind uf = new UnionFind(edges.length);
        for(int i=0;i<edges.length;i++) {
            if(i == deleteEdgeIndex) {
                continue;
            }
            int[] edge = edges[i];
            uf.union(edge[0], edge[1]);
        }
        return uf.isConnected(edges[deleteEdgeIndex][0], edges[deleteEdgeIndex][1]);
    }
}

class UnionFind {
    int[] root;
    int size;
    UnionFind(int n) {
        root = new int[n+1];
        for(int i=1;i<=n;i++) {
            root[i] = i;
        }
    }
    public int find(int x) {
        if(x != root[x]) {
            root[x] = find(root[x]);
        }
        return root[x];
    }
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            root[rootX] = rootY;
        }
    }
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
