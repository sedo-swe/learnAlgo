package main.ltcode._06_linked_list;

import main.ltcode.utils.ListNode;
import main.ltcode.utils.PrintUtils;

/**
 * 143. Reorder List (Medium)
 */
@FunctionalInterface
interface IntReorderList {
    void reorderList(ListNode head);
}

public class ReorderList {
    ListNode head = null;
    IntReorderList intReorderList = (head -> {
        this.head = head;
        reorder2(head, 1);
    });

    private ListNode reorder(ListNode current) {
        if (current == null || current.next == null) {
            return current;
        }

        ListNode returned = reorder(current.next);
        System.out.println("returned: "+ (returned != null ? returned.val : "null"));
        System.out.println("  1. head: "+ this.head.val + ", current: " + (current != null ? current.val : "null") + " " + (this.head == current) + head +" "+current);
        if (this.head == returned) {
            return null;
        }
        if (returned != null) {
            ListNode headNext = this.head.next;
            this.head.next = returned;
            returned.next = headNext;
            current.next = null;
            this.head = this.head.next.next;
        } else {
            return null;
        }
        System.out.println("  2. head: "+ this.head.val + ", current: " + (current != null ? current.val : "null"));

        if (this.head == current) {
            return null;
        }

        return current;
    }

    private ListNode reorder2(ListNode current, int index) {
        if (current == null || current.next == null) {
            return current;
        }

        ListNode returned = reorder2(current.next, index + 1);

        if (this.head == current) {
            return null;
        }
        if (returned != null) {
            if (index > 1) {
                ListNode headNext = this.head.next;
                this.head.next = returned;
                returned.next = headNext;
                current.next = null;
                this.head = this.head.next.next;
                if (this.head == current) {
                    return null;
                }
            }
        } else {
            return null;
        }
        return current;
    }

    public void test(IntReorderList func) {
        ListNode l01_04 = new ListNode(4);
        ListNode l01_03 = new ListNode(3, l01_04);
        ListNode l01_02 = new ListNode(2, l01_03);
        ListNode l01_01 = new ListNode(1, l01_02);

        func.reorderList(l01_01);
        System.out.println("Expected: [1, 4, 2, 3], Actual: " + PrintUtils.printListNodeList(l01_01));

        ListNode l02_05 = new ListNode(5);
        ListNode l02_04 = new ListNode(4, l02_05);
        ListNode l02_03 = new ListNode(3, l02_04);
        ListNode l02_02 = new ListNode(2, l02_03);
        ListNode l02_01 = new ListNode(1, l02_02);

        func.reorderList(l02_01);
        System.out.println("Expected: [1, 5, 2, 4, 3], Actual: " + PrintUtils.printListNodeList(l02_01));
    }

    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        reorderList.test(reorderList.intReorderList);
    }
}
