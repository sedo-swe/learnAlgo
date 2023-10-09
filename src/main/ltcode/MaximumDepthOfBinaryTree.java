package main.ltcode;

import main.ltcode.utils.TreeNode;

@FunctionalInterface
interface IntMaximumDepthOfBinaryTree {
    int maxDepth(TreeNode root);
}

/**
 *  104. Maximum Depth of Binary Tree
 */
public class MaximumDepthOfBinaryTree {

    /*
        First idea
            - Use recursive, return current depth, compare both with Math.max()
            ==> Accepted: 0 ms
     */
    IntMaximumDepthOfBinaryTree intMaximumDepthOfBinaryTree1st = ((root) -> {
        if (root == null) { return 0; }

        if (root.left == null && root.right == null) { return 1; }

        return findDepth(root);
    });

    private int findDepth(TreeNode node) {
        if (node == null) { return 0; }

        return Math.max(this.findDepth(node.left), this.findDepth(node.right)) + 1;
    }

    public void test(IntMaximumDepthOfBinaryTree func) {

        TreeNode treeNode1_1 = new TreeNode(15);
        TreeNode treeNode1_2 = new TreeNode(7);
        TreeNode treeNode1_3 = new TreeNode(9);
        TreeNode treeNode1_4 = new TreeNode(20, treeNode1_1, treeNode1_2);
        TreeNode treeNode1_5 = new TreeNode(3, treeNode1_3, treeNode1_4);
        // Input: root = [3,9,20,null,null,15,7]
        System.out.println("Expected: 3, Actual: " + func.maxDepth(treeNode1_5));

        TreeNode treeNode2_1 = new TreeNode(2);
        TreeNode treeNode2_2 = new TreeNode(1, null, treeNode2_1);
        // Input: root = [3,9,20,null,null,15,7]
        System.out.println("Expected: 2, Actual: " + func.maxDepth(treeNode2_2));
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree maximumDepthOfBinaryTree = new MaximumDepthOfBinaryTree();
        maximumDepthOfBinaryTree.test(maximumDepthOfBinaryTree.intMaximumDepthOfBinaryTree1st);
    }
}
