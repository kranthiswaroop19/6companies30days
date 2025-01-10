class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;

        // Frequency counters for digits not in the correct position
        int[] secretFreq = new int[10];
        int[] guessFreq = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if (s == g) {
                // Count as a bull
                bulls++;
            } else {
                // Increment the frequency counts for mismatched digits
                secretFreq[s - '0']++;
                guessFreq[g - '0']++;
            }
        }

        // Calculate cows based on the minimum counts of each digit in secret and guess
        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretFreq[i], guessFreq[i]);
        }

        // Return the result in "xAyB" format
        return bulls + "A" + cows + "B";
    }
}
