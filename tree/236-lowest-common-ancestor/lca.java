/**
 * Problem: 236. Lowest Common Ancestor of a Binary Tree
 *
 * Topic: Trees, DFS
 *
 * Approach:
 * - Traverse both left and right subtrees recursively.
 * - If current node is p or q, return it.
 * - If both left and right return non-null,
 *   current node is the Lowest Common Ancestor.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode p,
                                         TreeNode q) {

        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null)
            return right;

        if (right == null)
            return left;

        return root;
    }
}