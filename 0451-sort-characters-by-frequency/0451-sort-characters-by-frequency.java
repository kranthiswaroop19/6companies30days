import java.util.*;

class Solution {
    public String frequencySort(String s) {
        // Step 1: Count character frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Sort characters by frequency in descending order
        List<Character> characters = new ArrayList<>(frequencyMap.keySet());
        characters.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

        // Step 3: Build the result string
        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            int freq = frequencyMap.get(c);
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }
}
