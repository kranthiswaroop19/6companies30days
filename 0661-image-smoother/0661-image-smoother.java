class Solution {
    public int[][] imageSmoother(int[][] img) {
        // Dimensions of the input image
        int m = img.length, n = img[0].length;
        // Resultant smoothed image
        int[][] result = new int[m][n];
        
        // Directions for the 3x3 grid
        int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        
        // Traverse each cell of the image
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int total = 0, count = 0;
                // Check all 3x3 neighbors
                for (int k = 0; k < 9; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    // Check bounds
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        total += img[x][y];
                        count++;
                    }
                }
                // Calculate the smoothed value
                result[i][j] = total / count;
            }
        }
        
        return result;
    }
}
