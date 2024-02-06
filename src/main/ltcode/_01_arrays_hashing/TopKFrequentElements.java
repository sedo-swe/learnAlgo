package main.ltcode._01_arrays_hashing;

import java.util.*;

/**
 *  347. Top K Frequent Elements (Medium)
 */
@FunctionalInterface
interface IntTopKFrequentElements {
    int[] topKFrequent(int[] nums, int k);
}
public class TopKFrequentElements {
    IntTopKFrequentElements intTopKFrequentElements1st = ((nums, k) -> {
        // Count frequency via HashMap
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (Integer num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // Order via PriorityQueue
        PriorityQueue<AbstractMap.SimpleEntry<Integer, Integer>> order = new PriorityQueue<>(new Comparator<AbstractMap.SimpleEntry<Integer, Integer>>() {
            @Override
            public int compare(AbstractMap.SimpleEntry<Integer, Integer> o1, AbstractMap.SimpleEntry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Set<Integer> keys = frequency.keySet();
        for (Integer i : keys) {
            order.add(new AbstractMap.SimpleEntry<>(i, frequency.get(i)));
        }

        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = order.remove().getKey();
        }
        return topK;
    });

    IntTopKFrequentElements intTopKFrequentElementsSol1 = ((nums, k) -> {
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] counter = new int[max - min + 1];
        for (int num : nums) {
            counter[num - min]++;
        }

        for (int idx = 0; idx < counter.length; idx++) {
            if (counter[idx] > 0) {
                int val = idx + min;
                int freq = counter[idx];
                if (bucket[freq] == null) {
                    bucket[freq] = new ArrayList<Integer>();
                }
                bucket[freq].add(val);
            }
        }

        int index = 0;
        int[] res = new int[k];
        int ptr = nums.length;
        while (index < k) {
            if (bucket[ptr] != null) {
                for (int val : bucket[ptr]) {
                    res[index++] = val;
                    if (index == k) {
                        return res;
                    }
                }
            }
            ptr--;
        }

        return res;
    });

    private String printString(int[] nums) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Integer i : nums) {
            sb.append(i + ", ");
        }
        sb.append("]");
        if (sb.length() >= 4) {
            sb.replace(sb.length() - 3, sb.length() - 1, "");
        }
        return sb.toString();
    }

    public void test(IntTopKFrequentElements func) {
        System.out.println("Expected: [1, 2], Actual: " + this.printString(func.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println("Expected: [1], Actual: " + this.printString(func.topKFrequent(new int[]{1}, 1)));
        System.out.println("Expected: [-102, 3], Actual: " + this.printString(func.topKFrequent(new int[]{-102, -102, -102, -102, 2, 2, 3, 3, 3, 4}, 2)));
    }

    public static void main(String[] args) {
        TopKFrequentElements top = new TopKFrequentElements();
//        top.test(top.intTopKFrequentElements1st);
        top.test(top.intTopKFrequentElementsSol1);
    }
}


/*
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // return heap(nums, k);
        return countingSort(nums,k);
    }

    private int[] heap(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>((n1, n2) -> n2.count - n1.count);
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        ArrayList<Integer> arrayList = new ArrayList();
        for(int i = 0; i < k; i++) {
            Node n = pq.poll();
            for (int j = 0; j < n.count; j++) {
                arrayList.add(n.num);
            }
        }
        int[] arr = new int[arrayList.size()];
        for(int i = 0 ; i < arrayList.size(); i++) {
            arr[i] = arrayList.get(i);
        }
        return arr;
    }


    private int[] countingSort(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i : nums) {
            if  (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }

        int range = max - min;
        int[] counter = new int[range+1];
        Arrays.fill(counter, 0);

        int maxCounter = 0;
        for(int i : nums) {
            counter[i-min]++;
            if (counter[i-min] > maxCounter) {
                maxCounter = counter[i-min];
            }
        }

        ArrayList<Integer>[] topk  = new ArrayList[nums.length + 1];
        for(int i=0; i < counter.length; i++) {
            if (topk[counter[i]] == null) {
                topk[counter[i]] = new ArrayList();
            }
            topk[counter[i]].add(i+min);
        }

        int[] result = new int[k];
        int iterator =0;
        for(int i = topk.length-1; i >=0; i--) {
            if (topk[i] != null) {
                for(Integer ik : topk[i]) {
                    result[iterator] = ik;
                    iterator++;
                    if (iterator > k-1) {
                        return result;
                    }
                }
            }
        }
        return result;
    }
}

    public class Node {
        public int num;
        public int count;

        public Node(int num , int count) {
            this.num = num;
            this.count = count;
        }
    }

 */