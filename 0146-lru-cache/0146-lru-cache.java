import java.util.*;

class LRUCache {

    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Create dummy head and tail for the doubly linked list
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1; // Key not found
        }
        Node node = map.get(key);
        moveToHead(node); // Mark as recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Update the value and move to the head
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Add a new node
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);

            // If the cache exceeds capacity, remove the least recently used node
            if (map.size() > capacity) {
                Node lru = removeTail();
                map.remove(lru.key);
            }
        }
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
