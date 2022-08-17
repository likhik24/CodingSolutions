package Arrays;
import java.util.*;
public class FindMinElementInRotatedSortedArray
{
//first find the pivot element which is elem whcih has greater value than i+1 or less than value than i-1, then this element is th epivot
// //we do binary search on the array to find pivot elem

    public int binarySearchtoFindPivot(int[] nums) {
        int low=0;
        int high = nums.length-1;
        while(low <= high) {
            int middle = low +(high-low)/2;
            if(nums[middle] > nums[middle+1])
                return middle;
            else if(middle >= 1 && nums[middle] < nums[middle-1])
                return middle-1;
            else if(nums[middle] < nums[low])
                high=middle;
            else low=middle+1;
        }
        return low;
    }

     int elemSearch(int arr[], int n,
                                   int key)
    {
        int pivot = binarySearchtoFindPivot(arr);

        // If we didn't find a pivot, then
        // array is not rotated at all
        if (pivot == -1)
            return Arrays.binarySearch(arr, 0, n - 1, key);

        // If we found a pivot, then first
        // compare with pivot and then
        // search in two subarrays around pivot
        if (arr[pivot] == key)
            return pivot;
        if (arr[0] <= key)
            return Arrays.binarySearch(arr, 0, pivot - 1, key);
        return Arrays.binarySearch(arr, pivot + 1, n - 1, key);
    }
}
