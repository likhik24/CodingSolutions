package Matrix;
import java.util.*;
/*
High Level Knowledge Needed

minimum vertex cover is number of vertices required to cover all the edges within the graph

If we add an edge between any 1 and its 4 adjcent cells that are also 1, the question transforms into finding the minimum vertex cover.
For a bipartite graph, the minimum vertex cover is the same as the maximum bipartite matching.
Kuhn's algo is used to solve bipartite matching efficiently.
Some Details

The first implementation (21ms) is a standard Kuhn's algorithm implementation; the second way is (18ms).
Memory limit is tight -
I built the connection map between 1s first and instantly got MLE even though all test cases passsed.
Instead of recreating a seen boolean 2D array for each DFS,
we take advantage of "counting" technique to avoid reallocating m*n memory over and over.
 */
public class MinimumOperationsToRemoveAdjacentOnes {


        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        int m;
        int n;
        public int minimumOperations(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int ans = 0, count = 0;
            int[][] match = new int[m][n];
            for (int i = 0; i < m; i++){
                Arrays.fill(match[i], -1);
            }
            int[][] seen = new int[m][n];
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    //for each grid cell which is 1 we will make this as a new bipartite(visited variable here acts as the bipartite id that is used to
                    // identify if the current node is already part of same bipartite and
                    // we will do dfs to check if its not matched as part of a bipartite previously
                    // or dfs of its matching cell is part of same bipartite as our current one otherwise update its group id
                    //if the neighbor cells are all zeroes we dont need to make this 1 a 0 , so we return false;
                    //if neighboring ones are already matched and they are part of same bipartite
                    // / dont have any neighboring 1s that belong to different bipartite we return false;


                    if (grid[i][j]>0&&dfs(i, j, ++count, grid, match, seen)){
                        ans++;
                    }
                }
            }

            return ans/2; // divide by 2 because we want num of pairs.
        }

        private boolean dfs(int i, int j, int visited, int[][] grid, int[][] match, int[][] seen){
            if (seen[i][j] == visited)
                return false;
            seen[i][j]=visited;
            for (int k = 0; k < 4; k++){
                int x = i + dx[k];
                int y = j + dy[k];
                if (x < 0 || y < 0 || x == m || y == n || grid[x][y] == 0 || seen[x][y] == visited){
                    continue;
                }
                if (match[x][y]==-1||dfs(match[x][y]/n, match[x][y]%n, visited, grid, match, seen)){
                    match[x][y]=i*n+j;
                    return true;
                }
            }
            return false;
        }

    public static void main(String[] args) {
       MinimumOperationsToRemoveAdjacentOnes ones = new MinimumOperationsToRemoveAdjacentOnes();
       System.out.println(ones.minimumOperations(new int[][]{{0,1,0,0,1,0},{1,1,1,1,1,1},{0,1,0,0,1,0}}));

    }
}
