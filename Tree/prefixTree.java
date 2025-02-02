package Tree;
import Tree.Tree;
import java.util.*;
class TreeNode {
    char key;
    TreeNode left;
    TreeNode right;
    int level;
    TreeNode(char key, int level) {
        this.key = key;
        this.level = level;
    }
}
public class prefixTree {
    
    public TreeNode buildTree(TreeMap<Integer, ArrayList<Character>> map) {
      TreeNode root = new TreeNode(map.get(0).get(0), 0);
     
      map.remove(0);
      
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(map.size() != 0 ) {
            int size = queue.size(); //1 2
            ArrayList<Character> valuesToInsert = map.firstEntry().getValue(); //{e} {b,z}
           Collections.sort(valuesToInsert);
            for(int i=0; i<size;i++) {
                TreeNode curr = queue.poll(); //a {e,0}
               if(valuesToInsert.size() > 0) {
                if(curr.key == '0' || curr.level == 0) 
                    {
                        char first = valuesToInsert.remove(0);
                        if(curr.left == null) {
                            curr.left = new TreeNode(first, curr.level+1);
                            queue.add(curr.left);
                        }
                        if(curr.right == null && valuesToInsert.size() != 0) {
                            char second = valuesToInsert.remove(0);
                            curr.right = new TreeNode(second, curr.level+1);
                            queue.add(curr.right);
                        }
                        else { 
                            curr.right =new TreeNode('0', curr.level+1);
                            queue.add(curr.right);
                        }
                        
                    }
                }
            }
            
            if(map.firstEntry().getKey() == level+1)
             map.pollFirstEntry();
            level++;
           
        }
            
        
         
      
      return root;
    }
    public static void main(String[] args) {
        prefixTree solution = new prefixTree();
        TreeMap<Integer, ArrayList<Character>>  map = new TreeMap<>();
    //    map.put(1, new ArrayList<>());
        map.put(0, new ArrayList<>());
        map.get(0).add('a');
       //map.get(1).add('e');
       // map.get(1).add('k');
        map.put(2, new ArrayList<>());
        map.get(2).add('b');
        map.get(2).add('z');
        map.get(2).add('c');
        map.get(2).add('d');
        TreeNode root = solution.buildTree(map);
        System.out.println(root.key);
        System.out.println(root.left.key);
        System.out.println(root.right.key);
        if(root.left.left != null)
        System.out.println(root.left.left.key);
        if(root.left.right != null)
        System.out.println(root.left.right.key);
    if(root.right.left != null)
        System.out.println(root.right.left.key);
       if(root.right.right != null)
        System.out.println(root.right.right.key);

    }
}
