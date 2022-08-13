package DynamicProgramming;
import java.util.*;
//There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
//
//        In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
//
//        Your score is the sum of the points of the cards you have taken.
//
//        Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
//
//
//
//        Example 1:
//
//        Input: cardPoints = [1,2,3,4,5,6,1], k = 3
//        Output: 12
//        Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
//        Example 2:
//
//        Input: cardPoints = [2,2,2], k = 2
//        Output: 4
//        Explanation: Regardless of which two cards you take, your score will always be 4.
//        Example 3:
//
//        Input: cardPoints = [9,7,7,9,7,7,9], k = 7
//        Output: 55
//        Explanation: You have to take all the cards. Your score is the sum of points of all cards.

public class MaximumScoreCardsWeCanPick {
    public int maxScore(int[] cardPoints, int k) {
        int[] leftMax  = new int[k+1];
        if(k >= cardPoints.length) {
            Integer a[] = new Integer[cardPoints.length];
            int index=0;
            for(int i: cardPoints) {
                a[index++] = i;
            }
            List<Integer> arr = Arrays.asList(a);

            return arr.stream().mapToInt(Integer::intValue).sum();
        }
        leftMax[0] = 0;
        int[] rightMax = new int[k+1];
        for(int i=0;i<k;i++) {
            leftMax[i+1] = cardPoints[i] + leftMax[i];
            rightMax[i+1] = rightMax[i] + cardPoints[cardPoints.length-i-1];
        }
//        0 1 2 ..k  leftMax - taking 0 from left till taking k from left ( so leftMax[k] = taking all k elements from right)
//        0 1 2 .. k rightMax - taking 0 from right till taking k from right ( so rightMax[k] = taking all k elements from right)
//        we can get sum of i + j from leftMax, rightmax where i=0 from leftMax;j=k from rightMax to get maxscore sum of picking k elements ( some from left, some from right);


        int i=0;
        int j=k;
        int sum = 0;

        while(i<=k && j>=0) {
            sum = Math.max(leftMax[i++] + rightMax[j--] ,sum);
        }
        return sum;
    }
}
