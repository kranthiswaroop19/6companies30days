import java.util.Arrays;

class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages); // Sort the ages array
        int n = ages.length;
        int totalRequests = 0;

        for (int i = 0; i < n; i++) {
            int ageX = ages[i];
            if (ageX < 15) continue; // No friend requests if age[x] < 15

            // Find the valid range for age[y] using two pointers or binary search
            int minAge = (int) (0.5 * ageX + 7);

            // Find the first index where age[y] > minAge
            int lowerBound = findLowerBound(ages, minAge + 1);
            // Find the last index where age[y] <= ageX
            int upperBound = findUpperBound(ages, ageX);

            // Calculate valid requests in the range
            if (upperBound >= lowerBound) {
                totalRequests += (upperBound - lowerBound + 1);
                // Subtract one to exclude self-request (x == y)
                totalRequests -= 1;
            }
        }

        return totalRequests;
    }

    // Find the first index where age > target
    private int findLowerBound(int[] ages, int target) {
        int left = 0, right = ages.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ages[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // Find the last index where age <= target
    private int findUpperBound(int[] ages, int target) {
        int left = 0, right = ages.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ages[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
