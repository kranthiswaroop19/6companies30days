/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int result = 0;

    public int countPairs(TreeNode root, int distance) {
        if (root == null) return 0;

        dfs(root, distance);
        return result;
    }

    private int[] dfs(TreeNode node, int distance) {
        // Base case: if the node is null, return an empty array of distances
        if (node == null) {
            return new int[distance + 1];
        }

        // If the node is a leaf, return an array with one leaf at distance 1
        if (node.left == null && node.right == null) {
            int[] dist = new int[distance + 1];
            dist[1] = 1;
            return dist;
        }

        // Recursive calls for left and right subtrees
        int[] leftDist = dfs(node.left, distance);
        int[] rightDist = dfs(node.right, distance);

        // Count the good pairs between left and right subtrees
        for (int l = 1; l <= distance; l++) {
            for (int r = 1; r <= distance; r++) {
                if (l + r <= distance) {
                    result += leftDist[l] * rightDist[r];
                }
            }
        }

        // Prepare the new distance array for the parent node
        int[] newDist = new int[distance + 1];
        for (int i = 1; i < distance; i++) {
            newDist[i + 1] = leftDist[i] + rightDist[i];
        }

        return newDist;
    }
}
