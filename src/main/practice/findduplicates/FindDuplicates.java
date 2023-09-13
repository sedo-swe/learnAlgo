package main.practice.findduplicates;

import java.time.LocalDate;
import java.util.LinkedList;


public class FindDuplicates {
    public void temp() {
        int position = 1;
        LinkedList<Integer> lists = new LinkedList<>();

        lists.add(new Integer(3));
        lists.add(new Integer(4));
        lists.add(new Integer(2));
        lists.add(new Integer(3));
        lists.add(new Integer(1));
        lists.add(new Integer(5));

        int pos = lists.size()-1;
        int val = -1;
        for (int i=0; i<lists.size()+5; i++) {
            val = lists.get(pos);
            System.out.print(val + " ");
            pos = val - position;
        }

        System.out.println();
        LinkedList<Integer> lists2 = new LinkedList<>();

        lists2.add(new Integer(4));
        lists2.add(new Integer(3));
        lists2.add(new Integer(1));
        lists2.add(new Integer(1));
        lists2.add(new Integer(4));

        pos = lists2.size() - 1;
        val = -1;
        for (int i=0; i<lists2.size()+5; i++) {
            val = lists2.get(pos);
            System.out.print(val + " ");
            pos = val - position;
        }

    }

    public int findDup(LinkedList<Integer> list) {
        int n = list.size() - 1;

        // Step 1: Get inside a cycle
        // Start at position n + 1(last element in list) and walk n steps
        // to find a position guaranteed to be in a cycle
        int positionInCycle = n + 1;
        for (int i=0; i<n; i++) {
            positionInCycle = list.get(positionInCycle - 1);
        }


        // Step 2: Find the length of the cycle
        // Find the length of the cycle by remembering a position in the cycle
        // and counting the steps it takes to get back to that position
        int rememberedPositionInCycle = positionInCycle;
        int currentPositionInCycle = list.get(positionInCycle - 1); // 1 step ahead
        int cycleStepCount = 1;

        while(currentPositionInCycle != rememberedPositionInCycle) {
            currentPositionInCycle = list.get(currentPositionInCycle - 1);
            cycleStepCount++;
        }


        // Step 3: Find the first node of the cycle
        // Start two pointers
        // (1) at position n+1
        // (2) ahead of position n+1 as many steps as the cycle's length
        int pointerStart = n + 1;
        int pointerAhead = n + 1;
        for (int i=0; i<cycleStepCount; i++) {
            pointerAhead = list.get(pointerAhead - 1);
        }

        while (pointerStart != pointerAhead) {
            pointerStart = list.get(pointerStart - 1);
            pointerAhead = list.get(pointerAhead - 1);
        }

        return pointerStart;
    }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today.getDayOfMonth());
        System.out.println(Integer.toString(today.getDayOfMonth()));
        if (Integer.toString(today.getDayOfMonth()).equals("23"))
            System.out.println("Test 1-1");
        else
            System.out.println("Test 1-2");
        if ("23".equals(today.getDayOfMonth()))
            System.out.println("Test 2-1");
        else
            System.out.println("Test 2-2");
        FindDuplicates fd = new FindDuplicates();
//        fd.temp();

        LinkedList<Integer> lists = new LinkedList<>();

        lists.add(new Integer(3));
        lists.add(new Integer(4));
        lists.add(new Integer(2));
        lists.add(new Integer(3));
        lists.add(new Integer(1));
        lists.add(new Integer(5));

        for (Integer i : lists) {
            System.out.print(i.intValue() + " ");
        }
        System.out.println("--> duplicated: " + fd.findDup(lists));


        LinkedList<Integer> lists2 = new LinkedList<>();

        lists2.add(new Integer(4));
        lists2.add(new Integer(3));
        lists2.add(new Integer(1));
        lists2.add(new Integer(1));
        lists2.add(new Integer(4));

        for (Integer i : lists2) {
            System.out.print(i.intValue() + " ");
        }
        System.out.println("--> duplicated: " + fd.findDup(lists2));

        LinkedList<Integer> lists3 = new LinkedList<>();

        lists3.add(new Integer(4));
        lists3.add(new Integer(9));
        lists3.add(new Integer(1));
        lists3.add(new Integer(5));
        lists3.add(new Integer(7));
        lists3.add(new Integer(8));
        lists3.add(new Integer(3));
        lists3.add(new Integer(10));
        lists3.add(new Integer(3));
        lists3.add(new Integer(6));
        lists3.add(new Integer(2));

        for (Integer i : lists3) {
            System.out.print(i.intValue() + " ");
        }
        System.out.println("--> duplicated: " + fd.findDup(lists3));


        LinkedList<Integer> lists4 = new LinkedList<>();

        lists4.add(new Integer(2));
        lists4.add(new Integer(3));
        lists4.add(new Integer(1));
        lists4.add(new Integer(3));

        for (Integer i : lists4) {
            System.out.print(i.intValue() + " ");
        }
        System.out.println("--> duplicated: " + fd.findDup(lists4));


        LinkedList<Integer> lists5 = new LinkedList<>();

        lists5.add(new Integer(2));
        lists5.add(new Integer(1));
        lists5.add(new Integer(3));
        lists5.add(new Integer(4));
        lists5.add(new Integer(1));

        for (Integer i : lists5) {
            System.out.print(i.intValue() + " ");
        }
        System.out.println("--> duplicated: " + fd.findDup(lists5));


        LinkedList<Integer> lists6 = new LinkedList<>();

        lists6.add(new Integer(5));
        lists6.add(new Integer(1));
        lists6.add(new Integer(3));
        lists6.add(new Integer(2));
        lists6.add(new Integer(4));
        lists6.add(new Integer(3));

        for (Integer i : lists6) {
            System.out.print(i.intValue() + " ");
        }
        System.out.println("--> duplicated: " + fd.findDup(lists6));
    }
}
