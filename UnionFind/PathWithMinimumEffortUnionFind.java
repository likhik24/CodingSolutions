package UnionFind;
//You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

//        A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//        Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
//
//
//        Example 1:
//
//
//
//        Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//        Output: 2
//        Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
//        This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
//        Example 2:
//
//
//
//        Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//        Output: 1
//        Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

//Using Disjoint Set is another intuitive way to solve the problem.
// Each cell in the matrix is a single node/component in a graph.
// The path from the current cell to adjacent cells is an edge connecting the 2 cells.
// Using this intuition, we could use Union Find algorithm to form a connected component
// from the source cell to the destination cell.
//
//        Initially, every cell is a disconnected component and we aim to form a single connected component
//        that connects the source cell to the destination cell. Each connected component connects multiple cells and is identified by a parent. We must continue connecting components until the source cell and destination cell shares the same parent.
//
//        The union find algorithm performs 2 operations,
//
//        Find(x): Returns the parent of the connected component to which x belongs.
//
//        Union(x, y): Merges the two disconnected components to which x and y belongs.
//
//        To efficiently implement the above operations, we could use Union By Rank and Path Compression strategy.
//
//        Algorithm
//
//        Initially, each cell is a disconnected component, so we initialize each cell as a parent of itself.
//        Also we flatten a 2D matrix into a 1D matrix of size row * col and each cell (currentRow, currentCol)
//        in a 2D matrix can be stored at (currentRow * col + currentCol) in a 1D matrix.
//        The below figure illustrates this idea.
//        img
//
//        We also build an edgeList which consists of the absolute difference between every adjacent cell in the matrix.
//        We also sort the edge list in non-decreasing order of difference.
//        The below example illustrates the edge list of given heights matrix [[1,2,2],[3,8,2],[5,3,5]] sorted by difference.
//        img
//
//        Start iterating over the sorted edge list and connect each edge to form a connected component using Union Find Algorithm.
//
//        After every union, check if the source cell (0) and destination cell (row * col - 1) are connected.
//        If yes, the absolute difference between the current edge is our result.
//        Since we access the edges in increasing order of difference, and the current edge connected
//        the source and destination cell, we are sure that the current difference is the
//        maximum absolute difference in our path with minimum efforts


import java.util.*;

//create edges between adjacent cells along with difference b/w two vertices in edge saved as difference
//sort all the edges by difference and find if
//class Edge {
//    int vertex1; // ( row1*rows.length+col1)
//    int vertex2; // ( row2*rows.length+col2)
//    int difference; // stores absolute difference between vertex1 cell and vertex2 cell

//}
public class PathWithMinimumEffortUnionFind {
    class Edge {
        int x;
        int y;
        int difference;

        Edge(int x, int y, int difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        List<Edge> edgeList;

        public UnionFind(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            parent = new int[row * col];
            edgeList = new ArrayList<>();
            rank = new int[row * col];
            for (int currentRow = 0; currentRow < row; currentRow++) {
                for (int currentCol = 0; currentCol < col; currentCol++) {
                    if (currentRow > 0) {
                        edgeList.add(new Edge(currentRow * col + currentCol,
                                (currentRow - 1) * col + currentCol,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow - 1][currentCol]))
                        );
                    }
                    if (currentCol > 0) {
                        edgeList.add(new Edge(currentRow * col + currentCol,
                                currentRow * col + currentCol - 1,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow][currentCol - 1]))
                        );
                    }
                    parent[currentRow * col + currentCol] = currentRow * col + currentCol;
                }
            }
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                if (rank[parentX] > rank[parentY]) parent[parentY] = parentX;
                else if (rank[parentX] < rank[parentY]) parent[parentX] = parentY;
                else {
                    parent[parentY] = parentX;
                    rank[parentX] += 1;
                }
            }
        }
    }
        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            if (row == 1 && col == 1) return 0;
            UnionFind unionFind = new UnionFind(heights);
            List<Edge> edgeList = unionFind.edgeList;
            Collections.sort(edgeList, (e1, e2) -> e1.difference - e2.difference);

            for (int i = 0; i < edgeList.size(); i++) {
                int x = edgeList.get(i).x;
                int y = edgeList.get(i).y;
                unionFind.union(x, y);
                if (unionFind.find(0) == unionFind.find(row * col - 1)) return edgeList.get(i).difference;
            }
            return -1;
        }

}
