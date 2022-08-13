package Graph;
import java.util.*;
/*
There are m boys and n girls in a class attending an upcoming party.

        You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1. If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party. A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.

        Return the maximum possible number of accepted invitations.



        Example 1:

        Input: grid = [[1,1,1],
        [1,0,1],
        [0,0,1]]
        Output: 3
        Explanation: The invitations are sent as follows:
        - The 1st boy invites the 2nd girl.
        - The 2nd boy invites the 1st girl.
        - The 3rd boy invites the 3rd girl.
        Example 2:

        Input: grid = [[1,0,1,0],
        [1,0,0,0],
        [0,0,1,0],
        [1,1,1,0]]
        Output: 3
        Explanation: The invitations are sent as follows:
        -The 1st boy invites the 3rd girl.
        -The 2nd boy invites the 1st girl.
        -The 3rd boy invites no one.
        -The 4th boy invites the 2nd girl.


        Constraints:

        grid.length == m
        grid[i].length == n
        1 <= m, n <= 200
        grid[i][j] is either 0 or 1.
*/


public class MaximumNumberOfInvitations {
    public int maximumInvitations(int[][] grid) {
        //here they are 2 groups boys, girls which have edges from one boy to multiple girls and each boy can only invite one girl
        //we need to find maximum bipartite matching between two groups
        // create array to store fixed girls invites of which boy is giving the girl invite
        //then for each boy in the grid , we will call dfs to find (if this boy can give a gift to a girl who is already fixed with another boy or who is not fixed with another boy) ( in case if this boy have to give invite to fixed girl , we will check if that boy of fixed girl can invite another girl who is not fixed) it returns true that means this boy can gift to a girl who has not fixed yet/fixed with naother boy but that boy can find replacement of another girl
        // we increment invitations if dfs returns true;

        int[] girlsInvitedBy = new int[grid[0].length];
        Arrays.fill(girlsInvitedBy, -1);
        int count = 0;

        for(int boy=0;boy<grid.length;boy++) {
            if(dfs(boy, girlsInvitedBy, grid, new HashSet<>()))
                count++;
        }
        return count;
    }

    public boolean dfs(int boy, int[] girlsInvitedBy, int[][] grid, HashSet<Integer> girlsAlreadyInvited) {
        for(int girl=0;girl<grid[0].length;girl++) {
            if(grid[boy][girl] == 1 && !girlsAlreadyInvited.contains(girl)) {
                girlsAlreadyInvited.add(girl);
                if(girlsInvitedBy[girl] == -1 || dfs(girlsInvitedBy[girl], girlsInvitedBy, grid, girlsAlreadyInvited)) {
                    girlsInvitedBy[girl] = boy;

                    return true;
                }
            }
        }
        return false;
    }


}
