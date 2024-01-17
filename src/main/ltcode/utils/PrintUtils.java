package main.ltcode.utils;

import java.util.List;
import java.util.ListIterator;

public class PrintUtils {
    public static String printIntArrayString(int[] nums) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        if (nums != null) {
            for (int num : nums) {
                sb.append(num + ", ");
            }
        }

        sb.append("]");
        if (sb.length() >= 4) {
            sb.replace(sb.length() - 3, sb.length() - 1, "");
        }
        return sb.toString();
    }

    public static String printListNodeList(ListNode head) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while (head != null) {
            sb.append(head.val + ", ");
            head = head.next;
        }

        sb.append("]");
        if (sb.length() >= 4) {
            sb.replace(sb.length() - 3, sb.length() - 1, "");
        }
        return sb.toString();
    }

    public static String printListInListInteger(List<List<Integer>> subsets) {
        if (subsets == null || subsets.size() == 0) {
            return "[]";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        ListIterator<List<Integer>> outsideIter = subsets.listIterator();
        int lengthOutside = sb.length();
        while (outsideIter.hasNext()) {
            StringBuffer sbInside = new StringBuffer();
            List<Integer> outside = outsideIter.next();
            ListIterator<Integer> insideIter = outside.listIterator();
            sbInside.append("[");
            int length = sbInside.length();
            while (insideIter.hasNext()) {
                Integer i = insideIter.next();
                sbInside.append(i + ",");
            }
            if (length != sbInside.length()) {
                sbInside.setLength(sbInside.length() - 1);
            }
            sbInside.append("]");
            sb.insert(1, sbInside.toString() + ",");
        }
        if (lengthOutside != sb.length()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");

        return sb.toString();
    }
}
