import java.util.*;
//On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
//
//        The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
//
//        Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
//
//
//
//        Example 1:
//
//
//        Input: board = [[1,2,3],[4,0,5]]
//        Output: 1
//        Explanation: Swap the 0 and the 5 in one move.
public class SlidingPuzzle {
        public int slidingPuzzle(int[][] board) {
            int m = 2, n = 3;
            StringBuilder sb = new StringBuilder();
            String target = "123450";

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
            }
            String start = sb.toString();
            //store neighboring indices of each index
            int[][] neighbor = new int[][]{
                    {1, 3},//neighbors of 0 index are {1,3}
                    {0, 4, 2},
                    {1, 5},
                    {0, 4},
                    {3, 1, 5},
                    {4, 2}
            };


            Queue<String> q = new LinkedList<>();
            HashSet<String> visited = new HashSet<>();

            q.offer(start);
            visited.add(start);

            int step = 0;
            while (!q.isEmpty()) {
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    String cur = q.poll();

                    if (target.equals(cur)) {
                        return step;
                    }

                    int idx = 0;
                    for (; cur.charAt(idx) != '0'; idx++) ;
                    //pick idx which has char as 0 and find its neighbors to swap, swap the indices and add new string to queue to process and keep incrementing steps for all neighboring strings processed
                    for (int adj : neighbor[idx]) {
                        String new_board = swap(cur.toCharArray(), adj, idx);

                        if (!visited.contains(new_board)) {
                            q.offer(new_board);
                            visited.add(new_board);
                        }
                    }
                }
                step++;
            }

            return -1;
        }

        private String swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            return new String(chars);
        }
}
