package Matrix;
import java.util.*;
//You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
//
//        An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
//
//        You may change 0's to 1's to connect the two islands to form one island.
//
//        Return the smallest number of 0's you must flip to connect the two islands
public class ShortestBridge {
    // TC : O(M * N)
// SC : O(M * N)


    private Queue<int[]> coordinates;
    private int[][] dirs;
    public int shortestBridge(int[][] grid) {
        coordinates = new LinkedList<>();
        dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        boolean foundOneIsalnd = false; // as soon as the first tip of first island is found, make it true so that we do not continue looking
        int rows = grid.length, cols = grid[0].length;

        for(int i = 0; i < rows; i++) {
            if(foundOneIsalnd) break; // the for loop does not stop even after we found first island so break the loop manually
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 1 && !foundOneIsalnd) {
                    foundOneIsalnd = true;
                    dfs(grid, i, j);
                    break; // this will only break the inner for loop
                }
            }
        }

        int shortestBridge = 0;

        // BFS to nearest tip of island 2
        while(!coordinates.isEmpty()) {
            int size = coordinates.size();

            for(int i = 0; i < size; i++) {
                int[] curr = coordinates.poll();

                for(int[] dir : dirs) { // check in all directions
                    int neighborX = curr[0] + dir[0];
                    int neighborY = curr[1] + dir[1];

                    // out of bound or already taken care of
                    if(neighborX < 0 || neighborX > rows - 1 || neighborY < 0 || neighborY > cols - 1 || grid[neighborX][neighborY] == 2) {
                        continue;
                    }

                    // found the SECOND island
                    if(neighborX >= 0 && neighborX < rows && neighborY >= 0 && neighborY < cols && grid[neighborX][neighborY] == 1) {
                        return shortestBridge;
                    }

                    // if the neighbor is 0, that means it could be used to make the shortest bridge between 2 islands so add it to queue and make it 2
                    if(grid[neighborX][neighborY] == 0) {
                        coordinates.add(new int[] {neighborX, neighborY});
                        grid[neighborX][neighborY] = 2;
                    }
                }
            }

            shortestBridge++;
        }

        return shortestBridge;
    }

    private void dfs(int[][] grid, int i, int j) {
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] != 1) {
            return;
        }

        grid[i][j] = 2; // change to anything other than 0 and 1
        coordinates.add(new int[] {i, j}); // preserve the coordinate

        dfs(grid, i + 1, j); // down
        dfs(grid, i - 1, j); // up
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left
    }

}
