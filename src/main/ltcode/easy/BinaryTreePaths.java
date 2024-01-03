package main.ltcode.easy;

import main.ltcode.BinarySearch;
import main.ltcode.utils.TreeNode;

import java.util.*;

@FunctionalInterface
interface IntBinaryTreePaths {
    List<String> binaryTreePaths(TreeNode root);
}
public class BinaryTreePaths {
//    HashMap<TreeNode, String> visited = new HashMap<>();
    HashMap<TreeNode, TreeNode> parent = null;
    HashSet<TreeNode> leaves = null;

    /*
        How to find leaf?
            - no children

        1st idea.
            BFS
                record parent for each node
                add leaf into list
                retrieve path of leaf only
                return

            DFS
                traverse until reaching leaf
     */
    IntBinaryTreePaths intBinaryTreePaths = ((root) -> {
        parent = new HashMap<>();
        leaves = new HashSet<>();

        parent.put(root, null);
        dfsTraverse(root);
        Iterator<TreeNode> iterator = leaves.iterator();
        List<String> paths = new ArrayList<>();
        while (iterator.hasNext()) {
            TreeNode cur = iterator.next();
            StringBuffer sb = new StringBuffer();
            while (cur != null) {
                sb.insert(0, cur.val + "->");
                cur = parent.get(cur);
            }
            paths.add((sb.length() >= 3 ? sb.delete(sb.length() - 2, sb.length()).toString() : sb.toString()));
        }
        return paths;
    });

    private void dfsTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();

        if (node.left == null && node.right == null) {
            leaves.add(node);
            return;
        }

        if (node.left != null) {
            parent.put(node.left, node);
            dfsTraverse(node.left);
        }

        if (node.right != null) {
            parent.put(node.right, node);
            dfsTraverse(node.right);
        }
    }

    IntBinaryTreePaths intBinaryTreePathsSol = ((root) -> {
        List<String> ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        getPath(root, ans, path);
        return ans;
    });

    public void getPath(TreeNode root, List<String> paths, StringBuilder path) {
        if (root == null) {
            return;
        }
        int len = path.length();
        path.append(root.val);
        if (root.left == null && root.right == null) {
            paths.add(path.toString());
        }
        else {
            path.append("->");
            getPath(root.left, paths, path);
            getPath(root.right, paths, path);
        }
        path.setLength(len);
    }

    public void test(IntBinaryTreePaths func) {
        TreeNode t01_4 = new TreeNode(5);
        TreeNode t01_3 = new TreeNode(3);
        TreeNode t01_2 = new TreeNode(2, null, t01_4);
        TreeNode t01_1 = new TreeNode(1, t01_2, t01_3);
        System.out.println("Expected: [1->2->5,1->3], Actual: " + this.printListString(func.binaryTreePaths(t01_1)));

        TreeNode t02_1 = new TreeNode(1);
        System.out.println("Expected: [1], Actual: " + this.printListString(func.binaryTreePaths(t02_1)));
    }

    private String printListString(List<String> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (String s : list) {
            sb.append(s + ",");
        }
        sb.append("]");
        return (sb.length() >= 4 ? sb.delete(sb.length() - 2, sb.length() - 1).toString() : sb.toString());
    }

    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        binaryTreePaths.test(binaryTreePaths.intBinaryTreePaths);
        System.out.println("Sol");
        binaryTreePaths.test(binaryTreePaths.intBinaryTreePathsSol);
    }
}
