class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // Sort the greed factors and cookie sizes
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0; // Pointers for children and cookies
        int count = 0;    // Count of satisfied children

        // Iterate until we run out of either children or cookies
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) { // If the cookie satisfies the child's greed
                count++;        // Increment the count of satisfied children
                i++;            // Move to the next child
            }
            j++; // Move to the next cookie (always move this)
        }

        return count; // Return the number of satisfied children
    }
}
