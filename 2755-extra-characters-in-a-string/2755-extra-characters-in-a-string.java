import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        Set<String> wordSet = new HashSet<>();
        for (String word : dictionary) {
            wordSet.add(word);
        }

        // dp[i] represents the minimum extra characters for substring s[0:i]
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // Base case: No extra characters if we consider an empty string
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            // Case 1: Not using any dictionary word ending at i-1
            dp[i] = dp[i - 1] + 1;

            // Case 2: Check for dictionary words ending at i-1
            for (int j = 0; j < i; j++) {
                if (wordSet.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }

        return dp[n];
    }
}
