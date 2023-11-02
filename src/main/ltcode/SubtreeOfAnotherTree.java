package main.ltcode;

import main.ltcode.utils.TreeNode;

import java.util.Stack;

@FunctionalInterface
interface IntSubtreeOfAnotherTree {
    boolean isSubtree(TreeNode root, TreeNode subRoot);
}
public class SubtreeOfAnotherTree {
    /*
        First idea
            - Use pre-order with DFS
                --> Accepted:

     */
    IntSubtreeOfAnotherTree intSubtreeOfAnotherTree1st = ((root, subRoot) -> {
        String rootString = traverseTreeDFS(root);
        String subString = traverseTreeDFS(subRoot);

        return rootString.contains(subString);
    });

    private String traverseTreeDFS(TreeNode node) {
        StringBuffer sb = new StringBuffer();

//        if (node == null) {
//            return sb.toString();
//        }
//
//        if (node.left == null && node.right == null) {
//            return sb.append(node.val).toString();
//        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        TreeNode cur = null;

        while (!stack.isEmpty()) {
            cur = stack.pop();
            sb.append(","+cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            } else {
                sb.append("lnu");
            }
            if (cur.right != null) {
                stack.push(cur.right);
            } else {
                sb.append("rnu");
            }
        }
        return sb.length() > 0 ? sb.toString() : "";
    }

    IntSubtreeOfAnotherTree intSubtreeOfAnotherTreeRecurSolution = ((root, subRoot) -> {
        return isSubtree(root, subRoot);
    });

    private boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null) {
            return false;
        }

        // identical
        if (subRoot == null || isSameTree(root, subRoot)) {
            return true;
        }

        // subtree of left or subtree of right
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);

        return left && right;
    }

    public void test(IntSubtreeOfAnotherTree func) {
        TreeNode t1_2 = new TreeNode(2);
        TreeNode t1_1 = new TreeNode(1);
        TreeNode t1_4 = new TreeNode(4, t1_1, t1_2);
        TreeNode t1_5 = new TreeNode(5);
        TreeNode t1_3 = new TreeNode(3, t1_4, t1_5);

        TreeNode t2_2 = new TreeNode(2);
        TreeNode t2_1 = new TreeNode(1);
        TreeNode t2_4 = new TreeNode(4, t2_1, t2_2);
        System.out.println("Expected: true, Actual: " + func.isSubtree(t1_3, t2_4));


        TreeNode t3_0 = new TreeNode(0);
        TreeNode t3_2 = new TreeNode(2, t3_0, null);
        TreeNode t3_1 = new TreeNode(1);
        TreeNode t3_4 = new TreeNode(4, t3_1, t3_2);
        TreeNode t3_5 = new TreeNode(5);
        TreeNode t3_3 = new TreeNode(3, t3_4, t3_5);

        TreeNode t4_2 = new TreeNode(2);
        TreeNode t4_1 = new TreeNode(1);
        TreeNode t4_4 = new TreeNode(4, t4_1, t4_2);
        System.out.println("Expected: false, Actual: " + func.isSubtree(t3_3, t4_4));

        TreeNode t5_1 = new TreeNode(12);
        TreeNode t6_1 = new TreeNode(2);
        System.out.println("Expected: false, Actual: " + func.isSubtree(t5_1, t6_1));
    }

    public static void main(String[] args) {
        SubtreeOfAnotherTree subtreeOfAnotherTree = new SubtreeOfAnotherTree();
        System.out.println("= Iterative ==========================");
        subtreeOfAnotherTree.test(subtreeOfAnotherTree.intSubtreeOfAnotherTree1st);
        System.out.println("= Recursive ==========================");
        subtreeOfAnotherTree.test(subtreeOfAnotherTree.intSubtreeOfAnotherTreeRecurSolution);
    }
}
