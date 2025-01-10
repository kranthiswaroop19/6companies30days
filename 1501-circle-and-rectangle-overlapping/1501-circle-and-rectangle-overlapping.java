class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
         int xClosest = Math.max(x1, Math.min(xCenter, x2));
        int yClosest = Math.max(y1, Math.min(yCenter, y2));

        // Calculate the squared distance from the circle's center to the closest point
        int distanceSquared = (xClosest - xCenter) * (xClosest - xCenter) 
                            + (yClosest - yCenter) * (yClosest - yCenter);

        // Check if the squared distance is within the circle's radius squared
        return distanceSquared <= radius * radius;
        
    }
}