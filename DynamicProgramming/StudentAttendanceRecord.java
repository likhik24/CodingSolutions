package DynamicProgramming;

import java.sql.Time;
//An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
//
//        'A': Absent.
//        'L': Late.
//        'P': Present.
//        Any student is eligible for an attendance award if they meet both of the following criteria:
//
//        The student was absent ('A') for strictly fewer than 2 days total.
//        The student was never late ('L') for 3 or more consecutive days.
//        Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
//
//
//
//        Example 1:
//
//        Input: n = 2
//        Output: 8
//        Explanation: There are 8 records with length 2 that are eligible for an award:
//        "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//        Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
//        Example 2:
//
//        Input: n = 1
//        Output: 3
//        Example 3:
//
//        Input: n = 10101
//        Output: 183236316

public class StudentAttendanceRecord {
//     char[] chars = {'A','L', 'P'};
//     HashSet<String> combinations = new HashSet<>();
//     public int checkRecord(int n) {
//         HashSet<String> combinations = new HashSet<>();

//         constructCombi(n, new StringBuilder() ,0, combinations);
//         return combinations.size()% (10^9+7);
//     }

//     public boolean isValid(StringBuilder curr, char ch, int countOfA) {
//         if(curr.length() < 1)
//             return true;
//         if(curr.indexOf("LL") != -1 && ch == 'L')
//             return false;
//         if(countOfA >= 1 && ch == 'A')
//             return false;
//         return true;
//     }

//     public void constructCombi(int n, StringBuilder curr, int countOfA, HashSet<String> combinations) {
//         if(curr.length() == n) {
//             if(!combinations.contains(String.valueOf(curr)))
//                 combinations.add(String.valueOf(curr));
//             return;
//         }
//         for(int i=0;i<chars.length;i++) {
//             if(isValid(curr, chars[i], countOfA))
//             {
//                 curr.append(chars[i]);
//                 if(chars[i] == 'A')
//                     ++countOfA;
//                 constructCombi(n, curr, countOfA, combinations);
//                 if(curr.charAt(curr.length()-1) == 'A')
//                     --countOfA;
//                  curr.deleteCharAt(curr.length()-1);
//             }
//         }
//     }

        //above algorithm time complexity is O(3^N) and Space complexity O(n)
        //we can optimize it further by considering all length strings we can from with L and P as there can be only one A in the string
        // if we have number of combinations for Lp as f(n) , For combinations with A, it will be F(i-1)*F(n-i) where charAt index i is A;
        //ALSO THERE IS RECURRENCE RELATION BETWEEN F(N) AND F(N-1) AS number of combinations we can form with n-1 length strings *2 will be number of combinations with n ( the last one lteer can be L/p which makes it 2 COMBINATIONS*F(N-1) ,
    // NOW F(N-1) CAN BE FORMED WITH 4 N-3 COMBINATIONS (WHERE LAST TWO LETTERS CAN BE PP,LL,LP,PL ) SO 4*(N-3) OUT OF WHICH EACH n-3 CAN BE CONVERTED INTO N-4 + 2 ( LAST ONE CHAR CAN BE l/p) BUT  IF (N-4)
    // IS VALID HAVING L IN END and we take last char in n-3 as L and have last two chars in N-1 as LL ,this is invalid so we need to remove 1 possibility of (N-4) ,
    // it should be (N-4)+1 where last character is always P ( so the last 2 characters can be LL/PP/LP/PL)
        // so f(n) = 2*F(n-1) - F(n-4)

        // we can use dynamic programming memorization to compute and store results for optimization
        int M = 1000000007;
        public int checkRecord(int n) {
            int[] f = new int[n <= 5 ? 6 : n + 1];
            int sum=0;
            f[0] = 1;
            f[1] = 2;
            f[2] = 4;
            f[3] = 7; //LLL is not possible so 8-1
            for(int i=4;i<=n;i++) {
                f[i] = ((2 * f[i - 1]) % M + (M - f[i - 4])) % M;
            }
            sum += f[n];
            //NOW THIS F[N] doesnot have A, if it has one A
            // then sum += f[i-1]*f[n-i];
            for(int i=1;i<=n;i++) {
                sum += (f[i-1]*f[n-i])%M;
            }
            return sum%M;
        }
// Time complexity: O(N) // Space complexity: O(N)

}
