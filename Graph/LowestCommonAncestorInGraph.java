package Graph;

import java.util.*;

// in a directed acyclic graph we can find lowest common ancestor using
// coloring nodes from one target until we find the node of different color from other target
class Node {
    int key;
    ArrayList<Node> parents;
    ArrayList<Node> children;
    Node(int key) {
        parents = new ArrayList<>();
        children = new ArrayList<>();
        this.key = key;
    }
}
public class LowestCommonAncestorInGraph {


    String node1Color = "Red";
    String node2Color = "Blue";
//    Node A  - (e,f) -> {i,g,j,k}
//    Node b - {c,d) -> {i,l,m,n}

        public Node findLowestCommonAncestor(Node node1, Node node2) {
            HashMap<Integer, String> nodesColoringMap = new HashMap<>();
            if(node1 == null && node2 == null)
                return null;
            if(node1 == null || node2 == null)
                return null;
            Queue<Node> queue = new LinkedList<>(); // c d i g
            queue.add(node1);
            queue.add(node2);
            boolean isLevelTerminate = false;
            nodesColoringMap.put(node1.key, node1Color); //a,red b,blue
            nodesColoringMap.put(node2.key, node2Color);
            while (!queue.isEmpty()) {
                int size = queue.size(); //a b
                if(isLevelTerminate) //to handle termination when there is no ancestor for previous level nodes
                    return null;
                for (int i = 0; i < size; i++) {
                    Node curr = queue.poll(); //e
                    String currColor = nodesColoringMap.get(curr.key); //red
                    ArrayList<Node> parents = curr.parents; //i,g
                    if(curr.parents == null || curr.parents.size() == 0 )
                        isLevelTerminate = true;
                    for (Node parent : parents) {
                        if (nodesColoringMap.containsKey(parent.key)) {
                            if (nodesColoringMap.get(parent.key) != currColor)
                                return parent;
                        } else {
                            nodesColoringMap.put(parent.key, currColor); //e,red f,red c,blue d, blue i,red, g,red
                            queue.add(parent);
                        }
                    }
                }
            }
            return null;
        }

        public static void main(String[] args) {
            LowestCommonAncestorInGraph lowestCommonAncestorInGraph = new LowestCommonAncestorInGraph();
           ArrayList<Node> nodes = new ArrayList<>();
           for(int i=0;i<14;i++)
            nodes.add(new Node(i));

            nodes.get(0).parents.add(nodes.get(4));
            nodes.get(0).parents.add(nodes.get(5));
            nodes.get(1).parents.add(nodes.get(2));
            nodes.get(1).parents.add(nodes.get(3));
            nodes.get(4).parents.add(nodes.get(6));
            nodes.get(4).parents.add(nodes.get(8));
            nodes.get(5).parents.add(nodes.get(8));
            nodes.get(5).parents.add(nodes.get(10));
            nodes.get(2).parents.add(nodes.get(11));
            nodes.get(2).parents.add(nodes.get(10));
            nodes.get(3).parents.add(nodes.get(12));
            nodes.get(3).parents.add(nodes.get(13));
            System.out.println(lowestCommonAncestorInGraph.findLowestCommonAncestor(nodes.get(0), nodes.get(1)).key);
        }
}
