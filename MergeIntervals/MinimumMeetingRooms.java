package MergeIntervals;

import java.util.*;
//Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

//
//        Example 1:
//
//        Input: intervals = [[0,30],[5,10],[15,20]]
//        Output: 2
//        Example 2:
//
//        Input: intervals = [[7,10],[2,4]]
//        Output: 1
//        Approach 1: Priority Queues
//        We can't really process the given meetings in any random order. The most basic way of processing the meetings is in increasing order of their start times and this is the order we will follow. After all, it makes sense to allocate a room to the meeting that is scheduled for 9 a.m. in the morning before you worry about the 5 p.m. meeting, right?
//
//        Let's do a dry run of an example problem with sample meeting times and see what our algorithm should be able to do efficiently.
//
//        We will consider the following meeting times for our example (1, 10), (2, 7), (3, 19), (8, 12), (10, 20), (11, 30). The first part of the tuple is the start time for the meeting and the second value represents the ending time. We are considering the meetings in a sorted order of their start times. The first diagram depicts the first three meetings where each of them requires a new room because of collisions.
//
//
//        The next 3 meetings start to occupy some of the existing rooms. However, the last one requires a new room altogether and overall we have to use 4 different rooms to accommodate all the meetings.
//
//
//        Sorting part is easy, but for every meeting how do we find out efficiently if a room is available or not? At any point in time we have multiple rooms that can be occupied and we don't really care which room is free as long as we find one when required for a new meeting.
//
//        A naive way to check if a room is available or not is to iterate on all the rooms and see if one is available when we have a new meeting at hand.
//
//        However, we can do better than this by making use of Priority Queues or the Min-Heap data structure.
//
//        Instead of manually iterating on every room that's been allocated and checking if the room is available or not, we can keep all the rooms in a min heap where the key for the min heap would be the ending time of meeting.
//
//        So, every time we want to check if any room is free or not, simply check the topmost element of the min heap as that would be the room that would get free the earliest out of all the other rooms currently occupied.
//
//        If the room we extracted from the top of the min heap isn't free, then no other room is. So, we can save time here and simply allocate a new room.
//
//        Let us look at the algorithm before moving onto the implementation.
//
//        Algorithm
//
//        Sort the given meetings by their start time.
//        Initialize a new min-heap and add the first meeting's ending time to the heap. We simply need to keep track of the ending times as that tells us when a meeting room will get free.
//        For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
//        If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
//        If not, then we allocate a new room and add it to the heap.
//        After processing all the meetings, the size of the heap will tell us the number of rooms allocated. This will be the minimum number of rooms needed to accommodate all the meetings.

// Time Complexity : O(Nlogn)


//another approach two pointer to basically sort start & endtimes seperately and keep incrementing pointers and calculate usedrooms
// when start[startptr]> end[endptr] that means after a meeting current one ends this new start is starting so, we decrement usedrooms and increment endpointer

//class Solution {
//    public int minMeetingRooms(int[][] intervals) {
//
//        // Check for the base case. If there are no intervals, return 0
//        if (intervals.length == 0) {
//            return 0;
//        }
//
//        Integer[] start = new Integer[intervals.length];
//        Integer[] end = new Integer[intervals.length];
//
//        for (int i = 0; i < intervals.length; i++) {
//            start[i] = intervals[i][0];
//            end[i] = intervals[i][1];
//        }
//
//        // Sort the intervals by end time
//        Arrays.sort(
//                end,
//                new Comparator<Integer>() {
//                    public int compare(Integer a, Integer b) {
//                        return a - b;
//                    }
//                });
//
//        // Sort the intervals by start time
//        Arrays.sort(
//                start,
//                new Comparator<Integer>() {
//                    public int compare(Integer a, Integer b) {
//                        return a - b;
//                    }
//                });
//
//        // The two pointers in the algorithm: e_ptr and s_ptr.
//        int startPointer = 0, endPointer = 0;
//
//        // Variables to keep track of maximum number of rooms used.
//        int usedRooms = 0;
//
//        // Iterate over intervals.
//        while (startPointer < intervals.length) {
//
//            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
//            if (start[startPointer] >= end[endPointer]) {
//                usedRooms -= 1;
//                endPointer += 1;
//            }
//
//            // We do this irrespective of whether a room frees up or not.
//            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
//            // remain the same in that case. If no room was free, then this would increase used_rooms
//            usedRooms += 1;
//            startPointer += 1;
//
//        }
//
//        return usedRooms;
//    }
//}
public class MinimumMeetingRooms {

    class Solution {
        public int minMeetingRooms(int[][] intervals) {

            // Check for the base case. If there are no intervals, return 0
            if (intervals.length == 0) {
                return 0;
            }

            // Min heap
            PriorityQueue<Integer> allocator =
                    new PriorityQueue<Integer>(
                            intervals.length,
                            (a, b) -> a - b);

            // Sort the intervals by start time
            Arrays.sort(
                    intervals,
                    (a, b) -> a[0] - b[0]);

            // Add the first meeting
            allocator.add(intervals[0][1]);

            // Iterate over remaining intervals
            for (int i = 1; i < intervals.length; i++) {

                // If the room due to free up the earliest is free, assign that room to this meeting.
                if (intervals[i][0] >= allocator.peek()) {
                    allocator.poll();
                }

                // If a new room is to be assigned, then also we add to the heap,
                // If an old room is allocated, then also we have to add to the heap with updated end time.
                allocator.add(intervals[i][1]);
            }

            // The size of the heap tells us the minimum rooms required for all the meetings.
            return allocator.size();
        }
    }
}
