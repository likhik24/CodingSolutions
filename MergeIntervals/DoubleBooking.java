package MergeIntervals;


//You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
//
//        A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
//
//        The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
//
//        Implement the MyCalendar class:
//
//        MyCalendar() Initializes the calendar object.
//        boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
//
//
//        Example 1:
//
//        Input
//        ["MyCalendar", "book", "book", "book"]
//        [[], [10, 20], [15, 25], [20, 30]]
//        Output
//        [null, true, false, true]

//Approach #2: Balanced Tree [Accepted]
//        Intuition
//
//        If we maintained our events in sorted order, we could check whether an event could be booked in O(\log N)O(logN) time (where NN is the number of events already booked) by binary searching for where the event should be placed. We would also have to insert the event in our sorted structure.
//
//        Algorithm
//
//        We need a data structure that keeps elements sorted and supports fast insertion. In Java, a TreeMap is the perfect candidate. In Python, we can build our own binary tree structure.
//
//        For Java, we will have a TreeMap where the keys are the start of each interval, and the values are the ends of those intervals. When inserting the interval [start, end), we check if there is a conflict on each side with neighboring intervals: we would like calendar.get(prev)) <= start <= end <= next for the booking to be valid (or for prev or next to be null respectively.)
//
//        For Python, we will create a binary tree. Each node represents some interval [self.start, self.end) while self.left, self.right represents nodes that are smaller or larger than the current node.

//Complexity Analysis
//
//        Time Complexity (Java): O(NlogN), where NN is the number of events booked. For each new event, we search that the event is legal in O(\log N)O(logN) time, then insert it in O(1)O(1) time.
//
//
//        Space Complexity: O(N), the size of the data structures used

import java.util.*;

public class DoubleBooking {
    class MyCalendar {
        TreeMap<Integer, Integer> calendar;

        MyCalendar() {
            calendar = new TreeMap();
        }

        public boolean book(int start, int end) {
            Integer prev = calendar.floorKey(start),
                    next = calendar.ceilingKey(start);
            if ((prev == null || calendar.get(prev) <= start) &&
                    (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }
}
