package UnionFind;

import java.util.*;

//Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal directions.
//
//        The score of a path is the minimum value in that path.
//
//        For example, the score of the path 8 → 4 → 5 → 9 is 4.
//
//
//        Example 1:
//
//
//        Input: grid = [[5,4,5],[1,2,6],[7,4,6]]
//        Output: 4
//        Explanation: The path with the maximum score is highlighted in yellow.
//        Example 2:
//
//
//        Input: grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
//        Output: 2
//        Intuition
//
//        Take a look at the picture below. Let's say that the cells in colors are the visited cells, and the grey cells are the unvisited cells. We can tell a path has been found when the top-left cell is 4-directionally connected to the bottom-right cell.
//
//        limits
//
//        We can maximize the score of a path by always picking the unvisited cell with the largest value. To determine what order we should visit the cells, we can sort them by their values. Then we traverse these cells in order from the largest value to the smallest value. Each time we visit a cell, we mark it as visited and use the union-find data structure to connect this cell with its visited neighbors.
//
//        After visiting each cell, we check if the top-left cell and the bottom-right cell have been connected, if so, it means that there is at least one 4 directionally connected path between them, and the last cell we visit is the 'last piece of the puzzle' in this path. Since we are traversing the cells by their decreasing values, the value of the last visited cell is the minimum value in this path and, therefore, the maximum minimum score for all valid paths.
//
//
//        Sort all the cells decreasingly by their values.
//        Iterate over the sorted cells from the largest value, for each visited cell, check if it has any 4-directionally connected visited neighbor cells, if so, we use the union-find data structure to connect it with its visited neighbors.
//        Check if the top-left cell is connected with the bottom-right cell.
//        If so, return the value of the last visited cell.
//        Otherwise, repeat from the step 2.
//
//
//        This great question utilize the power of Union-Find which enables "constant" time query. The tricks are actually pretty straightforward:
//
//        sort both queries by increasing weight
//        sort edges by increasing weight
//        for each query, union all edges whose weight is less than this query
//        check if two nodes in query belongs to the same group
//        This idea is pretty important (yet not unique). We may utilize similar tricks in these problems:
//
//        1631. Path With Minimum Effort
//        1102. Path With Maximum Minimum Value
public class PathWithMaximumMinimumValue {
        class UF {
            // root for recording all the roots.
            private int[] parent;
            private int[] rank;
            public UF(int R, int C) {
                rank = new int[R * C];
                parent = new int[R * C];
                for (int i = 0; i < parent.length; ++i)
                    parent[i] = i;
            }

            // Find the root of x.
            public int find(int x) {
                if (x != parent[x])
                    return find(parent[x]);
                return parent[x];
            }

            // union the roots of x and y.
            public void union(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rootX != rootY) {
                    if (rank[rootX] > rank[rootY]) {
                        parent[rootY] = rootX;
                    } else if (rank[rootX] < rank[rootY]) {
                        parent[rootX] = rootY;
                    } else {
                        parent[rootY] = rootX;
                        rank[rootX] += 1;
                    }
                }
            }
        }

        // 4 directions to a cell's possible neighbors.
        private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public int maximumMinimumPath(int[][] grid) {
            int R = grid.length, C = grid[0].length;

            // Sort all the cells by their values.
            List<int[]> vals = new ArrayList<>();

            // Intialize the root of all the cells and mark
            // all the cells as false (unvisited).
            boolean[][] visited = new boolean[R][C];

            // Root of all the R * C cells
            UF uf = new UF(R, C);

            // Intialize the root of all the cells.
            for (int row = 0; row < R; ++row)
                for (int col = 0; col < C; ++col)
                    vals.add(new int[]{row, col});

            // Sort all the cells by values from large to small.
            Collections.sort(vals, (gridA, gridB) -> {
                return grid[gridB[0]][gridB[1]] - grid[gridA[0]][gridA[1]];
            });

            // Iteration over the sorted cells.
            for (int[] curGrid : vals) {
                int curRow = curGrid[0], curCol = curGrid[1];
                int curPos = curRow * C + curCol;

                // Mark the current cell as visited.
                visited[curRow][curCol] = true;
                for (int[] dir : dirs) {
                    int newRow = curRow + dir[0];
                    int newCol = curCol + dir[1];
                    int newPos = newRow * C + newCol;

                    // Check if the current cell has any unvisited neighbors
                    // with value larger than or equal to val.
                    if (newRow >= 0 && newRow < R && newCol >= 0
                            && newCol < C && visited[newRow][newCol] == true) {
                        // If so, we connect the current cell with this neighbor.
                        uf.union(curPos, newPos);
                    }
                }

                // Check if the top-left cell is connected with the bottom-right cell.
                if (uf.find(0) == uf.find(R * C - 1)) {
                    // If so, return the value of the current cell.
                    return grid[curRow][curCol];
                }
            }
            return -1;
        }

}
