package GreedyAlgorithm;
import java.util.*;

public class MaximumSplitOfEvenIntegers {
    public List<Long> maximumEvenSplit(long finalSum) {
        if(finalSum%2 != 0)
            return new ArrayList<>();

        long nextNum = 2;
        long sum = 0;
        List<Long> result = new ArrayList<>();
        while(true) {
            if(sum + nextNum <= finalSum)
            {
                result.add(nextNum);
                sum += nextNum;
                nextNum +=2 ;
            }
            else break;
        }
        if(sum != finalSum) {

            sum -= result.get(result.size()-1);
            result.remove(result.size()-1);

            result.add(finalSum-sum);

        }

        return result;
    }
}
