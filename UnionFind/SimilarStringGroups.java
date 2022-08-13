package UnionFind;

import java.util.stream.*;
//Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
//
//        For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
//
//        Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
//
//        We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
//
//
//
//        Example 1:
//
//        Input: strs = ["tars","rats","arts","star"]
//        Output: 2
//        Example 2:
//
//        Input: strs = ["omv","ovm"]
//        Output: 1
public class SimilarStringGroups {

        int groups = 0;
        //n-2 letters should be in same position as atleast one string in group
        class UnionFind {

            int[] parent;
            int[] rank;
            int length;
            public UnionFind(int n) {
                length=n;
                parent = IntStream.range(0,n).toArray();
                rank = new int[n];
            }

            public void union(int x, int y) {
                int parentx = find(x);
                int parenty = find(y);
                if(parentx == parenty)
                    return;
                if(rank[parentx] > rank[parenty])
                    parent[parenty] = parentx;
                if(rank[parenty] > rank[parentx])
                    parent[parentx] = parenty;
                if(rank[parenty] == rank[parentx]) {
                    parent[parentx] = parenty;
                    rank[parenty]++;
                }
            }

            public int find(int x) {
                if(parent[x] != x) return find(parent[x]);
                return parent[x];
            }

            public int getNumGroups() {
                return (int)IntStream.range(0, parent.length).filter(i -> i == parent[i]).count();
            }


        }
        private boolean isSimilar(String str1, String str2) {
            long count = IntStream.range(0, str1.length()).filter(i -> str1.charAt(i) != str2.charAt(i)).count();
            return count == 0 || count == 2;
        }

        public int numSimilarGroups(String[] strs) {
            UnionFind un = new UnionFind(strs.length);
            IntStream.range(0, un.length).forEach(i -> IntStream.range(i+1, un.length).filter(j -> isSimilar(strs[i], strs[j])).forEach(j -> un.union(i, j)));
            long numGroups = IntStream.range(0, un.length).filter(i -> un.parent[i] == i).count();
            return (int)numGroups;

        }

}
