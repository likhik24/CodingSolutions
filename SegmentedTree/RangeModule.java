package SegmentedTree;

//A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
//
//        A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
//
//        Implement the RangeModule class:
//
//        RangeModule() Initializes the object of the data structure.
//        void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
//        boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
//        void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
//
//
//        Example 1:
//
//        Input
//        ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
//        [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
//        Output
//        [null, null, null, true, false, true]
//

        // Explanation:
//we want to add ranges that we track everytime addrange is called to the current segmentnode
//state will tell us if we are tracking it or not, initially we initalize segment tree with 0, 10^9 range with state as false,
//        and keep adding range when addrange is called, use query range to return true/false if we r tracking that range,
//removerange will remove range of numbers provided from segmenttree by marking state as false;
// when updating we keep updating all nodes state and until we find node whose node.l>= left && node.r <= right
//we need to make sure we both left & right are true i.e update(node.left, left, right)
// & update(node.right, left,right) because if we r adding 10,20 we should add 10,15 & also 15,20


class RangeModule {
    SegmentNode root;
    int max = (int) Math.pow(10,9);
    public RangeModule() {
        root = new SegmentNode(0,max,false);
    }

    public void addRange(int left, int right) {
        update(root,left,right,true);
    }
    private boolean update(SegmentNode node,int l,int r,boolean state){
        if(l<=node.l && r>=node.r){
            node.state = state;
            node.left = null;
            node.right = null;
            return node.state;
        }
        if(l>=node.r || r<=node.l) return node.state; // 10 20 0 10^9
        int mid = node.l + (node.r - node.l) / 2;
        if(node.left==null){
            node.left = new SegmentNode(node.l,mid,node.state);
            node.right = new SegmentNode(mid,node.r,node.state);
        }
        boolean left = update(node.left, l, r,state);
        boolean right = update(node.right, l, r,state);
        node.state = left && right;
        return node.state;
    }

    public boolean queryRange(int left, int right) {
        return query(root,left,right);
    }
    private boolean query(SegmentNode node,int l,int r){
        if(l>=node.r || r<=node.l) return true;
        if((l<=node.l && r>=node.r) || (node.left==null)) return node.state;
        int mid = node.l + (node.r - node.l) / 2;
        if(r<=mid){
            return query(node.left,l,r);
        }else if(l>=mid){
            return query(node.right,l,r);
        }else{
            return query(node.left,l,r) && query(node.right,l,r);
        }
    }

    public void removeRange(int left, int right) {
        update(root,left,right,false);
    }
}
class SegmentNode{
    public int l, r;
    public boolean state;
    public SegmentNode left, right;
    public SegmentNode(int l,int r,boolean state){
        this.l = l;
        this.r = r;
        this.state = state;
    }

    public static void main(String[] args){
        RangeModule module = new RangeModule();
        module.addRange(10,20);
        System.out.println(module.queryRange(10,30));
        module.addRange(20,30);
        System.out.println(module.queryRange(10,30));
        module.removeRange(10, 20);
        System.out.println(module.queryRange(10,30));


    }
}
