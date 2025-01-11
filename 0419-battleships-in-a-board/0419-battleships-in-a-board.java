class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length;       // Number of rows
        int n = board[0].length;    // Number of columns
        int count = 0;              // Counter for battleships

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Check if the current cell is 'X'
                if (board[i][j] == 'X') {
                    // Check if it's the start of a new battleship
                    if ((i == 0 || board[i - 1][j] != 'X') && // No battleship above
                        (j == 0 || board[i][j - 1] != 'X')) { // No battleship to the left
                        count++; // Increment the count
                    }
                }
            }
        }
        return count; // Return the total number of battleships
    }
}
