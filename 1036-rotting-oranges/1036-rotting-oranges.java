class Solution {
    public int orangesRotting(int[][] grid) {
         int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        // Initialize the queue with all rotten oranges and count fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // Add rotten orange position
                } else if (grid[i][j] == 1) {
                    freshOranges++; // Count fresh oranges
                }
            }
        }

        // If there are no fresh oranges, return 0
        if (freshOranges == 0) {
            return 0;
        }

        // Directions array for 4-directional movement
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = 0;

        // Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rotted = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                // Check all 4 adjacent cells
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];

                    // If the cell is within bounds and has a fresh orange
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2; // Rot the fresh orange
                        queue.offer(new int[]{newX, newY}); // Add it to the queue
                        freshOranges--; // Decrease count of fresh oranges
                        rotted = true;
                    }
                }
            }

            // Increment minutes only if at least one orange rotted in this iteration
            if (rotted) {
                minutes++;
            }
        }

        // If there are still fresh oranges left, return -1
        return freshOranges == 0 ? minutes : -1;
    }
}