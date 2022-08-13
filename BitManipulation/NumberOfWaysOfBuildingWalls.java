package BitManipulation;
import java.util.*;
/*
Build Single Layer
        First of all, to figure out how many ways could we build a wall, we need to know how many ways could we built ONE SINGLE LAYER.
        We could definitely get the answer of this subproblem by recursion & backtracking, however, here I use bit manipulation instead.
        There are two reasons: 1. width is no greater than 10, so the complexity of bitmasking here is not too much; 2. In the next part,
        we could quickly find out if two configurations of bricks could be adjacent by bitmasking. Actually I tried both recursion and bit manipulation in this step,
        and it seems that bit manipulation submissions are a little bit faster than recursion submissions .

        We could uniquely represent a configuration of bricks with an integer. For example, when width = 9 there are 8 possible positions to put splits, the number of all combinations is 2^8=256.

        --------  // eight possible position to put splits
        Assume that we have bricks = [3] , then there is only one way to build one single layer:

        [3,3,3]
        i.e.

        --|--|--  // put splits at the 3rd and 6th positions
        And if we replace - and | with zero and one, we could get an integer:

        00100100  // i.e. 36
        So the question is, with given width and bricks, could we compute number of ways to build one layer?
        Since we know width is no greater than 10, we could just write a for loop from zero to 2^(width - 1) - 1,
        and for each number we verify it it is possible to build such a layer with given bricks
*/
public class NumberOfWaysOfBuildingWalls {
    private ArrayList<Integer> waysOfBuildLayer(int width, boolean[] bricks) {
        int waysOfSplit = 1 << (width - 1); //2^(width-1)
        bricks = new boolean[waysOfSplit];
        bricks[1] = true;
        bricks[2] = true;

        //System.out.println(waysOfSplit);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < waysOfSplit; i++) {
            if (possibleToBuild(i, bricks, width, waysOfSplit)) result.add(i);
        }
        return result;
    }

    private boolean possibleToBuild(int split, boolean[] bricks, int width, int waysOfSplit) {
        int wall = split | waysOfSplit;
        //System.out.println(wall);
        int curr = 1;
        while (wall != 0) {
            if (wall % 2 == 1) {

                if (!bricks[curr]) { System.out.println(curr); return false; }
                curr = 1;
            } else {
                curr++;
            }
            wall /= 2;
            System.out.println(wall + " - " + curr);

        }
        return true;
    }

    public int buildWall(int height, int width, int[] bricks) {
        int mod = 1000000007;
        boolean[] hasBricks = new boolean[11];
        for (int brick : bricks) {
            hasBricks[brick] = true;
        }

        // Find all possible way to build one single layer:
        ArrayList<Integer> buildLayer = waysOfBuildLayer(width, hasBricks);
        if (height == 1) {
            return buildLayer.size();
        }

        // Create adjacent list:
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < buildLayer.size(); i++) {
            int split = buildLayer.get(i);
            ArrayList<Integer> next = new ArrayList<Integer>();
            for (int j = 0; j < buildLayer.size(); j++) {
                int nextSplit = buildLayer.get(j);
                if ((split & nextSplit) == 0) {
                    next.add(j);
                }
            }
            nexts.add(next);
        }

        // Build the first layer:
        int[] thisLayer = new int[buildLayer.size()];
        Arrays.fill(thisLayer, 1);

        // Build the wall layer by layer:
        for (int i = 1; i < height; i++) {
            int[] nextLayer = new int[buildLayer.size()];
            for (int j = 0; j < thisLayer.length; j++) {
                ArrayList<Integer> next = nexts.get(j); // get adjacent layers that can be used with j th layer on this layer
                for (int nextSplit : next) {
                    nextLayer[nextSplit] = (nextLayer[nextSplit] + thisLayer[j]) % mod;
                }
            }
            thisLayer = nextLayer;
        }

        // Add all numbers of ways to build the last layer:
        int result = 0;
        for (int num : thisLayer) {
            result = (result + num) % mod;
        }
        return result;
    }


    public static void main(String[] args) {
        NumberOfWaysOfBuildingWalls ways = new NumberOfWaysOfBuildingWalls();
        ways.buildWall(2,3,new int[]{1,2});
    }

   /* Complexity
    The time complexity of function possibleToBuild(int split, boolean[] bricks, int width, int waysOfSplit) is O(width).

    The time complexity of function waysOfBuildLayer(int width, boolean[] bricks) is O(width * 2^(width - 1)). And the space complexity of its result, in worst case, could be O(2^(width - 1)). Since we know width is always no greater than 10, O(width * 2^(width - 1)) is still acceptable.

    The time complexity of buildWall(int height, int width, int[] bricks) depends on number of ways to build one single layer, let's say N. The worst case is N = 2^(width - 1). For example, when width = 10 and bricks = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], wherever we put split(s), it is always possible to build such a layer.

    After getting all possible ways of building one layer ArrayList<Integer> buildLayer, we build a graph ArrayList<ArrayList<Integer>> nexts such that for all nexts[i][j], buildLayer[i] and buildLayer[nexts[i][j]] could be adjacent. So how large could nexts be? The answer is O(3^(width - 1)).

    Why? Assume we have an n-bit number X and k bits of it are zeros, then we could find 2^ k numbers Y such that X & Y = 0. And there are C(n, k) n-bit numbers with k ones. Thus, for 0 <= X, Y < 2^n, we could find

    C(n, 0) * 2^0 + C(n, 1) * 2^1 + ... + C(n, k) * 2^k + ... + C(n, n) * 2^n
= C(n, 0) * 2^0 * 1^n + C(n, 1) * 2^1 * 1^(n-1) + ... + C(n, k) * 2^k * 1^(n-k) + ... + C(n, n) * 2^n * 1^0
            = (2 + 1)^n
= 3^n
    pairs of (X, Y) such that X & Y = 0

    After getting the graph nexts, we update information layer by layer. For each layer, the time complexity is also O(3^(width - 1)).

    Overall time complexity (worst case): O(width * 2^(width - 1) + height * 3^(width - 1))
    Overall space complexity (worst case): O(2^(width - 1) + 3^(width - 1)) */

}
