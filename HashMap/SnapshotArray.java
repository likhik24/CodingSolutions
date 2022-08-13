package HashMap;

import java.util.*;


//    Implement a SnapshotArray that supports the following interface:
//
//    SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
//    void set(index, val) sets the element at the given index to be equal to val.
//    int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
//    int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
//
//
//    Example 1:
//
//    Input: ["SnapshotArray","set","snap","set","get"]
//            [[3],[0,5],[],[0,6],[0,0]]
//    Output: [null,null,0,null,5]
    public class SnapshotArray {
        List<TreeMap<Integer, Integer>> result;
        int snaps = 0;
        int length = 0;
        public SnapshotArray(int length) {
            result = new ArrayList<>();
            for(int i = 0; i < length; i++){
                result.add(new TreeMap<>());
                result.get(i).put(0,0);
            }
        }

        public void set(int index, int val) {
            result.get(index).put(snaps, val);
        }

        public int snap() {

            return snaps++;
        }

        public int get(int index, int snap_id) {
            return result.get(index).floorEntry(snap_id).getValue();

        }
    }

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

