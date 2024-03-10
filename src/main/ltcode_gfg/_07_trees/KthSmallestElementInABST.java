package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

import java.util.*;

/**
 *  230. Kth Smallest Element in a BST (Medium)
 */
@FunctionalInterface
interface IntKthSmallestElementInABST {
    int kthSmallest(TreeNode root, int k);
}
public class KthSmallestElementInABST {
    /*
        1st idea
        Use PriorityQueue and BFS
        ==> Passed, time: O (n log n), space: O (n)
     */
    IntKthSmallestElementInABST priorityQueue = ((root, k) -> {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            pq.add(cur.val);
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = pq.poll();
        }
        return result;
    });

    /*
        2nd idea
        Use PriorityQueue and BFS, improved slightly
        ==> Passed, time: O (n log k), space: O (k)
     */
    IntKthSmallestElementInABST priorityQueueImproved = ((root, k) -> {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            pq.add(cur.val);
            if (pq.size() > k) {
                pq.poll();
            }
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
        return pq.poll();
    });

    /*
        Fastest, time: O (n), space: O (n)
     */
    IntKthSmallestElementInABST traverseInOrderIter = ((root, k) -> {
        int n = 0;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !s.isEmpty()) {
            while (cur != null) {
                s.add(cur);
                cur = cur.left;
            }
            cur = s.pop();
            n++;
            if (n == k)
                return cur.val;
            cur = cur.right;
        }
        return -1;
    });

    /*
        Faster, time: O (n), space: O (n)
     */
    IntKthSmallestElementInABST traverseInOrderRecur = ((root, k) -> {
        List<Integer> ordered = new ArrayList<>();
        recursive(root, ordered);
        return ordered.get(k - 1);
    });

    private void recursive(TreeNode node, List<Integer> ordered) {
        if (node == null) return;

        recursive(node.left, ordered);
        ordered.add(node.val);
        recursive(node.right, ordered);
    }

    public void test(IntKthSmallestElementInABST func) {
        TreeNode t01_04 = new TreeNode(2);
        TreeNode t01_03 = new TreeNode(4);
        TreeNode t01_02 = new TreeNode(1, null, t01_04);
        TreeNode t01_01 = new TreeNode(2, t01_02, t01_03);
        System.out.println("Expected: 1, Actual: " + func.kthSmallest(t01_01, 1));

        TreeNode t02_06 = new TreeNode(1);
        TreeNode t02_05 = new TreeNode(4);
        TreeNode t02_04 = new TreeNode(2, t02_06, null);
        TreeNode t02_03 = new TreeNode(6);
        TreeNode t02_02 = new TreeNode(3, t02_04, t02_05);
        TreeNode t02_01 = new TreeNode(5, t02_02, t02_03);
        System.out.println("Expected: 3, Actual: " + func.kthSmallest(t02_01, 3));
    }

    public static void main(String[] args) {
        KthSmallestElementInABST k = new KthSmallestElementInABST();
        k.test(k.priorityQueue);
        k.test(k.priorityQueueImproved);
        k.test(k.traverseInOrderIter);
        k.test(k.traverseInOrderRecur);
    }
}
