import java.util.*;

class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // Memoization map to store results of subproblems
        Map<String, Integer> memo = new HashMap<>();
        
        // Call the helper function to calculate the minimum cost
        return dfs(price, special, needs, memo);
    }
    
    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<String, Integer> memo) {
        // Convert the needs array to a string key for memoization
        String key = needs.toString();
        
        // If we've already computed the minimum cost for this needs state, return the result
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // Base case: If all needs are zero, no cost is required
        boolean allZero = true;
        for (int need : needs) {
            if (need > 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) {
            return 0;
        }
        
        // Calculate the cost if we buy the items individually without using any special offer
        int minCost = 0;
        for (int i = 0; i < needs.size(); i++) {
            minCost += needs.get(i) * price.get(i);
        }
        
        // Try each special offer
        for (List<Integer> offer : special) {
            // Check if we can apply this offer
            List<Integer> newNeeds = new ArrayList<>();
            boolean canApplyOffer = true;
            for (int i = 0; i < needs.size(); i++) {
                if (needs.get(i) < offer.get(i)) {
                    canApplyOffer = false;
                    break;
                }
                newNeeds.add(needs.get(i) - offer.get(i));
            }
            
            // If the offer can be applied, recursively calculate the new cost after applying the offer
            if (canApplyOffer) {
                int offerCost = offer.get(needs.size());
                minCost = Math.min(minCost, dfs(price, special, newNeeds, memo) + offerCost);
            }
        }
        
        // Memoize the result for the current needs state
        memo.put(key, minCost);
        return minCost;
    }
}
