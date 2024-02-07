package main.ltcode_gfg._06_linked_list;

import main.ltcode_gfg.utils.ListNode;

import java.util.Stack;

@FunctionalInterface
interface IntReverseLinkedList {
    ListNode IntReverseList(ListNode head);
}

/**
 *  206. Reverse Linked List (Easy)
 */
public class ReverseLinkedList {
    /*
        First idea
            - Use stack, reverse using pop
            --> O(n), O(n)

        Second idea
            - Set two nodes for another head and previous
              read one from original list and connect previous into it, and assign it into previous
            --> O(n), O(n)
            ==> Accepted, 0 ms (100.00%), 41.59 MB (21.02%)
     */
    IntReverseLinkedList reverseLinkedListIter1st = ((head) -> {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode current = stack.pop(), newHead = current;
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
        current.next = null;
        return newHead;
    });

    IntReverseLinkedList reverseLinkedListIter2nd = ((head) -> {
        ListNode reversedHead = null, next = null, current = head;

        if (current == null || current.next == null) {
            return current;
        }

        while (current != null) {
//            if (reversedHead == null) {
//                reversedHead = new ListNode(current.val);
//                current = current.next;
//            } else {
//            }
            next = current.next;
            current.next = reversedHead;
            reversedHead = current;
            current = next;
        }

        return reversedHead;
    });

    IntReverseLinkedList reverseLinkedListIterSolution = ((head) -> {
        ListNode current = head;
        ListNode previous = null;
        ListNode nextCurrent = null;

        while (current != null) {
            nextCurrent = current.next;
            current.next = previous;
            previous = current;
            current = nextCurrent;
        }

        return previous;
    });

    IntReverseLinkedList reverseLinkedListRecurSolution = ((head) -> {
        return rev(head, null);
    });

    private ListNode rev(ListNode node, ListNode pre) {
        if (node == null) {
            return pre;
        }

        ListNode nextNode = node.next;
        node.next = pre;
        return rev(nextNode, node);
    }

    private String printList(ListNode head) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val + ",");
            cur = cur.next;
        }
        if (sb.lastIndexOf(",") > 0) {
            sb.deleteCharAt(sb.lastIndexOf(",") );
        }
        sb.append("]");
        return sb.toString();
    }

    public void test(IntReverseLinkedList func) {
//        {1, 2, 3, 4, 5}
        ListNode n1_5 = new ListNode(5);
        ListNode n1_4 = new ListNode(4, n1_5);
        ListNode n1_3 = new ListNode(3, n1_4);
        ListNode n1_2 = new ListNode(2, n1_3);
        ListNode n1_1 = new ListNode(1, n1_2);
        System.out.println("Expected: [5,4,3,2,1], Actual: " + this.printList(func.IntReverseList(n1_1)));

        ListNode n2_2 = new ListNode(2);
        ListNode n2_1 = new ListNode(1, n2_2);
        System.out.println("Expected: [2,1], Actual: " + this.printList(func.IntReverseList(n2_1)));

        System.out.println("Expected: [], Actual: " + this.printList(func.IntReverseList(null)));
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        reverseLinkedList.test(reverseLinkedList.reverseLinkedListIter2nd);
        System.out.println("=======================");
        reverseLinkedList.test(reverseLinkedList.reverseLinkedListRecurSolution);
        System.out.println("=======================");
        reverseLinkedList.test(reverseLinkedList.reverseLinkedListIter1st);
    }
}
