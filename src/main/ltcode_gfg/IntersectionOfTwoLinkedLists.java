package main.ltcode_gfg;

import main.ltcode_gfg.utils.ListNode;

@FunctionalInterface
interface IntIntersectionOfTwoLinkedLists {
    ListNode getIntersectionNode(ListNode headA, ListNode headB);
}
/**
 *  160. Intersection of Two Linked Lists (
 */
public class IntersectionOfTwoLinkedLists {
    /*
        First idea
            - Use HashMap, put all nodes of HeadA
            --> O(m + n), O(m)
            ==> Accepted: 5 ms (25.93%), 46.8 MB (27.02%)

        Second idea (for O(1) space)
            - Find length of both lists, skip the difference on longer side and start compare
            --> O(m + n), O(1)
     */
    IntIntersectionOfTwoLinkedLists intIntersectionOfTwoLinkedLists1st = ((headA, headB) -> {

        int lengthA = 0, lengthB = 0;
        ListNode curA = headA, curB = headB;
        while (curA != null) {
            lengthA++;
            curA = curA.next;
        }

        curB = headB;
        while (curB != null) {
            lengthB++;
            curB = curB.next;
        }

        ListNode longer = null, shorter = null;
        if (lengthA > lengthB) {
            longer = headA;
            shorter = headB;

        } else if (lengthA < lengthB) {
            longer = headB;
            shorter = headA;
        }

        return null;

    });

    public void test(IntIntersectionOfTwoLinkedLists func) {
        ListNode listNode1_A1 = new ListNode(4);
        ListNode listNode1_A2 = new ListNode(1);
        ListNode listNode1_B1 = new ListNode(5);
        ListNode listNode1_B2 = new ListNode(6);
        ListNode listNode1_B3 = new ListNode(1);
        ListNode listNode1_C1 = new ListNode(8);
        ListNode listNode1_C2 = new ListNode(4);
        ListNode listNode1_C3 = new ListNode(5);
        listNode1_A1.next = listNode1_A2;
        listNode1_A2.next = listNode1_C1;
        listNode1_B1.next = listNode1_B2;
        listNode1_B2.next = listNode1_B3;
        listNode1_B3.next = listNode1_C1;
        listNode1_C1.next = listNode1_C2;
        listNode1_C2.next = listNode1_C3;

        ListNode result1 = func.getIntersectionNode(listNode1_A1, listNode1_B1);
        System.out.println("Expected: 8, Actual: " + result1 != null ? result1.val : -1);
    }

    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists intersectionOfTwoLinkedLists = new IntersectionOfTwoLinkedLists();
        intersectionOfTwoLinkedLists.test(intersectionOfTwoLinkedLists.intIntersectionOfTwoLinkedLists1st);
    }
}
