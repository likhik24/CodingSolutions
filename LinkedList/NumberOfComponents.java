package LinkedList;
import java.util.*;

public class NumberOfComponents {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public class ListNode {
         int val;
          ListNode next;
         ListNode() {}
        ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
        public int numComponents(ListNode head, int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for(int n: nums)
                set.add(n);
            ListNode node = head;
            int numberOfComponents = 0;
            while(node != null) {
                if(set.contains(node.val)) {
                    numberOfComponents++;
                    while(node != null && set.contains(node.val)) {
                        set.remove(node.val);
                        node=node.next;
                    }

                }
                else {

                    while(node != null && !set.contains(node.val))
                        node = node.next;
                }
            }
            return numberOfComponents;
        }

}
