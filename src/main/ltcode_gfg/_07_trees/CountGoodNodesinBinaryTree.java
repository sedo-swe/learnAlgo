package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

/**
 *  1448. Count Good Nodes in Binary Tree (Medium)
 *
 */

public class CountGoodNodesinBinaryTree {
    int num = 0;
    public int goodNodes(TreeNode root) {
        dfsHelper(root, Integer.MIN_VALUE);
        return num;
    }

    private void dfsHelper(TreeNode node, int maxSoFar) {
        if (node == null)
            return;

        if (maxSoFar <= node.val) {
            num++;
            maxSoFar = node.val;
        }

        if (node.left != null)
            dfsHelper(node.left, maxSoFar);
        if (node.right != null)
            dfsHelper(node.right, maxSoFar);
        return;
    }

    public static void main(String[] args) {
        CountGoodNodesinBinaryTree cgn = new CountGoodNodesinBinaryTree();
        TreeNode t01_06 = new TreeNode(5);
        TreeNode t01_05 = new TreeNode(1);
        TreeNode t01_04 = new TreeNode(3);
        TreeNode t01_03 = new TreeNode(4, t01_05, t01_06);
        TreeNode t01_02 = new TreeNode(1, t01_04, null);
        TreeNode t01_01 = new TreeNode(3, t01_02, t01_03);
        System.out.println("Expected: 4, Actual: " + cgn.goodNodes(t01_01));
    }
}
