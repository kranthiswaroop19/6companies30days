class Solution {
    public long maximumSubarraySum(int[] nums, int k) {

        int n = nums.length;
        if (k > n) {
            return 0;
        }

        long maxSum = 0, currentSum = 0;
        Set<Integer> window = new HashSet<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            // Add the current element to the window and current sum
            currentSum += nums[right];

            // If the element already exists in the set, shrink the window from the left
            while (window.contains(nums[right])) {
                window.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }

            // Add the current element to the set
            window.add(nums[right]);

            // If the window size is greater than k, shrink it from the left
            if (right - left + 1 > k) {
                window.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }

            // If the window size is exactly k, update the max sum
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    
    }
}