package HashMap;

import java.util.*;
//Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.
//
//        Implement the ProductOfNumbers class:
//
//        ProductOfNumbers() Initializes the object with an empty stream.
//        void add(int num) Appends the integer num to the stream.
//        int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always the current list has at least k numbers.
//        The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
//
//
//
//        Example:
//
//        Input
//        ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
//        [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
//
//        Output
//        [null,null,null,null,null,null,20,40,0,null,32]
//
//        Explanation
//        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
//        productOfNumbers.add(3);        // [3]
//        productOfNumbers.add(0);        // [3,0]
//        productOfNumbers.add(2);        // [3,0,2]
//        productOfNumbers.add(5);        // [3,0,2,5]
//        productOfNumbers.add(4);        // [3,0,2,5,4]
//        productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20

//we can use prefix product to save running product for all indexes and when a num is 0 clear all elements in map as running product of last k elements when k>0 will be 0 add the default value 0,1 to the map to multiply by 1 for any new elements added
public class ProductOfLastKNumbers {
        TreeMap<Integer, Integer> indexProductMap = new TreeMap<>();
        int currindex=0;
        public ProductOfLastKNumbers() {
            indexProductMap.put(0,1);
        }

        public void add(int num) {
            int currProduct = indexProductMap.getOrDefault(currindex, 1);
            if(num != 0)
                indexProductMap.put(++currindex, num*(currProduct));
            else
            {
                indexProductMap.clear();
                indexProductMap.put(0,1);
                currindex=0;
            }
        }

        public int getProduct(int k) {
            int lastIndex = indexProductMap.lastKey();
            int firstIndex = lastIndex-k;
            int product = 1;
            if(lastIndex < k)
                return 0;
            return indexProductMap.get(lastIndex)/indexProductMap.get(firstIndex);
        }

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
}
