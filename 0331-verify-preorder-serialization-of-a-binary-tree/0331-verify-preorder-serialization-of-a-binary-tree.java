class Solution {
    public boolean isValidSerialization(String preorder) {
        // Step 1: Split the input string by commas to process nodes
        String[] nodes = preorder.split(",");
        
        // Step 2: Initialize slots to 1 (root node)
        int slots = 1;

        // Step 3: Process each node
        for (String node : nodes) {
            // Consume one slot for the current node
            slots--;

            // If slots become negative, the serialization is invalid
            if (slots < 0) {
                return false;
            }

            // If the node is not null, it adds two new slots for its children
            if (!node.equals("#")) {
                slots += 2;
            }
        }

        // Step 4: All slots should be consumed for the serialization to be valid
        return slots == 0;
    }
}
