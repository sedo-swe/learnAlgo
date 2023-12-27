package main.ltcode.medium;

import main.ltcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

@FunctionalInterface
interface IntMaximumBinaryTree {
    TreeNode constructMaximumBinaryTree(int[] nums);
}
public class MaximumBinaryTree {

    IntMaximumBinaryTree intMaximumBinaryTree1st = ((nums) -> {
        return createMaxBinaryTree(nums, 0, nums.length-1);
    });

    private TreeNode createMaxBinaryTree(int[] nums, int begin, int end) {  // [3,2,1,6,0,5], b:4, e:5
        // System.out.println("Begin: "+begin+", End: "+end+" ==> ");
        if (begin > end)
            return null;

        // Find Max among current array
        int maxIdx = getMaxValueIndex(nums, begin, end);                // maxIdx: 3 (6)
        // System.out.println(maxIdx+" ["+nums[maxIdx]+"]");

        // Split into left and right
        TreeNode left = createMaxBinaryTree(nums, begin, maxIdx - 1);   // left(,,), b: 0, maxIdx-1: 2
        TreeNode right = createMaxBinaryTree(nums, maxIdx + 1, end);    // right(,,), maxIdx+1: 4, e: 5

        // Create node with max value, left and right
        return new TreeNode(nums[maxIdx], left, right);
    }

    private int getMaxValueIndex(int[] nums, int begin, int end) {
        int max = Integer.MIN_VALUE;
        int maxIndex = begin;
        for (int i=begin; i<=end; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    IntMaximumBinaryTree intMaximumBinaryTreeSol = ((nums) -> {
        return createMaxBinaryTreeSol(nums, 0, nums.length-1);
    });
    private TreeNode createMaxBinaryTreeSol(int[] nums, int begin, int end) {
        if (begin > end)
            return null;

        // Find Max among current array
        int maxIdx = begin;
        for (int i = begin+1; i <= end; i++) {
            if( nums[maxIdx] < nums[i]) {
                maxIdx = i;
            }
        }

        // Split into left and right
        TreeNode left = createMaxBinaryTree(nums, begin, maxIdx - 1);
        TreeNode right = createMaxBinaryTree(nums, maxIdx + 1, end);

        // Create node with max value, left and right
        return new TreeNode(nums[maxIdx], left, right);
    }

    public void test(IntMaximumBinaryTree func) {
        System.out.println("Expected: [6,3,5,null,2,0,null,null,1], Actual: "
                + this.treeBFSTraverse(func.constructMaximumBinaryTree(new int[] {3,2,1,6,0,5})));

        System.out.println("Expected: [3,null,2,null,1], Actual: "
                + this.treeBFSTraverse(func.constructMaximumBinaryTree(new int[] {3,2,1})));
    }

    private String treeBFSTraverse(TreeNode node) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (current == null) {
                sb.append("null,");
            } else {
                sb.append(current.val + ",");
                if (current.left != null || current.right != null) {
                    queue.add(current.left);
                    queue.add(current.right);
                }
            }
        }

        if (sb.length() >= 4) {
            sb.replace(sb.length() - 1, sb.length() - 0, "");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MaximumBinaryTree max = new MaximumBinaryTree();
        max.test(max.intMaximumBinaryTreeSol);
    }
}
