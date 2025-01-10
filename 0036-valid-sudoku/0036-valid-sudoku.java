class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Use arrays of sets to track seen numbers in rows, columns, and sub-boxes
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        // Initialize the sets
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Iterate through each cell in the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num == '.') continue; // Skip empty cells

                // Check row
                if (rows[i].contains(num)) {
                    return false;
                }
                rows[i].add(num);

                // Check column
                if (cols[j].contains(num)) {
                    return false;
                }
                cols[j].add(num);

                // Check 3x3 sub-box
                int boxIndex = (i / 3) * 3 + (j / 3);
                if (boxes[boxIndex].contains(num)) {
                    return false;
                }
                boxes[boxIndex].add(num);
            }
        }

        return true;
    }
}
