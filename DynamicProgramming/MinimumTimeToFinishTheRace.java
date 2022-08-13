package DynamicProgramming;
import java.util.*;
//You are given a 0-indexed 2D integer array tires where tires[i] = [fi, ri] indicates that the ith tire can finish its xth successive lap in fi * ri(x-1) seconds.
//
//        For example, if fi = 3 and ri = 2, then the tire would finish its 1st lap in 3 seconds, its 2nd lap in 3 * 2 = 6 seconds, its 3rd lap in 3 * 22 = 12 seconds, etc.
//        You are also given an integer changeTime and an integer numLaps.
//
//        The race consists of numLaps laps and you may start the race with any tire. You have an unlimited supply of each tire and after every lap, you may change to any given tire (including the current tire type) if you wait changeTime seconds.
//
//        Return the minimum time to finish the race.
//
//
//
//        Example 1:
//
//        Input: tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
//        Output: 21
//        Explanation:
//        Lap 1: Start with tire 0 and finish the lap in 2 seconds.
//        Lap 2: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
//        Lap 3: Change tires to a new tire 0 for 5 seconds and then finish the lap in another 2 seconds.
//        Lap 4: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
//        Total time = 2 + 6 + 5 + 2 + 6 = 21 seconds.
//        The minimum time to complete the race is 21 seconds.
//        Example 2:
//
//        Input: tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
//        Output: 25

public class MinimumTimeToFinishTheRace {
        //TIME FOR X LAP FI*RI^(X-1)

// Intuition: We have a lot of tires, but the number of laps is limited to 1,000.

// We first compute how long it takes to finish n laps with each tire without changing it.

// Optimization: it only makes sense to use a tire while the lap time is less than fi + changeTime.

        // We track the best time to complete i laps, across all tires, in the best array.
        //changetime is 10^5 , if we find for how much power 2 because ri is min 2 should be less than 10^5 , we can find that 2^19 is the max number thats less than 10^5 the changeNumber as we can        //use changenumber and start from lap 0 for current tire
        // we can use this to max do 19 laps for same tire without changing it and save it in dp array
        //we find best times possible for each lap until laptime doesnot exceed the changeTime + tire[0] as we can use the same tire only until then

//     After that, we run DFS, memoising it by the number of laps to race (dp):

// For remaining laps, we find the best time by trying to race i laps, change a tire, and recurse on laps - i remaining laps.
// For i laps, we pick the best time to finish those laps.

        int maxLaps = 0; int[] dp;
        public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
            int totalLaps= 19;
            int best[] = new int[19];
            Arrays.fill(best, Integer.MAX_VALUE);
            for(int[] tire: tires) {
                int totalTime;
                int lapTime = totalTime = tire[0];
                for(int lap = 1; lap<=Math.min(numLaps, totalLaps) && lapTime<changeTime+tire[0];++lap) {
                    maxLaps = Math.max(lap, maxLaps);
                    best[lap] = Math.min(best[lap], totalTime);
                    lapTime *= tire[1];
                    totalTime += lapTime;
                }
            }
            dp = new int[numLaps+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            return dfs(best, numLaps, changeTime);
        }

        int dfs(int[] best, int laps, int changeTime) {
            if (laps == 0)
                return -changeTime;
            if (dp[laps] == Integer.MAX_VALUE) {
                for (int i = 1; i <= Math.min(laps, maxLaps); ++i)
                    dp[laps] = Math.min(dp[laps], best[i] + changeTime + dfs(best, laps - i, changeTime));
            }
            return dp[laps];
        }


}
