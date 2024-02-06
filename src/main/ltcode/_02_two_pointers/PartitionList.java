package main.ltcode._02_two_pointers;

import main.ltcode.utils.ListNode;

/**
 * 86. Partition List (Medium)
 */
@FunctionalInterface
interface IntPartitionList {
    ListNode partition(ListNode head, int x);
}
public class PartitionList {
    IntPartitionList intPartitionList1st = ((head, x) -> {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = null;   // indicates the last node that is smaller than pivot (located before pivot)
        ListNode right = null;   // indicates the first node that is after pivot
        ListNode prevRight = null;
        ListNode pivot = null;
        ListNode curr = null;

        // Find pivot
            // Find last node smaller than pivot, but stop searching if next one is bigger than pivot
        left = null;
        curr = head;
        boolean foundLeft = false;
        while (curr != null) {
            if (curr.val == x) {
                pivot = curr;
                curr = null;
            } else {
                if (!foundLeft && curr.val < x) {
                    left = curr;
                    foundLeft = true;
                }
                curr = curr.next;
            }
        }

        if (pivot == null) {
            return head;
        }

        if (left == null) { // head is bigger than pivot or pivot
            ListNode prevTemp = head;
            ListNode temp = head.next;
            while (temp != null) {
                if (temp.val < x) {
                    prevTemp.next = temp.next;
                    left = new ListNode(temp.val, head);
                    head = left;
                    temp = null;
                } else {
                    prevTemp = temp;
                    temp = temp.next;
                }
            }
        }

        if (left == null) {
            return head;
        }

        // Move all elements smaller than pivot before bigger than pivot that is on the left side of pivot
        ListNode prevCurr = left;                 // left: 1(4), pC: 4(3)
        curr = left.next;                         // curr: 3(0)
        while (curr!= null && curr != pivot) {    // 1-4-3-0-5-2
            if (curr.val < x) {                   // 1-4-3-0-5-2
                // move right next to left
                ListNode nextOfCurr = curr.next;  // nOc: 3(0)
                ListNode nextOfLeft = left.next;  // nOl: 4(3)
                left.next = curr;
                curr.next = nextOfLeft;
                prevCurr.next = nextOfCurr;
                curr = nextOfCurr;

                // assign left to next
                left = left.next;
            } else {
                prevCurr = curr;
                curr = curr.next;
            }
        }

        // Set right with a node right next to pivot
        // Set prevRight to use as a parent (initial would be pivot)
        right = pivot.next;     // pivot: 3(2), left: 2(2'), right: 2'(null)
        prevRight = pivot;      // pR: 3(5)

        // Compare right node with pivot, if less than pivot, then move it next to left node
        while (right != null) {                     // 1-4-3-2-5-2'
            if (right.val < x) {                    // 1>2>2'>4-3>5-
                // move right next to left
                ListNode nextOfRight = right.next;  // nOr: null
                ListNode nextOfLeft = left.next;    // nOl: 4(3)
                left.next = right;
                right.next = nextOfLeft;
                prevRight.next = nextOfRight;
                right = nextOfRight;

                // assign left to next
                left = left.next;
            } else {
                prevRight = right;
                right = right.next;
            }
        }

        // Continue until right reaches end of list
        return head;
    });

    /*
        O(n) for time, O(1) for memory
        More complex compare to using extra lists, but don't use extra memory for two lists.
     */
    IntPartitionList intPartitionList2nd = ((head, x) -> {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tailLess = null;   // indicates the last node that is less than pivot

        if (head.val < x) { // Check whether first node is less than pivot
            tailLess = head;
        }

        if (tailLess == null) { // head is bigger than pivot or pivot. Find first less element, and bring it to first place
            ListNode prevTemp = head;
            ListNode temp = head.next;
            while (temp != null) {
                if (temp.val < x) {
                    prevTemp.next = temp.next;
                    tailLess = new ListNode(temp.val, head);
                    head = tailLess;
                    temp = null;
                } else {
                    prevTemp = temp;
                    temp = temp.next;
                }
            }
        }

        if (tailLess == null) { // every element is greater than or equal to pivot
            return head;
        }

        // Move all elements less than pivot to the front of elements greater than pivot
        ListNode prevCurr = tailLess;                           // ll: 2(0), pC: 4(1)
        ListNode curr = tailLess.next;                          // curr: 1(3)
        boolean needToMove = false;                             // move: true
        while (curr!= null) {                                   // 2-0-4-1-3-1'-4'-0'-3', [4]
            if (curr.val < x) {                                 // 2-0-4-1-3-1'-4'-0'-3'
                if (needToMove) {   // move current node to next to tail of less (tailLess)
                    ListNode nextOfCurr = curr.next;            // nOc: 3(1')
                    ListNode nextOfTailLess = tailLess.next;    // nOl: 1'(null)
                    tailLess.next = curr;
                    curr.next = nextOfTailLess;
                    prevCurr.next = nextOfCurr;
                    curr = nextOfCurr;
                } else {
                    prevCurr = curr;
                    curr = curr.next;
                }
                tailLess = tailLess.next;
            } else {
                needToMove = true;
                prevCurr = curr;
                curr = curr.next;
            }
        }

        return head;
    });

