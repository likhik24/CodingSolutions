package PriorityQueue;
import java.util.*;
/*
You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.

Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.

It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.



Example 1:

Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
Output: 4
Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.
Example 2:

Input: points = [[0,0],[3,0],[9,2]], k = 3
Output: 3
Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.

 */
public class MaximumValueOfEquation {

        class Pair {
            int key;
            int value;
            Pair(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        public int findMaxValueOfEquation(int[][] points, int k) {
//         int[][][] dp = new int[points.length][points.length];
//         dp[i][j][0] = >taking i j has max value if xi-xj <=k
//             dp[i][j][1] cost of not taking i, j and proceeding to future indices

//             dp[i][i] = iNTEGER.MIN_VALUE;
//         MINCOST(0, k, dp, points)
//             if(index >= points.length)
//             return min_value;

//             dp[index][j][0] = dp[index-1][j][0], dp[index][0] math.max
//                 same with index j 1

            //here as its sorted based on x cordinate we can add all difference b/w yi+xi to pq to find max and sort prioritqueue to give max key  always and if keys are equal give the one with lowest x so we can remove it if its difference is greater than k

            PriorityQueue<Pair> pqueue = new PriorityQueue<>((a,b) ->( a.key == b.key ? a.value-b.value : b.key-a.key));
            int maxSum = Integer.MIN_VALUE;
            for(int[] point: points) {
                while(!pqueue.isEmpty() && point[0]-(pqueue.peek().value) > k)
                    pqueue.poll();
                int sum = point[1]+point[0]; //yj-xj
                if(!pqueue.isEmpty()) {
                    sum += pqueue.peek().key; //4
                }
                else {
                    sum = Integer.MIN_VALUE;
                }
                pqueue.add(new Pair(-point[0]+point[1], point[0])); //2,1  -2,2 5 5
                maxSum = Math.max(sum, maxSum); //4
            }
            return maxSum;
        }

}
