class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        
        // Find the median
        int median = nums[nums.length / 2];

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}