class Solution {
    public int squareFreeSubsets(int[] nums) {
        final int MOD = 1_000_000_007;
        int[] primeFactors = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int maxNum = 30;
        int[] numMask = new int[maxNum + 1];

        // Precompute the square-free bitmask for numbers 1 to 30
        for (int i = 1; i <= maxNum; i++) {
            for (int j = 0; j < primeFactors.length; j++) {
                int prime = primeFactors[j];
                if (i % (prime * prime) == 0) {
                    numMask[i] = -1; // Not square-free
                    break;
                }
                if (i % prime == 0) {
                    numMask[i] |= (1 << j);
                }
            }
        }

        // Count frequency of each number in nums
        int[] freq = new int[maxNum + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // DP array to count subsets with specific bitmask
        int[] dp = new int[1 << primeFactors.length];
        dp[0] = 1; // Base case: empty subset

        // Update DP for each number
        for (int num = 2; num <= maxNum; num++) {
            if (freq[num] == 0 || numMask[num] == -1) {
                continue; // Skip non-square-free numbers or numbers not in the array
            }
            int mask = numMask[num];
            for (int prevMask = (1 << primeFactors.length) - 1; prevMask >= 0; prevMask--) {
                if ((prevMask & mask) == 0) { // No conflict in prime factors
                    dp[prevMask | mask] = (int)((dp[prevMask | mask] + (long) dp[prevMask] * freq[num]) % MOD);
                }
            }
        }

        // Sum all subsets (including empty subset for now)
        long result = 0;
        for (int count : dp) {
            result = (result + count) % MOD;
        }

        // Handle the special case of ones
        int countOfOnes = freq[1];
        if (countOfOnes > 0) {
            result = (result * modPow(2, countOfOnes, MOD)) % MOD;
        }

        // Subtract the empty subset correctly (add MOD to ensure no negative result)
        result = (result - 1 + MOD) % MOD;

        return (int) result;
    }

    // Helper method to compute base^exp % mod
    private long modPow(int base, int exp, int mod) {
        long result = 1;
        long power = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * power) % mod;
            }
            power = (power * power) % mod;
            exp >>= 1;
        }
        return result;
    }
}
