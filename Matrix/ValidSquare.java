package Matrix;
import java.util.*;
/*
Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.

        The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.

        A valid square has four equal sides with positive length and four equal angles (90-degree angles).



        Example 1:

        Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
        Output: true
        Example 2:

        Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
        Output: false
        Example 3:

        Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
        Output: true */
public class ValidSquare {
    class Solution {
        // p1 p2p3 p4
        //     p1 p3 p2 p4
        //     p2 p1 p3 p4
        //1 2 3 4
        // if(first ==n)
        //     result.add(nums);
        // for(int i=first;i<n;i++)
        //     swap(i,first, arr)
        //     permute(first+1, arr)
        //     swap(i,first,arr);


      /*  Instead of considering all the permutations of arrangements possible, we can make use of maths to simplify this problem a bit. If we sort the given set of points based on their x-coordinate values, and in the case of a tie,
        based on their y-coordinate value, we can obtain an arrangement, which directly reflects the arrangement of points on a valid square boundary possible.
        */
        public double dist(int[] p1, int[] p2) {
            return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
        }
        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            int[][] p={p1,p2,p3,p4};
            Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
            return dist(p[0], p[1]) != 0 && dist(p[0], p[1]) == dist(p[1], p[3]) && dist(p[1], p[3]) == dist(p[3], p[2]) && dist(p[3], p[2]) == dist(p[2], p[0])   && dist(p[0],p[3])==dist(p[1],p[2]);
        }



//     public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
//        HashSet<Double> set = new HashSet<>(Arrays.asList(distance(p1, p2), distance(p1, p3), distance(p1, p4),
//             distance(p2, p3), distance(p2, p4), distance(p3, p4)));
//     return !set.contains(0) && set.size() == 2;
//     }

//     public double distance(int[] p1, int[] p2) {
//         return Math.pow(p1[0]-p2[0], 2) + Math.pow(p1[1]-p2[1], 2);
//     }

        // public boolean check(int[][] p) {
        // return getDist(p[0], p[1]) > 0 && getDist(p[0], p[1]) == getDist(p[1], p[2]) &&     getDist(p[1], p[2]) == getDist(p[0], p[3]) && getDist(p[2], p[3]) == getDist(p[0], p[1]) && getDist(p[1], p[3]) == getDist(p[2], p[0]);
        // }
        //similar to do how do permutations, if first == n then the current permutation is generated so we check for the result and return it;
//     public boolean checkAllPermutations(int[][] p, int first) {
//         if(first == p.length)
//             return check(p);
//         boolean res = false;
//         for(int i=first;i<p.length;i++) {
//             swap(p,i, first);
//             res |= checkAllPermutations(p, first+1);
//             swap(p, first, i);
//         }
//         return res;
//     }

//     public void swap(int[][] p,int index1, int index2) {
//         int[] arr = p[index1];
//         p[index1] = p[index2];
//         p[index2] = arr;
//     }
    }
}
