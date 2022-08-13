package Arrays;

public class MergeSort {

    // Divide & congquer
    // we divide the array into two halfes until each half has only one element or empty and then merge the two halfs by creating two arrays to store elements from low to mid, mid+1 to right
    // and update elements in array from low index to high index by picking the least element in two arrays at every step, we are doing sorting on every half which makes the total array sorted
    // O(n log(n)),  Sorting arrays on different machines. Merge Sort is a recursive algorithm and time complexity can be expressed as following recurrence relation.

    // T(n) = 2T(n/2) + θ(n)
        //this is stable sorting as when sorting we wont change the relative order of the elements in array (i.e when index i has element 1, index j has element 1 & i< j )
  public int[] mergeSort(int[] arr, int low, int high) {

      if(low < high) {
          int mid = low + ((high - low)/2); // 2;// 3
          mergeSort(arr, low, mid); //0 1 2 3
          mergeSort(arr, mid + 1, high);
          int[] r = merge(arr, low, mid, high);
          return r;
      }
      return arr;
  }
  public int[] merge (int[] arr, int l, int m, int r) {
      // Find sizes of two subarrays to be merged
      int n1 = m - l + 1;
      int n2 = r - m;

      /* Create temp arrays */
      int arr1[] = new int[n1];
      int arr2[] = new int[n2];

      /*Copy data to temp arrays*/
      for (int i = 0; i < n1; ++i)
          arr1[i] = arr[l + i];
      for (int j = 0; j < n2; ++j)
          arr2[j] = arr[m + 1 + j];
      int i=0;
      int j=0;
      int k=l;
      int[] res = new int[arr1.length+arr2.length];
      while(i<arr1.length && j < arr2.length) {
          if(arr1[i] >arr2[j])
              arr[k++] = arr2[j++];
          else arr[k++] = arr1[i++];
      }
      while(i< arr1.length)
          arr[k++] = arr1[i++];
      while(j< arr2.length)
          arr[k++] = arr2[j++];
      return arr;
  }
}