    /*
        O(n) for time, O(n) for memory
        More simple compare to not using extra lists.
     */
    IntPartitionList intPartitionListSol = ((head, x) -> {
        ListNode front = new ListNode();
        ListNode back = new ListNode();
        ListNode frontCurr = front;
        ListNode backCurr = back;
        while (head != null) {
            if (head.val < x) {
                frontCurr.next = head;
                frontCurr = frontCurr.next;
            } else {
                backCurr.next = head;
                backCurr = backCurr.next;
            }
            head = head.next;
        }
        frontCurr.next = back.next;
        backCurr.next = null;
        return front.next;
    });

    /* Sol from T - Start */
    private static class PartialList {
        ListNode head;
        ListNode tail;

        PartialList() {}

        void add(ListNode node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
    }

    public static ListNode partition(ListNode head, int x) {
        PartialList left = new PartialList();
        PartialList right = new PartialList();

        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                left.add(current);
            } else {
                right.add(current);
            }
            current = current.next;
        }
        if (left.head == null) {
            return right.head;
        } else if (right.head == null) {
            return left.head;
        } else {
            left.tail.next = right.head;
            right.tail.next = null;
            return left.head;
        }
    }
    /* Sol from T - End   */

    public void test(IntPartitionList func) {
        // 1-4-3-2-5-2, 3 ==> 1-2-2-(4)-3-(5)
        ListNode t1_6 = new ListNode(2);
        ListNode t1_5 = new ListNode(5, t1_6);
        ListNode t1_4 = new ListNode(2, t1_5);
        ListNode t1_3 = new ListNode(3, t1_4);
        ListNode t1_2 = new ListNode(4, t1_3);
        ListNode t1_1 = new ListNode(1, t1_2);
        System.out.println("Expected: [1-2-2-4-3-5], Actual: "+this.printListNodes(func.partition(t1_1, 3)));

        // 2-1, 2 ==> 1-2
        ListNode t2_2 = new ListNode(1);
        ListNode t2_1 = new ListNode(2, t2_2);
        System.out.println("Expected: [1-2], Actual: "+this.printListNodes(func.partition(t2_1, 2)));

        // 4-3-2-5-2, 3 ==> 2-2-4-3-5
        ListNode t3_5 = new ListNode(2);
        ListNode t3_4 = new ListNode(5, t3_5);
        ListNode t3_3 = new ListNode(2, t3_4);
        ListNode t3_2 = new ListNode(3, t3_3);
        ListNode t3_1 = new ListNode(4, t3_2);
        System.out.println("Expected: [2-2-4-3-5], Actual: "+this.printListNodes(func.partition(t3_1, 3)));

        // 1-4-3-0-5-2, 2 ==> 1-0-4-3-5-2
        ListNode t4_6 = new ListNode(2);
        ListNode t4_5 = new ListNode(5, t4_6);
        ListNode t4_4 = new ListNode(0, t4_5);
        ListNode t4_3 = new ListNode(3, t4_4);
        ListNode t4_2 = new ListNode(4, t4_3);
        ListNode t4_1 = new ListNode(1, t4_2);
        System.out.println("Expected: [1-0-4-3-5-2], Actual: "+this.printListNodes(func.partition(t4_1, 2)));

        // 3-1, 2 ==> 1-3
        ListNode t5_2 = new ListNode(1);
        ListNode t5_1 = new ListNode(3, t5_2);
        System.out.println("Expected: [1-3], Actual: "+this.printListNodes(func.partition(t5_1, 2)));

        // 1-1, 2 ==> 1-1
        ListNode t6_2 = new ListNode(1);
        ListNode t6_1 = new ListNode(1, t6_2);
        System.out.println("Expected: [1-1], Actual: "+this.printListNodes(func.partition(t6_1, 2)));

        // 2-0-4-1-3-1-4-0-3, 4 ==> 2-0-1-3-1-0-3-4-4
        ListNode t7_9 = new ListNode(3);
        ListNode t7_8 = new ListNode(0, t7_9);
        ListNode t7_7 = new ListNode(4, t7_8);
        ListNode t7_6 = new ListNode(1, t7_7);
        ListNode t7_5 = new ListNode(3, t7_6);
        ListNode t7_4 = new ListNode(1, t7_5);
        ListNode t7_3 = new ListNode(4, t7_4);
        ListNode t7_2 = new ListNode(0, t7_3);
        ListNode t7_1 = new ListNode(2, t7_2);
        System.out.println("Expected: [1-2-2-4-3-5], Actual: "+this.printListNodes(func.partition(t7_1, 4)));

    }

    private String printListNodes(ListNode node) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while (node != null) {
            sb.append(node.val + "-");
            node = node.next;
        }
        sb.append("]");
        if (sb.length() >= 4) {
            int length = sb.length();
            sb.replace(length - 2, length - 1, "");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PartitionList partitionList = new PartitionList();
        partitionList.test(partitionList.intPartitionList2nd);
    }
}
