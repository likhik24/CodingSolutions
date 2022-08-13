package DynamicProgramming;

import BitManipulation.NumberOfWaysOfBuildingWalls;

import java.util.*;

/*Complexity Analysis

        Time complexity: O(N×amount), where N is a length of coins array.

        Space complexity: O(amount) to keep dp array.

 */
public class NumberOfWaysToMakeAmount {
    public int coinsWays(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,0);
        //initally we have 0 combinations for all amount and to make 0 amount we need 0 coins which is 1 combination
        //now for each coin we take, we  compute combinations for all  amount s greater than or equal to current coin denimination we need to add currcombinations[amount] + combinations[amount-coin];
        //if we take coin with denomination 5, it doensot effect combinations of coins with denomination less than 5
        dp[0]= 1;

        return ways(coins,amount, dp);

    }

    public int ways(int[] coins, int amount, int[] dp) { //10 9 8 7 6 5 4 3 2 1
       // 2 5 10
        for(int coin: coins) {
            //2 cents  //5 //10 cents
            for(int i=coin;i<=amount;i++) {
                dp[i] += dp[i-coin]; //1 0 1 1 1 1 1 1 3 1
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        NumberOfWaysToMakeAmount ways = new NumberOfWaysToMakeAmount();
        System.out.println(ways.coinsWays(new int[]{1,2,5}, 11));
    }
}
