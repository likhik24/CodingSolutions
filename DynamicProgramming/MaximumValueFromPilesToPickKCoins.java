package DynamicProgramming;
import java.util.*;

public class MaximumValueFromPilesToPickKCoins {
    // we have x piles and we need to pikc k coins from x piles to get maxvalue where value is the coin denomination
    //we always need to pick from top of pile and remove it and add to our wallet, so we can only pick from top of the pile
            //choices we can make are for each pile we can pick a coin from top of pile or we dont pick any coin and we move to next pile
    //in current pile if we are picking coins we can pick from 1 to j coins where j<=k and pick k-j-1 coisn from next pile
   // we can use dp to handle this overlapping subproblems
        public int maximumValueCoins(List<List<Integer>> piles, int k) {
            int[][] dp = new int[piles.size()+1][k+1]; //this is to save the maxvalue of x coins we can pick from a pilenumber p
            for(int[] arr:dp)
                Arrays.fill(arr, -1);
            maximumCoins(piles, k,dp, 0);
            return dp[0][k];
        }
        public int maximumCoins(List<List<Integer>> piles, int k, int[][] dp, int pileNumber) {
            if(k == 0|| pileNumber >= piles.size())
                return 0;
            if(dp[pileNumber][k] != -1)
                return dp[pileNumber][k];
            int res = maximumCoins(piles, k, dp, pileNumber+1);//we dont pick any coins from current pile
            int curr = 0;
            for(int j=0;j<Math.min(piles.get(pileNumber).size(), k); j++) {
                curr += piles.get(pileNumber).get(j); //if we pick 0 to j coins from current pile , then we get maxvalue of k-j-1 coins we can pick from next pile
                res = Math.max(res, curr+maximumCoins(piles, k-j-1, dp, pileNumber+1));
            }
            dp[pileNumber][k] = res;
            return dp[pileNumber][k];
        }

        public static void main(String[] args) {
            List<List<Integer>> piles = new ArrayList<>();
            piles.add(new ArrayList<>(List.of(1,100,3)));
            piles.add(new ArrayList<>(List.of(7,8,9)));
            MaximumValueFromPilesToPickKCoins pilesToPickKCoins = new MaximumValueFromPilesToPickKCoins();
            System.out.println(pilesToPickKCoins.maximumValueCoins(piles, 2));

        }
}
