package Tree;

class Tree {
    Tree left;
    Tree right;
    int val;
    Tree(int val) {
        this.val = val;
        this.left = this.right = null;
    }
    Tree(int val, Tree left, Tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
