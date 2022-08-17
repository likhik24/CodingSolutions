package Strings;
import java.util.*;
public class TextJustification {

// class Solution {
//     public List<String> fullJustify(String[] words, int maxWidth) {
//         List<String> result = new ArrayList<>();
//         int nextIndex=0;
//         while(nextIndex < words.length)
//             nextIndex = findNextIndex( words, nextIndex, maxWidth, 0, result);
//         // String s = result.remove(result.size()-1);
//         // StringBuilder s1 = new StringBuilder();
//         // s.replaceAll(" ", "");
//         // while(s.length() < maxWidth)
//         return result;
//     }

//     public int findNextIndex(String[] words, int index, int maxWidth, int currLen, List<String> result) {
//         if(index >= words.length)
//             return index;
//         String word = words[index];
//         StringBuilder subres = new StringBuilder();
//         subres.append(word);
//            while(subres.length() < maxWidth && canPickNextIndex(++index, words, subres.length(), maxWidth)) {
//             subres.append(" ");
//             subres.append(words[index]);

//         }

//         if(subres.length() != maxWidth) {

//             ArrayList<Integer> list = new ArrayList<>();
//                     for(int i = 1; i < subres.length(); i++){
//                         if(subres.charAt(i) == ' ') list.add(i);
//                     }
//             int difference = maxWidth-subres.length();
//             if(list.size() == 0)
//             {
//                 while(subres.length() != maxWidth)
//                     subres.append(" ");
//             }
//             else {
//                 int x = difference/list.size();
//                 int y = difference % list.size() - list.size();
//                 for(int i=list.size()-1; i>=0 ;i--) {
//                     for(int j=0;j<x;j++) {

//                         subres.insert(list.get(i), " ");
//                     }
//                     y++;
//                     if(y>0)
//                         subres.insert(list.get(i), " ");
//                 }
//             }

//         }
//         result.add(subres.toString());
//         return index;

//     }

//     public boolean canPickNextIndex(int index, String[] words, int currLen, int maxWidth) {
//         if(index > words.length-1)
//             return false;
//         if(currLen + words[index].length()+1 <= maxWidth)
//             return true;
//         return false;
//     }
// }
//except lst lin all other lines need to be left justified ,so we add sace before every word until if its first word and when the word cant be inserted and line length != maxLength ,
// we need to insert spaces after every space, we get all indexes of space and split the length equally between them , if there is extra spaces to fill those should be on left most space, so we will do it when l=0
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(String str : words){
                int len = str.length();
                if(sb.length() == 0){
                    sb.append(str);
                }
                else if(sb.length() + 1 + len <= maxWidth){
                    sb.append(" ");
                    sb.append(str);
                }
                else{
                    if(sb.length() != maxWidth){
                        ArrayList<Integer> list = new ArrayList<>();
                        for(int i = 1; i < sb.length(); i++){
                            if(sb.charAt(i) == ' ') list.add(i);
                        }
                        int ln = list.size();
                        if(ln == 0){
                            while(sb.length() < maxWidth) sb.append(" ");
                        }
                        else{
                            int x = (maxWidth - sb.length()) / ln;
                            int y = (maxWidth - sb.length()) % ln - ln;
                            for(int i = ln -1; i >= 0; i--){
                                for(int j = 0; j < x; j++){
                                    sb.insert(list.get(i)," ");
                                }
                                y++;
                                if(y > 0) sb.insert(list.get(i)," ");
                            }
                        }

                    }
                    res.add(sb.toString());
                    sb = new StringBuilder(str);
                }
            }
            while(sb.length() < maxWidth) sb.append(" ");
            res.add(sb.toString());
            return res;
        }

}
