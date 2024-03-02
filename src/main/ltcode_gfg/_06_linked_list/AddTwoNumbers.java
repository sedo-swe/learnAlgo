package main.ltcode_gfg._06_linked_list;

import main.ltcode_gfg.utils.ListNode;
import main.ltcode_gfg.utils.PrintUtils;

/**
 * 2. Add Two Numbers (Medium)
 */

@FunctionalInterface
interface IntAddTwoNumbers {
    ListNode addTwoNumbers(ListNode l1, ListNode l2);
}

public class AddTwoNumbers {
    /*
        1st idea.
            run three times
                1. when both listnodes have values
                2. when l1 only has values
                3. when l2 only has values

        2nd idea.
            combine three individual runs into one

        Code perspective, 2nd idea looks clean, but speed perspective, 1st idea is much faster

        Both ways are time: O (n), space: O (1)
     */
    IntAddTwoNumbers intAddTwoNumbers = ((l1, l2) -> {
        ListNode ansH = null, cur = null, prev = null;
        ListNode l1Cur = l1, l2Cur = l2;
        int carrier = 0;

        while (l1Cur != null && l2Cur != null) {
            int digitSum = l1Cur.val + l2Cur.val + carrier;
            cur = new ListNode(digitSum % 10);
            carrier = digitSum / 10;

            if (ansH == null) {
                ansH = cur;
            }
            l1Cur = l1Cur.next;
            l2Cur = l2Cur.next;
            if (prev != null)
                prev.next = cur;
            prev = cur;
        }

        while (l1Cur != null) {
            int digitSum = l1Cur.val + carrier;
            cur = new ListNode(digitSum % 10);
            carrier = digitSum / 10;

            if (ansH == null) {
                ansH = cur;
            }
            l1Cur = l1Cur.next;
            if (prev != null)
                prev.next = cur;
            prev = cur;
        }

        while (l2Cur != null) {
            int digitSum = l2Cur.val + carrier;
            cur = new ListNode(digitSum % 10);
            carrier = digitSum / 10;

            if (ansH == null) {
                ansH = cur;
            }
            l2Cur = l2Cur.next;
            if (prev != null)
                prev.next = cur;
            prev = cur;
        }

        if (carrier != 0) {
            prev.next = new ListNode(carrier);
        }

        return ansH;
    });

    IntAddTwoNumbers intAddTwoNumbersImproved = ((l1, l2) -> {
        ListNode ansH = null, cur = null, prev = null;
        ListNode l1Cur = l1, l2Cur = l2;
        int carrier = 0;

        while (l1Cur != null || l2Cur != null) {
            int digitSum = (l1Cur != null ? l1Cur.val : 0) + (l2Cur != null ? l2Cur.val : 0) + carrier;
            cur = new ListNode(digitSum % 10);
            carrier = digitSum / 10;

            if (ansH == null) {
                ansH = cur;
            }
            l1Cur = l1Cur != null ? l1Cur.next : null;
            l2Cur = l2Cur != null ? l2Cur.next : null;
            if (prev != null)
                prev.next = cur;
            prev = cur;
        }

        if (carrier != 0) {
            prev.next = new ListNode(carrier);
        }

        return ansH;
    });

    public void test(IntAddTwoNumbers func) {
        ListNode t01_103 = new ListNode(3);
        ListNode t01_102 = new ListNode(4, t01_103);
        ListNode t01_101 = new ListNode(2, t01_102);
        ListNode t01_203 = new ListNode(4);
        ListNode t01_202 = new ListNode(6, t01_203);
        ListNode t01_201 = new ListNode(5, t01_202);
        System.out.println("Expected: [7,0,8], Actual: " + PrintUtils.printListNodeList(func.addTwoNumbers(t01_101, t01_201)));


        ListNode t02_103 = new ListNode(9);
        ListNode t02_102 = new ListNode(9, t02_103);
        ListNode t02_101 = new ListNode(9, t02_102);
        ListNode t02_201 = new ListNode(9);
        System.out.println("Expected: [8,0,0,1], Actual: " + PrintUtils.printListNodeList(func.addTwoNumbers(t02_101, t02_201)));
    }

    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();
        atn.test(atn.intAddTwoNumbers);

        /*ListNode l1 = null;
        ListNode l2 = l1;
        l1 = new ListNode(1);*/
    }
}
