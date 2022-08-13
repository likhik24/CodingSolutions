package TwoPointers;
//In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
//
//
//
//        Example 1:
//
//        Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
//        Output: true
//        Explanation: We can transform start to end following these steps:
//        RXXLRXRXL ->
//        XRXLRXRXL ->
//        XRLXRXRXL ->
//        XRLXXRRXL ->
//        XRLXXRRLX
//        Example 2:
//
//        Input: start = "X", end = "L"
//        Output: false
//IF WE OBSERVE XL can be LX , RX CAN BE XR that means x can move right, L can move left, we can basically check if we remove Xs the both strings are equal else return false, if equal then we need to check if l pointer in second string is less than l pointer in first string , r pointer in second string is greater than r pointer in first string
public class SwapLRInString {
    class Solution {
        public boolean canTransform(String start, String end) {
            int startL = 0, startR = 0;
            int endL = 0, endR = 0;
            String stLR = "", edLR = "";
            for(int i = 0; i < start.length(); i++) {
                if(start.charAt(i) != 'X') {
                    if(start.charAt(i) == 'L') {
                        startL++;
                    } else{
                        startR++;
                    }
                    stLR+= start.charAt(i);
                }
                if(end.charAt(i) != 'X') {
                    if(end.charAt(i) == 'L') {
                        endL++;
                    } else{
                        endR++;
                    }
                    edLR += end.charAt(i);
                }

                if(startL > endL || startR < endR) //Check conditions at each instant
                    return false;
            }

            if(startL != endL || startR != endR || !stLR.equals(edLR)) //check their final count and positions
                return false;

            return true;
        }

//     public boolean canTransform(String start, String end) {

//         int startL = 0, startR = 0;
//         int endL = 0, endR = 0;
//         String stLR = "", edLR = "";
//         for(int i = 0; i < start.length(); i++) {
//             if(start.charAt(i) != 'X') {
//                 if(start.charAt(i) == 'L') {
//                     startL++;
//                 } else{
//                     startR++;
//                 }
//                 stLR+= start.charAt(i);
//             }
//             if(end.charAt(i) != 'X') {
//                 if(end.charAt(i) == 'L') {
//                     endL++;
//                 } else{
//                     endR++;
//                 }
//                 edLR += end.charAt(i);
//             }

//             if(startL > endL || startR < endR) //Check conditions at each instant
//                 return false;
//         }

//         if(startL != endL || startR != endR || !stLR.equals(edLR)) //check their final count and positions
//             return false;

//         return true;
//     }

    }
}
