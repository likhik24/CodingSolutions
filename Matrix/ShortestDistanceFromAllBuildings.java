package Matrix;
import java.util.*;
/*You are given an m x n grid grid of values 0, 1, or 2, where:

        each 0 marks an empty land that you can pass by freely,
        each 1 marks a building that you cannot pass through, and
        each 2 marks an obstacle that you cannot pass through.
        You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.

        Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

        The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

        The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.



        Example 1:


        Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
        Output: 7
        Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
        The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
        So return 7.
        Example 2:

        Input: grid = [[1,0]]
        Output: 1
        Example 3:

        Input: grid = [[1]]
        Output: -1

 */
public class ShortestDistanceFromAllBuildings {

        int[][] dirs = {{1,0},{0,1}, {-1,0}, {0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        int rows;
        int cols;
        int houses=0;
        int houseCovered=0;
        int steps=0;
        int minDist = Integer.MAX_VALUE;
        public int shortestDistance(int[][] grid) {
            int obstacles = 0;
            rows = grid.length;
            cols = grid[0].length;
            int[][] total = new int[rows][cols];
            for(int i=0;i<rows;i++) {
                for(int j=0;j<cols;j++) {
                    if(grid[i][j] == 2)
                        obstacles++;
                    if(grid[i][j] == 1) {
                        queue.clear();
                        steps = 0;
                        queue.add(new int[]{i,j});
                        minDist = Integer.MAX_VALUE;
                        bfs( grid, houseCovered,total);
                        houses++;

                    }
                }
            }

            //  bfs(queue, grid, houseCovered);
            return minDist == Integer.MAX_VALUE ? -1 : minDist;
        }

        public void bfs(int[][] grid, int houseCovered, int[][] total) {
            while(!queue.isEmpty()) {


                steps++;
                for(int level = queue.size(); level>0;level--) {
                    int[] cell = queue.poll();
                    for(int[] dir:dirs) {
                        int nrow = cell[0]+dir[0];
                        int ncol = cell[1]+dir[1];
                        if(nrow < 0 || ncol<0 || nrow>= rows || ncol>= cols || grid[nrow][ncol] == 2 || grid[nrow][ncol] == 1 )
                            continue;
                        if(grid[nrow][ncol] == houses*(-1)) {
                            queue.add(new int[]{nrow, ncol});
                            total[nrow][ncol] += steps;
                            grid[nrow][ncol]--;
                            minDist = Math.min(total[nrow][ncol], minDist);
                        }

                    }
                }

            }
        }

}
