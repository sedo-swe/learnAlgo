package main.ltcode_gfg._01_arrays_hashing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindIncreasingTriplet {

    int[] s1 = new int[]{1, 2, 3, 4, 5};    // 1,2,3
    int[] s2 = new int[]{2, 1, 3, 4, 0};    // 1,3,4 or 2,3,4
    int[] s3 = new int[]{2, 1, 3, 0, 4};    // 1,3,4 or 2,3,4
//    int[] s3 = new int[]{2, 1, 4, 0, 3};    // none
//    int[] s3 = new int[]{0, 3, 2, 1};    // 0, 1, 5
    int[] s4 = new int[]{20, 1, 3, 100, 10, 12, 5, 13}; // 1,3,100 or 1,3,10 or 1,3,12 or 1,3,5 or 1,3,13 or 3,10,12 or 3,10,13 or 3,12,13
    int[] s5 = new int[]{1, 2, -1, 7, 5};   // 1,2,7 or 1,2,5
    int[] s6 = new int[]{7, 2, -1, 1, 5};   // -1,1,5
    int[] s7 = new int[]{7, 11, -1, 1, 15}; // 7,11,15 or -1,1,15
    int[] s8 = new int[]{7, 11, -1, 1, 5};  // -1,1,5
    int[] s9 = new int[]{7, 11, -1, 15};    // 7,11,15
    int[] s10 = new int[]{7, 11, -1, 3, 2, 5};  //-1,2,5
    int[] s11 = new int[]{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,3};  //-1,2,5

    public boolean findIncreasingConsequentTriplet(int[] nums) {
        int p1 = 0, p2 = 0, p3 = 0, prev = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < prev) {
                p1 = i;
                p2 = i;
                p3 = i;
            } else {
                if (p1 == p2 && p2 == p3) {
                    p2 = i;
                    p3 = i;
                } else if (p1 < p2 && p2 == p3) {
                    p3 = i;
                    break;
                }
            }
        }

        if (p1 < p2 && p2 < p3) {
            return true;
        }
        return false;
    }

    public void test1 () {
        System.out.println(this.findIncreasingConsequentTriplet(s1));
        System.out.println(this.findIncreasingConsequentTriplet(s2));
        System.out.println(this.findIncreasingConsequentTriplet(s3));
        System.out.println(this.findIncreasingConsequentTriplet(s4));
    }

    public boolean findIncreasingTriplet(int[] nums) {
        int p1 = 0, p2 = 0, p3 = 0, tempIdx = -1;
        int[] indexes = new int[3];
        int idx = 0;

        for (int i = 0; i < nums.length-1; i++) {
            tempIdx = this.findBiggerNumber(nums, i+1, nums[i]);
            if (tempIdx >= 0) {
                indexes[idx++] = tempIdx;
                i = tempIdx;
            }
        }

        if (p1 < p2 && p2 < p3) {
            return true;
        }
        return false;
    }

    private int findBiggerNumber(int[] nums, int startIdx, int target) {
        int idx = -1;

        for (int i = startIdx; i < nums.length; i++) {

        }

        return idx;
    }

    public void test2 () {
        System.out.println(this.findIncreasingTriplet(s1));
        System.out.println(this.findIncreasingTriplet(s2));
        System.out.println(this.findIncreasingTriplet(s3));
        System.out.println(this.findIncreasingTriplet(s4));
    }

    public boolean findIncreasingTripletBruteForce(int[] nums) {

        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] < nums[j]) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[j] < nums[k]) {
                            System.out.println(nums[i] + "[" + i + "] < " + nums[j] + "[" +  j + "] < " + nums[k] + "[" + k + "]");
                            return true;
                        }
                    }
                }
            }
        }


        return false;
    }

    public void test3 () {
        System.out.println(this.findIncreasingTripletBruteForce(s1));
        System.out.println(this.findIncreasingTripletBruteForce(s2));
        System.out.println(this.findIncreasingTripletBruteForce(s3));
        System.out.println(this.findIncreasingTripletBruteForce(s4));
    }

    public void findThreeSubsequence(int[] nums) {
        // If number of elements < 3
        // then no triplets are possible
        if (nums == null || nums.length < 3) {
            System.out.println("No such triplet found");
            return;
        }

        // track best sequence length
        // (not current sequence length)
        int seq = 1;

        // min number in array
        int minNum = nums[0];

        // least max number in best sequence
        // i.e. track arr[j] (e.g. in array [1, 5, 3] our best sequence
        // would be [1, 3] with arr[j] = 3)
        int maxSeq = Integer.MIN_VALUE;

        // save arr[i]
        int storeMin = minNum;

        // Iterate from 1 to nums.size()
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == minNum) {
                continue;
            }
            else if (nums[i] < minNum) {
                minNum = nums[i];
//                System.out.println("found new min of " + minNum + " from index " + i);
                continue;
            }

            // this condition is only hit
            // when current sequence size is 2
            else if (nums[i] < maxSeq) {
                // update best sequence max number to a smaller value
                // (i.e. we've found a smaller value for arr[j]
                maxSeq = nums[i];

                // store best sequence start value
                // i.e. arr[i]
                storeMin = minNum;
            }
            // Increase best sequence length &
            // save next number in our triplet
            else if (nums[i] > maxSeq) {
                seq++;

                // We've found our arr[k]!
                // Print the output
                if (seq == 3) {
                    System.out.println("Triplet: " + storeMin + ", " + maxSeq + ", " + nums[i]);
//                    System.out.println("Triplet: " + minNum + ", " + maxSeq + ", " + nums[i]);
                    return;
                }
                maxSeq = nums[i];
            }
        }

        // No triplet found
        System.out.println("No such triplet found");
    }

    public void test4 () {
//        this.findThreeSubsequence(s1);
//        this.findThreeSubsequence(s2);
//        this.findThreeSubsequence(s3);
//        this.findThreeSubsequence(s4);
//        this.findThreeSubsequence(s5);
        this.findThreeSubsequence(s6);
        for (int x = 0; x < s6.length; x++) {
            System.out.print(s6[x] + " ");
        }
//        this.findThreeSubsequence(s7);
//        this.findThreeSubsequence(s8);
//        this.findThreeSubsequence(s9);
//        this.findThreeSubsequence(s10);
//        for (int x = 0; x < s10.length; x++) {
//            System.out.print(s10[x] + " ");
//        }
    }

    public boolean verifyThereIsIncreasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (final int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                System.out.print("Found: " + first + ", " + second + ", " + num);
                return true;
            }
        }

        return false;
    }

    public void test5 () {
        System.out.println("\tS1 : " + this.verifyThereIsIncreasingTriplet(s1));
        System.out.println("\tS2 : " + this.verifyThereIsIncreasingTriplet(s2));
        System.out.println("\tS3 : " + this.verifyThereIsIncreasingTriplet(s3));
        System.out.println("\tS4 : " + this.verifyThereIsIncreasingTriplet(s4));
        System.out.println("\tS5 : " + this.verifyThereIsIncreasingTriplet(s5));
        System.out.println("\tS6 : " + this.verifyThereIsIncreasingTriplet(s6));
//        for (int x = 0; x < s6.length; x++) {
//            System.out.print(s6[x] + " ");
//        }
        System.out.println("\tS7 : " + this.verifyThereIsIncreasingTriplet(s7));
        System.out.println("\tS8 : " + this.verifyThereIsIncreasingTriplet(s8));
        System.out.println("\tS9 : " + this.verifyThereIsIncreasingTriplet(s9));
        System.out.println("\tS10: " + this.verifyThereIsIncreasingTriplet(s10));
//        for (int x = 0; x < s10.length; x++) {
//            System.out.print(s10[x] + " ");
//        }
    }

    public boolean searchIncreasingTriplet(int[] nums) {
        List<List<Integer>> candidates = new ArrayList<>();
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (final int num : nums) {
            if (num <= first) {
                first = num;

                List<Integer> newOne = new ArrayList<Integer>();
                newOne.add(num);
                candidates.add(newOne);
            } else if (num <= second) {
                second = num;

                List<List<Integer>> temp = candidates.stream().filter(
                        p -> (p.size() < 2) && (p.get(0) < num)
                ).collect(Collectors.toList());
                if (temp.size() > 0) {
                    temp.get(0).add(second);
                }
            } else {
//                System.out.print("Found: " + first + ", " + second + ", " + num);
                List<List<Integer>> temp = candidates.stream().filter(
                        p -> (p.size() == 2) && (p.get(1) < num)
                ).collect(Collectors.toList());
                if (temp.size() > 0) {
                    temp.get(0).add(num);
                    for (Integer i : temp.get(0)) {
                        System.out.print(i + " ");
                    }
                }
                return true;
            }
        }

        return false;
    }

    public void test6 () {
//        System.out.println("\tS1 : " + this.searchIncreasingTriplet(s1));
//        System.out.println("\tS2 : " + this.searchIncreasingTriplet(s2));
//        System.out.println("\tS3 : " + this.searchIncreasingTriplet(s3));
//        System.out.println("\tS4: " + this.searchIncreasingTriplet(s4));
//        System.out.println("\tS5 : " + this.searchIncreasingTriplet(s5));
//        System.out.println("\tS6 : " + this.searchIncreasingTriplet(s6));
//        System.out.println("\tS7 : " + this.searchIncreasingTriplet(s7));
//        System.out.println("\tS8: " + this.searchIncreasingTriplet(s8));
//        System.out.println("\tS9 : " + this.searchIncreasingTriplet(s9));
//        System.out.println("\tS10 : " + this.searchIncreasingTriplet(s10));
        System.out.println("\tS11 : " + this.searchIncreasingTriplet(s11));
    }


    public static void main(String[] args) {
        FindIncreasingTriplet f = new FindIncreasingTriplet();
//        f.test1();
//        f.test2();
//        f.test3();
//        f.test4();
//        f.test5();
        f.test6();
    }

}
