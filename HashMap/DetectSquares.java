package HashMap;
import java.util.*;

//You are given a stream of points on the X-Y plane. Design an algorithm that:
//
//        Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
//        Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
//        An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
//
//        Implement the DetectSquares class:
//
//        DetectSquares() Initializes the object with an empty data structure.
//        void add(int[] point) Adds a new point point = [x, y] to the data structure.
//        int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
//
//
//        Example 1:
//
//
//        Input
//        ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
//        [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
//        Output
//        [null, null, null, null, 1, 0, null, 2]

public class DetectSquares {
        HashMap<Integer, Set<Integer>> hmap = new HashMap<>();
        HashMap<String, Integer> cntMap = new HashMap<>();


        public DetectSquares() {

        }

        public void add(int[] point) {
            hmap.compute(point[1], (key, value) -> {
                if(value == null) {
                    HashSet<Integer> s = new HashSet<>();
                    s.add(point[0]);
                    return s;
                }
                else {
                    value.add(point[0]);
                    return value;
                }
            });
            cntMap.put(point[0] + "-" + point[1], cntMap.getOrDefault(point[0] + "-" + point[1], 0)+1);

        }

        public int count(int[] point) {
            int count = 0;

            Set<Integer> rows = hmap.getOrDefault(point[1], new HashSet<>());
            System.out.println(rows.size());
            if(rows.size() == 0)
                return 0;
            Iterator<Integer> r = rows.iterator();
            while(r.hasNext()) {
                int row = r.next();
                System.out.println(row);
                //right side points
                int size = point[0]-row;
                String first = row +"-" + point[1]; //3 10
                String second = row + "-" + (point[1] + size); // 3 18
                String third = point[0] + "-" +  (point[1] + size); //11 18
                if(cntMap.containsKey(second) && cntMap.containsKey(third)) {
                    count += cntMap.get(first)*cntMap.get(second)*cntMap.get(third);
                }

                //left side points
                second = row + "-" + (point[1] - size); // 3 2
                System.out.println(second);
                third = point[0] + "-" +  (point[1] - size); //11 2
                System.out.println(third);
                if(cntMap.containsKey(second) && cntMap.containsKey(third)) {
                    count += cntMap.get(first)*cntMap.get(second)*cntMap.get(third);
                }

            }
            return count;
        }


/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
}
