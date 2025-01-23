import java.util.*;

class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        // Step 1: Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] result = new int[n - 1];

        // Step 2: Iterate through all subsets of nodes using bitmask
        for (int mask = 1; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(i + 1);
                }
            }

            // Skip subsets with only one node
            if (subset.size() < 2) continue;

            // Check if the subset is a connected subtree
            if (isConnectedSubset(graph, subset)) {
                // Find the diameter of the subset
                int diameter = findDiameter(graph, subset);
                if (diameter > 0 && diameter < n) {
                    result[diameter - 1]++;
                }
            }
        }

        return result;
    }

    // Check if a subset is connected
    private boolean isConnectedSubset(List<List<Integer>> graph, List<Integer> subset) {
        Set<Integer> subsetSet = new HashSet<>(subset);
        Set<Integer> visited = new HashSet<>();
        dfs(subset.get(0), graph, subsetSet, visited);
        return visited.size() == subsetSet.size();
    }

    private void dfs(int node, List<List<Integer>> graph, Set<Integer> subsetSet, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : graph.get(node)) {
            if (subsetSet.contains(neighbor) && !visited.contains(neighbor)) {
                dfs(neighbor, graph, subsetSet, visited);
            }
        }
    }

    // Find the diameter of a subset using two BFS passes
    private int findDiameter(List<List<Integer>> graph, List<Integer> subset) {
        // First BFS to find the farthest node from an arbitrary starting node
        int farthestNode = bfs(subset.get(0), graph, subset);
        // Second BFS from the farthest node to find the diameter
        return bfsDiameter(farthestNode, graph, subset);
    }

    private int bfs(int start, List<List<Integer>> graph, List<Integer> subset) {
        Set<Integer> subsetSet = new HashSet<>(subset);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int farthestNode = start;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (subsetSet.contains(neighbor) && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    farthestNode = neighbor;
                }
            }
        }

        return farthestNode;
    }

    private int bfsDiameter(int start, List<List<Integer>> graph, List<Integer> subset) {
        Set<Integer> subsetSet = new HashSet<>(subset);
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distance = new HashMap<>();

        queue.offer(start);
        distance.put(start, 0);

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (subsetSet.contains(neighbor) && !distance.containsKey(neighbor)) {
                    distance.put(neighbor, distance.get(node) + 1);
                    queue.offer(neighbor);
                    maxDistance = Math.max(maxDistance, distance.get(neighbor));
                }
            }
        }

        return maxDistance;
    }
}
