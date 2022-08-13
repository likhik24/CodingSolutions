package Backtracking;
import java.util.*;

public class WordSearch {
        int[][] dirs = {{1,0},{0,1}, {-1,0},{0,-1}};
        public boolean exist(char[][] board, String word) {
            for(int i=0;i<board.length;i++) {
                for(int j=0;j<board[0].length;j++) {
                    if(board[i][j] == word.charAt(0)) {
                        char temp= board[i][j];

                        if(backtrack(board, i, j, 0, word))
                            return true;
                        board[i][j] = temp;
                    }
                }
            }
            return false;
        }

        public boolean backtrack(char[][] board, int row, int col, int index, String word) {
            if(index >= word.length() || row <0 || col<0 || row>=board.length || col >= board[0].length || board[row][col] != word.charAt(index))
                return false;
            if(index == word.length()-1 )
                return board[row][col] == word.charAt(index);
            boolean result = false;
            board[row][col] = '#';
            for(int[] dir:dirs) {
                int nrow = row+dir[0];
                int ncol = col+dir[1];
                if(nrow <0 || ncol<0 || nrow>=board.length || ncol >= board[0].length || board[nrow][ncol] != word.charAt(index+1))
                    continue;
                char temp=board[nrow][ncol];
                result |= backtrack(board, nrow, ncol, index+1, word);
                if(result)
                    return result;
                board[nrow][ncol] = temp;
            }
            return result;
        }
}
