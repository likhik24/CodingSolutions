package DynamicProgramming;
import java.util.*;
//we need to pick min coins to make the change amount form coisn array to pick 0 amount we need 0 coins, we can calculate how many coins we need for every amount whenever we pick a coin
// we have two choices, we can pick a coin amount and pick any other coin or this coin to get amount of (amount-coin)

public class CoinChange {

        public int coinChange(int[] coins, int amount) {
            int[]dp = new int[amount+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

             coinChange(coins, amount, dp);
            return dp[amount] == Integer.MAX_VALUE ? -1: dp[amount];

        }

        //11 10 8 6 4 3 2 1

        public int coinChange(int[] coins, int amount, int[] dp) {
            int min = Integer.MAX_VALUE;

            if( dp[amount] != Integer.MAX_VALUE)
                return dp[amount];
            for(int coin: coins) {
                //we try to pick this coin if coin < amount we need
                if(amount-coin >= 0) {

                    int subres = coinChange(coins, amount-coin, dp);
                    if(subres != Integer.MAX_VALUE)
                        min = Math.min(min, subres+1);
                }

            }
            dp[amount] = min;
            return dp[amount];
        }

}
