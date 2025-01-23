class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
       // Step 1: Initialize the distance matrix
        int[][] distance = new int[n][n];
        
        // Set initial distances: infinite for all except self-loops (distance = 0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        // Set the distances based on the edges
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            distance[from][to] = weight;
            distance[to][from] = weight;
        }
        
        // Step 2: Floyd-Warshall Algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }
        
        // Step 3: Find the city with the smallest number of reachable cities
        int minReachable = Integer.MAX_VALUE;
        int resultCity = -1;
        
        for (int i = 0; i < n; i++) {
            int reachableCount = 0;
            for (int j = 0; j < n; j++) {
                if (distance[i][j] <= distanceThreshold) {
                    reachableCount++;
                }
            }
            // Update the result based on the problem's constraints
            if (reachableCount < minReachable || (reachableCount == minReachable && i > resultCity)) {
                minReachable = reachableCount;
                resultCity = i;
            }
        }
        
        return resultCity; 
    }
}