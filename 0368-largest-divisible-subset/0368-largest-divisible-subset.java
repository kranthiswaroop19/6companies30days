class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        int n = nums.length;

        // Step 2: Initialize dp and prev arrays
        int[] dp = new int[n]; // dp[i] stores the size of the largest subset ending at index i
        int[] prev = new int[n]; // prev[i] stores the index of the previous element in the subset
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxSize = 0; // To track the size of the largest subset
        int maxIndex = -1; // To track the index of the last element in the largest subset

        // Step 3: Fill dp and prev arrays
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }

            // Update maxSize and maxIndex
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxIndex = i;
            }
        }

        // Step 4: Reconstruct the largest divisible subset
        List<Integer> result = new ArrayList<>();
        while (maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }

        // Reverse the result to maintain ascending order
        Collections.reverse(result);
        return result;
    }
}
