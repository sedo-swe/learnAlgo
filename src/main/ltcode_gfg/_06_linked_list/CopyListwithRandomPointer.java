package main.ltcode_gfg._06_linked_list;

/**
 *  138. Copy List with Random Pointer (Medium)
 */

import java.util.*;

@FunctionalInterface
interface IntCopyListwithRandomPointer {
    NodeR copyRandomList(NodeR head);
}

class NodeR {
    int val;
    NodeR next;
    NodeR random;

    public NodeR(int val, NodeR next, NodeR random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}
public class CopyListwithRandomPointer {
    IntCopyListwithRandomPointer first = (head -> {
        Map<NodeR, NodeR> copiedNodes = new HashMap<>();

        NodeR cur = head;
        NodeR newHead = null;
        NodeR newCur = newHead;

        while (cur != null) {
            copiedNodes.put(cur, new NodeR(cur.val, null, null));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            newCur = copiedNodes.get(cur);
            newCur.random = copiedNodes.get(cur.random);
            newCur.next = copiedNodes.get(cur.next);
            cur = cur.next;
        }
        return copiedNodes.get(head);
    });

    public void test(IntCopyListwithRandomPointer func) {
        NodeR t1_05 = new NodeR(1, null, null);
        NodeR t1_04 = new NodeR(10, t1_05, null);
        NodeR t1_03 = new NodeR(11, t1_04, null);
        NodeR t1_02 = new NodeR(13, t1_03, null);
        NodeR t1_01 = new NodeR(7, t1_02, null);
        t1_02.random = t1_01;
        t1_03.random = t1_05;
        t1_04.random = t1_03;
        t1_05.random = t1_01;
        NodeR t = func.copyRandomList(t1_01);
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while (t != null) {
            sb.append("["+t.val+","+(t.random == null ? null : t.random.val)+"],");
            t = t.next;
        }
        sb.append("]");
        System.out.println("Expected: [[7,null],[13,0],[11,4],[10,2],[1,0]], Actual: " + sb.toString());
    }

    public static void main(String[] args) {
        CopyListwithRandomPointer cp = new CopyListwithRandomPointer();
        cp.test(cp.first);
    }
}
