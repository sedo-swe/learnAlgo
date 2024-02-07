package main.ltcode_gfg;

import java.util.*;

@FunctionalInterface
interface IntPairSum {
    List<int[]> findPairs(int[] arr, int s);
}
public class PairSum {
    IntPairSum intPairSumTwoPointer = ((arr, s) -> {
        /*Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        List<int[]> pairs = new ArrayList<>();
        while (i <= j) {
            if (arr[i] + arr[j] == s) {
                pairs.add(new int[]{arr[i], arr[j]});
                if (i + 1 <= j && arr[i] == arr[i+1]) {
                    i++;
                } else if (j - 1 >= i && arr[j] == arr[j - 1]) {
                    j--;
                } else {
                    i++;
                    j--;
                }
            } else if (arr[i] + arr[j] < s) {
                i++;
            } else {
                j--;
            }
        }
        return pairs;*/
        // Used to store result.
        List < int[] > ans = new ArrayList();
        HashMap < Integer, Integer > map = new HashMap < > ();

        for (int num: arr) {
            // Store frequency of each unique element.
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Store unique elements.
        Integer[] keyArray = map.keySet().toArray(new Integer[map.keySet().size()]);

        Arrays.sort(keyArray);

        for (int key: keyArray) {
            if (key + key == s) {
                int freq = map.get(key);

                // Total number of such pairs will be 'FREQ*(FREQ-1)/2'.
                for (int j = 0; j < freq * (freq - 1) / 2; j++) {
                    ans.add(new int[] { key, key });
                }
            }
        }

        // Maintain two pointers.
        int low = 0;
        int high = keyArray.length - 1;

        while (low < high) {
            int currSum = keyArray[low] + keyArray[high];

            // If the current sum is equal to the target sum.
            if (currSum == s) {
                int freq = map.get(keyArray[low]) * map.get(keyArray[high]);

                // Total number of such pairs will be 'FREQ'.
                for (int j = 0; j < freq; j++) {
                    ans.add(new int[] { keyArray[low], keyArray[high] });
                }
                low++;
                high--;
            } else if (currSum < s) {
                low++;
            } else {
                high--;
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            int a = ans.get(i)[0], b = ans.get(i)[1];
            int res[] = new int[2];
            res[0] = Math.min(a, b);
            res[1] = Math.max(a, b);
            ans.set(i, res);
        }

        Collections.sort(ans, new Comparator < int[] > () {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });

        return ans;
    });

    IntPairSum intPairSumHashing = ((arr, s) -> {
        Arrays.sort(arr);
        HashMap<Integer, Integer> freq = new HashMap<>();
        List<int[]> pairs = new ArrayList<>();
        for (int i : arr) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            if (freq.containsKey(s - arr[i])) {
                int[] temp = new int[]{arr[i], s - arr[i]};
                for (int j = 0; j < freq.get(s - arr[i]); j++) {
                    pairs.add(temp);
                }
                freq.remove(s - arr[i]);
                freq.remove(arr[i]);
            }
        }
        return pairs;
        /*// arr: [2, -3, 3, 3, -2], s: 0
        int n = arr.length;     // n: 5

        // HashMap to store frequency of elements.
        HashMap<Integer, Integer> map = new HashMap();  // map: {2:1, -3:1, 3:2, -2:1}

        // This will store the result.
        List<int[]> ans = new ArrayList();              // ans: [[3, -3], [3, -3], [-2, 2]]
        for (int ele : arr) {                           // ele: -2
            int count = map.getOrDefault(s - ele, 0);   // s - ele: 2, count: 1

            int[] pair = new int[2];
            pair[0] = ele;              // -2
            pair[1] = s - ele;          // 2

            while (count-- > 0) {
                ans.add(pair);
            }

            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        // Sort elements in each pair
        for (int i = 0; i < ans.size(); i++) {
            int a = ans.get(i)[0], b = ans.get(i)[1];
            int res[] = new int[2];
            res[0] = Math.min(a, b);
            res[1] = Math.max(a, b);
            ans.set(i, res);
        }

        // Sort pairs
        Collections.sort(ans, new Comparator < int[] > () {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });

        return ans;*/
    });

    public void test(IntPairSum func) {
        System.out.println("  Expected: [[1,3], [2,3]], Actual: " + listToString(func.findPairs(new int[] {1,2,3,4,5}, 5)));
        System.out.println("  Expected: [[-3,3], [-3,3], [-2,2]], Actual: " + listToString(func.findPairs(new int[] {2,-3,3,3,-2}, 0)));
        System.out.println("  Expected: [[2,2], [2,2], [2,2]], Actual: " + listToString(func.findPairs(new int[] {2,-6,2,5,2}, 4)));
        System.out.println("  Expected: [[2,9], [3,8], [4,7]], Actual: " + listToString(func.findPairs(new int[] {2,3,4,7,8,9}, 11)));
        System.out.println("  Expected: [[2,9], [3,8], [3,8], [3,8], [4,7]], Actual: " + listToString(func.findPairs(new int[] {2,3,3,3,4,7,8,9}, 11)));
        System.out.println("  Expected: [[-4,9], [-3,8], [-2,7]], Actual: " + listToString(func.findPairs(new int[] {-2,-3,-4,7,8,9}, 5)));
    }

    private String listToString(List<int[]> list) {
        StringBuffer sb = new StringBuffer();
        for (int[] i : list) {
            sb.append("["+i[0] + "," + i[1]+"]");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PairSum pairSum = new PairSum();
        System.out.println("= Two Pointer ==================================");
        pairSum.test(pairSum.intPairSumTwoPointer);
        System.out.println("\n= Hashing ====================================");
        pairSum.test(pairSum.intPairSumHashing);
    }
}
