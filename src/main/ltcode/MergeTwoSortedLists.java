package main.ltcode;

import main.ltcode.utils.ListNode;

@FunctionalInterface
interface IntMergeTwoSortedLists {
    ListNode mergeTwoSortedLists(ListNode list1, ListNode list2);
}

public class MergeTwoSortedLists {

    /*
        First idea
            - set up ListNode index for each list,
                compare from the begining, and merge until end of both lists
     */
    ListNode resultListHead = null;

    IntMergeTwoSortedLists intMergeTwoSortedLists1st = ((list1, list2) -> {
        ListNode idx1 = list1;
        ListNode idx2 = list2;
        ListNode idx1Next = null, idx2Next = null, idx2Last = null;
                                                            // list1: 1 - 2 - 4
                                                            // list2: (1) - (3) - (4)
        while (idx1 != null || idx2 != null) {              // idx1: 1, idx2: (1)
//            System.out.println(idx1.val + " " + idx1.next + "/ " + idx2.val + " " + idx2.next);
                                                            // idx1N: 2, idx2N: (2)
            if (idx1 != null && idx2 != null) {
                if (idx1.val == idx2.val) {
                    idx1Next = idx1.next;
                    idx2Next = this.insertAfter(idx2, idx1);
                    idx1 = idx1Next;
                    idx2Last = idx2;
                    idx2 = idx2Next;
                } else if (idx1.val < idx2.val) {
                    if (idx1.next != null) {
                        if (idx1.next.val < idx2.val) {
                            idx1 = idx1.next;
                        } else {
                            idx1Next = this.insertAfter(idx1, idx2);
                            idx1 = idx1Next;
                        }
                    } else {
                        idx1Next = this.insertAfter(idx1, idx2);
                        idx1 = idx1Next;
                    }

                } else {
                    // idx2.val < idx1.val
                    if (idx2.next != null) {
                        if (idx2.next.val < idx1.val) {
                            idx2Last = idx2;
                            idx2 = idx2.next;
                        } else {
                            idx1Next = idx1.next;
                            idx2Next = this.insertAfter(idx2, idx1);
                            idx1 = idx1Next;
                            idx2Last = idx2;
                            idx2 = idx2Next;
                        }
                    } else {
                        this.insertAfter(idx2, idx1);
                        break;
                    }
                }
            } else if (idx1 != null) {
                idx1Next = idx1.next;
                if (idx2Last != null) {
                    this.insertAfter(idx2Last, idx1);
                } else {
                    return idx1;
                }
                idx1 = idx1Next;
            } else if (idx2 != null) {
                break;
            } else {
                break;
            }

        }

        return list2;
    });

    IntMergeTwoSortedLists intMergeTwoSortedListsSimple = ((list1, list2) -> {
        ListNode idx1 = list1, idx2 = list2;
        ListNode resultListTail = null, idx1Next = null, idx2Next = null;

        while (idx1 != null || idx2 != null) {
            if (idx1 != null && idx2 != null) {
                // compare values based on list2
                if (idx1.val < idx2.val) {
                    idx1Next = idx1.next;
                    resultListTail = this.insertAfter4Simple(resultListTail, idx1);
                    idx1 = idx1Next;
                } else if (idx2.val < idx1.val) {
                    idx2Next = idx2.next;
                    resultListTail = this.insertAfter4Simple(resultListTail, idx2);
                    idx2 = idx2Next;
                } else {
                    idx1Next = idx1.next;
                    idx2Next = idx2.next;
                    resultListTail = this.insertAfter4Simple(resultListTail, idx2);
                    resultListTail = this.insertAfter4Simple(resultListTail, idx1);
                    idx1 = idx1Next;
                    idx2 = idx2Next;
                }
            } else if (idx1 != null) {
                idx1Next = idx1.next;
                resultListTail = this.insertAfter4Simple(resultListTail, idx1);
                idx1 = idx1Next;
            } else if (idx2 != null) {
                idx2Next = idx2.next;
                resultListTail = this.insertAfter4Simple(resultListTail, idx2);
                idx2 = idx2Next;
            } else {
                break;
            }
        }
        return resultListHead;
    });

    private ListNode insertAfter4Simple(ListNode l1, ListNode l2) {    // l1: (1), l2: 1, temp: (2)
        if (l1 == null) {
            l1 = new ListNode(l2.val);
            resultListHead = l1;
            return l1;
        } else {
            l1.next = l2;
            return l2;
        }
    }

