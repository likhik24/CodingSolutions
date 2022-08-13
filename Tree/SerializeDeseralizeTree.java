package Tree;

import java.util.*;
public class SerializeDeseralizeTree {
    /**
     * Definition for a binary tree node.
     public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public class TreeNode {
          int val;
         TreeNode left;
         TreeNode right;
        TreeNode(int x) { val = x; }
      }
    public class Codec {

        // Encodes a tree to a single string.
        StringBuilder sb = new StringBuilder();
        public String serialize(TreeNode root) {
            if(root == null) {
                sb.append("null,");
                return sb.toString();
            }
            sb.append(root.val + ",");
            serialize(root.left);
            serialize(root.right);
            return sb.substring(0, sb.length()-1);
        }
        TreeNode root = null;
        // Decodes your encoded data to tree.

        public TreeNode rdeserialize(List<String> l) {
            // Recursive deserialization.
            if (l.get(0).equals("null")) {
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            if(this.root == null)
                this.root = root;
            l.remove(0);
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }
        public TreeNode deserialize(String data) {
            System.out.println(data);
            String[] nodes = data.split(",");
            List<String> nodesList = new LinkedList<String>(Arrays.asList(nodes));
            rdeserialize(nodesList);
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}
