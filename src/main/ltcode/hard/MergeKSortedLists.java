package main.ltcode.hard;

import main.ltcode.utils.ListNode;

import java.util.*;

@FunctionalInterface
interface IntMergeKSortedLists {
    ListNode mergeKLists(ListNode[] lists);
}
public class MergeKSortedLists {

    /*
        First idea.
            - Put them in one array, use built-in sort, create list and return it
            ==> 10 ms

        Second idea.
            - need to compare k sorted lists to pick up next element, how?
            --> PriorityQueue? putting all elements into PriorityQueue, so doesn't need to sort
            ==> 6 ms

        Third idea.
            - find min among current values from all lists in array
            - increase the index of the list provided min
            - repeat this until there is no remaining values from all lists
            ==> 145 ms

        Solution Iterative
            ==> 1 ms

        Solution Recursive
            ==> 2 ms
     */
    IntMergeKSortedLists mergeKList1st = ((lists) -> {
        if (lists == null || lists.length == 0) {
            return null;
        }

        List<Integer> immediateArray = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode currNode = lists[i];
            while (currNode != null) {
                immediateArray.add(currNode.val);
                currNode = currNode.next;
            }
        }

        immediateArray.sort((a, b) -> a - b);
        if (immediateArray.size() == 0) {
            return null;
        }
        ListNode head = new ListNode(immediateArray.get(0), null);
        ListNode current = head;
        for (int i = 1; i < immediateArray.size(); i++) {
            ListNode nextNode = new ListNode(immediateArray.get(i), null);
            current.next = nextNode;
            current = current.next;
        }
        return head;
    });

    IntMergeKSortedLists mergeKList2nd = ((lists) -> {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<Integer> values = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            while (node != null) {
                values.add(node.val);
                node = node.next;
            }
        }

        if (values.size() == 0) {
            return null;
        }

        ListNode head = new ListNode(values.remove(), null);
        ListNode current = head;
        while (!values.isEmpty()) {
            ListNode nextNode = new ListNode(values.remove(), null);
            current.next = nextNode;
            current = current.next;
        }
        return head;
    });

    IntMergeKSortedLists mergeKList3rd = (lists -> {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int min = Integer.MAX_VALUE;
        int minListIndex = -1;
        ListNode[] currentNodePerList = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            currentNodePerList[i] = lists[i];
        }
        boolean[] hasMorePerList = new boolean[lists.length];
        Arrays.fill(hasMorePerList, true);

        List<Integer> sorted = new ArrayList<>();

        while (hasNext(hasMorePerList)) {
            // Pick min among current nodes from each list
            for (int i = 0; i < currentNodePerList.length; i++) {
                if (currentNodePerList[i] != null) {
                    if (min > currentNodePerList[i].val) {
                        min = currentNodePerList[i].val;
                        minListIndex = i;
                    }
                } else {
                    hasMorePerList[i] = false;
                }
            }
            if (minListIndex > -1) {
                sorted.add(min);
                if (currentNodePerList[minListIndex] == null) {
                    hasMorePerList[minListIndex] = false;
                } else {
                    currentNodePerList[minListIndex] = currentNodePerList[minListIndex].next;
                }
            }
            min = Integer.MAX_VALUE;
            minListIndex = -1;
        }

        if (sorted.size() == 0) {
            return null;
        }

        ListNode head = new ListNode(sorted.get(0), null);
        ListNode current = head;
        for (int i = 1; i < sorted.size(); i++) {
            current.next = new ListNode(sorted.get(i), null);
            current = current.next;
        }
        return head;
    });

    private boolean hasNext(boolean[] ends) {
        boolean result = false;
        for (int i = 0; i < ends.length; i++) {
            if (ends[i]) {
                return true;
            }
        }
        return result;
    }

    IntMergeKSortedLists mergeKListSolIter = (lists -> {
        if (lists == null || lists.length == 0) {
            return null;
        }

        while(lists.length > 1){
            ListNode[] newLists = new ListNode[(lists.length + 1)/2];
            int j = 0;
            for (int i = 0; i < lists.length; i += 2) {
                if(i+1 < lists.length){
                    newLists[j++] = mergeLists(lists[i], lists[i+1]);
                }else{
                    //odd number of lists
                    newLists[j++] = lists[i];
                }
            }

            lists = newLists;
        }

        return lists[0];
    });

    public ListNode mergeLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while(l2 !=null && l1 != null){
            if(l1.val < l2.val){
                current.next = l1;
                l1=l1.next;
            }else{
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = (l2 == null) ? l1 : l2;

        return dummy.next;
    }


    // lists : [1->5, 2->6, 3->7,4->8]
    IntMergeKSortedLists mergeKListSolRecur = (lists -> {
        //Approach: Using MergeSort Algorithm
        return mergeSort(lists, 0, lists.length-1); // list, 0, 3
    });

    //method for merge sort
    private ListNode mergeSort(ListNode[] lists, int left, int right){  // list, 0, 0
        //base case
        if(left==right) return lists[left];                             // true ==> lists[left] (return the only remaining node)

        //case: if left<right
        if(left<right){                                                 // 0 < 1
            //devide the list in to two pieces
            int mid = (left+right)/2;                                   // mid: 0

            //left side: start to mid
            ListNode ll = mergeSort(lists, left, mid);                  // lists, 0, 1 ==> 0, 0: 1 / 2, 2: 3
                                                                        // ll: 1->2->5->6
            //right side: mid to end
            ListNode lr = mergeSort(lists, mid+1, right);          // lists, 2, 3 ==> 1, 1: 2 / 3, 3: 4
                                                                        // lr: 3->4->7->8
            //merge them
            return merge(ll, lr);                                       //
        }
        else return null;
    };

    //method for merge
    private ListNode merge(ListNode l1, ListNode l2){                   // l1: null(->null), l2: 6(->null)
        //base case
        if(l1==null) return l2;
        if(l2==null) return l1;

        //case 1: value of l1 is less than value of l2
        if(l1.val<l2.val){                                              // 5<6
            l1.next = merge(l1.next, l2);                               // 1(-> 5, 2: 2(->5(->6)) ==> 5(-> null, 6: 6)
            return l1;                                                  // return 1->2->5->6
        }
        //case 2: value of l2 is less than value of l1
        else{
            l2.next = merge(l1, l2.next);                               // 2(-> 5, 6: 5(->6))
            return l2;
        }
    }

    private String printToString(ListNode head) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while (head != null) {
            sb.append(head.val + ",");
            head = head.next;
        }
        sb.append("]");
        int length = sb.length();
        if (length >= 4) {
            sb.deleteCharAt(length - 2);
        }
        return sb.toString();
    }
    public void test(IntMergeKSortedLists func) {
        ListNode l1_e3_02 = new ListNode(6);
        ListNode l1_e3_01 = new ListNode(2, l1_e3_02);
        ListNode l1_e2_03 = new ListNode(4);
        ListNode l1_e2_02 = new ListNode(3, l1_e2_03);
        ListNode l1_e2_01 = new ListNode(1, l1_e2_02);
        ListNode l1_e1_03 = new ListNode(5);
        ListNode l1_e1_02 = new ListNode(4, l1_e1_03);
        ListNode l1_e1_01 = new ListNode(1, l1_e1_02);
        ListNode[] l1_list = {l1_e1_01, l1_e2_01, l1_e3_01};
        System.out.println("Expected: [1,1,2,3,4,4,5,6], Actual: " + this.printToString(func.mergeKLists(l1_list)));

        ListNode[] l2_list = {};
        System.out.println("Expected: [], Actual: " + this.printToString(func.mergeKLists(l2_list)));

        ListNode[] l3_list = {null};
        System.out.println("Expected: [], Actual: " + this.printToString(func.mergeKLists(l3_list)));

        ListNode l4_e2_04 = new ListNode(7);
        ListNode l4_e2_03 = new ListNode(6, l4_e2_04);
        ListNode l4_e2_02 = new ListNode(5, l4_e2_03);
        ListNode l4_e2_01 = new ListNode(4, l4_e2_02);
        ListNode l4_e1_03 = new ListNode(3);
        ListNode l4_e1_02 = new ListNode(2, l4_e1_03);
        ListNode l4_e1_01 = new ListNode(1, l4_e1_02);
        ListNode[] l4_list = {l4_e1_01, l4_e2_01};
        System.out.println("Expected: [1,2,3,4,5,6,7], Actual: " + this.printToString(func.mergeKLists(l4_list)));
    }

    public static void main(String[] args) {
        MergeKSortedLists m = new MergeKSortedLists();
        System.out.println("\n=== First Idea ==============");
        m.test(m.mergeKList1st);

        System.out.println("\n=== Second Idea =============");
        m.test(m.mergeKList2nd);

        System.out.println("\n=== Third Idea ==============");
        m.test(m.mergeKList3rd);

        System.out.println("\n=== Solution Iterate ========");
        m.test(m.mergeKListSolIter);

        System.out.println("\n=== Solution Recursive ======");
        m.test(m.mergeKListSolRecur);
    }
}
