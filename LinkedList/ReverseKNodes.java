package LinkedList;

public class ReverseKNodes {

      //Definition for singly-linked list.
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }



        public ListNode reverseLinkedList(ListNode head, int currK) {
            ListNode prev1 = null;
            ListNode curr = head;
            while(currK != 0 && curr != null) {
                ListNode next = curr.next; //4
                curr.next = prev1; //1
                prev1 = curr; //3
                curr = next; //4
                currK--;
            }
            return prev1;
        }

        public ListNode reverseKGroup(ListNode head, int k) {

            ListNode curr = head;
            int count = 0;
            while(count <k  && curr!= null) {
                count++;
                curr = curr.next;

            }

            if (count == k) {
                ListNode newHead = reverseLinkedList(head,k);
                head.next = reverseKGroup(curr, k);
                return newHead;

            }
            return head;
        }

}
