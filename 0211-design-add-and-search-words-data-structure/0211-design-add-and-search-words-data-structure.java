class WordDictionary {

    private class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26]; // For 26 lowercase English letters
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        return searchInNode(word, 0, root);
    }

    private boolean searchInNode(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index);
        if (c == '.') {
            // Check all possible children for the wildcard '.'
            for (TrieNode child : node.children) {
                if (child != null && searchInNode(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // Check for the specific character
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                return false;
            }
            return searchInNode(word, index + 1, node.children[charIndex]);
        }
    }
}
