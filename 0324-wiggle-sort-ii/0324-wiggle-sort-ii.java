class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        // Step 2: Create a copy of the sorted array
        int[] sorted = nums.clone();
        
        // Step 3: Split the sorted array into two halves
        int mid = (n - 1) / 2; // Last index of the left half
        int end = n - 1;       // Last index of the right half
        
        // Step 4: Place elements in the wiggle order
        for (int i = 0; i < n; i++) {
            nums[i] = (i % 2 == 0) ? sorted[mid--] : sorted[end--];
        }
    }
}