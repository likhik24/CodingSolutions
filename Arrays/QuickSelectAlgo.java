package Arrays;

import java.util.*;
import java.util.stream.*;

public class QuickSelectAlgo {
    //to sort the elements on left side of index to be less than index and then on right side of index to be greater than index
    public int[] quickselect(int[] arr, int k, int low, int high) {
        int pivotlocation = low; //this will have the location of pivot after sorting all elements less than  k to the pivotlocation
        while(pivotlocation != high-1) {
            for (int i = pivotlocation; i < high; i++) {
                if (arr[k] >= arr[i]) {
                    swap(arr, i, pivotlocation); //we will swap all values less than k to the leftside of pivot location once we have the pivotlocation we can swap it with k
                    pivotlocation++;
                }
            }
            if(pivotlocation == high)
                break;
            swap(arr, pivotlocation, k);
        }
        return arr;
    }

    public void swap(int[] arr ,int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        QuickSelectAlgo quick = new QuickSelectAlgo();
        int[] arr = {3,4,1,5,7,6,1,2};
        int k = new Random().nextInt(arr.length);
        //System.out.println(k);
        quick.quickselect(arr, 2,0,arr.length);
        IntStream.range(0,arr.length).forEach(i -> System.out.println(arr[i]));
    }
}
