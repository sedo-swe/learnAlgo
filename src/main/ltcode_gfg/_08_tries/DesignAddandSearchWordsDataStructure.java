package main.ltcode_gfg._08_tries;

import sun.text.normalizer.Trie;

/**
 *  211. Design Add and Search Words Data Structure (Medium)
 */
class TrieNode {
    char c;
    boolean isWord;
    TrieNode[] children;

    TrieNode(char c) {
        this.c = c;
        isWord = false;
        children = new TrieNode[26];
    }
}

public class DesignAddandSearchWordsDataStructure {
    TrieNode root;
    public DesignAddandSearchWordsDataStructure() {
        root = new TrieNode('\0');
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {

            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return searchHelper(word, this.root, 0);
    }

    private boolean searchHelper(String word, TrieNode cur, int index) {
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '.') {
                for (TrieNode temp : cur.children) {
                    if (temp != null && searchHelper(word, temp, i + 1))
                        return true;
                }
                return false;
            }

            if (cur.children[ch - 'a'] == null)
                return false;

            cur = cur.children[ch - 'a'];
        }
        return cur.isWord;
    }
}
