package Matrix;

import java.util.*;

//Given an M × N matrix consisting of only 0 or 1, change all elements of row i and column j to 0 if cell (i, j) has value 0. Do this without using any extra space for every (i, j) having value 0.

//We can solve this problem in O(M × N) time as well. The idea is to traverse the matrix once and use the first row and the first column (or last row and last column) to mark if any cell in the corresponding row or column has a value 0 or not. Before doing that, initially mark if the chosen row/column has any 0's present in them in two different flags.
//
//        Following is the C++, Java, and Python implementation of the idea. Note that this method will work for any integer matrix (not just binary matrix).


import java.util.Arrays;

class SetZeroes
{
    // Function to convert the matrix
    private static void convert(int[][] mat)
    {
        // base case
        if (mat == null || mat.length == 0) {
            return;
        }

        int M = mat.length;
        int N = mat[0].length;

        boolean rowFlag = false, colFlag = false;

        // scan the first row for any 0's
        for (int j = 0; j < N; j++)
        {
            if (mat[0][j] == 0)
            {
                rowFlag = true;
                break;
            }
        }

        // scan the first column for any 0's
        for (int i = 0; i < M; i++)
        {
            if (mat[i][0] == 0)
            {
                colFlag = true;
                break;
            }
        }

        // process the rest of the matrix and use the first row and the
        // first column to mark if any cell in the corresponding
        // row or column has a value 0 or not
        for (int i = 1; i < M; i++)
        {
            for (int j = 1; j < N; j++)
            {
                if (mat[i][j] == 0) {
                    mat[0][j] = mat[i][0] = 0;
                }
            }
        }

        // if `(0, j)` or `(i, 0)` is 0, assign 0 to cell `(i, j)`
        for (int i = 1; i < M; i++)
        {
            for (int j = 1; j < N; j++)
            {
                if (mat[0][j] == 0 || mat[i][0] == 0) {
                    mat[i][j] = 0;
                }
            }
        }

        // if `rowFlag` is true, then assign 0 to all cells of the first row
        for (int i = 0; rowFlag && i < N; i++) {
            mat[0][i] = 0;
        }

        // if `colFlag` is true, then assign 0 to all cells of the first column
        for (int i = 0; colFlag && i < M; i++) {
            mat[i][0] = 0;
        }
    }

    public static void main(String[] args)
    {
        int[][] mat =
                {
                        { 5, 3, 0, 8, 1 },
                        { 8, 1, 8, 4, 7 },
                        { 2, 6, 5, 0, 3 },
                        { 1, 4, 2, 7, 9 },
                        { 0, 1, 3, 6, 5 }
                };

        // convert the matrix
        convert(mat);

        // print matrix
        for (var r: mat) {
            System.out.println(Arrays.toString(r));
        }
    }


}
