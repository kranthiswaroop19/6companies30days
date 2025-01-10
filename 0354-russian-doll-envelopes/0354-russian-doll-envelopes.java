class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // Step 1: Sort envelopes
        // Sort by width in ascending order, and by height in descending order if widths are the same
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // Descending order of height
            }
            return a[0] - b[0]; // Ascending order of width
        });

        // Step 2: Extract the heights and find LIS
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }

        return lengthOfLIS(heights);
    }

    private int lengthOfLIS(int[] heights) {
        int[] dp = new int[heights.length];
        int len = 0;

        for (int height : heights) {
            int index = Arrays.binarySearch(dp, 0, len, height);
            if (index < 0) {
                index = -(index + 1); // Convert negative insertion point to positive index
            }
            dp[index] = height;
            if (index == len) {
                len++;
            }
        }

        return len;
    }
}
