class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        // Count the number of subarrays with exactly k odd numbers using sliding window
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            // Decrease k if the current number is odd
            if (nums[right] % 2 != 0) {
                k--;
            }

            // If k is negative, shrink the window from the left
            while (k < 0) {
                if (nums[left] % 2 != 0) {
                    k++;
                }
                left++;
            }

            // Add the number of subarrays ending at the current position
            count += right - left + 1;
        }

        return count;
    }
}
