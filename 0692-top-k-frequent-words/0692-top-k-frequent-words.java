import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count the frequency of each word
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Create a priority queue (min-heap) with a custom comparator
        PriorityQueue<String> heap = new PriorityQueue<>((w1, w2) -> {
            int freq1 = freqMap.get(w1);
            int freq2 = freqMap.get(w2);
            if (freq1 == freq2) {
                return w2.compareTo(w1); // Reverse lexicographical order
            }
            return freq1 - freq2; // Compare by frequency
        });

        // Step 3: Add words to the heap
        for (String word : freqMap.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll(); // Remove the least frequent or largest lexicographical
            }
        }

        // Step 4: Extract elements from the heap and build the result list
        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }

        // Since the heap provides elements in reverse order, we need to reverse the list
        Collections.reverse(result);

        return result;
    }
}
