//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int n = Integer.parseInt(in.readLine());
            String contact[] = in.readLine().trim().split("\\s+");
            String s = in.readLine();
            
            Solution ob = new Solution();
            ArrayList<ArrayList<String>> ans = ob.displayContacts(n, contact, s);
            for(int i = 0;i < ans.size();i++){
                for(int j = 0;j < ans.get(i).size();j++)
                    System.out.print(ans.get(i).get(j) + " ");
                System.out.println();
            }
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


//User function Template for Java
class Solution {
    static ArrayList<ArrayList<String>> displayContacts(int n, String contact[], String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        
        // Process each prefix of string s
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i); // Get prefix of length i
            TreeSet<String> matchingContacts = new TreeSet<>(); // Set to store unique contacts
            
            // Check each contact and see if it starts with the current prefix
            for (String contactName : contact) {
                if (contactName.startsWith(prefix)) {
                    matchingContacts.add(contactName);
                }
            }
            
            // If there are any matching contacts, add them to the result
            if (matchingContacts.size() > 0) {
                result.add(new ArrayList<>(matchingContacts));
            } else {
                // If no matches, add "0"
                result.add(new ArrayList<>(Collections.singletonList("0")));
            }
        }
        
        return result;
    }
}
