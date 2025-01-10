class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();
        
        while (columnNumber > 0) {
            columnNumber--; // Adjust for 1-based indexing
            int remainder = columnNumber % 26; // Find the current column letter
            char currentChar = (char) ('A' + remainder); // Convert to character
            result.append(currentChar); // Append the character
            columnNumber /= 26; // Move to the next digit
        }
        
        return result.reverse().toString(); // Reverse to get the correct order
    }
}
