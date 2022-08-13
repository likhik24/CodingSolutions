package Matrix;
import java.util.*;
/*
Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].

        The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:

        The rank is an integer starting from 1.
        If two elements p and q are in the same row or column, then:
        If p < q then rank(p) < rank(q)
        If p == q then rank(p) == rank(q)
        If p > q then rank(p) > rank(q)
        The rank should be as small as possible.
        The test cases are generated so that answer is unique under the given rules.



        Example 1:


        Input: matrix = [[1,2],[3,4]]
        Output: [[1,2],[2,3]]
        Explanation:
        The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
        The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
        The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
        The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
        Example 2:


        Input: matrix = [[7,7],[7,7]]
        Output: [[1,1],[1,1]]
        Example 3:


        Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
        Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
Approach 2: Sorting + DFS
        Intuition

        DFS is similar to BFS but differs in the order of searching. In most cases, when the search space is not huge, you can replace BFS with DFS.

        In approach 1, we used BFS to find out the connected parts of each point. Now, we use DFS instead.

        Algorithm

        Step 1: Initialize graphs for different values. Iterate matrix and link the rows and columns in the corresponding graph.

        Step 2: Initialize a value2index map to store connected parts.

        This map will contain the value - index mapping. In the index part, separate points to put the connected points in the same array, and to put non-connected points in different arrays. (one array represents a connected part.)
        Therefore, value2index should be in this form: {v1: [[point1, point2, ...], [point11, point12, ...], ...], v2: ...}, where point1, point2, ... are connected, and point11, point21, ... are also connected, but none of the points from different array are connected.
        Step 3: Fill in value2index map by iterating over the matrix again.

        For each point, use DFS to find out all the other connected points. Put all of them into value2index as an array.
        Remember to mark those points visited to avoid duplicate additions.
        Step 4: Sort the keys in value2index (i.e., all values in matrix).

        Step 5: Initialize our answer matrix. Iterate value2index in the order of sorted keys to fill in answer.

        For a given key (i.e., a value in matrix), we fill in answer by connected parts (i.e., one array).
        Note that for points in the same connected part, they share the same rank.
        For a connected part, Find out the minimum possible rank and update that rank.
        To reduce the time for searching the minimum possible rank, we need two arrays to record the maximum rank of each row and each column, respectively.

        Complexity Analysis

Let MM be the number of rows in matrix and NN be the number of columns in matrix.

Time Complexity: \mathcal{O}(NM\log(NM))O(NMlog(NM)).

We need \mathcal{O}(NM)O(NM) to iterate matrix to build graphs.
We need \mathcal{O}(NM)O(NM) to iterate matrix to build value2index. We only visit points at most twice, since we skip points visited in DFS.
We need \mathcal{O}(NM\log(NM))O(NMlog(NM)) to sort the keys in value2index, since there are at most \mathcal{O}(NM)O(NM) different keys.
We need \mathcal{O}(NM)O(NM) to iterate value2index to build answer.
Adding together, the time we needed is \mathcal{O}(NM\log(NM))O(NMlog(NM)).
Space Complexity: \mathcal{O}(NM)O(NM).

For graphs, we store \mathcal{O}(NM)O(NM) edges (viewing each point as an edge).
For value2index, we store \mathcal{O}(NM)O(NM) points.
For rowMax and columnMax, they have size of \mathcal{O}(M)O(M) and \mathcal{O}(N)O(N), respectively.
In total, the size we needed is \mathcal{O}(NM)O(NM).

*/
public class RankTransformationOfMAtrix {

        public void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> rowcols) {
            // the dfs function to find connected parts
            rowcols.add(node);
            for (int rowcol : graph.get(node)) {
                if (!rowcols.contains(rowcol)) {
                    dfs(rowcol, graph, rowcols);
                }
            }
        }

        public int[][] matrixRankTransform(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            // link row to col, and link col to row
            Map<Integer, Map<Integer, List<Integer>>> graphs = new HashMap<>();
            // graphs.get(v): the connection graph of value v
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = matrix[i][j];
                    // if not initialized, initial it
                    if (!graphs.containsKey(v)) {
                        graphs.put(v, new HashMap<Integer, List<Integer>>());
                    }
                    Map<Integer, List<Integer>> graph = graphs.get(v);
                    if (!graph.containsKey(i)) {
                        graph.put(i, new ArrayList<Integer>());
                    }
                    if (!graph.containsKey(~j)) {
                        graph.put(~j, new ArrayList<Integer>());
                    }
                    // link i to j, and link j to i
                    graph.get(i).add(~j);
                    graph.get(~j).add(i);
                }
            }

            // put points into `value2index` dict, grouped by connection
            // use TreeMap to help us sort the key automatically
            SortedMap<Integer, List<List<int[]>>> value2index = new TreeMap<>();
            int[][] seen = new int[m][n]; // mark whether put into `value2index` or not
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (seen[i][j] == 1) {
                        continue;
                    }
                    seen[i][j] = 1;
                    int v = matrix[i][j];
                    Map<Integer, List<Integer>> graph = graphs.get(v);
                    // use dfs to find the connected parts
                    Set<Integer> rowcols = new HashSet<Integer>();
                    dfs(i, graph, rowcols);
                    dfs(~j, graph, rowcols);
                    // transform rowcols into points
                    List<int[]> points = new ArrayList<>();
                    for (int rowcol : rowcols) {
                        for (int k : graph.get(rowcol)) {
                            if (k >= 0) {
                                points.add(new int[] { k, ~rowcol });
                                seen[k][~rowcol] = 1;
                            } else {
                                points.add(new int[] { rowcol, ~k });
                                seen[rowcol][~k] = 1;
                            }
                        }
                    }
                    if (!value2index.containsKey(v)) {
                        value2index.put(v, new ArrayList<List<int[]>>());
                    }
                    value2index.get(v).add(points);
                }
            }
            int[][] answer = new int[m][n]; // the required rank matrix
            int[] rowMax = new int[m]; // rowMax[i]: the max rank in i row
            int[] colMax = new int[n]; // colMax[j]: the max rank in j col
            for (int v : value2index.keySet()) {
                // update by connected points with same value
                for (List<int[]> points : value2index.get(v)) {
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
