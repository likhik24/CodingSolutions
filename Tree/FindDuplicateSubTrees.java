package Tree;

//        Given the root of a binary tree, return all duplicate subtrees.
//
//        For each kind of duplicate subtrees, you only need to return the root node of any one of them.
//
//        Two trees are duplicate if they have the same structure with the same node values.
//
//
//
//        Example 1:
//
//
//        Input: root = [1,2,3,4,null,2,4,null,null,4]
//        Output: [[2,4],[4]]
//        Example 2:
//
//
//        Input: root = [2,1,1]
//        Output: [[1]]
//        Example 3:
//
//
//        Input: root = [2,2,2,3,null,3,null]
//        Output: [[2,3],[3]]
//

//we can do postorder traversal and store existing result to find exact path match and once we found path matching we can add the node
import java.util.*;
public class FindDuplicateSubTrees {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

        List<TreeNode> result;
        HashMap<String,Integer> map = new HashMap<>();
        public boolean isLeaf(TreeNode root) {
            return root.left == null && root.right == null;
        }

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            result = new ArrayList<>();
            // search(root);
            findDuplicates(root, result);
            return result;
        }
        public String findDuplicates(TreeNode root1, List<TreeNode> result) {
            if(root1 == null)
                return "";

            StringBuilder sb = new StringBuilder();
            sb.append(findDuplicates(root1.left, result) + "-");
            sb.append(findDuplicates(root1.right, result)+ "-");
            sb.append(root1.val);
            map.put(String.valueOf(sb), map.getOrDefault(String.valueOf(sb),0)+1);
            if(map.get(String.valueOf(sb)) == 2)
                result.add(root1);
            return sb.toString();
        }

}
