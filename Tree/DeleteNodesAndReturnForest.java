package Tree;
import java.util.*;
/*
Given the root of a binary tree, each node in the tree has a distinct value.

        After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

        Return the roots of the trees in the remaining forest. You may return the result in any order.



        Example 1:


        Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
        Output: [[1,2,null,4],[6],[7]]
        Example 2:

        Input: root = [1,2,4,null,3], to_delete = [3]
        Output: [[1,2,4]]
*/
//forests are roots of different subtrees formed after deleting nodes, so when we delete a node all nodes below that level form their own forest
public class DeleteNodesAndReturnForest {

  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            ArrayList<TreeNode> result = new ArrayList<>();
            HashSet<Integer> nodesToDelete = new HashSet<>();
            for(int i: to_delete)
                nodesToDelete.add(i);
            del(result, root, nodesToDelete, true);

            return result;
        }
        //when we need to delete at particular level then we pass isroot as true to next level because all nodes in next level are disjoint sets and act as root which needs to be added
        public TreeNode del(ArrayList<TreeNode> res, TreeNode root, HashSet<Integer> nodesToDelete, boolean isRoot) {
            if(root == null)
                return null;

            boolean deleted = nodesToDelete.contains(root.val);
            if(isRoot && !deleted)
                res.add(root);
            root.left = del(res, root.left, nodesToDelete, deleted);
            root.right = del(res, root.right, nodesToDelete, deleted);
            return deleted ? null : root;
        }

}
