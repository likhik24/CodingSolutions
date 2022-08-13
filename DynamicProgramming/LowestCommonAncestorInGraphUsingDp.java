package DynamicProgramming;

//We can find the lowest common ancestor by computing the taregts parents at each level until root level,
// if root is the only ancestor which leads to time complexity of O(n^2)

//Parent[node][height h] = parent[parent[node][height h/2]][height h/2]
//        for finding parent of node at height h from node we cna find parent at height h/2 and parent of that node at h/2 gives us the parent at height h
//
//        if h is power(2,x) , then we can reduce it to x
//        h/2 is power(2,x-1) , we can reduce it to x-1
//
//        parent[node][1] = parent[2^0] =  1st parent of node which we can get doing dfs
//
//        parent[node][x] = parent[parent[node][x-1]][x-1]
//
//        time complexity is O(Nlogn ) where height is reduced by h/2 at every point


public class LowestCommonAncestorInGraphUsingDp {
    public int log(int  N) {
        System.out.println(Integer.numberOfLeadingZeros((N)));
        return 31-Integer.numberOfLeadingZeros((N));
    }
    public void dfs(int source, int parent, int[] parents, int[] level, int currlevel, int[][] adj) {
        if(parent == 0)
            level[source] = 0;
        else
            level[source] = currlevel;
            parents[source] = parent;
            int[] adjNodes = adj[source];
            for (int i = 0; i < adjNodes.length; i++) {
                if (adjNodes[i] == 1 && i != parent)
                    dfs(i, source, parents, level,currlevel+1, adj);

            }

    }

    public void buildParent(int[] parents, int[][] adj,int[] level) {
        for(int i=0;i<parents.length;i++)
            parents[i] = i;
        dfs(1,0,parents, level,1, adj);
    }

    public void findAncestors(int[] parents,int[][] Dp) {
        System.arraycopy(parents,1,Dp[0], 1,parents.length-1); //this copies the first parents of all nodes to dp[0] i.e at height 2^0 at hieght 1, which will hold all first parents of nodes Dp[1][j] = Dp[0][Dp[0][j]]
        // if you need ancestor of jth node at height 2^1 then we get 1st ancestor of dp[0][j] which gives ancestor at height 1
        for(int i=1; i<Dp.length;i++) {
            for(int j=1;j<parents.length;j++) {
                Dp[i][j] = Dp[i - 1][Dp[i - 1][j]];
            }
        }
    }

    public int findLca(int source, int dest, int[] levels, int[][] Dp, int[] parents) {
        if(levels[source] > levels[dest])
        {
            int temp = source;
            source = dest;
            dest = temp;
        }
        int diff = levels[dest] - levels[source];
        while(diff > 0) {
            int k = log(diff);
            dest = Dp[k][dest];
            diff -= (1 << k);
        }

//        while (source != dest) {
//            int i = log(levels[source]);
//            for (; i > 0 && Dp[i][source] == Dp[i][dest]; )
//                i--;
//
//            source = Dp[i][source];
//            dest = Dp[i][dest];
//        }
        int depth = 1;
        while(source != dest && depth <= levels[source]) {

                source = Dp[depth][source];
                dest = Dp[depth][dest];
                depth = depth * 2;

        }
        return source;
    }

    public static void main(String[] args) {
        LowestCommonAncestorInGraphUsingDp ancestorGraph = new LowestCommonAncestorInGraphUsingDp();
        int N = 5 ;//no of vertices
        int[] parents = new int[N+1];
        int[] level = new int[N+1];
        int[][] adj = new int[N+1][N+1];
        adj[1][2] = 1;
        adj[2][5] = 1;
        adj[1][3] = 1;
        adj[4][2] = 1;
        adj[3][4] = 1;
        int[][] Dp = new int[ancestorGraph.log(N)+1][N+1];
        ancestorGraph.buildParent(parents, adj, level); //we build parent map for all ndoes using dfs which covers all edges O(E+V)
        ancestorGraph.findAncestors(parents, Dp); //now find ancestors of each node at different heights
        System.out.println("Lowest common ancestor " + ancestorGraph.findLca(2,3, level,Dp, parents) );
        //vertex 1 has no indegree so it is root 1 has (2,3) as children , 2 has 5 as children, 3 has 4 as children 4 has 2 as children
    }
}
