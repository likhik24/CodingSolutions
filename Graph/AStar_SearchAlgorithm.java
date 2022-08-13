package Graph;


import java.util.*;
//grid has obstacles and blockers which block from moving in the grid from src to dest, we need to find the shortest path

/*Time Complexity
        Considering a graph, it may take us to travel all the edge to reach the destination cell from the source cell
        [For example, consider a graph where source and destination nodes are connected by a series of edges, like – 0(source) –>1 –> 2 –> 3 (target)
        So the worse case time complexity is O(E), where E is the number of edges in the graph

        Auxiliary Space In the worse case we can have all the edges inside the open list, so required auxiliary space in worst case is O(V), where V is the total number of vertices.

        Summary
        So when to use BFS over A*, when to use Dijkstra over A* to find the shortest paths ?
        We can summarise this as below-
        1) One source and One Destination-
        → Use A* Search Algorithm (For Unweighted as well as Weighted Graphs)
        2) One Source, All Destination –
        → Use BFS (For Unweighted Graphs)
        → Use Dijkstra (For Weighted Graphs without negative weights)
        → Use Bellman Ford (For Weighted Graphs with negative weights)
        3) Between every pair of nodes-
        → Floyd-Warshall
        → Johnson’s Algorithm */

public class AStar_SearchAlgorithm {
    int[] destination;
    int[][] dirs = new int[][]{{1,0},{0,1},{0,-1},{-1,0},{-1,1},{1,1},{1,-1},{-1,-1}};
    class CellValue {
        int row;
        int col;
        int[] parent;
        int parentDistance;
        double destDistance;
        double totalDistance;
        CellValue(int row, int col, int[] parent, int parentDistance) {
            this.row = row;
            this.col = col;
            this.parent = parent;
           this.parentDistance = parentDistance;
            updateDestDistance();
            totalDistance = destDistance + parentDistance;
        }

        public void updateDestDistance() {
            destDistance = Math.sqrt(Math.pow(destination[0]-row, 2) + Math.pow(destination[1] - col,2));
        }

    }
    public double distanceFromDest(CellValue cell) {
        return  Math.sqrt(Math.pow(destination[0]-cell.row, 2) + Math.pow(destination[1] - cell.col,2));
    }

    public String findShortestPath(int[][] grid, int[] src, int[] dest ) {
        this.destination = dest;
        PriorityQueue<CellValue> queue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.totalDistance));

        int R = grid.length;
        int C = grid[0].length;
        CellValue[][] cells = new CellValue[R][C];

        if(src[0] == dest[0] && src[1] == dest[1])
            return "";

        CellValue srcCell = new CellValue(src[0], src[1], new int[]{}, 0);
        cells[src[0]][src[1]] = srcCell;
        queue.add(new CellValue(src[0], src[1], new int[]{}, 0));
        while(!queue.isEmpty()) {
            CellValue curr = queue.poll();
            if(curr.row == destination[0] && curr.col == destination[1])
                return tracePath(curr, cells, src);
            for(int[] dir: dirs) {
                int nrow = curr.row+dir[0];
                int ncol = curr.col+dir[1];
                if(nrow >=0 && ncol>=0 && nrow<R && ncol <C && (grid[nrow][ncol] != 0)) {
                    if(cells[nrow][ncol] != null) {
                        if (cells[nrow][ncol].totalDistance > curr.parentDistance + 1 + distanceFromDest(cells[nrow][ncol])) {
                            cells[nrow][ncol].parent = new int[]{curr.row, curr.col};
                            cells[nrow][ncol].parentDistance = curr.parentDistance + 1;
                            cells[nrow][ncol].totalDistance = cells[nrow][ncol].parentDistance + distanceFromDest(cells[nrow][ncol]);
                            queue.add(cells[nrow][ncol]);

                        }
                    }
                    else {
                        CellValue neigh = new CellValue(nrow, ncol, new int[]{curr.row, curr.col}, curr.parentDistance + 1);
                        queue.add(neigh);

                        cells[neigh.row][neigh.col] = neigh;
                    }
                }
            }

        }
        return "";
    }

    public String tracePath(CellValue curr, CellValue[][] cells, int[] source) {
        StringBuilder res = new StringBuilder();
        while(curr.parent[0] != source[0] || curr.parent[1] != source[1]) {
            System.out.println(curr.parent[0] + "->" + curr.parent[1]);
            res.append(curr.parent[0] + "->" + curr.parent[1]);
            curr = cells[curr.parent[0]][curr.parent[1]];
        }
        return String.valueOf(res);

    }

    public static void main(String[] args) {
        AStar_SearchAlgorithm graph = new AStar_SearchAlgorithm();
        graph.findShortestPath(new int[][]{{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
             { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
    { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 } ,
    { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 } ,
    { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
        { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
        { 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 }}, new int[]{8,0}, new int[]{0,0});
        graph.findShortestPath(new int[][]{{ 1, 0, 1, 0}, {0,0,0,0}, {0,0,1,0}}, new int[]{2,2}, new int[]{0,2});
    }

}
