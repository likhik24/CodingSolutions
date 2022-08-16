package Stack;
import java.util.*;
/*
You are given a 0-indexed 2D integer array peaks where peaks[i] = [xi, yi] states that mountain i has a peak at coordinates (xi, yi). A mountain can be described as a right-angled isosceles triangle, with its base along the x-axis and a right angle at its peak. More formally, the gradients of ascending and descending the mountain are 1 and -1 respectively.

A mountain is considered visible if its peak does not lie within another mountain (including the border of other mountains).

Return the number of visible mountains.



Example 1:


Input: peaks = [[2,2],[6,3],[5,4]]
Output: 2
 */
public class FindingNumberOfVisibleMountains {

        public boolean within(int[] pa,int[] pb){
            // return True if `pb` is within `pa`

            return pb[0]+pb[1] <= pa[0]+pa[1] && pb[1]-pb[0] <= pa[1]-pa[0];
        }

        public int visibleMountains(int[][] peaks) {
            Stack<int[]> stack = new Stack<>();
            HashSet<Integer>set = new HashSet<>();
            Arrays.sort(peaks,  Comparator.comparingInt(a -> a[0]));
            for(int[] peak: peaks) {
                if(set.contains(peak[0]*(peaks.length)+peak[1]))
                {
                    if(!stack.isEmpty() && stack.peek()[0] == peak[0] && stack.peek()[1] == peak[1])
                        stack.pop();
                    continue;
                }

                while(!stack.isEmpty() && within(peak, stack.peek())) {
                    //overlapped i.e stack.pop() is in range of our current element, our current element is in higher range, so stack.remove(peek)
                    stack.pop();

                }

                if(stack.isEmpty() || !within(stack.peek(), peak)) //this is to check if our current element is already in range of our stack.peek(), i.e stack.peek() is in higher range
                    stack.add(peak);

                set.add(peak[0]*(peaks.length)+peak[1]);
            }
            return stack.size();
        }

}
