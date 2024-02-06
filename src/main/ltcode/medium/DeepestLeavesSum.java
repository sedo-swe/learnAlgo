package main.ltcode.medium;

import main.ltcode.utils.TreeNode;

import java.util.*;

/**
 * 1302. Deepest Leaves Sum (Medium)
 */

@FunctionalInterface
interface IntDeepestLeavesSum {
    int deepestLeavesSum(TreeNode root);
}

public class DeepestLeavesSum {
    IntDeepestLeavesSum intDeepestLeavesSumBFS = (root -> {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            sum = 0;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return sum;
    });

    IntDeepestLeavesSum intDeepestLeavesSumDFS = (root -> {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        List<Integer> sums = new ArrayList<>();
        dfs(root, sums, 0);

        return sums.get(sums.size()-1);
    });

    private void dfs(TreeNode node, List<Integer> sums, int level) {
        if (node == null) {
            return;
        }

        if (level == sums.size()) {
            sums.add(level, node.val);
        } else {
            sums.set(level, sums.get(level) + node.val);
        }
        dfs(node.left, sums, level + 1);
        dfs(node.right, sums, level + 1);
    }

    public void test(IntDeepestLeavesSum func) {
        TreeNode t01_08 = new TreeNode(8);
        TreeNode t01_07 = new TreeNode(7);
        TreeNode t01_06 = new TreeNode(6, null, t01_08);
        TreeNode t01_05 = new TreeNode(5);
        TreeNode t01_04 = new TreeNode(4, t01_07, null);
        TreeNode t01_03 = new TreeNode(3, null, t01_06);
        TreeNode t01_02 = new TreeNode(2, t01_04, t01_05);
        TreeNode t01_01 = new TreeNode(1, t01_02, t01_03);
        System.out.println("Expected: 15, Actual: " + func.deepestLeavesSum(t01_01));

//        [     6,
//           7,         8,
//        2,   7,    1,   3,
//      9, n, 1, 4, n,n, n, 5]

        TreeNode t02_11 = new TreeNode(5);
        TreeNode t02_10 = new TreeNode(4);
        TreeNode t02_09 = new TreeNode(1);
        TreeNode t02_08 = new TreeNode(9);
        TreeNode t02_07 = new TreeNode(3, null, t02_11);
        TreeNode t02_06 = new TreeNode(1);
        TreeNode t02_05 = new TreeNode(7, t02_09, t02_10);
        TreeNode t02_04 = new TreeNode(2, t02_08, null);
        TreeNode t02_03 = new TreeNode(8, t02_06, t02_07);
        TreeNode t02_02 = new TreeNode(7, t02_04, t02_05);
        TreeNode t02_01 = new TreeNode(6, t02_02, t02_03);
        System.out.println("Expected: 19, Actual: " + func.deepestLeavesSum(t02_01));
    }

    public static void main(String[] args) {
        DeepestLeavesSum deepestLeavesSum = new DeepestLeavesSum();
        deepestLeavesSum.test(deepestLeavesSum.intDeepestLeavesSumBFS);
        deepestLeavesSum.test(deepestLeavesSum.intDeepestLeavesSumDFS);
    }
}
