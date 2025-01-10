class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;

        int maxLength = 0;
        int i = 1;

        while (i < n - 1) {
            // Check if arr[i] is a peak
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                // Expand left
                int left = i - 1;
                while (left > 0 && arr[left - 1] < arr[left]) {
                    left--;
                }

                // Expand right
                int right = i + 1;
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }

                // Calculate mountain length
                int mountainLength = right - left + 1;
                maxLength = Math.max(maxLength, mountainLength);

                // Move i to the end of the current mountain
                i = right;
            } else {
                i++;
            }
        }

        return maxLength;
    }
}
