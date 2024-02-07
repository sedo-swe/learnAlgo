package main.ltcode_gfg._06_linked_list;

import main.ltcode_gfg.utils.ListNode;

import java.util.ArrayList;

/**
 *  148. Sort List (Medium)
 */
@FunctionalInterface
interface IntSortList {
    ListNode sortList(ListNode head);
}
public class SortList {

    IntSortList sortList1st = ((head) -> {
        if (head == null || head.next == null) {
            return head;
        }
        ArrayList<ListNode> sortedArray = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            sortedArray.add(current);
            current = current.next;
        }
        sortedArray.sort((a1, b1) -> a1.val - b1.val);

        ListNode newHead = sortedArray.get(0);
        current = newHead;
        for (int i=1; i<sortedArray.size(); i++) {
            current.next = sortedArray.get(i);
            current = current.next;
        }
        current.next = null;
        return newHead;
    });

    IntSortList sortListByMergeSort = ((head) -> {
//        System.out.println(this.getMiddle(head).val);
        return mergeSort(head);
    });

    public ListNode sortedMerge(ListNode a, ListNode b) {
        ListNode result = null;
        // Base Case
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        // Pick either a or b, and recur
        if (a.val <= b.val) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    public ListNode mergeSort(ListNode head) {
        // Base Case: if head is null
        if (head == null || head.next == null) {
            return head;
        }

        // Get the middle of the list
        ListNode middle = this.getMiddle(head);
        ListNode nextOfMiddle = middle.next;

        // Set the next of middle node to null
        middle.next = null;

        // Apply mergeSort on left list
        ListNode left = mergeSort(head);

        // Apply mergeSoft on right list
        ListNode right = mergeSort(nextOfMiddle);

        // Merge the left and right lists
        ListNode sortedList = sortedMerge(left, right);
        return sortedList;
    }

    private ListNode getMiddle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public void test(IntSortList func) {
        ListNode test1_4 = new ListNode(3);
        ListNode test1_3 = new ListNode(1, test1_4);
        ListNode test1_2 = new ListNode(2, test1_3);
        ListNode test1_1 = new ListNode(4, test1_2);
        System.out.println("Expected: [1, 2, 3, 4], Actual: [" + this.printListNodes(func.sortList(test1_1)) + "]");

        ListNode test2_5 = new ListNode(0);
        ListNode test2_4 = new ListNode(4, test2_5);
        ListNode test2_3 = new ListNode(3, test2_4);
        ListNode test2_2 = new ListNode(5, test2_3);
        ListNode test2_1 = new ListNode(-1, test2_2);
        System.out.println("Expected: [-1, 0, 3, 4, 5], Actual: [" + this.printListNodes(func.sortList(test2_1)) + "]");

        System.out.println("Expected: [], Actual: [" + this.printListNodes(func.sortList(null)) + "]");
    }

    private String printListNodes(ListNode head) {
        StringBuffer sb = new StringBuffer();
        ListNode current = head;
        while (current != null) {
            sb.append(current.val + ", ");
            current = current.next;
        }

        return sb.length() >= 3 ? sb.subSequence(0, sb.length()-2).toString() : sb.toString();
    }

    public static void main(String[] args) {
        SortList sortList = new SortList();
//        sortList.test(sortList.sortList1st);
        sortList.test(sortList.sortListByMergeSort);
    }
}
