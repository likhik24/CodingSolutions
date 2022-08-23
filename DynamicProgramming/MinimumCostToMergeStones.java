package DynamicProgramming;

/*
There are n piles of stones arranged in a row. The ith pile has stones[i] stones.

A move consists of merging exactly k consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these k piles.

Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

Let's first think this problem in a simple way, what if we can only merge 2 adjacent piles into one pile?

For given example [3,2,4,1], we will normally think this as a greedy problem, we always merge two relatively small piles.
[[3, 2], 4, 1] -> [5, [4, 1]] -> [5, 5] -> [10](cost: 20).

While one counterexample is [6,4,4,6],
if we merge greedily, [6, [4, 4], 6] -> [[6, 8], 6] -> [14, 6] -> [20] (cost: 42),
while the optimal way is [[6, 4], 4, 6] -> [10, [4, 6]] -> [10, 10] -> [20] (cost:40).

What if we think this problem reversely, which two piles should we merge at the last step?

We don't know which two piles to merge for now, but we can know the cost of that step, which is the sum of that two piles.
[3 | 2, 4, 1]
3 + 7 = 10
[3 , 2 | 4, 1]
5 + 5 = 10
[3 , 2, 4 | 1]
9 + 1 = 10
No matter how to split the two piles, the sum is always the sum of the two piles.

Now the only thing that matters is how to get the minimum cost to split to two piles.
So we need to know the minimum cost of merging left part to 1 pile, and minimum cost of merging right part to 1 pile, this is a typical sub problem.

State: Minimum cost merging piles from i to j to 1 pile.

Function: dp[i][j] = min(sum[i][j] + dp[i][k] + dp[k + 1][j]) (i <= k < j)

Init: dp[i][i] = 0 (Already a pile)

Answer: dp[1][len] (len is the stones number)

public int mergeStonesTwo(int[] stones) {
    if (stones == null || stones.length == 0) {
        return 0;
    }
    int len = stones.length;
    int max = Integer.MAX_VALUE;
    int[][] dp = new int[len + 1][len + 1];
    int[] prefixSum = new int[len + 1];
    int i, j, k, l;
    for (i = 1; i <= len; i++) {
        prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
    }

    for (i = 1; i <= len; i++) {
        dp[i][i] = 0;
    }

    for (l = 2; l <= len; l++) {
        for (i = 1; i <= len - l + 1; i++) {
            j = i + l - 1;
            dp[i][j] = max;
            int sum = prefixSum[j] - prefixSum[i - 1];
            for (k = i; k < j; k++) {
                dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum);
            }
        }
    }

    return dp[1][len];
}
Time Complexity: O(n ^ 3) (n is the number of stone)

During interview, this might be our first question, the follow-up may ask us what if we can merge x consecutive piles? Just like original problem.
Now our sub problem becomes: we need to know the minimum cost of merging left part to x - 1 piles and right part to 1 pile.
Our state has one more information to know, how many piles. So we add the field to our dp array.

State: Minimum cost merging piles from i to j to k pile.

Function:
dp[i][j][1] = dp[i][j][K] + sum[i][j] (dp[i][j][K] != max)
dp[i][j][k] = min(dp[i][t][k - 1] + dp[t + 1][j][1]) (t ∈ [i, j) && dp[i][t][k - 1] != max && dp[t+1][j][1] != max && k ∈ [2, K])

Init: dp[i][i][1] = 0 (Already a pile) others = max

Answer: dp[1][len][1] (len is the stones number)

Time Complexity: O(n^3 * K) (n is the number of stone)

Similar problem include 312. Burst Balloons. They are all dynamic programming problem related to interval.


// Top-Down
  public int mergeStones(int[] stones, int k) {
        dp = new int[stones.length+1][stones.length+1][k+1];
        //exxactly k consecutive piles
        //move cost is total num of stones in these piles
        //find min cost to merge all piles of stones into one pile
        //if we take this pile we need to take adjacent pile
         if ((stones.length - 1) % (k - 1) != 0) {
            return -1;
        }
        ArrayList<Integer> stonesList = new ArrayList<>();
        //if we take this stone , adjacent k-1 stones take mergecost
        //if we dont take this stone
        for(int stone: stones)
            stonesList.add(stone);
        int total = minCost(stonesList, k, 0, new ArrayList<>(stonesList), 0);
        return total == Integer.MAX_VALUE ? -1 : total;
    }

    public int minCost(ArrayList<Integer> stonesList, int k, int index, ArrayList<Integer> currList, int cost) {
        if(currList.size() == 1)
        {

            totalCost = Math.max(cost, totalCost);
            return 0;
        }

        if(index >= currList.size() || k>currList.size())
            return Integer.MAX_VALUE;
         int takeCost = Integer.MAX_VALUE;
        int notTakeCost = minCost(stonesList, k, index+1, currList, cost);;
        if(k+index<= currList.size()) {
            //dp[index][k+index]
            int sum= currList.subList(index, Math.min(k+index, currList.size())).stream().mapToInt(Integer::intValue).sum();
            ArrayList<Integer> sub = new ArrayList<>(currList);
            sub.removeAll(currList.subList(index, Math.min(k+index, currList.size())));
            sub.add(sum);
            int cost1 =  minCost(stonesList, k, 0, sub, cost);
            if(cost1 != Integer.MAX_VALUE)
                takeCost = sum + cost1;
            //System.out.println(takeCost);
        }

         //System.out.println("notTake " + notTakeCost);
        return Math.min(takeCost, notTakeCost);
    }
 */
public class MinimumCostToMergeStones {
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if ((len - 1) % (K - 1) != 0) {
            return -1;
        }

        int i, j, k, l, t;

        int[] prefixSum = new int[len + 1];
        for (i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }

        int max = 99999999;
        int[][][] dp = new int[len + 1][len + 1][K + 1];
        for (i = 1; i <= len; i++) {
            for (j = 1; j <= len; j++) {
                for (k = 1; k <= K; k++) {
                    dp[i][j][k] = max;
                }
            }
        }

        for (i = 1; i <= len; i++) {
            dp[i][i][1] = 0;
        }

        for (l = 2; l <= len; l++) {
            for (i = 1; i <= len - l + 1; i++) {
                j = i + l - 1;
                for (k = 2; k <= K; k++) {
                    for (t = i; t < j; t++) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][t][k - 1] + dp[t + 1][j][1]);
                    }
                }

                dp[i][j][1] = dp[i][j][K] + prefixSum[j] - prefixSum[i - 1];
            }
        }

        return dp[1][len][1];
    }
}
