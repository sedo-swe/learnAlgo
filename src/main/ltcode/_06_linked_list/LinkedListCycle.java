package main.ltcode._06_linked_list;

import main.ltcode.utils.ListNode;

import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
interface IntLinkedListCycle {
    boolean intHasCycle(ListNode head);
}

/**
 *  141. Linked List Cycle (Easy)
 */
public class LinkedListCycle {
    /*
        First idea
            - use hashmap to store nodes visited, check current is whether visited or not
              if met visited, then it means it has cycle
            --> O(n), O(n)
            ==> Accepted: 5 ms (14.40%), 43.97 MB (13.78%)

        Second idea
            -
     */
    IntLinkedListCycle intLinkedListCycle1st = ((head) -> {
        if (head == null || head.next == null) {
            return false;
        }

        Map<ListNode, Integer> visited = new HashMap<>();
        int index = -1;
        while (head != null) {
            index++;
            if (visited.containsKey(head)) {
                return true;
            } else {
                visited.put(head, index);
            }
            head = head.next;
        }
        return false;
    });

    IntLinkedListCycle intLinkedListCycleSolution = ((head) -> {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    });

    public void test(IntLinkedListCycle func) {
        ListNode node1_4 = new ListNode(-4);
        ListNode node1_3 = new ListNode(0, node1_4);
        ListNode node1_2 = new ListNode(2, node1_3);
        ListNode node1_1 = new ListNode(3, node1_2);
        node1_4.next = node1_2;
        System.out.println("Expected: true, Actual: " + func.intHasCycle(node1_1));

        ListNode node2_2 = new ListNode(2);
        ListNode node2_1 = new ListNode(0, node2_2);
        node2_2.next = node2_1;
        System.out.println("Expected: true, Actual: " + func.intHasCycle(node2_1));

        ListNode node3_1 = new ListNode(1);
        System.out.println("Expected: false, Actual: " + func.intHasCycle(node3_1));

        ListNode node4_2 = new ListNode(2);
        ListNode node4_1 = new ListNode(1, node4_2);
        System.out.println("Expected: false, Actual: " + func.intHasCycle(node4_1));

        ListNode prev = null, last = null, head = null;
        for (int i = 0; i < 1000; i++) {
            if (i == 0) {
                last = new ListNode(i);
                head = last;
            } else {
                last = new ListNode(i, prev);
            }
            prev = last;
        }
        head.next = last.next;
        System.out.println("Expected: true, Actual: " + func.intHasCycle(head));
    }

    public static void main(String[] args) {
        LinkedListCycle linkedListCycle = new LinkedListCycle();
        System.out.println("1st trial \n===================================");
        linkedListCycle.test(linkedListCycle.intLinkedListCycle1st);

        System.out.println("\nSolution \n===================================");
        linkedListCycle.test(linkedListCycle.intLinkedListCycleSolution);
    }
}
