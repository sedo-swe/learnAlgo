package main.ltcode_gfg;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  2418. Sort the People (Easy)
 */
@FunctionalInterface
interface IntSortThePeople {
    String[] sortPeople(String[] names, int[] heights);
}
public class SortThePeople {

    IntSortThePeople sortPeople1st = ((names, heights) -> {
        Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < heights.length; i++) {
            map.put(heights[i], names[i]);
        }
        /*String[] result = map.keySet().stream().map(a -> map.get(a)).toArray(String[]::new);
        for (String s : result) {
            System.out.println(s);
        }

        String[] result1 = new String[map.keySet().size()];
        Iterator<Integer> iterator = map.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            result1[i++] = map.get(iterator.next());
        }
        for (String s : result1) {
            System.out.println(s);
        }*/
//        return map.keySet().stream().map((m) -> {return map.get(m);}).collect(Collectors.toList()).toArray(String[]::new);
        return map.keySet().stream().map(a -> map.get(a)).toArray(String[]::new);
    });

    IntSortThePeople sortThePeopleSol = (((names, heights) -> {
        SortName(heights , names , 0 , heights.length-1);
        return names;
    }));

    public static void SortName(int[] heights , String[] names , int Si , int Ei){
        if(Si >= Ei){
            return;
        }

        int pivot = heights[Ei];
        int leftTR = Si;
        int rightTR = Ei;

        while(leftTR < rightTR){
            while(heights[leftTR] >= pivot  && leftTR <rightTR ){
                leftTR++;
            }

            while(heights[rightTR] <= pivot && leftTR < rightTR){
                rightTR--;
            }

            swap(heights , leftTR, rightTR);
            swap(names , leftTR, rightTR);
        }
        swap(heights , leftTR, Ei);
        swap(names , leftTR, Ei);

        SortName(heights , names , Si , leftTR-1);
        SortName(heights , names , leftTR+1 , Ei);

    }

    public static void swap(int[] arr, int a , int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b]  = temp;
    }
    public static void swap(String[] names, int a , int b){
        String temp = names[a];
        names[a] = names[b];
        names[b] = temp;
    }

    public void test(IntSortThePeople func) {
        String[] names1 = {"Mary", "John", "Emma"};
        int[] heights1 = {180, 165, 170};
//        Arrays.stream(func.sortPeople(names1, heights1)).forEach((a)-> System.out.print(a + " "));
        System.out.println(Arrays.stream(func.sortPeople(names1, heights1)).collect(Collectors.joining(" ")));
        System.out.println("================");
        System.out.println(this.toStringFromArray(func.sortPeople(names1, heights1)));
    }

    private String toStringFromArray(String[] n) {
        StringBuffer sb = new StringBuffer();
        for (String i : n) {
            sb.append(i + ",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SortThePeople sortThePeople = new SortThePeople();
        sortThePeople.test(sortThePeople.sortThePeopleSol);
    }
}
