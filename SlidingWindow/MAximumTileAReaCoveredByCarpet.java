package SlidingWindow;
/*
You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.

You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.

Return the maximum number of white tiles that can be covered by the carpet.



Example 1:


Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
Output: 9
Explanation: Place the carpet starting on tile 10.
It covers 9 white tiles, so we return 9.
Note that there may be other places where the carpet covers 9 white tiles.
It can be shown that the carpet cannot cover more than 9 white tiles.
Example 2:


Input: tiles = [[10,11],[1,1]], carpetLen = 2
Output: 2
Explanation: Place the carpet starting on tile 10.
It covers 2 white tiles, so we return 2.
 */
import java.util.*;

public class MAximumTileAReaCoveredByCarpet {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        // sort tiles by their left index
        //     if(any tile len >= carpetlen)
        //         return tilelen

        int left=0;
        int right=0;
        int maxLen = Integer.MIN_VALUE;
        int cover = 0;
        //we keep a sliding window to move left, right pointers
        //we keep two pointers left and right to move right pointer when we can pick the entire right tile for carpet ,
        //and add it to covered area, when we cant add right tile to carpet area , then we can check until how much distance of right tile partially can we add to our cover
        // and pick that much distance to cover and update our maxLen and remove left carpet area from our covered zone as it cant cover the right tile
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0])); //{1,5},{10,11},{12,18} ,
        while(left<tiles.length && right < tiles.length & left<=right) {
            if(tiles[left][0]+carpetLen>tiles[right][1]) {
                cover += tiles[right][1]-tiles[right][0]+1;
                maxLen = Math.max(maxLen, cover);
                right++;
            }
            else {
                int midDist = tiles[left][0]+carpetLen-1;
                if(midDist >= tiles[right][0] && midDist <= tiles[right][1]) {
                    maxLen = Math.max(maxLen,cover+midDist-tiles[right][0]+1);

                }
                cover -= tiles[left][1]-tiles[left][0]+1;
                left++;
            }

        }
        return maxLen;

    }
}
