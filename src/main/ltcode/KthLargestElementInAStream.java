package main.ltcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
interface IntKthLargestElementInAStream {
    int add(int val);
}
public class KthLargestElementInAStream {

    int kth = -1;
    int kthIdx = -1;
    List<Integer> nums = null;

    KthLargestElementInAStream(int k, int[] nums) {
        this.kth = k;
        this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(this.nums);
    }
    IntKthLargestElementInAStream intKthLargestElementInAStream1st = (val -> {
        if (this.nums.size() == 0) {
            this.nums.add(val);
        }

        if (val <= this.nums.get(0)) {
            this.nums.add(0, val);
            return this.getKthValue();
        }

        if (val >= this.nums.get(this.nums.size() - 1)) {
            this.nums.add(val);
            return this.getKthValue();
        }

        // Assume kthIdx is greater than or equal 0
        kthIdx = this.nums.size() - this.kth;
        int curKthValue = this.getKthValue();
        if (val < curKthValue) {
            kthIdx++;
            this.nums.add(getIndexByBinarySearch(0, kthIdx - 1, val), val);
            return curKthValue;
        } else {
            this.nums.add(getIndexByBinarySearch(kthIdx + 1, this.nums.size() - 1, val), val);
            return this.getKthValue();
        }
    });

    private int getIndexByBinarySearch(int l, int r, int target) {
        int result = -1;
        int mid = 0;

        while (l <= r) {
            mid = (l + r) / 2;
            if (target <= this.nums.get(mid)) {
                result = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return result;
    }

    private int getKthValue() {
        kthIdx = this.nums.size() - this.kth;
        if (kthIdx >= 0) {
            return this.nums.get(kthIdx);
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public void test(IntKthLargestElementInAStream func) {
        System.out.println("Expected: 4, Actual: " + func.add(3));
        System.out.println("Expected: 5, Actual: " + func.add(5));
        System.out.println("Expected: 5, Actual: " + func.add(10));
        System.out.println("Expected: 8, Actual: " + func.add(9));
        System.out.println("Expected: 8, Actual: " + func.add(4));
    }

    public static void main(String[] args) {
//        KthLargestElementInAStream kthLargest1 = new KthLargestElementInAStream(3, new int[]{4, 5, 8, 2});
//        kthLargest1.test(kthLargest1.intKthLargestElementInAStream1st);

        KthLargestElementInAStream kthLargest2 = new KthLargestElementInAStream(1, new int[]{});
        kthLargest2.test(kthLargest2.intKthLargestElementInAStream1st);
    }
}