    private ListNode insertAfter(ListNode l1, ListNode l2) {    // l1: (1), l2: 1, temp: (2)
        ListNode temp = null;
        if (l1 != null) {
            temp = l1.next;
            l1.next = l2;
            l2.next = temp;
            return l2;
        } else {
            l1 = l2;
            return l1;
        }
    }

    private String printListNode(ListNode listNode) {
        String result = "[";
        while (listNode != null) {
            if (listNode.next != null) {
                result += listNode.val + ", ";
            } else {
                result += listNode.val;
            }
            listNode = listNode.next;
        }
        return result + "]";
    }

    public void test(IntMergeTwoSortedLists func) {
        ListNode first0_1_3 = new ListNode(3);
        ListNode first0_1_2 = new ListNode(2, first0_1_3);
        ListNode first0_1_1 = new ListNode(1, first0_1_2);

        ListNode first0_2_3 = new ListNode(6);
        ListNode first0_2_2 = new ListNode(5, first0_2_3);
        ListNode first0_2_1 = new ListNode(4, first0_2_2);
        System.out.println("[Expected]: {1,2,3,4,5,6}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first0_1_1, first0_2_1)));

        // list1_1 = {1,2,4}, list1_2 = {1,3,4};
        ListNode first1_1_3 = new ListNode(4);
        ListNode first1_1_2 = new ListNode(2, first1_1_3);
        ListNode first1_1_1 = new ListNode(1, first1_1_2);

        ListNode first1_2_3 = new ListNode(4);
        ListNode first1_2_2 = new ListNode(3, first1_2_3);
        ListNode first1_2_1 = new ListNode(1, first1_2_2);
        System.out.println("[Expected]: {1,1,2,3,4,4}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first1_1_1, first1_2_1)));

        ListNode first2_1_3 = new ListNode(4);
        ListNode first2_1_2 = new ListNode(2, first2_1_3);
        ListNode first2_1_1 = new ListNode(1, first2_1_2);

        ListNode first2_2_3 = new ListNode(4);
        ListNode first2_2_2 = new ListNode(3, first2_2_3);
        ListNode first2_2_1 = new ListNode(1, first2_2_2);
        System.out.println("[Expected]: {1,1,2,3,4,4}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first2_1_1, first2_2_1)));

        ListNode list2_1 = null, list2_2 = null;
        System.out.println("[Expected]: {}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(list2_1, list2_2)));

        ListNode first1_3_1 = null;
        ListNode first1_3_2 = new ListNode(0);
        System.out.println("[Expected]: {0}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first1_3_1, first1_3_2)));

        ListNode first1_4_1 = new ListNode(1);
        ListNode first1_4_2 = null;
        System.out.println("[Expected]: {1}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first1_4_1, first1_4_2)));

        ListNode first1_5_1 = new ListNode(2);
        ListNode first1_5_2 = new ListNode(1);
        System.out.println("[Expected]: {1, 2}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first1_5_1, first1_5_2)));

        ListNode first1_6_1 = new ListNode(5);
        ListNode first1_6_2 = new ListNode(4);
        ListNode first1_6_3 = new ListNode(2, first1_6_2);
        ListNode first1_6_4 = new ListNode(1, first1_6_3);
        System.out.println("[Expected]: {1, 2, 4, 5}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first1_6_1, first1_6_4)));

        ListNode first1_7_1 = new ListNode(5);
        ListNode first1_7_2 = new ListNode(4);
        ListNode first1_7_3 = new ListNode(2, first1_7_2);
        ListNode first1_7_4 = new ListNode(1, first1_7_3);
        System.out.println("[Expected]: {1, 2, 4, 5}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first1_7_4, first1_7_1)));

        ListNode first1_8_1 = new ListNode(3);
        ListNode first1_8_2 = new ListNode(-9, first1_8_1);
        ListNode first1_8_3 = new ListNode(7);
        ListNode first1_8_4 = new ListNode(5, first1_8_3);
        System.out.println("[Expected]: {-9, 3, 5, 7}, [Actual]: " + this.printListNode(func.mergeTwoSortedLists(first1_8_2, first1_8_4)));
    }

    public static void main(String[] args) {
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        mergeTwoSortedLists.test(mergeTwoSortedLists.intMergeTwoSortedListsSimple);
    }
}
