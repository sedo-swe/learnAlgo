package main.ltcode;

import main.ltcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

@FunctionalInterface
interface IntInvertBinaryTree {
    TreeNode invertTree(TreeNode root);
}

/**
 *  226. Invert Binary Tree (Easy)
 */
public class InvertBinaryTree {
    /*
        First idea
            - Use traverse Inorder like BFS
            ==> Accepted, 0 ms

        Second idea
            - Use traverse Inorder via recursive
            ==> Accepted, 0 ms
     */
    IntInvertBinaryTree invertBinaryTreeIter1st = (root -> {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        TreeNode cur = null, temp = null;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            temp = cur.right;
            cur.right = cur.left;
            cur.left = temp;

            if (cur.left != null) { queue.add(cur.left); }
            if (cur.right != null) { queue.add(cur.right); }
        }
        return root;
    });

    IntInvertBinaryTree invertBinaryTreeRecur1st = (root -> {
        return invertBTree(root);
    });

    private TreeNode invertBTree(TreeNode node) {
        if (node == null) {
            return node;
        }

        TreeNode temp = node.left;
        node.left = invertBTree(node.right);
        node.right = invertBTree(temp);

        return node;
    }

    public void test(IntInvertBinaryTree func) {
        TreeNode treeNode1_1 = new TreeNode(1);
        TreeNode treeNode1_3 = new TreeNode(3);
        TreeNode treeNode1_6 = new TreeNode(6);
        TreeNode treeNode1_9 = new TreeNode(9);
        TreeNode treeNode1_2 = new TreeNode(2, treeNode1_1, treeNode1_3);
        TreeNode treeNode1_7 = new TreeNode(7, treeNode1_6, treeNode1_9);
        TreeNode treeNode1_4 = new TreeNode(4, treeNode1_2, treeNode1_7);
        // input: [4,2,7,1,3,6,9]
        System.out.println("Expected: [4,7,2,9,6,3,1], Actual: " + this.printTreeNodeInorder(func.invertTree(treeNode1_4)));

        TreeNode treeNode2_1 = new TreeNode(1);
        TreeNode treeNode2_3 = new TreeNode(3);
        TreeNode treeNode2_2 = new TreeNode(2, treeNode2_1, treeNode2_3);
        // input: [2,1,3]
        System.out.println("Expected: [2,3,1], Actual: " + this.printTreeNodeInorder(func.invertTree(treeNode2_2)));

        System.out.println("Expected: [], Actual: " + this.printTreeNodeInorder(func.invertTree(null)));
    }

    private String printTreeNodeInorder(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (root != null) {
            Deque<TreeNode> queue = new LinkedList<>();
            queue.push(root);
            TreeNode cur = null;
            while (!queue.isEmpty()) {
                cur = queue.removeFirst();
                sb.append(cur.val + ", ");
                if (cur.left != null) { queue.add(cur.left); }
                if (cur.right != null) { queue.add(cur.right); }
            }
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
        invertBinaryTree.test(invertBinaryTree.invertBinaryTreeIter1st);
        invertBinaryTree.test(invertBinaryTree.invertBinaryTreeRecur1st);
    }
}
