package main.ltcode_gfg._06_linked_list;

import main.ltcode_gfg.utils.ListNode;

/**
 *  19. Remove Nth Node From End of List (Medium)
 */
@FunctionalInterface
interface IntRemoveNthNodeFromEndofList {
    ListNode removeNthFromEnd(ListNode head, int n);
}

public class RemoveNthNodeFromEndofList {
    /*
        1st idea
            Use recursive
            time: O (n), space: O (1)
     */
    IntRemoveNthNodeFromEndofList recursive = ((head, n) -> {
        int order = recursiveHelper(head, n, null);

        if (order == n)
            return head.next;
        else
            return head;
    });

    private int recursiveHelper(ListNode node, int n, ListNode parent) {
        if (node == null) {
            return 0;
        }
        int order = recursiveHelper(node.next, n, node) + 1;
        if (order == n) {
            parent.next = node.next;
            node = null;
        }
        return order;
    }

    IntRemoveNthNodeFromEndofList bruteForce = ((head, n) -> {
        if(head.next == null)return head.next;
        int c = 0;
        ListNode temp = head;
        while(temp != null){
            ++c;
            temp = temp.next;
        }
        if(c == n)return head.next;

        int res = c - n;
        temp = head;

        while(res > 1){//move to n-1 th node from end
            --res;
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return head;
    });

//    public void test(IntRemoveNthNodeFromEndofList func) {
//        System.out.println("Expected: , Actual: " + func.removeNthFromEnd());
//    }
}

    /*
         Use two pointers, left, right and one extra for 1 step behind right
            Put lCnt, rCnt to count from both sides
            Start moving toward middle until
                either left == right or left == rightBehind
            If left == right,
                total length is lCnt + rCnt - 1
            If left == rightBehind,
                 total length is lCnt + rCnt - 2
            total length should be b/w 1 & n
            If n >=
     */