package main.ltcode;

import main.ltcode.utils.Pair;
import main.ltcode.utils.TreeNode;

import java.util.AbstractMap;
import java.util.Map;

@FunctionalInterface
interface IntBalancedBinaryTree {
    boolean isBalanced(TreeNode root);
}

/**
 * 110. Balanced Binary Tree (Easy)
 */
public class BalancedBinaryTree {

    boolean isBalanced = true;

    /*
        First idea
            - Use recursive to get height of the node,
            compare heights of left and right subtrees and update global variable for balance
     */
    IntBalancedBinaryTree intBalancedBinaryTree1st = (root -> {
        isBalanced = true;
        getHeight(root);
        return isBalanced;
    });

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return Math.max(left, right) + 1;
    }

    IntBalancedBinaryTree intBalancedBinaryTreeSolution = (root -> {
        return dfs(root).getKey();
    });

    private Map.Entry<Boolean, Integer> dfs(TreeNode node) {
        if (node == null) {
            return Pair.of(true, 0);
        }
        Map.Entry<Boolean, Integer> left = dfs(node.left);
        Map.Entry<Boolean, Integer> right = dfs(node.right);


        boolean balanced = left.getKey() && right.getKey() && Math.abs(left.getValue() - right.getValue()) <= 0;
        return Pair.of(balanced, 1 + Math.max(left.getValue(), right.getValue()));
    }

    public void test(IntBalancedBinaryTree func) {
        TreeNode treeNode1_1 = new TreeNode(15);
        TreeNode treeNode1_2 = new TreeNode(7);
        TreeNode treeNode1_3 = new TreeNode(20, treeNode1_1, treeNode1_2);
        TreeNode treeNode1_4 = new TreeNode(9);
        TreeNode treeNode1_5 = new TreeNode(3, treeNode1_4, treeNode1_3);
        // Input: [3,9,20,null,null,15,7]
        System.out.println("Expected: true, Actual: " + func.isBalanced(treeNode1_5));

        TreeNode treeNode2_1 = new TreeNode(4);
        TreeNode treeNode2_2 = new TreeNode(4);
        TreeNode treeNode2_3 = new TreeNode(3, treeNode2_1, treeNode2_2);
        TreeNode treeNode2_4 = new TreeNode(3);
        TreeNode treeNode2_5 = new TreeNode(2, treeNode2_3, treeNode2_4);
        TreeNode treeNode2_6 = new TreeNode(2);
        TreeNode treeNode2_7 = new TreeNode(1, treeNode2_5, treeNode2_6);
        // Input: [1,2,2,3,3,null,null,4,4]
        System.out.println("Expected: false, Actual: " + func.isBalanced(treeNode2_7));

        System.out.println("Expected: true, Actual: " + func.isBalanced(null));
    }

    public static void main(String[] args) {
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        balancedBinaryTree.test(balancedBinaryTree.intBalancedBinaryTree1st);
        balancedBinaryTree.test(balancedBinaryTree.intBalancedBinaryTreeSolution);
    }

}
