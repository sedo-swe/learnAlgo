package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  105. Construct Binary Tree from Preorder and Inorder Traversal (Medium)
 */
@FunctionalInterface
interface IntConstructBinaryTreefromPreorderandInorderTraversal {
    TreeNode buildTree(int[] preorder, int[] inorder);
}
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    /*
        Solution without using Array copies
        7 ms, time: O (n), space: O (n)
     */
    IntConstructBinaryTreefromPreorderandInorderTraversal first = ((preorder, inorder) -> {
        return buildTree(preorder, inorder);
    });

    private TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        int mid = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) mid = i;
        }

        root.left =
                buildTree(Arrays.copyOfRange(preorder, 1, mid + 1), Arrays.copyOfRange(inorder, 0, mid));
        root.right =
                buildTree(Arrays.copyOfRange(preorder, mid + 1, preorder.length), Arrays.copyOfRange(inorder, mid + 1, inorder.length));
        return root;
    }

    /*
        Solution without using Array copies
        1 ms, time: O (n), space: O (n)
     */
    Map<Integer, Integer> inorderPositions = new HashMap<>();
    IntConstructBinaryTreefromPreorderandInorderTraversal withoutArrayCopy = ((preorder, inorder) -> {
        if (preorder.length < 1 || inorder.length < 1) return null;

        for (int i = 0; i < inorder.length; i++) {
            inorderPositions.put(inorder[i], i);
        }
        return builder(preorder, 0, 0, inorder.length - 1);
    });

    public TreeNode builder(
            int[] preorder,
            int preorderIndex,
            int inorderLow,
            int inorderHigh
    ) {
        if (preorderIndex > preorder.length - 1 || inorderLow > inorderHigh) return null;

        int currentVal = preorder[preorderIndex];
        TreeNode n = new TreeNode(currentVal);
        int mid = inorderPositions.get(currentVal);

        n.left = builder(preorder, preorderIndex + 1, inorderLow, mid - 1);
        n.right =
                builder(preorder, preorderIndex + (mid - inorderLow) + 1, mid + 1, inorderHigh);
        return n;
    }

    /*
        leetcode sol:
        0 ms, time: O (n), space: O (n)
     */
    private int i = 0;
    private int p = 0;
    IntConstructBinaryTreefromPreorderandInorderTraversal leetcodeSol = ((preorder, inorder) -> {
        return build(preorder, inorder, Integer.MIN_VALUE);
    });

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (p >= preorder.length) {
            return null;
        }
        if (inorder[i] == stop) {
            ++i;
            return null;
        }

        TreeNode node = new TreeNode(preorder[p++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }

    public void test(IntConstructBinaryTreefromPreorderandInorderTraversal func) {
        System.out.println("Expected: [3,9,20,null,null,15,7], Actual: " + func.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
        System.out.println("Expected: [-1], Actual: " + func.buildTree(new int[]{-1}, new int[]{-1}));
    }
}
