package main.ltcode_gfg._07_trees;

import main.ltcode_gfg.utils.TreeNode;

/**
 *  235. Lowest Common Ancestor of a Binary Search Tree (Medium)
 */
@FunctionalInterface
interface IntLowestCommonAncestorOfaBinarySearchTree {
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
}
public class LowestCommonAncestorOfaBinarySearchTree {
    IntLowestCommonAncestorOfaBinarySearchTree intLowestCommonAncestorOfaBinarySearchTree = ((root, p, q) -> {
        return findLCA(root, p, q);
    });

    private TreeNode findLCA(TreeNode cur, TreeNode p, TreeNode q) {
        if (p.val < cur.val && q.val < cur.val) {
            return findLCA(cur.left, p, q);
        }
        if (p.val > cur.val && q.val > cur.val) {
            return findLCA(cur.right, p, q);
        }
        return cur;
    }

    public void test(IntLowestCommonAncestorOfaBinarySearchTree func) {
        TreeNode tn01_05 = new TreeNode(5);
        TreeNode tn01_03 = new TreeNode(3);
        TreeNode tn01_04 = new TreeNode(4, tn01_03, tn01_05);
        TreeNode tn01_01 = new TreeNode(0);
        TreeNode tn01_09 = new TreeNode(9);
        TreeNode tn01_07 = new TreeNode(7);
        TreeNode tn01_08 = new TreeNode(8, tn01_07, tn01_09);
        TreeNode tn01_02 = new TreeNode(2, tn01_01, tn01_04);
        TreeNode tn01_06 = new TreeNode(6, tn01_02, tn01_08);

        System.out.println("Expected: 6, Actual: " + func.lowestCommonAncestor(tn01_06, new TreeNode(2), new TreeNode(8)).val);
        System.out.println("Expected: 2, Actual: " + func.lowestCommonAncestor(tn01_06, new TreeNode(2), new TreeNode(4)).val);

        TreeNode tn03_01 = new TreeNode(1);
        TreeNode tn03_02 = new TreeNode(2, tn03_01, null);
        System.out.println("Expected: 2, Actual: " + func.lowestCommonAncestor(tn03_02, new TreeNode(2), new TreeNode(1)).val);
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfaBinarySearchTree lca = new LowestCommonAncestorOfaBinarySearchTree();
        lca.test(lca.intLowestCommonAncestorOfaBinarySearchTree);
    }
}
