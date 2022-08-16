package Backtracking;
import java.util.*;

/*
You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter. Each row of blocks contains one less block than the row beneath it and is centered on top.

To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed. A triangular pattern consists of a single block stacked on top of two blocks. The patterns are given as a list of three-letter strings allowed, where the first two characters of a pattern represent the left and right bottom blocks respectively, and the third character is the top block.

For example, "ABC" represents a triangular pattern with a 'C' block stacked on top of an 'A' (left) and 'B' (right) block. Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
You start with a bottom row of blocks bottom, given as a single string, that you must use as the base of the pyramid.

Given bottom and allowed, return true if you can build the pyramid all the way to the top such that every triangular pattern in the pyramid is in allowed, or false otherwise.



Example 1:


Input: bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
Output: true
Explanation: The allowed triangular patterns are shown on the right.
Starting from the bottom (level 3), we can build "CE" on level 2 and then build "A" on level 1.
There are three triangular patterns in the pyramid, which are "BCC", "CDE", and "CEA". All are allowed.
Example 2:


Input: bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
Output: false
Explanation: The allowed triangular patterns are shown on the right.
Starting from the bottom (level 4), there are multiple ways to build level 3, but trying all the possibilites, you will get always stuck before building level 1.

 //bottom level is highest level, we start form bottom which needs to use  allowed 2 length prefixes
        //so always allowed length os 3 and we need to use this to build pyramid level by level, so
        //we build hashmap to store the 2 length substrings of allowed along with their suffixes as
       // 2 length strings  need to be used in bbuilding pyramids as always a pyramid is formed by placing a character on top of two blocks
        //now we check if in bottom every 2 length strings are part of allowed other wise return false , if we dont have a substring of length 2 that matches, then we cant build pyramidn with that bottom
            //then get all suffixes list of two length pyramids which will go on next level as for each level with n blocks , we will have next level with n-1 blocks
            //then we set bottom as strings we get from suffix which needs to form the new level
            //to get the suffixes for each prefix we are using and concat them for the next level bottom , we use backtracking

 */
public class PyramidTransitionMatrix {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        //bottom level is highest level, we start form bottom which needs to use  allowed 2 length prefixes
        //so always allowed length os 3 and we need to use this to build pyramid level by level, so
        //we build hashmap to store the 2 length substrings of allowed along with their suffixes as
        // 2 length strings  need to be used in bbuilding pyramids as always a pyramid is formed by placing a character on top of two blocks
        //now we check if in bottom every 2 length strings are part of allowed other wise return false , if we dont have a substring of length 2 that matches, then we cant build pyramidn with that bottom
        //then get all suffixes list of two length pyramids which will go on next level as for each level with n blocks , we will have next level with n-1 blocks
        //then we set bottom as strings we get from suffix which needs to form the new level
        //to get the suffixes for each prefix we are using and concat them for the next level bottom , we use backtracking


        HashMap<String, ArrayList<String>> prefixSuffixBlocks = new HashMap<>();
        for(String allow: allowed) {
            if(!prefixSuffixBlocks.containsKey(allow.substring(0,2)))
                prefixSuffixBlocks.put(allow.substring(0,2), new ArrayList<>());
            prefixSuffixBlocks.computeIfPresent(allow.substring(0,2), (key,value) -> { value.add(allow.substring(2)); return value;});
        }
        return helper(prefixSuffixBlocks, bottom, allowed);
    }

    public boolean helper(HashMap<String, ArrayList<String>> prefixSuffixBlocks, String bottom,List<String> allowed) {
        if(bottom.length() == 1)
            return true;
        for(int i=0;i<bottom.length()-1;i++) {
            if(!prefixSuffixBlocks.containsKey(bottom.substring(i, i+2)))
                return false;
        }
        List<String> ls = new ArrayList<>();
        ls = getList(prefixSuffixBlocks, bottom, 0, new StringBuilder(), ls); //get possible list of allow substrings
        for(String s: ls) {
            if(helper(prefixSuffixBlocks, s, allowed)) return true; //if we can use atleast one bottom from one of these to form pyramid, that is valid result
        }

        return false;
    }

    public List<String> getList(HashMap<String, ArrayList<String>> prefixSuffixBlocks, String bottom, int index, StringBuilder sb, List<String> result) {
        if(index >= bottom.length()-1) {
            result.add(sb.toString());
            return result;
        }
        for(String s: prefixSuffixBlocks.get(bottom.substring(index,index+2))) {
            sb.append(s);
            getList(prefixSuffixBlocks, bottom, index+1, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
        return result;
    }
}
