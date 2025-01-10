import java.util.*;

class Solution {
    private int[][] rects;
    private int[] prefixSums;
    private int totalPoints;
    private Random random;

    public Solution(int[][] rects) {
        this.rects = rects;
        this.prefixSums = new int[rects.length];
        this.random = new Random();

        // Compute the prefix sum array based on the number of points in each rectangle
        int sum = 0;
        for (int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
            int area = (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            sum += area;
            prefixSums[i] = sum;
        }
        this.totalPoints = sum;
    }

    public int[] pick() {
        // Randomly choose a target point index
        int target = random.nextInt(totalPoints) + 1;

        // Find the rectangle index using binary search
        int rectIndex = binarySearch(target);

        // Get the rectangle's boundaries
        int[] rect = rects[rectIndex];
        int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];

        // Generate a random point within the chosen rectangle
        int x = x1 + random.nextInt(x2 - x1 + 1);
        int y = y1 + random.nextInt(y2 - y1 + 1);

        return new int[]{x, y};
    }

    private int binarySearch(int target) {
        int low = 0, high = prefixSums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (target <= prefixSums[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
