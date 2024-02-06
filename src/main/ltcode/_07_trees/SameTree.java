package main.ltcode._07_trees;

import main.ltcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

@FunctionalInterface
interface IntSameTree {
    boolean isSameTree(TreeNode p, TreeNode q);
}

/**
 * 100. Same Tree (Easy)
 */
public class SameTree {

    /*
        First idea
            - Write a function returning a string of the values of all TreeNodes by BFS
              Compare two strings
              --> O(n), O(n)
              ==> Accepted: 1 ms (100.00%), 39.5 MB (93.74%)
     */
    IntSameTree intSameTree1st = ((p, q) -> {
        String p1 = this.traverseTreeReturnAllValues(p);
        String q1 = this.traverseTreeReturnAllValues(q);

        if (p1.equals(q1)) {
            return true;
        }

        return false;
    });

    private String traverseTreeReturnAllValues(TreeNode node) {
        StringBuffer sb = new StringBuffer();
        if (node == null) {
            return sb.toString();
        }

        if (node.left == null && node.right == null) {
            return sb.append(node.val).toString();
        }
        sb.append("[");

        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(node);
        TreeNode cur = null;

        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            sb.append(cur.val);

            if (cur.left != null) { queue.push(cur.left); }
            else { sb.append("ln"); }
            if (cur.right != null) { queue.push(cur.right); }
            else { sb.append("rn"); }
        }

        sb.append("]");
        return sb.length() > 2 ? sb.toString() : "";
    }

    IntSameTree intSameTreeRecurSolution = ((p, q) -> {
        return checkSubtreeEqual(p, q);
    });

    private boolean checkSubtreeEqual(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        // Known they are not BOTH, so if either is null, they are not equal
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return checkSubtreeEqual(p.left, q.left) && checkSubtreeEqual(p.right, q.right);
    }

    public void test(IntSameTree func) {
        TreeNode t1_3 = new TreeNode(3);
        TreeNode t1_2 = new TreeNode(2);
        TreeNode t1_1 = new TreeNode(1, t1_2, t1_3);

        TreeNode t2_3 = new TreeNode(3);
        TreeNode t2_2 = new TreeNode(2);
        TreeNode t2_1 = new TreeNode(1, t2_2, t2_3);

        System.out.println("Expected: true; Actual: " + func.isSameTree(t1_1, t2_1));

        TreeNode t3_2 = new TreeNode(2);
        TreeNode t3_1 = new TreeNode(1, t3_2, null);

        TreeNode t4_2 = new TreeNode(2);
        TreeNode t4_1 = new TreeNode(1, null, t4_2);

        System.out.println("Expected: false; Actual: " + func.isSameTree(t3_1, t4_1));

        TreeNode t5_3 = new TreeNode(1);
        TreeNode t5_2 = new TreeNode(2);
        TreeNode t5_1 = new TreeNode(1, t5_2, t5_3);

        TreeNode t6_3 = new TreeNode(2);
        TreeNode t6_2 = new TreeNode(1);
        TreeNode t6_1 = new TreeNode(1, t6_2, t6_3);

        System.out.println("Expected: false; Actual: " + func.isSameTree(t5_1, t6_1));
    }

    public static void main(String[] args) {
        SameTree sameTree = new SameTree();
        System.out.println("= intSameTree1st ============================");
        sameTree.test(sameTree.intSameTree1st);
        System.out.println("= intSameTreeRecurSolution ==================");
        sameTree.test(sameTree.intSameTreeRecurSolution);
    }
}
