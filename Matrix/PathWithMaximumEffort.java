package Matrix;

import java.util.*;
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

//. If we observe, the problem is similar to finding the shortest path from a source cell to a destination cell. Here, the shortest path is the one with minimum absolute difference between every adjacent cells in that path. Also, since there is height associated with each cell, simple BFS traversal won't be sufficient.
//
//        The absolute difference between adjacent cells A and B can be perceived as the weight of an edge from cell A to cell B. Thus, we could use Dijkstra's Algorithm which is used to find the shortest path in a weighted graph with a slight modification of criteria for the shortest path.
//
//        Let's look at the algorithm in detail.
//
//We use a differenceMatrix of size \text{row} \cdot \text{col}row⋅col where each cell represents the minimum effort required to reach that cell from all the possible paths. Also, initialize we all the cells in the differenceMatrix to infinity \text{(MAX\_INT)}(MAX_INT) since none of the cells are reachable initially.
//
//        As we start visiting each cell, all the adjacent cells are now reachable. We update the absolute difference between the current cell and adjacent cells in the differenceMatrix. At the same time, we also push all the adjacent cells in a priority queue. The priority queue holds all the reachable cells sorted by its value in differenceMatrix, i.e the cell with minimum absolute difference with its adjacent cells would be at the top of the queue.
//
//        We begin by adding the source cell (x=0, y=0) in the queue. Now, until we have visited the destination cell or the queue is not empty, we visit each cell in the queue sorted in the order of priority. The less is the difference value(absolute difference with adjacent cell) of a cell, the higher is its priority.
//
//        Get the cell from the top of the queue curr and visit the current cell.
//
//        For each of the 4 cells adjacent to the current cell, calculate the maxDifference which is the maximum absolute difference to reach the adjacent cell (adjacentX, adjacentY) from current cell (curr.x, curr.y).
//
//        If the current value of the adjacent cell (adjacentX, adjacentY) in the difference matrix is greater than the maxDifference, we must update that value with maxDifference. In other words, we have found that the path from the current cell to the adjacent cell takes lesser efforts than the other paths that have reached the adjacent cell so far. Also, we must add this updated difference value in the queue.
//
//    **********  Ideally, for updating the priority queue, we must delete the old value and reinsert with the new maxDifference value. But, as we know that the updated maximum value is always lesser than the old value and would be popped from the queue and visited before the old value, we could save time and avoid removing the old value from the queue.
//
//        At the end, the value at differenceMatrix[row - 1][col - 1] is the minimum effort required to reach the destination cell (row-1,col-1).

//
//we can also TRY UNIONFIND TO COMBINE COMPONENTS, use bfs/dfs with binary search taking a distance of mid and viewing if there is path possible below mid or above mid and update mid

public class PathWithMaximumEffort { int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    class Cell {
        int x;
        int y;
        Integer difference;

        Cell(int x, int y, Integer difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }

    public int minimumEffortPath(int[][] heights) {
        int[][] differenceMatrix = new int[heights.length][heights[0].length];
        for(int[] diff:differenceMatrix) {
            Arrays.fill(diff, Integer.MAX_VALUE);
        }
        int row = heights.length;
        int col = heights[0].length;
        differenceMatrix[0][0] = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>((a, b) -> (a.difference.compareTo(b.difference)));
        boolean[][] visited = new boolean[row][col];
        queue.add(new Cell(0, 0, differenceMatrix[0][0]));

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            visited[curr.x][curr.y] = true;
            if (curr.x == row - 1 && curr.y == col - 1)
                return curr.difference;
            for (int[] direction : directions) {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[curr.x][curr.y]);
                    if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                        differenceMatrix[adjacentX][adjacentY] = maxDifference;
                        queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[row - 1][col - 1];
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }


        public int minimumEffortPathUnionFind(int[][] heights) {
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
}

}
