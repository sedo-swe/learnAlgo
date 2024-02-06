package main.ltcode._04_stack;

import java.util.ArrayList;

/**
 * 155. Min Stack (medium)
 */

public class MinStack {
    ArrayList<Integer> stack = null;
    Node head = null;
    int minIndex = 0;
    int curIndex = -1;

    public MinStack() {
        stack = new ArrayList<>();
    }

    public void push(int val) {
        stack.add(val);
        curIndex++;
        if (val < stack.get(minIndex)) {
            minIndex = curIndex;
        }
    }

    public void pop() {
        stack.remove(curIndex--);
        minIndex = this.findMinIndex();
    }

    public int top() {
        return stack.get(curIndex);
    }

    public int getMin() {
        return stack.get(minIndex);
    }

    private int findMinIndex() {
        int minIndex = 0;
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) < stack.get(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // From solution
    public void pushSol(int val) {
        if (head == null) {
            head = new Node(val, val, null);
        } else {
            head = new Node(Math.min(head.min, val), val, head);
        }
    }

    public void popSol() {
        head = head.prev;
    }

    public int topSol() {
        return head.val;
    }

    public int getMinSol() {
        return head.min;
    }

    public static void main(String[] args) {
        System.out.println("= From 1st ==========================");
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());

        System.out.println("= From Sol ==========================");
        MinStack stackSol = new MinStack();
        stackSol.pushSol(-2);
        stackSol.pushSol(0);
        stackSol.pushSol(-3);
        System.out.println(stackSol.getMinSol());
        stackSol.popSol();
        System.out.println(stackSol.getMinSol());
    }

    public class Node {
        int min;
        int val;
        Node prev;

        public Node(int min, int val, Node prev) {
            this.min = min;
            this.val = val;
            this.prev = prev;
        }
    }
}
