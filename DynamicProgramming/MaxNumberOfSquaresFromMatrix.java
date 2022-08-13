package DynamicProgramming;
import java.util.*;

//Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
//
//
//
//        Example 1:
//
//        Input: matrix =
//        [
//        [0,1,1,1],
//        [1,1,1,1],
//        [0,1,1,1]
//        ]
//        Output: 15
//        Explanation:
//        There are 10 squares of side 1.
//        There are 4 squares of side 2.
//        There is  1 square of side 3.
//        Total number of squares = 10 + 4 + 1 = 15.
//        Example 2:
//
//        Input: matrix =
//        [
//        [1,0,1],
//        [1,1,0],
//        [1,1,0]
//        ]
//        Output: 7
//        Explanation:
//        There are 6 squares of side 1.
//        There is 1 square of side 2.
//        Total number of squares = 6 + 1 = 7.
//
//
//        Constraints:
//
//        1 <= arr.length <= 300
//        1 <= arr[0].length <= 300
//        0 <= arr[i][j] <= 1
public class MaxNumberOfSquaresFromMatrix {

    public int countSquares(int[][] matrix) {
        int count=0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++) if(matrix[i][matrix[0].length-1] == 1) dp[i][matrix[0].length-1] = 1;
        for(int i=0;i<matrix[0].length;i++) if(matrix[matrix.length-1][i] == 1) dp[matrix.length-1][i] = 1;

        //we are checking if i,j is left top corner of the square, whats the max square we can form, it will be min of right most column values and current column values for i,i+1
        for(int i=matrix.length-2;i>=0;i--)
        {
            for(int j=matrix[0].length-2; j>=0;j--) {
                // if(i == matrix.length-1 || j==matrix[0].length-1)
                //     continue;
                if(matrix[i][j] == 1) { //check if the right edge min is also one and current edge is also 1 ( an edge is the col line formed on current row and right edge is column line formed on enxt column with same row coordinates)
                    dp[i][j] = 1+ Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i+1][j+1]));
                }
            }
        }
        for(int i = 0; i < matrix.length; i++){
            count += (int)Arrays.stream(dp[i]).sum();
        }
        return count;
    }

}
