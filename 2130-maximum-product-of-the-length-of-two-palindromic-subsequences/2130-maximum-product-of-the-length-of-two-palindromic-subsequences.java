import java.util.HashMap;
import java.util.Map;

class Solution {
    // Method to check if a string is a palindrome
    private boolean isPalindrome(String subseq) {
        int left = 0, right = subseq.length() - 1;
        while (left < right) {
            if (subseq.charAt(left) != subseq.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public int maxProduct(String s) {
        int n = s.length();
        Map<Integer, Integer> palindromicLengths = new HashMap<>();

        // Generate all subsequences using bitmask
        for (int mask = 1; mask < (1 << n); mask++) {
            StringBuilder subseq = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subseq.append(s.charAt(i));
                }
            }
            if (isPalindrome(subseq.toString())) {
                palindromicLengths.put(mask, subseq.length());
            }
        }

        // Find the maximum product of lengths of two disjoint palindromic subsequences
        int maxProduct = 0;
        for (int mask1 : palindromicLengths.keySet()) {
            for (int mask2 : palindromicLengths.keySet()) {
                if ((mask1 & mask2) == 0) { // Disjoint subsequences
                    int product = palindromicLengths.get(mask1) * palindromicLengths.get(mask2);
                    maxProduct = Math.max(maxProduct, product);
                }
            }
        }

        return maxProduct;
    }
}
