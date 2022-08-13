package Matrix;

import java.util.*;

public class DesignCanvas {
    char[][] matrix;
    HashMap<String, ArrayList<Character>> map  = new HashMap<>();
    HashMap<Character, int[]> charCellMap = new HashMap<>();
    DesignCanvas(int m, int n) {
        matrix = new char[m][n];
        for(char[] mat: matrix)
            Arrays.fill(mat, '.');
        printMatrix();
    }

    void draw(int startRow, int startCol, int length, int width, char value) {
       for(int i=startRow; i<startRow+length && i< matrix.length; i++) {
           for(int j=startCol;j<startCol+width && j<matrix[0].length;j++) {
               System.out.println(i + "-" + j);
               if(matrix[i][j] != '.')
               {
                   System.out.println(matrix[i][j]);
                   ArrayList<Character> curr = map.getOrDefault(i+ "-" + j, new ArrayList<>());
                   curr.add(matrix[i][j]);
                   map.put(i+"-"+j, curr);
               }
               matrix[i][j] = value;

           }
       }
        printMatrix();
    }

    void move(int startRow, int startCol, int newRow, int newCol, char value) {
        int i = startRow;
        while (i < newRow && value == matrix[i][startCol] ) {
           i++;
        }
        int length = i-startRow;
        int j = startCol;
        while (j < newCol && value == matrix[startRow][j] ) {
            j++;
        }
        int width = j-startCol;
        System.out.println(width + " " + length);
        for( i=startRow;i<length+startRow;i++) {
            for( j=startCol;j<width+startCol;j++) {
                if(map.containsKey(i+ "-" +'j')) {
                    matrix[i][j] = map.get(i + "-" + j).get(0);
                    ArrayList<Character> toUpdate =map.get(i + "-" + j);
                    toUpdate.remove(0);
                    if(toUpdate.size() == 0)
                        map.remove(i + "-" + j);
                    else map.put(i + "-" + j, toUpdate);
                }
                else {
                    matrix[i][j] = '.';
                }

            }
        }
        draw(newRow, newCol, length, width, value);
    }

    void printMatrix() {
        for(int i=0;i<matrix.length;i++) {
            System.out.println();
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
    }

public static void main(String[] args) {
   DesignCanvas canvas = new DesignCanvas(10,25);
   canvas.draw(0,0,4,6, 'a');
    canvas.draw(3,4,6,4, 'b');
    canvas.move(3,2, 5,7,'b');
}

}
