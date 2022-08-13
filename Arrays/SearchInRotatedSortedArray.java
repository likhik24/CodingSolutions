package Arrays;
//10 11 4 5 6 7 8 9
//4 5 6 7 0 1 2
public class SearchInRotatedSortedArray {
    public int binarySearch(int[] arr, int target) {
        int low = 0;
        int high=arr.length-1;
        while(low <= high) {
            int mid = low+(high-low)/2;
            if(arr[mid] == target)
                return mid;
            if(arr[mid] >= arr[low]) {
                if(target>= arr[low] && target<arr[mid])
                    high=mid-1;
                else low=mid+1;
            }
            else {
                if(target <= arr[high] && target> arr[mid])
                    low=mid+1;
                else high=mid-1;
            }

        }
        return  -1;
    }
}
