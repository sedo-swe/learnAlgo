package main.ltcode_gfg.etc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *  Generate binary numbers
 *
 *  Given a number max, write an algorithm that generates all binary integers from 1 to max.
 *  Examples:
 *      Input: max = 2
 *      Output: ["1", "10"]
 *
 *      Input: max = 5
 *      Output: ["1", "10", "11", "100", "101"]
 */
@FunctionalInterface
interface IntGetAllBinaries {
    List<String> getBinaries(int max);
}
public class GetAllBinaries {
    IntGetAllBinaries getAllBinariesWithQueue = (max -> {
        Queue<String> queue = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        queue.offer("1");
        for (int i = 0; i < max; i++) {
            String cur = queue.poll();
            result.add(cur);
            queue.offer(cur + "0");
            queue.offer(cur + "1");
        }
//        System.out.println(queue);
        return result;
    });

    IntGetAllBinaries getAllBinariesWithBitManipulation = (max -> {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            result.add(this.binary(i+1));
        }
        return result;
    });

    private String binary(int m) {
        int shift = 1;
        StringBuffer sb = new StringBuffer();
        while (m > 0) {
            sb.insert(0, (m & 1));
            m = m >> 1;
        }
        return sb.toString();
    }

    public void test(IntGetAllBinaries func) {
        System.out.println("" + func.getBinaries(5));
    }

    public static void main(String[] args) {
        GetAllBinaries getAllBinaries = new GetAllBinaries();
        getAllBinaries.test(getAllBinaries.getAllBinariesWithQueue);
        System.out.println();
        getAllBinaries.test(getAllBinaries.getAllBinariesWithBitManipulation);
    }
}
