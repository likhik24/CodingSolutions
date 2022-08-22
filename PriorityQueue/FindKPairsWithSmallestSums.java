package PriorityQueue;
//
//You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
//
//        Define a pair (u, v) which consists of one element from the first array and one element from the second array.
//
//        Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
//
//
//
//        Example 1:
//
//        Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//        Output: [[1,2],[1,4],[1,6]]
//        Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//        Example 2:
//
//        Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//        Output: [[1,1],[1,1]]
//        Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//        Example 3:
//
//        Input: nums1 = [1,2], nums2 = [3], k = 3
//        Output: [[1,3],[2,3]]
//        Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]

//we can convert this problem into get k smallest element in sorted matrix by having matrix[i][j] as the sum of nums1[i] + nums2[j], then the rows will be sorted along with columns


//WE WILL TAKE J AS 0 AND COMPUTE SUM FOR ALL NUMS1 as( nums1[i]+nums2[0])  WHICH WILL GIVE US 1ST MIN SUM , then we keep polling min and adding it to result while decrementing k and adding (i,j+1) to the queue as the next min can be (i+1, j ) & (i,j+1) and i+1,j will already be in the priorityQueue

import java.util.*;

public class FindKPairsWithSmallestSums {
    class Pair {
        int[] numsindextopick;
        Pair(int i, int j)
        {
            numsindextopick = new int[2];
            numsindextopick[0] = i; //index of elem in nums1
            numsindextopick[1] = j; //index of elem in nums2
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        HashSet<String> set = new HashSet<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a.numsindextopick[0]] + nums2[a.numsindextopick[1]])));

        for(int i=0;i<nums1.length && i<k;i++) {
            pq.add(new Pair(i,0));
        }

        List<List<Integer>> result = new ArrayList<>();
        while(k-- > 0 && !pq.isEmpty()) {
            Pair curr = pq.poll();
            List<Integer> res = new ArrayList<>();
            res.add(nums1[curr.numsindextopick[0]]);
            res.add(nums2[curr.numsindextopick[1]]);
            result.add(res);
            if(curr.numsindextopick[1]+1 < nums2.length)
                pq.add(new Pair(curr.numsindextopick[0], curr.numsindextopick[1]+1));
        }

        return result;
    }
}
