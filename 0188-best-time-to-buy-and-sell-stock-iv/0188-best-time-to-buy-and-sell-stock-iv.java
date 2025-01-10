class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        // If k is greater than or equal to n/2, treat it as an unlimited transactions problem
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        // DP array: dp[i][j] is the max profit with at most i transactions up to day j
        int[][] dp = new int[k + 1][n];

        // Iterate over transactions
        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0]; // max(dp[i-1][p] - prices[p] for all p < j)
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }

        return dp[k][n - 1];
    }
}
