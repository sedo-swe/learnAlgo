package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@FunctionalInterface
interface IntDiameterOfBinaryTree {
    int diameterOfBinaryTree(TreeNode root);
}

/**
 *  543. Diameter of Binary Tree
 */
public class DiameterOfBinaryTree {
    int maxDiameter = -1;
    IntDiameterOfBinaryTree intDiameterOfBinaryTreeRecur1st = (root -> {
        maxDiameter = -1;
        findDiameter(root);
        return maxDiameter;
    });

    private int findDiameter(TreeNode node) {
        if (node == null) { return -1; }

        int left = 1 + findDiameter(node.left);
        int right = 1 + findDiameter(node.right);
        maxDiameter = Math.max(maxDiameter, (left + right));

        return Math.max(left, right);
    }

    IntDiameterOfBinaryTree intDiameterOfBinaryTreeIter1st = (root -> {
        int diameter = 0;
        if (root == null) {
            return diameter;
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && !map.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !map.containsKey(node.right)) {
                stack.push(node.right);
            } else {
                stack.pop();
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);
                map.put(node, 1 + Math.max(leftDepth, rightDepth));
                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }
        return diameter;
    });


    public void test(IntDiameterOfBinaryTree func) {

        TreeNode treeNode1_1 = new TreeNode(5);
        TreeNode treeNode1_2 = new TreeNode(4);
        TreeNode treeNode1_3 = new TreeNode(3);
        TreeNode treeNode1_4 = new TreeNode(2, treeNode1_2, treeNode1_1);
        TreeNode treeNode1_5 = new TreeNode(1, treeNode1_4, treeNode1_3);
        // Input: root = [1,2,3,4,5]
        System.out.println("Expected: 3, Actual: " + func.diameterOfBinaryTree(treeNode1_5));

        TreeNode treeNode2_1 = new TreeNode(2);
        TreeNode treeNode2_2 = new TreeNode(1, treeNode2_1, null );
        // Input: root = [1,2]
        System.out.println("Expected: 1, Actual: " + func.diameterOfBinaryTree(treeNode2_2));

        TreeNode treeNode3_01 = new TreeNode(-2);
        TreeNode treeNode3_02 = new TreeNode(-4);
        TreeNode treeNode3_03 = new TreeNode(-1);
        TreeNode treeNode3_04 = new TreeNode(9, treeNode3_01, null);
        TreeNode treeNode3_05 = new TreeNode(5, null, null);
        TreeNode treeNode3_06 = new TreeNode(6, treeNode3_02, null);
        TreeNode treeNode3_07 = new TreeNode(0, null, treeNode3_03);
        TreeNode treeNode3_08 = new TreeNode(-6, treeNode3_04, null);
        TreeNode treeNode3_09 = new TreeNode(-6, treeNode3_05, null);
        TreeNode treeNode3_10 = new TreeNode(6, treeNode3_07, treeNode3_06);
        TreeNode treeNode3_11 = new TreeNode(-4, null, null);
        TreeNode treeNode3_12 = new TreeNode(-7, treeNode3_09, treeNode3_08);
        TreeNode treeNode3_13 = new TreeNode(9, treeNode3_10, null);
        TreeNode treeNode3_14 = new TreeNode(-3, treeNode3_11, null);
        TreeNode treeNode3_15 = new TreeNode(-9, treeNode3_13, treeNode3_12);
        TreeNode treeNode3_16 = new TreeNode(-3, treeNode3_15, treeNode3_14);
        TreeNode treeNode3_17 = new TreeNode(-7, null, null);
        TreeNode treeNode3_18 = new TreeNode(4, treeNode3_17, treeNode3_16);
        // Input: [4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]
        System.out.println("Expected: 8, Actual: " + func.diameterOfBinaryTree(treeNode3_18));
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        System.out.println("= Recur ======================");
        diameterOfBinaryTree.test(diameterOfBinaryTree.intDiameterOfBinaryTreeRecur1st);
        System.out.println("= Iter =======================");
        diameterOfBinaryTree.test(diameterOfBinaryTree.intDiameterOfBinaryTreeIter1st);
    }
}
