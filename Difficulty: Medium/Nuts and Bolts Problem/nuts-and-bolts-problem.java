//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            int n = Integer.parseInt(br.readLine().trim());
            char[] nuts = new char[n], bolts = new char[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                nuts[i] = (inputLine[i].charAt(0));
            }
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                bolts[i] = (inputLine[i].charAt(0));
            }

            new Solution().matchPairs(n, nuts, bolts);
            for (int i = 0; i < n; i++) {
                System.out.print(nuts[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(bolts[i] + " ");
            }
            System.out.println();
        
System.out.println("~");
}
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    // Predefined order for sorting
    private static final char[] ORDER = { '!', '#', '$', '%', '&', '*', '?', '@', '^' };

    void matchPairs(int n, char[] nuts, char[] bolts) {
        // Create a mapping for the order
        HashMap<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < ORDER.length; i++) {
            orderMap.put(ORDER[i], i);
        }

        // Sort both nuts and bolts using quicksort-based logic
        quickSort(nuts, bolts, 0, n - 1, orderMap);
    }

    private void quickSort(char[] nuts, char[] bolts, int low, int high, HashMap<Character, Integer> orderMap) {
        if (low < high) {
            // Partition bolts using the first nut as the pivot
            int pivotIndex = partition(bolts, low, high, nuts[low], orderMap);

            // Partition nuts using the matched bolt as the pivot
            partition(nuts, low, high, bolts[pivotIndex], orderMap);

            // Recur for left and right partitions
            quickSort(nuts, bolts, low, pivotIndex - 1, orderMap);
            quickSort(nuts, bolts, pivotIndex + 1, high, orderMap);
        }
    }

    private int partition(char[] arr, int low, int high, char pivot, HashMap<Character, Integer> orderMap) {
        int i = low;
        for (int j = low; j < high; j++) {
            if (orderMap.get(arr[j]) < orderMap.get(pivot)) {
                swap(arr, i, j);
                i++;
            } else if (arr[j] == pivot) {
                swap(arr, j, high); // Move pivot to end
                j--; // Re-check the swapped element
            }
        }
        swap(arr, i, high); // Place the pivot in its correct position
        return i;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
