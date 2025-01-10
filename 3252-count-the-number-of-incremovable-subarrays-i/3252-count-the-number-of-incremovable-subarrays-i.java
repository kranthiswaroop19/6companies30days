class Solution {
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int count = 0;

        // Iterate over all possible subarrays
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (isIncremovable(nums, l, r)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isIncremovable(int[] nums, int l, int r) {
        int n = nums.length;

        // Check left neighbor of the subarray
        if (l > 0 && r + 1 < n && nums[l - 1] >= nums[r + 1]) {
            return false;
        }

        // Check remaining array for strict increasing order
        for (int i = 0; i < l - 1; i++) {
            if (nums[i] >= nums[i + 1]) return false;
        }
        for (int i = r + 1; i < n - 1; i++) {
            if (nums[i] >= nums[i + 1]) return false;
        }

        return true;
    }
}
