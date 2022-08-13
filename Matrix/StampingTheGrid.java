package Matrix;
import java.util.*;
/*
You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).

You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:

Cover all the empty cells.
Do not cover any of the occupied cells.
We can put as many stamps as we want.
Stamps can overlap with each other.
Stamps are not allowed to be rotated.
Stamps must stay completely inside the grid.
Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.



Example 1:


Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
Output: true
Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
Example 2:


Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
Output: false
Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.
I recorded consecutive empty rows, filled all fillable spots by 1, and then confirmed if everything is filled
Please see the comments for more detailed explanation

Given grid of size m x n
Time Complexity O(mn)
Memory O(n) since we use the existing grid // need pre = O(n)
 */
public class StampingTheGrid {
    int rows;
    int cols;
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        rows = grid.length;
        cols = grid[0].length;
        int[] pre = new int[cols];
        Arrays.fill(pre,1);
        //fill all zeroes in a row with corresponding height of zeroes -1 so we can find number of rows which have zeroes above a particular cell if this cell acts as the bottom row

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j] == 0) {
                    grid[i][j] = (pre[j] != 1) ? (Math.max(pre[j]-1, -stampHeight)) : -1;
                }

            }
            pre = grid[i]; //override previous rows values to pre so that nextrow zeroes will have incremented value of pre[i]-height to capture from how much height from top they are zeroes

        }

        //we marked height of all zeroes to be -zeroes hieght which can be from -1 to -stampheight, now we iterate from row of stampheight-1 to see if grid[i][j] = -stampehight that means htis row can be bottom row of a stamp and also increment count to see if we can form stampwidth worth of 0s in current row consecutively, if we dont find a -stampheight in middle of row we reduce count to 0 as we cannot form a stamp with current cell as bottom most row and left most col, if count == stampswidth we can form the stamp
        for(int i=stampHeight-1;i<grid.length;i++) {
            int count = 0;
            for(int j=0;j<cols;j++) {
                if(grid[i][j] == -stampHeight) count++;
                else count=0;
                if(count == stampWidth) fill(i,j,grid, stampHeight, stampWidth);
                else if (count > stampWidth) fill(i,j,grid,stampHeight,1);
            }

        }
        for(int[] arr: grid) {
            for (int v: arr) {
                if (v != 1) return false;
            }
        }
        return true;
    }
    private void fill( int r, int c,int[][] grid, int height, int width) {
        for (int i = c - width + 1; i <= c; i++) {
            int j = r;
            while (j >= r - height + 1 && grid[j][i] != 1) // this is to fill rows with 1 until all rows consecutively above current r have -stampheight as value
                grid[j--][i] = 1;
        }
    }


    //0 1 4 4
    public boolean dfs(int row, int col, int[][]grid, int stampHeight, int stampWidth) {
        boolean res = true;
        if(row == stampHeight || col == stampWidth)
            return true;

        if(grid[row][col] == 1)
            return false;
        grid[row][col] = -1;
        if(row+1 <= stampHeight && row+1 < rows && grid[row+1][col] != -1) {
            res &= dfs(row+1, col, grid, stampHeight, stampWidth);
            if(res == false)
                return false;
        }
        if(row-1 >= stampHeight && row-1 >=0 && grid[row-1][col] != -1) {
            res &= dfs(row-1, col, grid, stampHeight, stampWidth);
            if(res == false)
                return false;
        }
        if(col+1 <= stampWidth && col+1 < cols && grid[row][col+1] != -1) {
            res &= dfs(row, col+1, grid, stampHeight, stampWidth);
            if(res == false)
                return false;
        }
        if(col-1 >= stampWidth && col-1 >= 0 && grid[row][col-1] != -1) {
            res &= dfs(row, col-1, grid, stampHeight, stampWidth);
            if(res == false)
                return false;
        }
        return res;
    }
}
