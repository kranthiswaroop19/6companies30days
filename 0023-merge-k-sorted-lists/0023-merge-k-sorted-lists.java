/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Base case: If the input array is null or empty, return null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-Heap to store ListNodes, sorted by their values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-empty list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // Dummy node to help build the merged linked list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge process using the heap
        while (!minHeap.isEmpty()) {
            // Extract the smallest node
            ListNode smallest = minHeap.poll();
            // Append it to the merged list
            current.next = smallest;
            current = current.next;

            // If the extracted node has a next node, add it to the heap
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        // Return the merged list, skipping the dummy node
        return dummy.next;
    }
}
