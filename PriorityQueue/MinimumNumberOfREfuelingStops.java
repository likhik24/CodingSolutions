package PriorityQueue;

import java.util.*;
  // Minimum Number of Refueling Stops

//        A car travels from a starting position to a destination which is target miles east of the starting position.
//
//        There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has fueli liters of gas.
//
//        The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
//
//        Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.
//
//        Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
//
//        Example 1:
//
//        Input: target = 1, startFuel = 1, stations = []
//        Output: 0
//        Explanation: We can reach the target without refueling.
//        Example 2:
//
//        Input: target = 100, startFuel = 1, stations = [[10,100]]
//        Output: -1
//        Explanation: We can not reach the target (or even the first gas station).
//        Example 3:
//
//        Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
//        Output: 2

public class MinimumNumberOfREfuelingStops {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if(startFuel >= target)
                return 0;
            if(stations.length == 0)
                return startFuel >= target ? 0 : -1;

            PriorityQueue<Integer> fuelsHeap = new PriorityQueue<>(Collections.reverseOrder());
            //start dest
            //two decisons take stop , not take stop
            //if startfuel > stop

            int count = 0;
            int prevLocation = 0;
            for(int[] station: stations) {
                int location = station[0];
                int fuel = station[1];
                startFuel -= location-prevLocation;
                if(startFuel < 0 && !fuelsHeap.isEmpty()) {
                    startFuel += fuelsHeap.poll();
                    count++;
                }
                fuelsHeap.offer(fuel);
                if(startFuel < 0)
                    return -1;
                prevLocation = location;
            }
            startFuel -= target-prevLocation;
            if(startFuel < 0 && !fuelsHeap.isEmpty()) {
                startFuel += fuelsHeap.poll();
                count++;
            }
            if(startFuel < 0)
                return -1;

            return count;
        }

}
