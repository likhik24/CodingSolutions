package Matrix;
//Time complexity : O(2^{m+n})
//        ). The search is repeated for each valid increasing path. In the worst case we can have O(2^{m+n})
//        ) calls if we keep doing dfs from each cell
//
//        Space complexity : O(mn)O(mn). For each DFS we need O(h)O(h) space used by the system stack, where hh is the maximum depth of the recursion. In the worst case, O(h) = O(mn)O(h)=O(mn)
//

// APPROACH 2
//TO optmize we can do dfs keeping a cache to store result of i.j cell that is already visited if(cache[i][j] != 0) return cache[i][j] else for each direction from i and j we do dfs by updating cache[i][j] = Math.max(cache[i][j], dfs(nrow, ncol))

//this will  make sure one edge is visited only once and one vertex is visited only once, here vertices are mn, WHERE m is rows, n is columns Time complexity O(V+E) i.e O(MN+MN) where E are edges we have max of 4 edges from each cell

//3RD approach
//Approach #3
// (Peeling Onion) [Accepted]
//        Intuition
//
//        The result of each cell only related to the result of its neighbors. Can we use dynamic programming?
//
//        Algorithm
//
//        If we define the longest increasing path starting from cell (i, j)(i,j) as a function
//
//        f(i, j)f(i,j)
//
//        then we have the following transition function
//
//        f(i, j) = max\{f(x, y)| (x, y)~\mathrm{is~a~neighbor~of} (i, j)~\mathrm{and} ~\mathrm{matrix}[x][y] \gt \mathrm{matrix}[i][j]\} + 1f(i,j)=max{f(x,y)∣(x,y) is a neighbor of(i,j) and matrix[x][y]>matrix[i][j]}+1
//
//        This formula is the same as used in the previous approaches. With such transition function, one may think that it is possible to use dynamic programming to deduce all the results without employing DFS!
//
//        That is right with one thing missing: we don't have the dependency list.
//
//        For dynamic programming to work, if problem B depends on the result of problem A, then we must make sure that problem A is calculated before problem B. Such order is natural and obvious for many problems. For example the famous Fibonacci sequence:
//
//        F(0) = 1, F(1) = 1, F(n) = F(n - 1) + F(n - 2)F(0)=1,F(1)=1,F(n)=F(n−1)+F(n−2)
//
//        The subproblem F(n)F(n) depends on its two predecessors. Therefore, the natural order from 0 to n is the correct order. The dependent is always behind the dependee.
//
//        The terminology of such dependency order is "Topological order" or "Topological sorting":
//
//        Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge (u, v)(u,v), vertex uu comes before vv in the ordering.
//
//        In our problem, the topological order is not natural. Without the value in the matrix, we couldn't know the dependency relation of any two neighbors A and B. We have to perform the topological sort explicitly as a preprocess. After that, we can solve the problem dynamically using our transition function following the stored topological order.
//
//        There are several ways to perform the topological sorting. Here we employ one of them called "Peeling Onion".
//
//        The idea is that in a DAG, we will have some vertex who doesn't depend on others which we call "leaves".
//        We put these leaves in a list (their internal ordering does matter), and then we remove them from the DAG. After the removal, there will be new leaves.
//        We do the same repeatedly as if we are peeling an onion layer by layer. In the end, the list will have a valid topological ordering of our vertices.
//
//        In out problem, since we want the longest path in the DAG, which equals to the total number of layers of the "onion". Thus, we can count the number of layers during "peeling" and return the counts in the end without invoking dynamic programming.


import java.util.*;

public class LongestIncreasingPathInMatrix {
    // Naive DFS Solution
// Time Limit Exceeded
    public class Solution {
        //APPROACH 1
//        private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//        private int m, n;
//
//        public int longestIncreasingPath(int[][] matrix) {
//            if (matrix.length == 0) return 0;
//            m = matrix.length;
//            n = matrix[0].length;
//            int ans = 0;
//            for (int i = 0; i < m; ++i)
//                for (int j = 0; j < n; ++j)
//                    ans = Math.max(ans, dfs(matrix, i, j));
//            return ans;
//        }
//
//        private int dfs(int[][] matrix, int i, int j) {
//            int ans = 0;
//            for (int[] d : dirs) {
//                int x = i + d[0], y = j + d[1];
//                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
//                    ans = Math.max(ans, dfs(matrix, x, y));
//            }
//            return ++ans;
//        }

        int[][] dirs = {{1,0},{0,1}, {-1,0}, {0,-1}};
        int ans = 1;
        public int longestIncreasingPath(int[][] matrix) {
            int m = matrix.length;
            int n=matrix[0].length;
            int[][] grid = new int[m+2][n+2];
            int[][] indegree = new int[m+2][n+2];
            for(int i=0;i<matrix.length;i++)
            {
                for(int j=0;j<matrix[0].length;j++) {
                    grid[i+1][j+1] = matrix[i][j];

                }
            }
            for(int i=1;i<=matrix.length;++i) {
                for(int j=1;j<=matrix[0].length;++j) {
                    for(int[] dir : dirs) {
                        if(grid[i+dir[0]][j+dir[1]] > grid[i][j])
                            indegree[i][j]++;
                    }
                }
            }
            m += 2;
            n += 2;
            Queue<int[]> queue = new LinkedList<>();
            for(int i=1;i<m-1;i++) {
                for(int j=1;j<n-1;j++) {
                    if(indegree[i][j] == 0)
                        queue.add(new int[]{i,j});
                }
            }
            int height = 0;
            while(!queue.isEmpty()) {
                int size = queue.size();
                Queue<int[]> newleaves = new LinkedList<>();
                for(int i=0;i<size;i++) {
                    int[] cell = queue.poll();
                    for(int[] dir: dirs ) {
                        int nrow = cell[0]+dir[0];
                        int ncol = cell[1]+dir[1];
                        if(grid[nrow][ncol] < grid[cell[0]][cell[1]]) {
                            --indegree[nrow][ncol];
                            if(indegree[nrow][ncol] == 0)
                                newleaves.add(new int[]{nrow, ncol});
                        }
                    }
                }
                height++;
                queue = newleaves;
            }
            return height;



        }
    }
}
