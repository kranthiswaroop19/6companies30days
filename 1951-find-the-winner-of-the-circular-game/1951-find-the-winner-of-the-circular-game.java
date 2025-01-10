class Solution {
    public int findTheWinner(int n, int k) {
        return josephus(n, k) + 1; // Adjusting 0-based indexing to 1-based
    }

    // Recursive function for the Josephus problem
    private int josephus(int n, int k) {
        if (n == 1) {
            return 0; // Base case: only one person left, index 0
        }
        // Recursive step: find the position of the winner in the reduced circle
        return (josephus(n - 1, k) + k) % n;
    
    }
}