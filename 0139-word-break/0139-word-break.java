import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert the wordDict to a HashSet for faster lookup
        Set<String> wordSet = new HashSet<>(wordDict);
        
        // DP array to store whether the substring s[0:i] can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Base case: empty string can be segmented
        
        // Iterate through the string
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // If s[j:i] is in the dictionary and dp[j] is true, set dp[i] to true
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check further
                }
            }
        }
        
        // The result is whether the entire string can be segmented
        return dp[s.length()];
    }
}
