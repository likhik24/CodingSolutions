package Tree;
import java.util.*;

public class CousinsInATree {

      //Definition for a binary tree node.
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


        TreeNode r = new TreeNode(101);
        public boolean isCousins(TreeNode root, int x, int y) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean foundCousin = false;
            while(!queue.isEmpty() && !foundCousin) { //1
                int size=queue.size(); //2 3 4
                boolean visitedR = false;
                for(int i=0;i<size;i++) {
                    TreeNode curr = queue.poll();
                    if(curr.val == 101) {
                        visitedR = true;
                        continue;
                    }
                    if(curr.val == x || curr.val == y) {
                        if(foundCousin & visitedR )
                            return true;
                        else if(!foundCousin) {
                            foundCousin = true;
                            visitedR = false;
                        }
                        else {
                            return false;
                        }
                    }

                    if(curr.left != null) queue.add(curr.left);
                    if(curr.right != null) queue.add(curr.right);

                    queue.add(r);
                }

            }


            return false;
        }

}
