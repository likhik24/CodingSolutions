package MergeIntervals;
import java.util.*;

public class EmployeeFreeTime {

//    We are given a list schedule of employees, which represents the working time for each employee.
//
//    Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
//
//    Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
//
//            (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
//            For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
//            Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
//    Example 1:
//
//    Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
//    Output: [[3,4]]
//    Explanation: There are a total of three employees, and all common
//    free time intervals would be [-inf, 1], [3, 4], [10, inf].
//    We discard any intervals that contain inf as they aren't finite.
//    Example 2:
//
//    Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
//    Output: [[5,6],[7,9]]
    class Interval {
        int start;
        int end;
        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervals = new LinkedList<>();

        LinkedList<Interval> check = new LinkedList<>();
        LinkedList<Interval> res = new LinkedList<>();
        for(List<Interval> interv: schedule) {
            intervals.addAll(interv);
        }

        Collections.sort(intervals, (interv1, interv2) -> Integer.compare(interv1.start, interv2.start));
        if(intervals.size() <= 1)
            return new ArrayList<Interval>();

        for(int i=0;i<intervals.size()-1;i++) {

            if(intervals.get(i+1).start < intervals.get(i).end) {
                intervals.get(i+1).start = intervals.get(i).start;
                intervals.get(i+1).end = Math.max(intervals.get(i).end, intervals.get(i+1).end);

            }
            else {
                check.add(intervals.get(i));
            }
        }
        check.add(intervals.get(intervals.size()-1));

        for(int k =0; k<check.size()-1;k++) {
            if(check.get(k).end < check.get(k+1).start)
                res.add(new Interval(check.get(k).end,check.get(k+1).start));
        }


        return res;
    }
}

