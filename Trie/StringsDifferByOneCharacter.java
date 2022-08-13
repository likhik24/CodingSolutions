package Trie;

import java.util.*;

public class StringsDifferByOneCharacter {
        class Trie {
            TrieNode root = new TrieNode();

            public void addWord(String word) {
                TrieNode node = root;
                for(char ch:word.toCharArray()) {
                    if(!node.children.containsKey(ch))
                        node.children.put(ch, new TrieNode());
                    node = node.children.get(ch);
                    node.count += 1;
                }
                node.isWord = true;
            }

            private boolean containsKeys(TrieNode node, String word) {
                for(char ch: word.toCharArray()) {
                    if(!node.children.containsKey(ch))
                        return false;
                    node = node.children.get(ch);
                }
                return true;
            }

            public boolean containsCharByDiffOne(String word1) {
                TrieNode node = root;
                for(int i=0;i<word1.length();i++) {
                    char ch = word1.charAt(i);
                    HashMap<Character, TrieNode> map = node.children;


                    for(Map.Entry<Character, TrieNode> entry: map.entrySet()) {
                        if(entry.getKey() == ch)
                            continue;
                        else {
                            if(containsKeys(entry.getValue(), word1.substring(i+1)))
                                return true;
                        }

                    }
                    node = node.children.get(ch);
                }

                return false;
            }
        }
        class TrieNode {
            HashMap<Character, TrieNode> children;
            boolean isWord=false;
            int count=0;

            TrieNode() {
                children = new HashMap<>();
            }

        }

        public boolean differByOne(String[] words) {
            Trie root = new Trie();
            for(String word: words) {
                root.addWord(word);
            }
            for(String word:words) {
                if(root.containsCharByDiffOne(word))
                    return true;
            }
            return false;
        }
}
