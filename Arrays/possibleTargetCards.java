package Arrays;
import java.util.*;

public class possibleTargetCards {
    
    //use operators add, subtract, multiply , division to make target 24
    //every operator is b/w two operands
    //we can construct all possible permutations
    //apply + - *
    //apply - * /
    //lets do + , -, * 
    boolean isPossible = false;
    public  boolean canGetTarget(int[] nums, int target) {
        backtrack(nums, 0, 0, 0,target, new ArrayList<>() );
       return isPossible;
    }
    public ArrayList<Integer> backtrack(int[] nums, int index, int currsum, int lastVal, int target, ArrayList<Integer> result) {
        if(index>= nums.length) 
        { 
            if(target == currsum )
                isPossible = true;
            for(int curr:result)
            {
                if(curr == target) {
                    isPossible = true; 
                    break;
                }
            }
            return result;
        }
        ArrayList<Integer> currnextValues = new ArrayList<>();
        //3-5*8 currval+(lasteval*currop)-lastEval (-2-40) = -42
    int value = currsum;
        if(index != 0) {
             //+ op
             value = currsum+nums[index];
            ArrayList<Integer> nextValues = backtrack(nums, index+1, value, nums[index], target, new ArrayList<>());
            for(int next: nextValues) {
                if(next+value == target || next*value == target || value-next == target)
                {
                    isPossible = true;
                    return result;
                }
               result.add(next);
            }
           
            // - op
            value = currsum-nums[index];
            nextValues = backtrack(nums, index+1, value, -nums[index], target, new ArrayList<>());
           
             //* op
            value = currsum+(lastVal*nums[index])-lastVal; //6+(2*8)-2 = 22-2 = 20
            nextValues = backtrack(nums, index+1, value, (lastVal*nums[index])-lastVal, target, new ArrayList<>());
           
            
            //* op */
            value = currsum*nums[index]; 
            nextValues = backtrack(nums, index+1, value, (currsum*(nums[index]-1)),  target, new ArrayList<>());
            
            // division op


        }
        else {
          backtrack(nums, index+1, nums[index], nums[index], target, new ArrayList());
        }
        return result;
    }

    public static void main(String[] args) {
       possibleTargetCards pos = new  possibleTargetCards();
       int[] cards = new int[]{4,2,8,7};
       System.out.println(pos.canGetTarget(cards, 62));
    }
}
