package main.ltcode_gfg._09_heap_priority_queue;

import java.util.*;

/**
 *  703. Kth Largest Element in a Stream (Easy)
 */
@FunctionalInterface
interface IntKthLargestElementInAStreamSol {
    int add(int val);
}
public class KthLargestElementInAStreamSol {

    int k;
    PriorityQueue<Integer> heap;

    KthLargestElementInAStreamSol(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();
        for (int n : nums) {
            heap.offer(n);
        }

        while (heap.size() > k) {
            heap.poll();
        }
    }
    IntKthLargestElementInAStreamSol intKthLargestElementInAStreamSol = (val -> {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
        return heap.peek();
    });

    public void test(IntKthLargestElementInAStreamSol func) {
        System.out.println("\n============================");

        System.out.println("Expected: 4, Actual: " + func.add(3));
        System.out.println("Expected: 5, Actual: " + func.add(5));
        System.out.println("Expected: 5, Actual: " + func.add(10));
        System.out.println("Expected: 8, Actual: " + func.add(9));
        System.out.println("Expected: 8, Actual: " + func.add(4));
    }

    public static void main(String[] args) {
        KthLargestElementInAStreamSol kthLargest1 = new KthLargestElementInAStreamSol(3, new int[]{4, 5, 8, 2});
        kthLargest1.test(kthLargest1.intKthLargestElementInAStreamSol);

        KthLargestElementInAStreamSol kthLargest2 = new KthLargestElementInAStreamSol(1, new int[]{});
        kthLargest2.test(kthLargest2.intKthLargestElementInAStreamSol);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        System.out.println(pq.add(1));
//        System.out.println(pq.stream().map(s -> {System.out.println(s)};));
        System.out.println(pq.offer(1));

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        maxHeap.add(1);
        maxHeap.add(5);
        maxHeap.add(7);
        maxHeap.add(10);
        System.out.println(maxHeap.peek());

        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        maxHeap2.add(1);
        maxHeap2.add(5);
        maxHeap2.add(7);
        maxHeap2.add(10);
        System.out.println(maxHeap2.peek());

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        minHeap.add(1);
        minHeap.add(5);
        minHeap.add(7);
        minHeap.add(10);
        System.out.println(minHeap.peek());

    }
}
