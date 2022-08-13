package Strings;

import java.util.Arrays;

import java.util.regex.Pattern;

public class WildCardMatching {

    //Using Backtracking worst case  O(N^2) -> if we valdate * as empty and go all way to the end of value string to find a mismatch///we come back to index of * and valdiate it as a character and proceed further
//    The trick is to keep track of the index of a wildcard character each time you come across one, and similarly keep track of the current index in S whenever a wildcard index is found in P.
//    Then continue iterating, and if we get to two mismatched characters, "backtrack" to the previous wildcard index and continue.
//    Once we iterate over every character in S, return true if either pIdx == p.size() (i.e. we searched through every character in P), or if pIdx < p.size() and every remaining character in P is a wildcard. The return statement above accounts for both cases.
//    public boolean isMatch(String v, String pattern) {
//        int s=0, p=0, backtrackSIndex = -1,
//        wildCardPIndex = -1;
//
//        while(s<v.length()){
//            if(p<pattern.length() && (pattern.charAt(p) == '?' || v.charAt(s) == pattern.charAt(p))){
//                s++;
//                p++;
//                continue;
//            }
//            if(p<pattern.length() && pattern.charAt(p) == '*')      {
//                backtrackSIndex = s; //wes tore s index to backtrack to s+1 if in future any mismatch happens
//                wildCardPIndex = p;  //we will store wild character index where p = '*' to come back and start if in future something is not matching,
//                ++p; //incrementing p as we can assume p as empty character as its wild and compare s and p+1
//                 continue;
//            }
//            if(wildCardPIndex != -1){ // we have a mismatch and also index of wild character which we can go back to
//                p = wildCardPIndex ++; // we will start iteration from wildcardindex and increment wildcardindex for future use ( wildcardindex can also be used to match multiple characters i n s)
//                s = ++backtrackSIndex; // we will increment s to 1+backtracindex by (matching wildcardindex and character at backtrackSIndex)
//                continue;
//            }
//            return false;
//
//        }
//        // System.out.println("s = "+s+", v.length() = "+v.length()+", p = "+p+", pattern.length() "+pattern.length());
//        while(p<pattern.length() && pattern.charAt(p) == '*') p++;
//        return s== v.length() && p==pattern.length();
//    }
    int sindex= 0;
    public boolean isMatchUtil(String v, String pattern) {
        //System.out.println(pattern);
        int index = v.indexOf(pattern);
        if(index == -1)
            return false;
        sindex += index+pattern.length();
       // System.out.println(sindex);
      return true;
    }
    public boolean isMatch(String v, String pattern) {
        //time complexity will be number of * in array

        sindex= 0;
        if(pattern.indexOf("*") == -1)
            return v.equals(pattern);
        if((pattern.isEmpty() && !v.isEmpty()) || ((!pattern.isEmpty() && pattern.contains("[a-zA-Z]+")) && v.isEmpty()))
            return false;
        String str2 = "\\*";
        String patternsSplitByWildChar[] = pattern.split(str2);


        for(int i=0;i<patternsSplitByWildChar.length;i++) {
            if(patternsSplitByWildChar[i] == "" || !patternsSplitByWildChar[i].matches("[a-zA-Z]+") )
                continue;

            if(sindex >= v.length())
            {
                for(int k=i;k<patternsSplitByWildChar.length;k++) {
                    if(!patternsSplitByWildChar[k].isEmpty())
                        return false;
                }
                return true;
            }
           if(!isMatchUtil(v.substring(sindex), patternsSplitByWildChar[i])) return false;

        }
        return true;
    }



}
