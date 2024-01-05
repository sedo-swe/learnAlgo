package main.ltcode.medium;

/**
 *  208. Implement Trie (Prefix Tree) (Medium, Solution)
 */
public class ImplementTrieSol {
    Node root;

    ImplementTrieSol() {
        root = new Node('\0'); // Create dummy node
    }

    public void insert(String word) {
        Node curr = root;
        for (char x : word.toCharArray()) {
            if (curr.children[x - 'a'] == null) {
                curr.children[x - 'a'] = new Node(x);
            }
            curr = curr.children[x - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Node res = this.getLast(word);
        return (res != null && res.isWord);
    }

    public Node getLast(String word) {
        Node curr = root;
        for (char x : word.toCharArray()) {
            if (curr.children[x - 'a'] == null) {
                return null;
            }
            curr = curr.children[x - 'a'];
        }
        return curr;
    }

    public boolean startsWith(String prefix) {
        Node res = this.getLast(prefix);
        if (res == null) {
            return false;
        }
        return true;
    }

    public void test() {
        ImplementTrieSol implementTrieSol = new ImplementTrieSol();
        implementTrieSol.insert("apple");
        System.out.println("Expected: true, Actual: " + implementTrieSol.search("apple"));
        System.out.println("Expected: false, Actual: " + implementTrieSol.search("app"));
        System.out.println("Expected: true, Actual: " + implementTrieSol.startsWith("app"));
        implementTrieSol.insert("app");
        System.out.println("Expected: true, Actual: " + implementTrieSol.search("app"));
        System.out.println("Expected: true, Actual: " + implementTrieSol.startsWith("app"));
        System.out.println("Expected: false, Actual: " + implementTrieSol.startsWith("apep"));
    }

    public void test2() {
        ImplementTrieSol implementTrieSol = new ImplementTrieSol();
        implementTrieSol.insert("hotdog");
        System.out.println("Expected: false, Actual: " + implementTrieSol.startsWith("dog"));
    }

    public static void main(String[] args) {
        ImplementTrieSol implementTrieSol = new ImplementTrieSol();
        implementTrieSol.test();
        System.out.println("= Test 2 =======");
        implementTrieSol.test2();
    }

    class Node {
        private char val;
        private boolean isWord;
        private Node[] children;

        Node(char val) {
            this.val = val;
            this.isWord = false;
            this.children = new Node[26];
        }
    }
}
