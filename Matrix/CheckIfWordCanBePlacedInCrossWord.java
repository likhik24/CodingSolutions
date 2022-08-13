package Matrix;
/*You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), ' ' to represent any empty cells, and '#' to represent any blocked cells.

        A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top) in the board if:

        It does not occupy a cell containing the character '#'.
        The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
        There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
        There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
        Given a string word, return true if word can be placed in board, or false otherwise.



        Example 1:


        Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
        Output: true
        Explanation: The word "abc" can be placed as shown above (top to bottom).
        Example 2:


        Input: board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
        Output: false
        Explanation: It is impossible to place the word because there will always be a space/letter above or below it.
*/
public class CheckIfWordCanBePlacedInCrossWord {
          //if there is a row or column with more than m-word.length, n-word.length continuous empty cells // vomit it
//         //if blocked cells+word.length > m, n in a row/column vomit it
//         // start with
//         for row:rows {
//             emptyOrlettercells = 0
//             for col:cols {

//                 if(board[row][col] != '#')
//                     emptyOrletercels ++;
//                 else {
//                     if(word.len == emptyorlettercells)
//                         boolean canplace = checkifrowcanbeused(row, col, word);
//                     if(canplace)return true;
//                     emptyOrletercels = 0;
//                 }
//             }
//             if(word.len == emptyorlettercells)
//                  boolean canplace = checkifrowcanbeused(row,n-1 (board[0].length-1) , word);
//                 if(canplace)return true;

//         }

//         //same above logic for columns
//     }

//         checkifrowcanbeused()
//             take twopointers from forward from backward
//             to see if u can insert word from left to right and right to left
//                 for int i=0 to word.length;
//                    if(board[row][col-i] != '') {
//                        if(board[row][col-i] = word.charAt(word.length()-1-i))
//                            from forward = false;
//                        if(board[row][col-i] = word.charAt(i))
//                            frombackward= false;
//                    }
//     }

    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int len = word.length();
        for(int i = 0; i < m; i++){
            int preEmptyOrLetterCells = 0;
            for(int j = 0; j < n; j++){
                if(board[i][j] != '#'){
                    preEmptyOrLetterCells++;
                }else{
                    if(preEmptyOrLetterCells == len){
                        if(checkRow(board, word, i, j - 1)){
                            return true;
                        }
                    }
                    preEmptyOrLetterCells = 0;
                }
            }
            if(preEmptyOrLetterCells == len){
                if(checkRow(board, word, i, n - 1)){
                    return true;
                }
            }
        }
        for(int j = 0; j < n; j++){
            int preEmptyOrLetterCells = 0;
            for(int i = 0; i < m; i++){
                if(board[i][j] != '#'){
                    preEmptyOrLetterCells++;
                }else{
                    if(preEmptyOrLetterCells == len){
                        if(checkCol(board, word, i - 1, j)){
                            return true;
                        }
                    }
                    preEmptyOrLetterCells = 0;
                }
            }
            if(preEmptyOrLetterCells == len){
                if(checkCol(board, word, m - 1, j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRow(char[][] board, String word, int row, int endIndex){
        boolean findForward = true;
        boolean findBackward = true;
        int len = word.length();
        for(int incre = 0; incre < len; incre++){
            if(board[row][endIndex - incre] != ' '){
                if(board[row][endIndex - incre] != word.charAt(len - 1 - incre)){
                    findForward = false;
                }
                if(board[row][endIndex - incre] != word.charAt(incre)){
                    findBackward = false;
                }
                if(!findForward && !findBackward){
                    break;
                }
            }
        }
        if(findForward || findBackward){
            return true;
        }
        return false;
    }

    private boolean checkCol(char[][] board, String word, int endIndex, int col){
        boolean findForward = true;
        boolean findBackward = true;
        int len = word.length();
        for(int incre = 0; incre < len; incre++){
            if(board[endIndex - incre][col] != ' '){
                if(board[endIndex - incre][col] != word.charAt(len - 1 - incre)){
                    findForward = false;
                }
                if(board[endIndex - incre][col] != word.charAt(incre)){
                    findBackward = false;
                }
                if(!findForward && !findBackward){
                    break;
                }
            }
        }
        if(findForward || findBackward){
            return true;
        }
        return false;
    }
}
