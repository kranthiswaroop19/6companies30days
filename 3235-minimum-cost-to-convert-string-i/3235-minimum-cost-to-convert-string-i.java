class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length();
        int ALPHABET_SIZE = 26;
        long INF = Long.MAX_VALUE / 2; // Use a large value to represent "infinity"

        // Initialize cost matrix
        long[][] costMatrix = new long[ALPHABET_SIZE][ALPHABET_SIZE];
        for (long[] row : costMatrix) {
            Arrays.fill(row, INF);
        }

        // Self-transformation cost is 0
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            costMatrix[i][i] = 0;
        }

        // Fill cost matrix based on input data
        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            costMatrix[from][to] = Math.min(costMatrix[from][to], cost[i]);
        }

        // Apply Floyd-Warshall to find minimum cost between any two characters
        for (int k = 0; k < ALPHABET_SIZE; k++) {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                for (int j = 0; j < ALPHABET_SIZE; j++) {
                    if (costMatrix[i][k] != INF && costMatrix[k][j] != INF) {
                        costMatrix[i][j] = Math.min(costMatrix[i][j], costMatrix[i][k] + costMatrix[k][j]);
                    }
                }
            }
        }

        // Calculate the total cost to convert source to target
        long totalCost = 0;
        for (int i = 0; i < n; i++) {
            int from = source.charAt(i) - 'a';
            int to = target.charAt(i) - 'a';

            // If transformation is impossible, return -1
            if (costMatrix[from][to] == INF) {
                return -1;
            }

            totalCost += costMatrix[from][to];
        }

        return totalCost;
        
    }
}