package main.ltcode.utils;

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
}
