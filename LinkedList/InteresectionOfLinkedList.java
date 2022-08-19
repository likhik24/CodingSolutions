package LinkedList;
class Node {
    int val;
    Node left;
    Node right;
    Node parent;
}
public class InteresectionOfLinkedList {

        public Node lowestCommonAncestor(Node p, Node q) {
            Node runner1 = p, runner2 = q;

            while (runner1 != runner2) { // Guaranteed to complete since both nodes belong to same tree
                // We are simulating a cycle when any of the conditions below is satisfied
                // by pointing runner to the head of the other "list"  to make sure
                // intersection is found before either of the below conditions are satisfied again
                runner1 = (runner1 == null)?q:runner1.parent;
                runner2 = (runner2 == null)?p:runner2.parent;
            }

            return runner1;
        }

}
