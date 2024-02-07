package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

import java.util.*;

/**
 *  637. Average of Levels in Binary Tree (Easy)
 */
@FunctionalInterface
interface IntAverageofLevelsinBinaryTree {
    List<Double> averageOfLevels(TreeNode root);
}

public class AverageofLevelsinBinaryTree {
    IntAverageofLevelsinBinaryTree intAverageofLevelsinBinaryTreeBFS = ((root) -> {
        if (root == null) {
            List<Double> zero = new ArrayList<>();
            zero.add(0D);
            return zero;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<TreeNode> levels = new ArrayDeque<>();
        List<Double> result = new ArrayList<>();
        Double sum = 0D;
        int cnt = 0;

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.removeFirst();
            sum += cur.val;
            cnt++;

            if (cur.left != null) {
                levels.add(cur.left);
            }
            if (cur.right != null) {
                levels.add(cur.right);
            }

            if (queue.isEmpty()) {
                queue.addAll(levels);
                levels = new ArrayDeque<>();
                result.add(sum / cnt);
                sum = 0D;
                cnt = 0;
            }
        }
        return result;
    });

    IntAverageofLevelsinBinaryTree intAverageofLevelsinBinaryTreeBFS2 = ((root) -> {
        if (root == null) {
            List<Double> zero = new ArrayList<>();
            zero.add(0D);
            return zero;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Double> result = new ArrayList<>();
        Double sum = 0D;
        int cnt = 0;

        queue.add(root);
        int queueLen = queue.size();

        while (queueLen > 0) {
            for (int i = 0; i < queueLen; i++) {
                TreeNode cur = queue.removeFirst();
                sum += cur.val;
                cnt++;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            queueLen = queue.size();
            result.add(sum / cnt);
            sum = 0D;
            cnt = 0;
        }
        return result;
    });

    IntAverageofLevelsinBinaryTree intAverageofLevelsinBinaryTreeDFS = (root -> {
        HashMap<Integer, List<Double>> levels = new HashMap<>();
        List<Double> result = new ArrayList<>();
        dfs(root, 0, levels);
        for (Integer level : levels.keySet()) {
            Double sum = 0D;
            List<Double> vals = levels.get(level);
            for (Double val : vals) {
                sum += val;
            }
            result.add(sum / vals.size());
        }
        return result;
    });

    private void dfs(TreeNode cur, int level, HashMap<Integer, List<Double>> levels) {
        if (cur == null) {
            return;
        }

        level++;
        List<Double> valAtLevel = levels.getOrDefault(level, new ArrayList<>());
        valAtLevel.add(Double.valueOf(cur.val));
        levels.put(level, valAtLevel);

        if (cur.left != null) {
            dfs(cur.left, level, levels);
        }

        if (cur.right != null) {
            dfs(cur.right, level, levels);
        }
    }

    public void test(IntAverageofLevelsinBinaryTree func) {
        TreeNode t01_05 = new TreeNode(7);
        TreeNode t01_04 = new TreeNode(15);
        TreeNode t01_03 = new TreeNode(20, t01_04, t01_05);
        TreeNode t01_02 = new TreeNode(9);
        TreeNode t01_01 = new TreeNode(3, t01_02, t01_03);
        System.out.println(func.averageOfLevels(t01_01));
    }

    public static void main(String[] args) {
        AverageofLevelsinBinaryTree averageofLevelsinBinaryTree = new AverageofLevelsinBinaryTree();
        System.out.println("\n= BFS =======");
        averageofLevelsinBinaryTree.test(averageofLevelsinBinaryTree.intAverageofLevelsinBinaryTreeBFS);
        averageofLevelsinBinaryTree.test(averageofLevelsinBinaryTree.intAverageofLevelsinBinaryTreeBFS2);
        System.out.println("\n= DFS =======");
        averageofLevelsinBinaryTree.test(averageofLevelsinBinaryTree.intAverageofLevelsinBinaryTreeDFS);
    }
}
