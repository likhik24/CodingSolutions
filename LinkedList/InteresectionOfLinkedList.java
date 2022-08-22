package LinkedList;
class Node {
    int val;
    Node left;
    Node right;
    Node parent;
}
public class InteresectionOfLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
        public Node lowestCommonAncestor(Node p, Node q) {
            Node runner1 = p, runner2 = q;

            while (runner1 != runner2) { // Guaranteed to complete since both nodes belong to same tree
                // We are simulating a cycle when any of the conditions below is satisfied
                // by pointing runner to the head of the other "list"  to make sure
                // intersection is found before either of the below conditions are satisfied again
                //whenever a point reaches null faster we will swap it with head of other pointer and we cover the difference between them by doing the same for each pointer so both reach the same level distance from the ancestor node
                runner1 = (runner1 == null)?q:runner1.parent;
                runner2 = (runner2 == null)?p:runner2.parent;
            }

            return runner1;
        }

        /*In code, we could write this algorithm with 4 loops, one after the other, each doing the following:

Calculate NN; the length of list A.
Calculate MM; the length of list B.
Set the start pointer for the longer list.
Step the pointers through the list together.
While this would have a time complexity of O(N + M)O(N+M) and a space complexity of O(1)O(1) and would be fine for an interview, we can still simplify the code a bit! As some quick reassurance, most people will struggle to come up with this next part by themselves. It takes practice and seeing lots of linked list and other math problems.

If we say that cc is the shared part, aa is exclusive part of list A and bb is exclusive part of list B, then we can have one pointer that goes over a + c + b and the other that goes over b + c + a. Have a look at the diagram below, and this should be fairly intuitive.

Diagram showing that one pointer could go over a + c + b while the other goes over b + c + a, and then both will end up on the intersection node.

This is the above algorithm in disguise - one pointer is essentially measuring the length of the longer list, and the other is measuring the length of the shorter list, and then placing the start pointer for the longer list. Then both are stepping through the list together. By seeing the solution in this way though, we can now implement it as a single loop.

Algorithm

Set pointer pA to point at headA.
Set pointer pB to point at headB.
While pA and pB are not pointing at the same node:
If pA is pointing to a null, set pA to point to headB.
Else, set pA to point at pA.next.
If pB is pointing to a null, set pB to point to headA.
Else, set pB to point at pB.next.
return the value pointed to by pA (or by pB; they're the same now).
 */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        //whenever a point reaches null faster we will swap it with head of other pointer and we cover the difference between them by doing the same for each pointer so both reach the same level distance from the conjuction node
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
