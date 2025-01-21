import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // Sort the asteroids array in ascending order
        Arrays.sort(asteroids);

        // Use a long to avoid overflow during mass accumulation
        long currentMass = mass;

        // Iterate through the sorted asteroids
        for (int asteroid : asteroids) {
            if (currentMass >= asteroid) {
                currentMass += asteroid; // Absorb the asteroid
            } else {
                return false; // Planet is destroyed
            }
        }

        // All asteroids are destroyed
        return true;
    }
}
