import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) return 0;

        Map<Integer, Integer> freqMap = new HashMap<>();
        int count = 0;

        // Count the frequency of each number
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Check pairs
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int frequency = entry.getValue();

            if (k == 0) {
                // For k = 0, count numbers that appear more than once
                if (frequency > 1) {
                    count++;
                }
            } else {
                // For k > 0, check if num + k exists in the map
                if (freqMap.containsKey(num + k)) {
                    count++;
                }
            }
        }

        return count;
    }
}
