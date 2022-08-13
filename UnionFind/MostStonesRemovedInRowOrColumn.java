package UnionFind;
import java.util.*;
/*
On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

        A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

        Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.



        Example 1:

        Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
        Output: 5
        Explanation: One way to remove 5 stones is as follows:
        1. Remove stone [2,2] because it shares the same row as [2,1].
        2. Remove stone [2,1] because it shares the same column as [0,1].
        3. Remove stone [1,2] because it shares the same row as [1,0].
        4. Remove stone [1,0] because it shares the same column as [0,0].
        5. Remove stone [0,1] because it shares the same row as [0,0].
        Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
        Example 2:

        Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
        Output: 3
        Explanation: One way to make 3 moves is as follows:
        1. Remove stone [2,2] because it shares the same row as [2,0].
        2. Remove stone [2,0] because it shares the same column as [0,0].
        3. Remove stone [0,2] because it shares the same row as [0,0].
        Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
        Example 3:

        Input: stones = [[0,0]]
        Output: 0
        Explanation: [0,0] is the only stone on the plane, so you cannot remove it.


 A good strategy
        In fact, the proof is really straightforward.
        You probably apply a DFS, from one stone to next connected stone.
        You can remove stones in reversed order.
        In this way, all stones can be removed but the stone that you start your DFS.

        One more step of explanation:
        In the view of DFS, a graph is explored in the structure of a tree.
        As we discussed previously,
        a tree can be removed in topological order,
        from leaves to root.


        4. Count the number of islands
        We call a connected graph as an island.
        One island must have at least one stone left.
        The maximum stones can be removed = stones number - islands number

        The whole problem is transferred to:
        What is the number of islands?

        You can show all your skills on a DFS implementation,
        and solve this problem as a normal one.


        5. Unify index
        Struggle between rows and cols?
        You may duplicate your codes when you try to the same thing on rows and cols.
        In fact, no logical difference between col index and rows index.

        An easy trick is that, add 10000 to col index.
        So we use 0 ~ 9999 for row index and 10000 ~ 19999 for col.


        6. Search on the index, not the points
        When we search on points,
        we alternately change our view on a row and on a col.

        We think:
        a row index, connect two stones on this row
        a col index, connect two stones on this col.

        In another view：
        A stone, connect a row index and col.

        Have this idea in mind, the solution can be much simpler.
        The number of islands of points,
        is the same as the number of islands of indexes.


        7. Union-Find
        I use union find to solve this problem.
        As I mentioned, the elements are not the points, but the indexes.

        for each point, union two indexes.
        return points number - union number
        Copy a template of union-find,
        write 2 lines above,
        you can solve this problem in several minutes.


        Complexity
        union and find functions have worst case O(N), amortize O(1)
        The whole union-find solution with path compression,
        has O(N) Time, O(N) Space

        If you have any doubts on time complexity,
        please refer to wikipedia first.


 */
public class MostStonesRemovedInRowOrColumn {

        int M= 10^9+7;
//     class Point {
//         int x;
//         int y;

//         Point(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
//         public int hashCode() {
//             return x*M+y;
//         }
//         public boolean equals(Point a) {

//         }
//     }
//     class UnionFind {
//         ArrayList<Point> parent; point(0,0);
//         rank = new Point[]
//         HashMap<Integer, Point> colPointMap; //0 , point  1, point
//         HashMap<Integer, Point> rowPointMap; //0, point

//     }

        class UnionFind {
            HashMap<Integer, Integer> map = new HashMap<>();
            int[] parent;
            int length;
            int islands=0;
            UnionFind(int n) {
                parent = new int[n*n];
                length = n;
            }

            public int find(int x) {
                if (map.putIfAbsent(x, x) == null)
                    islands++;
                if (x != map.get(x))
                    map.put(x, find(map.get(x)));
                return map.get(x);
            }

            public void union(int x, int y) {
                x = find(x);
                y = find(y);
                if (x != y) {
                    map.put(x, y);
                    islands--;
                }
            }
        }


        public int removeStones(int[][] stones) {
            UnionFind uf = new UnionFind(stones.length);
            for (int i = 0; i < stones.length; ++i)
                uf.union(stones[i][0], ~stones[i][1]);
            return stones.length - uf.islands;
        }




}
