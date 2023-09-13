package main.practice.findduplicates;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicatesRe {
    public int findDuplicatesInList(List<Integer> list) {
        // Move in to loop
        // - Move n steps from the tail to make sure that you're in the loop
        int n = list.size();
        int currentPosition = n;

        for (int i = 0; i < n; i++) {
            currentPosition = list.get(currentPosition - 1);
        }
//        System.out.println("\t" + currentPosition + " position in loop.");

        // Find the length of the loop
        int length = 1;
        int rememberPosition = currentPosition;
        currentPosition = list.get(currentPosition - 1);

        while (rememberPosition != currentPosition) {
            currentPosition = list.get(currentPosition - 1);
            length++;
        }
//        System.out.println("\t" + length + " steps");

        // Use stick to find the first node
        int positionStart = n;
        int positionEnd = n;
        for (int i = 0; i < length; i++) {
            positionEnd = list.get(positionEnd - 1);
        }

//        System.out.println(positionStart + " start pos, " + positionEnd + " end pos");

        while (positionStart != positionEnd) {
            positionStart = list.get(positionStart - 1);
            positionEnd = list.get(positionEnd - 1);
        }
//        System.out.println("\t" + positionStart);

        return positionStart;
    }

    public static void main(String[] args) {
        FindDuplicatesRe findDuplicatesRe = new FindDuplicatesRe();

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(new Integer(3));
        l1.add(new Integer(1));
        l1.add(new Integer(2));
        l1.add(new Integer(2));

        System.out.println("Actual: " + findDuplicatesRe.findDuplicatesInList(l1) + ", Expected: " + 2);

        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(new Integer(3));
        l2.add(new Integer(1));
        l2.add(new Integer(2));
        l2.add(new Integer(4));
        l2.add(new Integer(1));

        System.out.println("Actual: " + findDuplicatesRe.findDuplicatesInList(l2) + ", Expected: " + 1);

        List<Integer> l3 = new ArrayList<Integer>();
        l3.add(new Integer(1));
        l3.add(new Integer(1));

        System.out.println("Actual: " + findDuplicatesRe.findDuplicatesInList(l3) + ", Expected: " + 1);

        List<Integer> l4 = new ArrayList<Integer>();
        l4.add(new Integer(1));
        l4.add(new Integer(2));
        l4.add(new Integer(3));
        l4.add(new Integer(2));

        System.out.println("Actual: " + findDuplicatesRe.findDuplicatesInList(l4) + ", Expected: " + 2);

        List<Integer> l5 = new ArrayList<Integer>();
        l5.add(new Integer(1));
        l5.add(new Integer(2));
        l5.add(new Integer(5));
        l5.add(new Integer(5));
        l5.add(new Integer(5));
        l5.add(new Integer(5));

        System.out.println("Actual: " + findDuplicatesRe.findDuplicatesInList(l5) + ", Expected: " + 5);

        List<Integer> l6 = new ArrayList<Integer>();
        l6.add(new Integer(4));
        l6.add(new Integer(1));
        l6.add(new Integer(4));
        l6.add(new Integer(8));
        l6.add(new Integer(3));
        l6.add(new Integer(2));
        l6.add(new Integer(7));
        l6.add(new Integer(6));
        l6.add(new Integer(5));

        System.out.println("Actual: " + findDuplicatesRe.findDuplicatesInList(l6) + ", Expected: " + 4);
    }
}
