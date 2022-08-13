package DynamicProgramming;

import java.util.*;
//We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
//
//        You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
//
//        If you choose a job that ends at time X you will be able to start another job that starts at time X.
//
//
//
//        Example 1:
//
//
//
//        Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
//        Output: 120
//        Explanation: The subset chosen is the first and fourth job.
//        Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
//        Example 2:
//
//
//
//        Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
//        Output: 150
//        Explanation: The subset chosen is the first, fourth and fifth job.
//        Profit obtained 150 = 20 + 70 + 60.
//        Example 3:
//
//
//
//        Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
//        Output: 6

public class MaximumProfitInJobScheduling {
     class JobSchedule {
            int startTime;
            int endTime;
            int profit;

            JobSchedule(int startTime, int endTime, int profit) {
                this.startTime = startTime;
                this.endTime = endTime;
                this.profit = profit;
            }

        }

        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            ArrayList<JobSchedule> scheduling = new ArrayList<>();
            for(int i=0;i<startTime.length;i++) {
                scheduling.add(new JobSchedule(startTime[i], endTime[i],profit[i]));
            }
            Collections.sort(scheduling, (a,b) -> Integer.compare(a.startTime, b.startTime));

            int i=0;
            for(JobSchedule schedule: scheduling) {
                startTime[i] = schedule.startTime;
                endTime[i] = schedule.endTime;
                profit[i] = schedule.profit;
                i++;
            }
            int[] profits = new int[startTime.length];
            Arrays.fill(profits, Integer.MIN_VALUE);
            return dp(startTime, endTime, profit, 0, 0, profits);

        }

        public int getNextJobIndex(int[] startTime, int endingTime, int index) {
            for(int i=index;i<startTime.length;i++) {
                if(startTime[i] >= endingTime)
                    return i;
            }
            return -1;
        }
        public int dp(int[] startTime, int[] endTime, int[] profit, int index, int currprofit, int[] profits) {
            if(index >= startTime.length)
                return 0;
            if(profits[index] != Integer.MIN_VALUE)
                return profits[index];
            int profited = dp(startTime, endTime, profit, index+1, currprofit, profits);
            int nextJobIndex = getNextJobIndex(startTime, endTime[index], index+1);
            if (nextJobIndex != -1)
                currprofit = Math.max(profit[index] + dp(startTime, endTime, profit, nextJobIndex, currprofit, profits), profited) ;
            else currprofit = Math.max(profit[index], profited);
            return profits[index] = currprofit;
        }

}
