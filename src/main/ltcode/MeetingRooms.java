package main.ltcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Easy
    Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
    determine if a person could attend all meetings.

    Example 1:
        Input:  [[0,30],[5,10],[15,20]]
        Output: false

    Example 2:
        Input:  [[7,10],[2,4]]
        Output: true
 */
@FunctionalInterface
interface IntMeetingRooms {
    boolean canAttendMeetings(List<Interval> intervals);
}

public class MeetingRooms {

    /*
        First idea.
            Sort the array in ascending order according to its start time in intervals.
            Check adjacent intervals whether they both are overwrapped.
     */
    IntMeetingRooms intMeetingRooms = ((intervals) -> {
        if (intervals == null || intervals.size() <= 1) {
            return true;
        }

        Collections.sort(intervals, (a, b) -> a.start - b.start);

        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i + 1).start <= intervals.get(i).end) {
                return false;
            }
        }
        return true;
    });

    IntMeetingRooms intMeetingRooms2nd = ((intervals) -> {
        if (intervals == null || intervals.size() <= 1) {
            return true;
        }

        int length = intervals.size();

        int[] starts = new int[length];
        int[] ends = new int[length];

        for (int i = 0; i < length; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int j = 0;
        while (j + 1 < length) {
            if (ends[j] > starts[j + 1]) {
                return false;
            }
            j++;
        }

        return true;
    });

    public void test(IntMeetingRooms func) {
        List<Interval> intervals1 = new LinkedList<>();
        intervals1.add(new Interval(0, 30));
        intervals1.add(new Interval(5, 10));
        intervals1.add(new Interval(15, 20));
        System.out.println("Expected: false, Actual: " + func.canAttendMeetings(intervals1));

        List<Interval> intervals2 = new LinkedList<>();
        intervals2.add(new Interval(7, 10));
        intervals2.add(new Interval(2, 4));
        System.out.println("Expected: true,  Actual: " + func.canAttendMeetings(intervals2));

        List<Interval> intervals3 = new LinkedList<>();
        intervals3.add(new Interval(15, 20));
        intervals3.add(new Interval(5, 10));
        intervals3.add(new Interval(11, 16));
        System.out.println("Expected: false,  Actual: " + func.canAttendMeetings(intervals3));

        List<Interval> intervals4 = new LinkedList<>();
        intervals4.add(new Interval(15, 20));
        intervals4.add(new Interval(5, 10));
        intervals4.add(new Interval(9, 14));
        System.out.println("Expected: false,  Actual: " + func.canAttendMeetings(intervals4));

        List<Interval> intervals5 = new LinkedList<>();
        intervals5.add(new Interval(15, 20));
        intervals5.add(new Interval(11, 13));
        intervals5.add(new Interval(5, 10));
        System.out.println("Expected: true,  Actual: " + func.canAttendMeetings(intervals5));
    }

    public static void main(String[] args) {
        MeetingRooms mr = new MeetingRooms();
        mr.test(mr.intMeetingRooms);
    }
}
