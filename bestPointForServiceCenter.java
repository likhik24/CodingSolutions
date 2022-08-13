import java.util.*;
//A delivery company wants to build a new service center in a new city. The company knows the positions of all the customers in this city on a 2D-Map and
// wants to build the new center in a position such that the sum of the euclidean distances to all customers is minimum.
//
//        Given an array positions where positions[i] = [xi, yi] is the position of the ith customer on the map, return the minimum sum of the euclidean distances to all customers.
//
//        In other words, you need to choose the position of the service center [xcentre, ycentre] such that the following formula is minimized:
//
//
//        Answers within 10-5 of the actual value will be accepted.
//
//
//
//        Example 1:
//
//
//        Input: positions = [[0,1],[1,0],[1,2],[2,1]]
//        Output: 4.00000
//        Explanation: As shown, you can see that choosing [xcentre, ycentre] = [1, 1] will make the distance to each customer = 1, the sum of all distances is 4 which is the minimum possible we can achieve


public class bestPointForServiceCenter {
        private double getDistance(int[][] positions, double x, double y) {
            double distance = 0.0;
            for (int i = 0; i < positions.length; ++i) {
                distance += Math.sqrt((x - positions[i][0]) * (x - positions[i][0]) + (y - positions[i][1]) * (y - positions[i][1]));
            }
            return distance;
        }
        //we keep incrementing x and y which is the service center point and compute deltaX and deltaY using learning rate h while deltaX & deltaY > MIN
        public double getMinDistSum(int[][] positions) {
            if (positions.length == 1) return 0;
            // I decide MIN value which passes TC. If you need a more accurate answer, you can reduce MIN further.
            final double MIN = 1e-9;

            // h is a learning rate, and (x, y) is an iterating point.
            double h = 10.0d, x = 0.0d, y = 0.0d, deltaX = 0.0d, deltaY = 0.0d;
            do {
                double newDeltaX = 0.0d, newDeltaY = 0.0d;
                for (int i = 0; i < positions.length; ++i) {
                    // Skip location which is exactly same with iterating point in order to avoid dividing with 0.
                    if (x == positions[i][0] && y == positions[i][1]) continue;

                    // Calculate deltaX & Y by multiplying a learning rate & gradient.
                    double inv = 1 / Math.sqrt((x - positions[i][0]) * (x - positions[i][0]) + (y - positions[i][1]) * (y - positions[i][1]));
                    newDeltaX += 2 * h * (x - positions[i][0]) * inv;
                    newDeltaY += 2 * h * (y - positions[i][1]) * inv;
                }
                // Oscillation case - Reduce a learning rate to an half.
                if (newDeltaX * deltaX < 0 && newDeltaY * deltaY < 0) {
                    h /= 2;
                }
                deltaX = newDeltaX;
                deltaY = newDeltaY;

                // Gradient descent.
                x = x - deltaX;
                y = y - deltaY;
            } while (Math.abs(deltaX) > MIN && Math.abs(deltaY) > MIN);
            return getDistance(positions, x, y);
        }

}
