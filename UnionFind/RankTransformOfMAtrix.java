package UnionFind;
import java.util.*;
/*
As we mentioned in approach 1, Union-Find (or UF, Disjoint Set) can be applied to find the connected parts.

        Since Union-Find is not the essence of this problem (and considering the length of the article), we will not provide a very detailed explanation of Union-Find here. You can find some tutorials on other problems that require Union-Find, such as Redundant Connection or Most Stones Removed with Same Row or Column.

        Now, we will have a quick review of Union-Find, and explain how we can use Union-Find to find the connected parts.

        Similar to approach 1, we view the matrix points as edges that connect rows and columns.

        As we know, we can view Union-Find as a forest-like structure (forest represents many trees). For example:

        Figure 8

        To store this structure, we can store the node's parents in a map or an array. The root's parent is itself. A map is used here for illustration.

        This structure provides two methods, find and union.

        For function find, it returns the root of the given node.

        For function union, it accepts two nodes and merges the two trees that the nodes belong to.

        For example, if we want to union node Row2 and node Col1, we will have something like this:

        Figure 9

        What union needs to do is to assure that find(Row2) and find(Col1) yield the same value. That's it.

        There are two main optimizations we can do in Union-Find: path compression and union by rank.

        Path compression means that when we apply find, we can link the nodes on our search path to the root directly, which will reduce the search time for the next time.

        For union by rank, when we merge two trees, what we do is to link a tree's root to the other tree's leaf. But which tree's root should be linked? We can assign each tree a rank, and link the low-rank tree's root to the high-rank tree's leaf. The rank can be the size of the tree or the number of layers of the tree.

        With path compression and union by rank, if we perform find and union NN times, it can be done in almost \mathcal{O}(N)O(N).

        In fact, the time complexity is \mathcal{O}(N\alpha(N))O(Nα(N)), where function \alpha(n)α(n) is inverse Ackermann function, which is much smaller than \log(n)log(n) and approximately constant. The proof of the complexity is complicated, and interested readers can go to Wikipedia to check the detail.

        Now, back to our problem. We need to find the connected parts.

        We can use the union function to union rows and columns together and use find to determine which connected parts the given point belongs to.

        Similar to approach 1, we use 0 and positive numbers to mark the row, and the complement numbers to mark the column.

        Algorithm

        Step 1: Implement find and union for Union-Find.

        find receives an integer and returns the "root" of that integer.
        union accepts two integers and merges them into the same union.
        Step 2: Initialize Union-Finds (UFs) for different values. Iterate matrix and union the rows and columns in the corresponding Union-Find.

        Step 3: Initialize a value2index map to store connected parts.

        This map will contain the value - index mapping. In the index part, separate points to put the connected points in the same array, and to put non-connected points in different arrays. (one array represents a connected part.)
        We mark those array by the "root" of points in Union-Find (so value2index is actually a nested map).
        Therefore, value2index should be in this form: {v1: {root1: [point1, point2, ...], root2: [point11, point12, ...], ...}, v2: ...}, where point1, point2, ... are connected, and point11, point21, ... are also connected, but none of points from different set are connected.
        Step 4: Fill in value2index map by iterate matrix again.

        For a point, use find to calculate its "root". Put the point in the corresponding set.
        Step 5: Sort the keys in value2index (i.e., all values in matrix).

        Step 6: Initialize our answer matrix. Iterate value2index in the order of sorted keys to fill in answer.

        For a given key (i.e., a value in matrix), we fill in answer by connected parts (i.e., one array).
        Note that for points in the same connected part, they share the same rank.
        For a connected part, Find out the minimum possible rank and update that rank.
        To reduce the time for searching the minimum possible rank, we need two arrays to record the maximum rank of each row and each column, respectively.

        Complexity Analysis

        Let MM be the number of rows in matrix and NN be the number of columns in matrix.

        Time Complexity: \mathcal{O}(NM\log(NM))O(NMlog(NM)).

        We need \mathcal{O}(NM\log(NM))O(NMlog(NM)) to iterate matrix to build UFs. However, with both union by rank and path compression, we can achieve \mathcal{O}(NM\alpha(NM))O(NMα(NM)), where function \alpha(n)α(n) is inverse Ackermann function, which is much smaller than \log(n)log(n) and approximately constant.
        We need \mathcal{O}(NM)O(NM) to iterate matrix to build value2index.
        We need \mathcal{O}(NM\log(NM))O(NMlog(NM)) to sort the keys in value2index, since there are at most \mathcal{O}(NM)O(NM) different keys.
        We need \mathcal{O}(NM)O(NM) to iterate value2index to build answer.
        Adding together, the time we needed is \mathcal{O}(NM\log(NM))O(NMlog(NM)).
        Space Complexity: \mathcal{O}(NM)O(NM).

        For UFs, we store \mathcal{O}(NM)O(NM) edges (viewing each point as an edge).
        For value2index, we store \mathcal{O}(NM)O(NM) points.
        For rowMax and columnMax, they have size of \mathcal{O}(M)O(M) and \mathcal{O}(N)O(N), respectively.
        In total, the size we needed is \mathcal{O}(NM)O(NM).

*/
public class RankTransformOfMAtrix {


        // implement find and union
        public int find(Map<Integer, Integer> UF, int x) {
            if (x != UF.get(x)) {
                UF.put(x, find(UF, UF.get(x)));
            }
            return UF.get(x);
        }

        public void union(Map<Integer, Integer> UF, int x, int y) {
            if (!UF.containsKey(x)) {
                UF.put(x, x);
            }
            if (!UF.containsKey(y)) {
                UF.put(y, y);
            }
            UF.put(find(UF, x), find(UF, y));
        }

        public int[][] matrixRankTransform(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            // link row and col together
            Map<Integer, Map<Integer, Integer>> UFs = new HashMap<>();
            // UFs.get(v): the Union-Find of value v
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = matrix[i][j];
                    if (!UFs.containsKey(v)) {
                        UFs.put(v, new HashMap<Integer, Integer>());
                    }
                    // union i to j
                    union(UFs.get(v), i, ~j);
                }
            }

            // put points into `value2index` dict, grouped by connection
            // use TreeMap to help us sort the key automatically
            SortedMap<Integer, Map<Integer, List<int[]>>> value2index = new TreeMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = matrix[i][j];
                    if (!value2index.containsKey(v)) {
                        value2index.put(v, new HashMap<Integer, List<int[]>>());
                    }
                    Map<Integer, List<int[]>> indexes = value2index.get(v);
                    int f = find(UFs.get(v), i);
                    if (!indexes.containsKey(f)) {
                        indexes.put(f, new ArrayList<int[]>());
                    }
                    indexes.get(f).add(new int[] { i, j });
                }
            }

            int[][] answer = new int[m][n]; // the required rank matrix
            int[] rowMax = new int[m]; // rowMax[i]: the max rank in i row
            int[] colMax = new int[n]; // colMax[j]: the max rank in j col
            for (int v : value2index.keySet()) {
                // update by connected points with same value
                for (List<int[]> points : value2index.get(v).values()) {
                    int rank = 1;
                    for (int[] point : points) {
                        rank = Math.max(rank, Math.max(rowMax[point[0]], colMax[point[1]]) + 1);
                    }
                    for (int[] point : points) {
                        answer[point[0]][point[1]] = rank;
                        // update rowMax and colMax
                        rowMax[point[0]] = Math.max(rowMax[point[0]], rank);
                        colMax[point[1]] = Math.max(colMax[point[1]], rank);
                    }
                }
            }
            return answer;
        }


}
