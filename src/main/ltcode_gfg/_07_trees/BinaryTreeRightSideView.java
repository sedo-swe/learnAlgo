package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *  199. Binary Tree Right Side View (Medium)
 */

@FunctionalInterface
interface IntBinaryTreeRightSideView {
    List<Integer> rightSideView(TreeNode root);
}
public class BinaryTreeRightSideView {
    IntBinaryTreeRightSideView bfsTraverse = (root -> {
        List<Integer> rightNodeInLevel = new ArrayList<>();
        if (root == null) {
            return rightNodeInLevel;
        }
        // Queue -> Traverse, check size in while loop (Single queue)
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                if (i == 1)
                    rightNodeInLevel.add(cur.val);
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
        }
        return rightNodeInLevel;
    });

    IntBinaryTreeRightSideView dfsTraverse = (root -> {
        List<Integer> rightNodeInLevel = new ArrayList<>();
        List<List<Integer>> nodes = new ArrayList<>();
        if (root == null) {
            return rightNodeInLevel;
        }
        // Queue -> Traverse, check size in while loop (Single queue)
        dfs(root, 0, nodes);
        for (List<Integer> level : nodes) {
            rightNodeInLevel.add(level.get(level.size() - 1));
        }
        return rightNodeInLevel;
    });

    private void dfs(TreeNode node, int level, List<List<Integer>> nodes) {
        if (node == null) {
            return;
        }

        if (nodes.size() == level) {
            List<Integer> curLevel = new ArrayList<>();
            curLevel.add(node.val);
            nodes.add(curLevel);
        } else {
            List<Integer> curLevel = nodes.get(level);
            curLevel.add(node.val);
        }
        if (node.left != null)
            dfs(node.left, level + 1, nodes);
        if (node.right != null)
            dfs(node.right, level + 1, nodes);
    }

    IntBinaryTreeRightSideView dfsTraverseImproved = (root -> {
        List<Integer> list = new ArrayList<>();
        rec(root, list, 0);
        return list;
    });

    public void rec(TreeNode root, List<Integer> list, int depth) {
        if (root == null) return;

        if (list.size() == depth) list.add(root.val);

        if (root.right != null) rec(root.right, list, depth+1);

        if (root.left != null) rec(root.left, list, depth+1);
    }

    public void test(IntBinaryTreeRightSideView func) {
        TreeNode t01_05 = new TreeNode(5);
        TreeNode t01_04 = new TreeNode(4);
        TreeNode t01_03 = new TreeNode(3, null, t01_04);
        TreeNode t01_02 = new TreeNode(2, null, t01_05);
        TreeNode t01_01 = new TreeNode(1, t01_02, t01_03);
        System.out.println("Expected: [[1],[3],[4]], Actual: " + func.rightSideView(t01_01));

        TreeNode t02_03 = new TreeNode(3);
        TreeNode t02_01 = new TreeNode(1, null, t02_03);
        System.out.println("Expected: [[1, 3]], Actual: " + func.rightSideView(t02_01));

        System.out.println("Expected: [], Actual: " + func.rightSideView(null));
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView b = new BinaryTreeRightSideView();
        b.test(b.bfsTraverse);
        b.test(b.dfsTraverse);
        b.test(b.dfsTraverseImproved);

    }
}
