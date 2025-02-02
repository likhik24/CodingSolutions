package ArrayList;

import java.util.*;
//You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
//
//        To complete the ith replacement operation:
//
//        Check if the substring sources[i] occurs at index indices[i] in the original string s.
//        If it does not occur, do nothing.
//        Otherwise if it does occur, replace that substring with targets[i].
//        For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
//
//        All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
//
//        For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
//        Return the resulting string after performing all replacement operations on s.
//
//        A substring is a contiguous sequence of characters in a string.
//
//
//
//        Example 1:
//
//        Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
//        Output: "eeebffff"
public class FindAndReplaceInString {
     class Point {
            int index;
            String source;
            String target;
            Point(int index, String source, String target) {
                this.index = index;
                this.source = source;
                this.target = target;
            }


        }


        public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
            ArrayList<Point> res = new ArrayList<Point>();
            for(int i=0;i<indices.length;i++) {
                if(indices[i]+sources[i].length() > s.length())
                    continue;
                if(s.substring(indices[i], indices[i]+sources[i].length()).equals(sources[i])) {
                    res.add(new Point(indices[i],sources[i],  targets[i]));
                }
            }
            Collections.sort(res, new Comparator<Point>() {
                public int compare(Point a, Point b) {
                    return a.index-b.index;
                }});


            StringBuilder result = new StringBuilder();
            int start = 0;

            for(Point r : res) {
                if(start < r.index) {
                    result.append(s.substring(start, r.index));
                result.append(r.target);
                start = r.index+r.source.length();
                } else return 
            }

            if(s.length() > start) {
                result.append(s.substring(start));
            }
            return result.toString();
        }

}
