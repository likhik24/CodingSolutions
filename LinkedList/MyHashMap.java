package LinkedList;
import java.util.*;

class MyHashMap {
    class Pair<I extends Number, I1 extends Number> {
        int first;
        int second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    class Bucket {
        private List<Pair<Integer, Integer>> map = new LinkedList<Pair<Integer, Integer>>();
        public Integer get(Integer key) {
            for(Pair<Integer, Integer> pa: map) {
                if(pa.first == key)
                    return pa.second;
            }
            return -1;
        }

        public void update(Integer key, Integer value) {
            for(Pair<Integer, Integer> pa: map) {
                if(pa.first == key) {
                    return;
                }

            }
            map.add(new Pair(key, value));

        }
        public void remove(Integer key) {
            for(Pair<Integer, Integer> pair: map) {
                if(pair.first == key) {
                    map.remove(pair);
                    break;
                }
            }
        }
    }
    private int keySpace = 2069;
    private List<Bucket> hash_table;
    public MyHashMap() {
        this.hash_table = new ArrayList<Bucket>();
        for(int i=0; i < keySpace; i++)
            this.hash_table.add(new Bucket());
    }

    public void put(int key, int value) {
        int hashKey = key % this.keySpace;
        this.hash_table.get(hashKey).update(key, value);
    }

    public int get(int key) {
        int hashKey = key % this.keySpace;
        return this.hash_table.get(hashKey).get(key);
    }

    public void remove(int key) {
        int hashKey = key % this.keySpace;
        this.hash_table.get(hashKey).remove(key);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

