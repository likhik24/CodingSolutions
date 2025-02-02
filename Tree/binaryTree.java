package Tree;

import java.util.HashMap;
import Tree.Tree ;
public class binaryTree {
 
    HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
    Tree root=null;
    int preorderIndex = 0;
    public Tree constructNode(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++)
            valueIndexMap.put(inorder[i], i);
        //root = new Tree(preorder[0]);
        constructTree(preorder,inorder,0,inorder.length-1);
        System.out.println(root.val);
        return inordertraverse(root);
    }
    //0 6
    public Tree constructTree(int[] preorder, int[] inorder, int low, int high) {
        //int mid = low+(high-low)/2;
        if(low > high || high >= inorder.length || low >= inorder.length || preorderIndex >= preorder.length)
            return null;
        Tree node = new Tree(preorder[preorderIndex++]); //3 9 1

        if (root == null)
            root = node;
        node.left = constructTree(preorder,inorder, low,valueIndexMap.get(node.val)-1); //0 2  ( 0 0 // 2 2 ) 0 -1 // 1 0
        node.right = constructTree(preorder,inorder, valueIndexMap.get(node.val)+1, high); //4 6 // 2 2

        return node;
    }

    public Tree inordertraverse(Tree root1) {
        if(root1 == null)
            return root1;
        inordertraverse(root1.left);
        System.out.println(root1.val);
        inordertraverse(root1.right);
        return root1;
    }

}
