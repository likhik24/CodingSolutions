package PriorityQueue;

import java.util.*;

//Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
//
//        Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//        You must find a solution with a memory complexity better than O(n2).
//
//
//
//        Example 1:
//
//        Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//        Output: 13
//        Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
//        Example 2:
//
//        Input: matrix = [[-5]], k = 1
//        Output: -5

public class KthSmallestElementInSortedMatrix {
    class Point {
        int index;
        int value;
        int valueIndex;
        Point(int index, int value, int valueIndex) {
            this.index = index;
            this.value = value;
            this.valueIndex = valueIndex;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Point> pQueue = new PriorityQueue<>(new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return a.value - b.value;
            }
        });
        for(int i=0;i<matrix.length;i++)
            pQueue.add(new Point(i, matrix[i][0],0));

        while(!pQueue.isEmpty() && k>1) {
            Point curr = pQueue.poll();
            --k;
            if(curr.valueIndex < matrix.length-1)
                pQueue.add(new Point(curr.index, matrix[curr.index][curr.valueIndex+1], curr.valueIndex+1));

        }
        return pQueue.peek().value;


    }

}
