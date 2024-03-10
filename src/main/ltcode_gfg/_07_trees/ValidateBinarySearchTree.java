package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

/**
 *  98. Validate Binary Search Tree (Medium)
 */
@FunctionalInterface
interface IntValidateBinarySearchTree {
    boolean isValidBST(TreeNode root);
}
public class ValidateBinarySearchTree {
    IntValidateBinarySearchTree sol = (root -> {
        return dfs1(root, null, null);
    });

    private boolean dfs1(TreeNode node, Integer min, Integer max) {
        if (node == null)
            return true;
        if ((min != null && node.val <= min || max != null && node.val <= max))
            return false;
        return dfs1(node.left, min, node.val) && dfs1(node.right, node.val, max);
    }

    public void test(IntValidateBinarySearchTree func) {
        TreeNode t01_03 = new TreeNode(3);
        TreeNode t01_02 = new TreeNode(2);
        TreeNode t01_01 = new TreeNode(1, t01_02, t01_03);
        System.out.println(func.isValidBST(t01_01));

    }

    public static void main(String[] args) {
        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        v.test(v.sol);
    }

    /*
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return false;
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        if (root.left != null && root.right != null) {
            return (dfs(root.left) < root.val && root.val < dfs(root.right));
        }
        else if (root.right != null)
            return root.val < dfs(root.right);
        else if (root.left != null)
            return dfs(root.left) < root.val;
        else
            return true;
    }

    private int dfs(TreeNode node) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        if (node.left != null && node.right != null) {
            left = dfs(node.left);
            right = dfs(node.right);
            if (!(left < node.val && node.val < right))
                return Integer.MIN_VALUE;
            else
                return left;
        } else if (node.right != null) {
            right = dfs(node.right);
            if (!(node.val < right))
                return Integer.MAX_VALUE;
            else
                return node.val;
        } else if (node.left != null) {
            left = dfs(node.left);
            if (!(node.val < left))
                return Integer.MIN_VALUE;
            else
                return node.val;
        } else {
            return node.val;
        }
    }
     */

    /*
    class Solution {
    public boolean isValidBST(TreeNode root) {
        return bfs(root);
    }

    private boolean bfs(TreeNode node) {
        if (node == null)
            return true;

        if (node.left != null) {
            if (node.left.val >= node.val)
                return false;
        }

        if (node.right != null) {
            if (node.right.val <= node.val)
                return false;
        }

        return bfs(node.left) && bfs(node.right);
    }
}
     */

}
