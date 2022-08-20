package Arrays;

public class ReversePairs {
    // 1 3 2 3 1
    public int reversePairs(int[] nums) {
        //reverse pairs are nums which are in decreasing order and nums[i] > 2*nums[j]
        //think about an algorithm that divides the array so we can compare side by side elements are sorted or forming reverse pair
        // we can use mergesort
        //when arr[i] > 2*Arr[j] where i. j are part of two half which are sorted that means (all pairs from i to middle of array will be greater than arr[j] where m is mid between i&j ) so we increment count by m-i
        return mergeSort(nums, 0, nums.length-1);
        
    }
    
    public int mergeSort(int arr[],int l,int r)
    {
    if(l==r)
    {
        return 0;
    }

    int mid=(r-l)/2+l;
    int x=mergeSort(arr,l,mid);
    int y=mergeSort(arr,mid+1,r);
    int z=merge(arr,l,mid,r);
    return x+y+z;
}

public int merge(int[] arr,int l,int mid,int r)
{
    int i=l;
    int j=mid;
    int count=0;
    int aux[]=new int[r-l+1];
    int k=0;
    while(i<=mid&&j<=r)
    {
        
        if((long)arr[i]>(long)(2*(long)arr[j]))
        {
            count+=mid-i;
            j++;
        }
        else{
            i++;
        }
    }


    i=l;
    j=mid;

    while(i<=mid&&j<=r)
    {
        if(arr[i]<arr[j])
        {
            aux[k]=arr[i];
            i++;
            k++;
        }
        else {
            aux[k]=arr[j];
            k++;
            j++;
        }
    }
    
    while(i<=mid-1){
         aux[k]=arr[i];
            i++;
            k++;
    }
    while(j<=r)
    {
       aux[k]=arr[j];

            k++;
            j++;  
    }


    for(int x=0;x<r-l+1;x++)
    {
        arr[x+l]=aux[x];
    }

    return count;
}

}
