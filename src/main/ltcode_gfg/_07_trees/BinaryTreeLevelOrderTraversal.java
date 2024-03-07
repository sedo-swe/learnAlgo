package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *  102. Binary Tree Level Order Traversal (Medium)
 */

@FunctionalInterface
interface IntBinaryTreeLevelOrderTraversal {
    List<List<Integer>> levelOrder(TreeNode root);
}
public class BinaryTreeLevelOrderTraversal {
    /*
        1st idea.
        Use level order traversal with single queue & initialize with size() in for loop
        ==> Passed. time: O (n), space: O (n)
     */
    IntBinaryTreeLevelOrderTraversal bfsTraverse = (root -> {
        List<List<Integer>> nodesInLevelOrder = new ArrayList<>();
        if (root == null) {
            return nodesInLevelOrder;
        }
        // Queue -> Traverse, check size in while loop (Single queue)
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                curLevel.add(cur.val);
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
            nodesInLevelOrder.add(curLevel);
        }
        return nodesInLevelOrder;
    });

    IntBinaryTreeLevelOrderTraversal dfsTraverse = (root -> {
        List<List<Integer>> nodesInLevelOrder = new ArrayList<>();
        if (root == null) {
            return nodesInLevelOrder;
        }
        // Queue -> Traverse, check size in while loop (Single queue)
        dfs(root, 0, nodesInLevelOrder);
        return nodesInLevelOrder;
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

    public void test(IntBinaryTreeLevelOrderTraversal func) {
        TreeNode t01_05 = new TreeNode(7);
        TreeNode t01_04 = new TreeNode(15);
        TreeNode t01_03 = new TreeNode(20, t01_04, t01_05);
        TreeNode t01_02 = new TreeNode(9);
        TreeNode t01_01 = new TreeNode(3, t01_02, t01_03);
        System.out.println("Expected: [[3],[9,20],[15,7]], Actual: " + func.levelOrder(t01_01));

        TreeNode t02_01 = new TreeNode(1);
        System.out.println("Expected: [[1]], Actual: " + func.levelOrder(t02_01));

        System.out.println("Expected: [], Actual: " + func.levelOrder(null));
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal b = new BinaryTreeLevelOrderTraversal();
        b.test(b.bfsTraverse);
        b.test(b.dfsTraverse);

        List<List<Integer>> n = new ArrayList<>();
        System.out.println(n.size());
    }
}
