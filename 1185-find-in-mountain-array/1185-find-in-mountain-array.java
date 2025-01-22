/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index);
 *     public int length();
 * }
 */

class Solution {
    // Private helper method for binary search
    private int binarySearch(MountainArray mountainArr, int target, int left, int right, boolean ascending) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = mountainArr.get(mid);
            
            if (midValue == target) {
                return mid;
            }
            
            if (ascending) {
                if (midValue < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (midValue > target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        // Step 1: Find the peak of the mountain array
        int left = 0, right = mountainArr.length() - 1;
        int peak = -1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1; // Move towards the peak
            } else {
                right = mid; // Narrow down the peak range
            }
        }
        peak = left;

        // Step 2: Search in the increasing part of the array
        int index = binarySearch(mountainArr, target, 0, peak, true);
        if (index != -1) {
            return index;
        }

        // Step 3: Search in the decreasing part of the array
        return binarySearch(mountainArr, target, peak + 1, mountainArr.length() - 1, false);
    }
}
