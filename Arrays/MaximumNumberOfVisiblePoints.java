package Arrays;
import java.util.*;

//You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.
//
//        Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
//
//
//        You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.
//
//        There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.
//
//        Return the maximum number of points you can see.
//
//
//
//        Example 1:
//
//
//        Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
//        Output: 3
//        Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
//        Example 2:
//
//        Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
//        Output: 4
//        Explanation: All points can be made visible in your field of view, including the one at your location.

public class MaximumNumberOfVisiblePoints {
        public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {

            int x1 = location.get(0);
            int y1 = location.get(1);
            int same = 0 ;
            double theta[];
            for(List<Integer> point: points) {
                int x2 = point.get(0);
                int y2 = point.get(1);
                if(x1 == x2 && y1 == y2) {
                    same++;
                    continue;
                }
            }
            theta = new double[points.size()-same];
            int ind = 0;
            for(List<Integer> point: points) {
                int x2 = point.get(0);
                int y2 = point.get(1);
                if(x2 != x1 || y2 != y1)
                    theta[ind++] =  Math.atan2( y2 - y1, x2 - x1 );

            }
            Arrays.sort(theta);
            double[] twice = new double[theta.length*2];
            for(int i=0;i<twice.length;i++)
                twice[i] = theta[i%theta.length];
            for(int i = theta.length; i < twice.length; i++){
                twice[i] += 2.0 * Math.PI;
            }
            int max = 0;
            int start = 0;
            int end = 0;
            double rad = Math.toRadians((double)angle);
            while(start<twice.length && end < twice.length) {
                if(start == end) {
                    end++;
                    max = Math.max(max,1);
                    continue;
                }
                else if(twice[end]-twice[start] <= rad) {
                    max = Math.max(max, end-start+1);
                    end++;

                }
                else { start++; }
            }
            return max+same;
        }
}
