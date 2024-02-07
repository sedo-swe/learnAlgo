package main.ltcode_gfg._01_arrays_hashing;

import main.ltcode_gfg.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  108. Convert Sorted Array to Binary Search Tree (Easy)
 */
@FunctionalInterface
interface IntConvertSortedArrayToBinarySearchTree {
    TreeNode sortedArrayToBST(int[] nums);
}

public class ConvertSortedArrayToBinarySearchTree {
    IntConvertSortedArrayToBinarySearchTree intConvertSortedArrayToBinarySearchTree1st = ((nums) -> {

       return getMiddleNode(nums, 0, nums.length - 1);
    });

    private TreeNode getMiddleNode(int[] nums, int start, int end) {
        TreeNode mNode = null;

        if (start > end) {
            return mNode;
        }

        int middle = (start + end) / 2;
        mNode = new TreeNode(nums[middle]);
        mNode.left = getMiddleNode(nums, start, middle - 1);
        mNode.right = getMiddleNode(nums, middle + 1, end);

        return mNode;
    }

    public void test(IntConvertSortedArrayToBinarySearchTree func) {
        int[] nums1 = {-10, -3, 0, 5, 9};
        System.out.println("Expected: [0,-10,5,null,-3,null,9], Actual: " + toStringTreeNodeBST(func.sortedArrayToBST(nums1)));

        int[] nums2 = {1, 3};
        System.out.println("Expected: [1,null,3], Actual: " + toStringTreeNodeBST(func.sortedArrayToBST(nums2)));
    }

    private String toStringTreeNodeBST(TreeNode node) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = null;
        queue.add(node);

        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current != null) {
                sb.append(current.val);
                sb.append(",");
                if (current.left != null || current.right != null) {
                    queue.add(current.left);
                    queue.add(current.right);
                }
            }
            else {
                sb.append("null,");
            }
        }

        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree pr = new ConvertSortedArrayToBinarySearchTree();
        pr.test(pr.intConvertSortedArrayToBinarySearchTree1st);
    }
}
