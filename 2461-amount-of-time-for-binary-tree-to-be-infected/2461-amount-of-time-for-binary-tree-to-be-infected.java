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
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        // Step 2: Perform BFS starting from the "start" node
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int minutes = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return minutes;
    }

    // Helper function to build the graph
    private void buildGraph(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) return;

        // Add the current node to the graph
        graph.putIfAbsent(node.val, new ArrayList<>());
        if (parent != null) {
            graph.get(node.val).add(parent.val);
            graph.get(parent.val).add(node.val);
        }

        // Recurse for the left and right children
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    
    }
}