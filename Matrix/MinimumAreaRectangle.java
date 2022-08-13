package Matrix;

import java.util.*;

public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        //closest points with same x coordinate
        //closet points with y coordinate
        // 1,1,
        // 3,1
        //     3,3 1,3
        //we need to store y-coordinates or x-coordinates in a map grouped by x/y coordinates and see if we see this y coordinates for any other x as the rectangle length should be same for all y-edges
        Arrays.sort(points, (a,b) -> Integer.compare(a[0], b[0]));
        Map<Integer, List<Integer>> rows = new TreeMap<>();
        int ans = Integer.MAX_VALUE;

        for(int[] point : points) {
            int x = point[0];
            rows.computeIfAbsent(x, z->new ArrayList<>()).add(point[1]);
        }

        Map<Integer, Integer> lastX = new HashMap<>();
        for(int r: rows.keySet()) {
            List<Integer> row = rows.get(r);
            for(int i=0;i<row.size();i++) {
                for(int j=i+1;j<row.size();j++) {
                    int y1 = row.get(i);
                    int y2 = row.get(j);
                    int code = 40001*y1 + y2;
                    if(lastX.containsKey(code))
                        ans = Math.min(ans, (r - lastX.get(code))*(y2-y1));
                    lastX.put(code,r); // override existing x with new x which is much closer to the next row as rows in rows.keySet() will be in ascending order which will form minimum rectangle when we take closer points
                }
            }
        }
        return ans;


    }
}
