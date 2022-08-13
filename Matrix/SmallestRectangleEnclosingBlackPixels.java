package Matrix;

//        You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.
//
//        The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.
//
//        Given two integers x and y that represents the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
//
//        You must write an algorithm with less than O(mn) runtime complexity
//
//
//
//        Example 1:
//
//
//        Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
//        Output: 6
//        Example 2:
//
//        Input: image = [["1"]], x = 0, y = 0
//        Output: 1
public class SmallestRectangleEnclosingBlackPixels {
     private int top, bottom, left, right;
        public int minArea(char[][] image, int x, int y) {
            if(image.length == 0 || image[0].length == 0) return 0;
            top = bottom = x;
            left = right = y;
            dfs(image, x, y);
            return (right - left) * (bottom - top);
        }
        private void dfs(char[][] image, int x, int y){
            if(x < 0 || y < 0 || x >= image.length || y >= image[0].length ||
                    image[x][y] == '0')
                return;
            image[x][y] = '0'; // mark visited black pixel as white
            top = Math.min(top, x);
            bottom = Math.max(bottom, x + 1);
            left = Math.min(left, y);
            right = Math.max(right, y + 1);
            dfs(image, x + 1, y);
            dfs(image, x - 1, y);
            dfs(image, x, y - 1);
            dfs(image, x, y + 1);
        }

}
