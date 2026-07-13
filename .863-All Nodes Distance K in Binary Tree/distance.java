/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {

    private void markParents(TreeNode root,
                             Map<TreeNode, TreeNode> parentTrack) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode current = queue.poll();

            if (current.left != null) {
                parentTrack.put(current.left, current);
                queue.offer(current.left);
            }

            if (current.right != null) {
                parentTrack.put(current.right, current);
                queue.offer(current.right);
            }
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        Map<TreeNode, TreeNode> parentTrack = new HashMap<>();
        markParents(root, parentTrack);

        Map<TreeNode, Boolean> visited = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.put(target, true);

        int currLevel = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            if (currLevel++ == k)
                break;

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();

                if (node.left != null &&
                        !visited.containsKey(node.left)) {

                    visited.put(node.left, true);
                    queue.offer(node.left);
                }

                if (node.right != null &&
                        !visited.containsKey(node.right)) {

                    visited.put(node.right, true);
                    queue.offer(node.right);
                }

                if (parentTrack.containsKey(node) &&
                        !visited.containsKey(parentTrack.get(node))) {

                    visited.put(parentTrack.get(node), true);
                    queue.offer(parentTrack.get(node));
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }

        return result;
    }
}