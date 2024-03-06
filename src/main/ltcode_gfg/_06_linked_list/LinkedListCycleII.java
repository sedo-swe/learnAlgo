package main.ltcode_gfg._06_linked_list;

import main.ltcode_gfg.utils.ListNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 142. Linked List Cycle II (Medium)
 */

@FunctionalInterface
interface IntLinkedListCycleII {
    ListNode detectCycle(ListNode head);
}

public class LinkedListCycleII {

    /*
        1st idea.
            Use HashSet
            ==> Passed but slow, time: O (n), space: O (n)
     */
    IntLinkedListCycleII detectCycleSet = (head -> {
        Set<ListNode> visited = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (visited.contains(cur)) {
                return cur;
            } else {
                visited.add(cur);
            }
            cur = cur.next;
        }
        return null;
    });

    IntLinkedListCycleII detectCycleFastSlow = (head -> {
        ListNode meetingPoint = hasCycle(head);

        if (meetingPoint != null) {
            System.out.println(meetingPoint.val);
            ListNode potr1 = head;
            ListNode potr2 = meetingPoint;

            System.out.println("B: " + potr1.val + " " + potr2.val);
            while (potr1 != potr2) {
                potr1 = potr1.next;
                potr2 = potr2.next;
                System.out.println(potr1.val + " " + potr2.val);
            }

            return potr1;
        }
        return null;
    });

    public ListNode hasCycle(ListNode head) {
        if (head != null) {
            ListNode fastNode = head;
            ListNode slowNode = head;

            while (fastNode != null && fastNode.next != null) {
                fastNode = fastNode.next.next;
                slowNode = slowNode.next;

                if (fastNode == slowNode)
                    return fastNode;
            }
        }
        return null;
    }

    public void test(IntLinkedListCycleII func) {
//        ListNode t01_04 = new ListNode(-4);
//        ListNode t01_03 = new ListNode(0, t01_04);
//        ListNode t01_02 = new ListNode(2, t01_03);
//        ListNode t01_01 = new ListNode(3, t01_02);
//        t01_04.next = t01_02;
//
//        ListNode result = func.detectCycle(t01_01);
//        System.out.println("Expected: 2, Actual: " + (result == null ? -1 : result.val));


//        ListNode t02_02 = new ListNode(2);
//        ListNode t02_01 = new ListNode(1, t02_02);
//        t02_02.next = t02_01;
//
//        ListNode result2 = func.detectCycle(t02_01);
//        System.out.println("Expected: 1, Actual: " + (result2 == null ? -1 : result2.val));
//
//        ListNode t03_01 = new ListNode(1);
//        ListNode result3 = func.detectCycle(t03_01);
//        System.out.println("Expected: -1, Actual: " + (result3 == null ? -1 : result3.val));

        ListNode t04_05 = new ListNode(5);
        ListNode t04_04 = new ListNode(4, t04_05);
        ListNode t04_03 = new ListNode(3, t04_04);
        ListNode t04_02 = new ListNode(2, t04_03);
        ListNode t04_01 = new ListNode(1, t04_02);
        t04_05.next = t04_03;

        ListNode result4 = func.detectCycle(t04_01);
        System.out.println("Expected: 3, Actual: " + (result4 == null ? -1 : result4.val));
    }

    public static void main(String[] args) {
        LinkedListCycleII l = new LinkedListCycleII();
//        l.test(l.detectCycleSet);
        l.test(l.detectCycleFastSlow);
    }

}
