package PriorityQueue;

import java.util.*;
// There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
//
//        We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:
//
//        Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
//        Every worker in the paid group must be paid at least their minimum wage expectation.
//        Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.
//
//
//
//        Example 1:
//
//        Input: quality = [10,20,5], wage = [70,50,30], k = 2
//        Output: 105.00000
//        Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
//        Example 2:
//
//        Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
//        Output: 30.66667
//        Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.


public class MinCostToHireWorkers {
    class Point {
        int wage;
        int quality;
        double ratio;
        int index;
        Point(int index, int wage, int quality, double ratio) {
            this.wage = wage;
            this.quality = quality;
            this.ratio = ratio;
            this.index= index;
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        // 1.33 8 0.2 0.2 7

        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.ratio));
        for(int i=0;i<quality.length; i++) {
            queue.add(new Point(i, wage[i], quality[i], (double)(wage[i]/quality[i])));
        }
        int sum = 0;
        double ans = 1e9;
        PriorityQueue<Integer> pool = new PriorityQueue();
        while(!queue.isEmpty() ) {
            Point curr = queue.poll();
            pool.offer(-curr.quality);
            sum += curr.quality;

            if (pool.size() > k)
                sum += pool.poll(); // to remove higher quality worker that is already added in pool if pool has more elements than k as they need to be paid more if we take into account ratio of curr worker which is higher than ratio of higher quality worker in the pool
            if (pool.size() == k)
                ans = Math.min(ans, sum * curr.ratio);

        }
        return ans;
    }
}
