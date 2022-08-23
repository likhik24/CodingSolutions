package DynamicProgramming;
/*
Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:

Every song is played at least once.
A song can only be played again only if k other songs have been played.
Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 109 + 7.



Example 1:

Input: n = 3, goal = 3, k = 1
Output: 6
Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].
Example 2:

Input: n = 2, goal = 3, k = 0
Output: 6
Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].
Example 3:

Input: n = 2, goal = 3, k = 1
Output: 2
Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2]
 */
public class NumberOfMusicPlayLists {
//     Approach 1: Dynamic Programming
// Intuition

// Let dp[i][j] be the number of playlists of length i that have exactly j unique songs. We want dp[L][N], and it seems likely we can develop a recurrence for dp.

// Algorithm

// Consider dp[i][j]. Last song, we either played a song for the first time or we didn't. If we did, then we had dp[i - 1][j - 1] * (N - j + 1) ways to choose it. If we didn't,
// then we repeated a previous song in dp[i-1][j] * max(j-K, 0) ways (j of them, except the last K ones played are banned.)

        //we have goal positions to fill, we have n songs which need to be played atleast once and k is gap between current song being used again
        //for first n positions in k we can arrange them in 6 different ways
        // for n+1 to goal positions we take k restriction into account we cant use song at n position until n+k+1 position
        //     we need to get goal length  songs that can be filled with n songs
        //     if at any length we have not used this song then no.of.ways = (N-j+1) where j is unique length of songs
        //         , N is length of playlist
        //         if we have used this song then we have (j-k) ways to pick osngs as we cant use song for k positions
        public int numMusicPlaylists(int N, int L, int K) {
            int MOD = 1_000_000_007;

            long[][] dp = new long[L+1][N+1];
            dp[0][0] = 1;
            for (int i = 1; i <= L; ++i)
                for (int j = 1; j <= N; ++j) {
                    dp[i][j] += dp[i-1][j-1] * (N-j+1);
                    dp[i][j] += dp[i-1][j] * Math.max(j-K, 0);
                    dp[i][j] %= MOD;
                }

            return (int) dp[L][N];
        }
//we have n unique songs, l lenngth goal , if we take this song newly then it mes we have n-j+1 ways to arrange it where j is length of goal ,
// if we are taking already used song, in j length we can place it after k positions of 1st occurence so we can place at j-k positions dp[i][j] += dp[i-1][j]*Math.max(j-k,0), dp[i][j] = dp[i-1][j-1]*(N-j+1)

}
