package SegmentedTree;
import java.util.*;

public class SegmentedTree {
//    Representation of Segment trees
//
//    Leaf Nodes are the elements of the input array.
//    Each internal node represents some merging of the leaf nodes. The merging may be different for different problems. For this problem, merging is sum of leaves under a node.
//    An array representation of tree is used to represent Segment Trees. For each node at index i, the left child is at index 2*i+1, right child at 2*i+2 and the parent is at  ⌊(i – 1) / 2⌋.
//    Like Heap, the segment tree is also represented as an array. The difference here is, it is not a complete binary tree. It is rather a full binary tree (every node has 0 or 2 children) and all levels are filled except possibly the last level. Unlike Heap, the last level may have gaps between nodes. Below are the values in the segment tree array for the above diagram.
//
//    Below is memory representation of segment tree for input array {1, 3, 5, 7, 9, 11}
//    st[] = {36, 9, 27, 4, 5, 16, 11, 1, 3, DUMMY, DUMMY, 7, 9, DUMMY, DUMMY}
    //segmented tree arr[0..n-1]
    //Height of the segment tree will be ⌈log₂n⌉. Since the tree is represented using array and relation between parent and child indexes must be maintained, size of memory allocated for segment tree will be 2 * 2⌈log2n⌉  – 1.

//    Time Complexity:
//
//    Time Complexity for tree construction is O(n). There are total 2n-1 nodes, and value of every node is calculated only once in tree construction.
//    Time complexity to query is O(Logn). To query a sum, we process at most four nodes at every level and number of levels is O(Logn).
//    The time complexity of update is also O(Logn). To update a leaf value, we process one node at every level and number of levels is O(Logn)
int[] segmentTreeArray;
    int n;
    int[] arr;
    public SegmentedTree(int[] nums) {
        // Allocate memory for segment tree
        //Height of segment tree
        int length = nums.length;
        int height = (int)Math.ceil(Math.log(length)/Math.log(2));
        int resultLength = (2*(int)Math.pow(2,height))-1;
        segmentTreeArray = new int[resultLength];
        this.n = nums.length;
        arr = nums;
        constructTree(0, length-1, nums, 0, 0);//0 5 0
    }


    public int constructTree(int low, int high,int[] arr, int sindex, int index) {
        if (low == high) {
            segmentTreeArray[sindex] = arr[low];
            return segmentTreeArray[sindex];
        }
        int middle = low + (high-low)/2;
        return segmentTreeArray[sindex] = constructTree(low, middle, arr, 2*sindex+1, index) + constructTree(middle+1, high, arr, 2*sindex+2, index);
    }

    public void updateQuery(int index, int val) {
       update(0, n-1, 0, index, val);
    }

    public int getSumQuery(int left, int right) {
        return query(0, n-1, 0, left, right);
    }

    int query(int startOfRange, int endOfRange, int indexOfSegmentTree, int l, int r) {
        if(endOfRange < l || startOfRange > r) {
            return 0;
        }

        if(startOfRange >= l && endOfRange <= r) {
            return segmentTreeArray[indexOfSegmentTree];
        }

        int mid = (startOfRange + endOfRange)/2;
        int leftSide = query(startOfRange, mid, indexOfSegmentTree*2 + 1, l, r);
        int rightSide = query(mid+1, endOfRange, indexOfSegmentTree*2 + 2, l, r);
        return leftSide + rightSide;

    }

    void update(int startOfRange, int endOfRange, int indexOfSegmentTree, int index, int val) {
        if(startOfRange == endOfRange) {
            segmentTreeArray[indexOfSegmentTree] = val;
            return;
        }
        int mid = (startOfRange + endOfRange)/2;
        if(mid >= index) {
            update(startOfRange, mid, indexOfSegmentTree*2 + 1, index, val);
        } else {
            update(mid + 1, endOfRange, indexOfSegmentTree*2 + 2, index, val);
        }
        segmentTreeArray[indexOfSegmentTree] = segmentTreeArray[indexOfSegmentTree*2 +1] + segmentTreeArray[indexOfSegmentTree*2 +2];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,11,3,4,12,2,5};
        SegmentedTree tree =new SegmentedTree(arr);
        System.out.println(tree.getSumQuery(1, 3));
        System.out.println("Sum after updated query ");
        tree.updateQuery( 2, 7);
        System.out.println(tree.getSumQuery(1, 3));
    }
}
