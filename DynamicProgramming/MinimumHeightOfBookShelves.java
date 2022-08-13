package DynamicProgramming;
import java.util.*;
/*
You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book. You are also given an integer shelfWidth.

We want to place these books in order onto bookcase shelves that have a total width shelfWidth.

We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth, then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down. We repeat this process until there are no more books to place.

Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.

For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.



Example 1:


Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.
Example 2:

Input: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
Output: 4
 */
public class MinimumHeightOfBookShelves {
    //choices are we can insert current book into new shelf  which will increase height of bookcase to currheight+ (height of new book)
    //or insert current book into existing shelf and incrementing current shelf height if height of our book is greater than height of our shelf
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int[][] dp = new int[books.length+1][shelfWidth+1];
        for(int[] arr:dp)
            Arrays.fill(arr, Integer.MAX_VALUE);
        return minHeight(books,0, 0, shelfWidth, shelfWidth, dp);
        //book to current shelf which increments shelf height

    }
    //   1,1, 3
    public int minHeight(int[][] books, int index, int height, int shelfWidth, int remWidth, int[][] dp) {
        if(index >= books.length)
            return height;
        if(dp[index][remWidth] != Integer.MAX_VALUE)
            return dp[index][remWidth];
        int height1 = Integer.MAX_VALUE;
        if(remWidth >= books[index][0])  //if we placed book in current shelf and move to next book incrementing current shelf height if height of our book is greater than height of our shelf
            height1 = minHeight(books, index+1, Math.max(height, books[index][1]),shelfWidth, remWidth-books[index][0], dp );

        int height2 = height + minHeight(books, index+1, books[index][1], shelfWidth, shelfWidth-books[index][0], dp); //if we place book in next shelf which increments height of bookcase and move to next book
        return dp[index][remWidth]=Math.min(height1, height2);
    }
}
