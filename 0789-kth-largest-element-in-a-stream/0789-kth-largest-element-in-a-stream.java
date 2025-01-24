import java.util.PriorityQueue;

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    // Constructor: Initializes the KthLargest object with k and nums array
    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>(k); // Min-heap to track the k largest elements
        // Add all elements from nums to the heap
        for (int num : nums) {
            add(num);
        }
    }
    
    // Adds a new value and returns the k-th largest element
    public int add(int val) {
        minHeap.offer(val); // Add the new value to the heap
        // If the heap size exceeds k, remove the smallest element
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        // Return the k-th largest element (smallest in the heap)
        return minHeap.peek();
    }
}


/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */