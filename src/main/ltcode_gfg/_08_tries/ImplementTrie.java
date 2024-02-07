package main.ltcode_gfg._08_tries;

import java.util.HashSet;
import java.util.stream.Stream;

/**
 *  208. Implement Trie (Prefix Tree) (Medium)
 */
public class ImplementTrie {
    HashSet<String> storage = null;
    public ImplementTrie() {
        storage = new HashSet<>();
    }

    public void insert(String word) {
        storage.add(word);
    }

    public boolean search(String word) {
        return storage.contains(word);
    }

    public boolean startsWith(String prefix) {
        Stream<String> result = storage.stream().filter((s) -> s.startsWith(prefix));
        return result.count() > 0 ? true : false;
    }

    public void test() {
        ImplementTrie implementTrie = new ImplementTrie();
        implementTrie.insert("apple");
        System.out.println("Expected: true, Actual: " + implementTrie.search("apple"));
        System.out.println("Expected: false, Actual: " + implementTrie.search("app"));
        System.out.println("Expected: true, Actual: " + implementTrie.startsWith("app"));
        implementTrie.insert("app");
        System.out.println("Expected: true, Actual: " + implementTrie.search("app"));
        System.out.println("Expected: true, Actual: " + implementTrie.startsWith("app"));
        System.out.println("Expected: false, Actual: " + implementTrie.startsWith("ap1p"));
    }

    public void test2() {
        ImplementTrie implementTrie = new ImplementTrie();
        implementTrie.insert("hotdog");
        System.out.println("Expected: false, Actual: " + implementTrie.startsWith("dog"));
    }

    public static void main(String[] args) {
        ImplementTrie i = new ImplementTrie();
        System.out.println("\nTest 1");
        i.test();
        System.out.println("\nTest 2");
        i.test2();
    }
}
