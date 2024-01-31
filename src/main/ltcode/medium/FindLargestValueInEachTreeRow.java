package main.ltcode.medium;

import main.ltcode.utils.TreeNode;

import java.util.*;

@FunctionalInterface
interface IntFindLargestValueInEachTreeRow {
    List<Integer> largestValues(TreeNode root);
}
public class FindLargestValueInEachTreeRow {
    IntFindLargestValueInEachTreeRow intFindLargestValueInEachTreeRow = (root -> {
        List<Integer> largestInEachRow = new ArrayList<>();
        if (root == null) {
            return largestInEachRow;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        Deque<TreeNode> nextLevel = new ArrayDeque<>();;
        int max = Integer.MIN_VALUE;

        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            max = Math.max(max, cur.val);
            if (cur.left != null) {
                nextLevel.offer(cur.left);
            }
            if (cur.right != null) {
                nextLevel.offer(cur.right);
            }

            if (queue.isEmpty()) {
                largestInEachRow.add(max);
                max = Integer.MIN_VALUE;
                queue.addAll(nextLevel);
                nextLevel = new ArrayDeque<>();
            }
        }
        return largestInEachRow;
    });

    IntFindLargestValueInEachTreeRow usingOneQueue = (root -> {
       List<Integer> largestInEachRow = new ArrayList<>();
        if (root == null) {
            return largestInEachRow;
        }
        Deque<TreeNode> queue = new ArrayDeque<>(Arrays.asList(root));
        int max = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            largestInEachRow.add(max);
            max = Integer.MIN_VALUE;
        }

        return largestInEachRow;
    });

    IntFindLargestValueInEachTreeRow solution = (root -> {
        List<Integer> largestInEachRow = new ArrayList<>();
        dfs(root, 0, largestInEachRow);
        return largestInEachRow;
    });

    private void dfs(TreeNode node, int level, List<Integer> largest) {
        if (node == null) {
            return;
        }

        if (level == largest.size()) {
            largest.add(node.val);
        } else {
            largest.set(level, Math.max(largest.get(level), node.val));
        }
        dfs(node.left, level + 1, largest);
        dfs(node.right, level + 1, largest);
    }

    public void test(IntFindLargestValueInEachTreeRow func) {
        TreeNode t01_06 = new TreeNode(9);
        TreeNode t01_05 = new TreeNode(3);
        TreeNode t01_04 = new TreeNode(5);
        TreeNode t01_03 = new TreeNode(2, null, t01_06);
        TreeNode t01_02 = new TreeNode(3, t01_04, t01_05);
        TreeNode t01_01 = new TreeNode(1, t01_02, t01_03);
        System.out.println("Expected: [1, 3, 9], Actual: " + func.largestValues(t01_01));

        TreeNode t02_03 = new TreeNode(3);
        TreeNode t02_02 = new TreeNode(2);
        TreeNode t02_01 = new TreeNode(1, t02_02, t02_03);
        System.out.println("Expected: [1, 3], Actual: " + func.largestValues(t02_01));
    }

    public static void main(String[] args) {
        FindLargestValueInEachTreeRow f1 = new FindLargestValueInEachTreeRow();
        f1.test(f1.intFindLargestValueInEachTreeRow);
        f1.test(f1.usingOneQueue);
        f1.test(f1.solution);
    }
}
