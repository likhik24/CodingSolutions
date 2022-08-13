package Arrays;

import java.util.*;
public class QuickSort {
    int[] arr;
    public QuickSort(int[] arr) {
        this.arr = arr;
    }
    public int[] partition(int[] arr, int low, int high) {
        if(low >= high)
            return arr;
     int pi = merge(arr, low, high);
     partition(arr, low, pi-1);
     partition(arr, pi+1, high);
     return arr;
    }

    public void swap(int[] arr, int i, int j) {
        int temp=arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public int merge(int[] arr,int low, int high) {
        // 3 5 7 1 2 6
        if(low >= high)
            return low;
        int pivot = high;
        int i=low; //0 1 2 3
        int j=low-1; //-1 0 1
        // 3 7 5 1 2
        while(i < high) {
            if (arr[i] < arr[pivot]) {
                j++; //1
            swap(arr, i, j);
        }
           i++;

        }
        swap(arr, j+1, pivot);
        for(int k: arr)
            System.out.println(k);
        return j+1;
    }


}
