import java.util.*;

class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        // Create a priority queue to find the k largest elements and their indices
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(new int[]{nums[i], i});
            if (minHeap.size() > k) {
                minHeap.poll(); // Keep only the k largest elements
            }
        }

        // Extract the indices of the k largest elements
        List<int[]> largestKElements = new ArrayList<>(minHeap);
        // Sort by index to maintain the original order
        largestKElements.sort(Comparator.comparingInt(a -> a[1]));

        // Create the result subsequence
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = largestKElements.get(i)[0];
        }

        return result;
    }
}
