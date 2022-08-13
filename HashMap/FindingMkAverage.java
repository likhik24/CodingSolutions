package HashMap;

//        You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.
//
//        The MKAverage can be calculated using these steps:
//
//        If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
//        Remove the smallest k elements and the largest k elements from the container.
//        Calculate the average value for the rest of the elements rounded down to the nearest integer.
//        Implement the MKAverage class:
//
//        MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
//        void addElement(int num) Inserts a new element num into the stream.
//        int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.
//
//
//        Example 1:
//
//        Input
//        ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
//        [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
//        Output
//        [null, null, null, -1, null, 3, null, null, null, 5]
//
//        Explanation
//        MKAverage obj = new MKAverage(3, 1);
//        obj.addElement(3);        // current elements are [3]
//        obj.addElement(1);        // current elements are [3,1]
//        obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
//        obj.addElement(10);       // current elements are [3,1,10]
//        obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
//        // After removing smallest and largest 1 element the container will be [3].
//        // The average of [3] equals 3/1 = 3, return 3
//        obj.addElement(5);        // current elements are [3,1,10,5]
//        obj.addElement(5);        // current elements are [3,1,10,5,5]
//        obj.addElement(5);        // current elements are [3,1,10,5,5,5]

import java.util.*;

public class FindingMkAverage {
    //we need to keep storing stream of numbers and only last m numbers and remove first and last k in them and return average of other numbers
    //we can use treemap for having sorted order of items by their index t to remove elements once map exceeds m elements as we only need  last m elements at any point
//    Q treeset helps us track the sum of all elements other than top k and smaller k,
//    we keep adding elems to smaller k and top k by getting smallest and top element in Q and polling them

    class Item implements Comparable<Item> {
        int key;
        int index;
        Item(int key, int index) {
            this.key = key;
            this.index = index;
        }

        public int getKey() {
            return this.key;
        }
        public int getValue() {
            return this.index;
        }
        @Override
        public int compareTo(Item item) {
            if(this.key == item.key) return (this.index-item.index); else return (this.key-item.key);
        }

        @Override
        public int hashCode() {
            return this.key*Integer.MAX_VALUE + this.index;
        }
    }

    int m;
    int k;
    TreeSet<Item> kLargerElems = new TreeSet<>();
    TreeSet<Item> kSmallerElems = new TreeSet<>();
    int currindex=0;
    int sum;
    TreeSet<Item> q = new TreeSet<>();
    TreeMap<Integer, Item> indexItemMap = new TreeMap<>();
    public FindingMkAverage(int m, int k) {
        this.m = m;
        this.k = k;

    }

    public void addElement(int num) {
        currindex++;
        Item item = new Item(num, currindex);
        indexItemMap.put(currindex, item);
        if(indexItemMap.size() > m) {
            Item oldestItem = indexItemMap.remove(indexItemMap.firstKey());
            if(kLargerElems.contains(oldestItem)) kLargerElems.remove(oldestItem);
            if(kSmallerElems.contains(oldestItem)) kSmallerElems.remove(oldestItem);
            if(q.contains(oldestItem)) {
                q.remove(oldestItem);
                sum -= oldestItem.key;
            }
        }
        addElement(item);
    }

    private void addElement(Item item){
        q.add(item);
        sum += item.getKey();
        Item largerElems = null, smallerElems = null, p1 = null, p2 = null;

        largerElems = q.pollLast();
        sum -= largerElems.getKey();

        if(q.size() == 0) smallerElems = largerElems;
        else {
            smallerElems = q.pollFirst();
            sum -= smallerElems.getKey();
        }
        kLargerElems.add(largerElems);
        kSmallerElems.add(smallerElems);

        if (kLargerElems.size() > k) p1 = kLargerElems.pollFirst();
        if (kSmallerElems.size() > k) p2 = kSmallerElems.pollLast();

        if(p1 != null && !kSmallerElems.contains(p1)) {
            q.add(p1);
            sum += p1.getKey();
        }

        if(p2 != null && !kLargerElems.contains(p2) && p1 != p2) {
            q.add(p2);
            sum += p2.getKey();
        }
    }



    public int calculateMKAverage() {
        if(currindex < m) return -1;
        else if(q.size() == 0) return 0;
        else return sum/q.size();
    }
}
