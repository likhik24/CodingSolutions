package Tree;
import Tree.Tree;
public class findNumOfIslands {
    
    public int findCountOfIslands(Tree input) {
        //islands are group of 1s surrounded by land (i.e 0's)
        int count=0;
        return dfs(input, false);
        

    }
    public int dfs(Tree root, boolean isParentIsland ) {
        if(root == null)
        return 0;
        if(root.val == 1 && isParentIsland)
         return dfs(root.left,  isParentIsland) + dfs(root.right, isParentIsland);
         else if(root.val == 1)
         return 1+dfs(root.left,  true) + dfs(root.right, true);
         else return dfs(root.left, false) + dfs(root.right, false);

    }

    public static void main(String[] args) {
        Tree root = new Tree(1);
        root.left = new Tree(1);
        root.right = new Tree(1);
        root.right.left = new Tree(0);
        root.right.right = new Tree(0);
        root.left.left = new Tree(1);
        root.left.right = new Tree(1);
        root.left.left.left = new Tree(1);
        root.left.left.right = new Tree(0);
        root.left.right.left = new Tree(1);
        root.left.right.right = new Tree(0);
        root.right.left.left = new Tree(0);
        root.right.left.right = new Tree(1);
        root.right.right.left = new Tree(1);
        root.right.right.right = new Tree(0);
        findNumOfIslands sol = new findNumOfIslands();
        System.out.println(sol.findCountOfIslands(root));
        // /Users/yashwanthpinneka/Desktop/Screenshot 2024-07-15 at 6.07.57 PM.png
    }
}
