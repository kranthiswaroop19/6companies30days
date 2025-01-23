import java.util.PriorityQueue;

class Solution {
    public int maximumProduct(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        
        // Min-heap to track the smallest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
        }

        // Perform k operations to increment the smallest element
        while (k > 0) {
            int smallest = minHeap.poll(); // Get the smallest element
            minHeap.add(smallest + 1);    // Increment it and add back
            k--;
        }

        // Compute the product of all elements modulo MOD
        long product = 1;
        while (!minHeap.isEmpty()) {
            product = (product * minHeap.poll()) % MOD;
        }

        return (int) product;
    }
}
