package Backtracking;

import java.util.*;
//Given a rectangle of size n x m, return the minimum number of integer-sided squares that tile the rectangle.
//
//
//
//        Example 1:
//
//
//
//        Input: n = 2, m = 3
//        Output: 3
//        Explanation: 3 squares are necessary to cover the rectangle.
//        2 (squares of 1x1)
//        1 (square of 2x2)
//        Example 2:
//
//
//
//        Input: n = 5, m = 8
//        Output: 5
//        Example 3:
//
//
//
//        Input: n = 11, m = 13
//        Output: 6

public class MinimumNumberOfSquaresToFitRectangle {

//     The basic idea is to fill the entire block bottom up. In every step, find the lowest left unfilled square first, and select a square with different possible sizes to fill it. We maintain a height array (skyline)
//     with length n while dfs. This skyline is the identity of the state.
//     The final result we ask for is the minimum number of squares for the state [m, m, m, m, m, m, m] (The length of this array is n).
//     Of course, backtrack without optimization will have a huge time complexity, but it can be pruned or optimized by the following three methods.

// When the current cnt (that is, the number of squares) of this skyline has exceeded the value of the current global optimal solution, then return directly.
// When the current skyline has been traversed, and the previous cnt is smaller than the current cnt, then return directly.
// When we find the empty square in the lowest left corner, we pick larger size for the next square first. This can make the program converge quickly. (It is a very important optimization)

        int maxCount = Integer.MAX_VALUE;
        // split rectangles into minimum number of squares as possible
        // 5*8 - min is 5;
        // take any of these 1*1 2*2 3*3 4*4 5*5
        //    if 5*5 -> 5*3  -> (2*3) -> (2*3) -> 2*2 -> 1*1
        // 4*4 1*1 4*4
        HashMap<Long, Integer> keyCountMap = new HashMap<>();
        //if count for a key that we computed is greater than or equal to exisitng count for that key we can return , if the curr count is greater than or equal to maxCount computed then return;
        //start with the left and bottom most cell and generate the highest possible square for that cell
        public int tilingRectangle(int n, int m) {
            if(n==m)
                return 1;
            if(n > m)
            {
                int temp = n;
                n=m;
                m=temp;
            }
            dfs(n,m,0, new int[n+1]);
            return maxCount;
        }

        public void dfs(int n, int m, int count, int[] height) {
            if(count >= maxCount)
                return;
            boolean isFull = true; //to check if already height m square is filled in the height and return as we start from lowest and go alll the way up
            int minHeight = Integer.MAX_VALUE;
            int pos = -1;
            for(int i=1;i<height.length;i++) {
                if (height[i] < m) isFull = false;
                if(height[i]< minHeight) {
                    pos= i;
                    minHeight = height[i];
                }
            }
            if(isFull) {
                maxCount = Math.min(maxCount, count);
                return;
            }
            //this is to check if we already traversed this cell and its in map and its count is less than current count
            long key = 0, base = m+1;
            for(int i=1;i<height.length;i++) {
                key += height[i]*base; //we multiply it by m+1 to get haskey for each cell
                base *= m+1; //for every next cell we increment the base to compute different hashkey for each cell
            }
            if(keyCountMap.containsKey(key)) { if(keyCountMap.get(key) <= count) return;}
            keyCountMap.put(key, count);
            int end = pos;

            while(end+1 < height.length && height[end+1] == height[end] && (end - pos + 1 + minHeight) < m) end++; //end-pos check is to make sure end+1 < m as we want to form a square of atleast 1
            for(int j=end;j>=pos;j--) {
                int k=j-pos+1;
                int[] temp = Arrays.copyOf(height, n+1);
                for(int d=pos;d<=j;d++)
                    temp[d] += k;
                dfs(n,m, count+1, temp);
            }
        }

}
