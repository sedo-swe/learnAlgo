package main.ltcode_gfg._04_stack;

import java.util.*;

/**
 * 853. Car Fleet (Medium)
 */

@FunctionalInterface
interface IntCarFleet {
    int carFleet(int target, int[] position, int[] speed);
}

public class CarFleet {
    IntCarFleet intCarFleet = ((target, position, speed) -> {
        if (position.length == 1) {
            return 1;
        }
        List<int[]> cars = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            cars.add(new int[]{position[i], speed[i]});
        }
        cars.sort((a, b) -> a[0] - b[0]);
        for (int[] car : cars) {
            System.out.print("["+car[0] + ", " + car[1]+"], ");
        }
        System.out.println();

        int carFleets = position.length;
        Stack<int[]> inRaceStack = new Stack<>();
        List<Integer> catchUp = new ArrayList<>();
        int run = target;
        while (run-->=0) {
            for (int i = 0; i < cars.size(); i++) {
                if (!catchUp.contains(i)) {
                    int[] car = cars.get(i);
                    car[0] = car[0] + car[1];
//                    System.out.println("C2: "+car[0]+ ": "+(!inRaceStack.isEmpty()) + " " + (!inRaceStack.isEmpty() ? inRaceStack.peek()[0] == car[0] : "empty"));
                    if (!inRaceStack.isEmpty() && inRaceStack.peek()[0] == car[0]) {
//                        System.out.println("carFleets");
                        inRaceStack.pop();
                        catchUp.add(i - 1);
                        carFleets--;
                        if (car[0] >= target) {
                            catchUp.add(i);
                            continue;
                        }
//                        System.out.println(carFleets);
                        inRaceStack.push(car);
                    } else {
                        inRaceStack.push(car);
                    }
                }
            }
        }
        return carFleets;
    });

    IntCarFleet intCarFleetCalc = ((target, position, speed) -> {
        if (position.length == 1) {
            return 1;
        }
        List<int[]> cars = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            cars.add(new int[]{position[i], speed[i]});
        }
        cars.sort((a, b) -> a[0] - b[0]);
        for (int[] car : cars) {
            System.out.println("["+car[0] + ", " + car[1]+"], ");
        }
        System.out.println();
        int n = cars.size();

        Stack<Double> inRaceStack = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            int[] car = cars.get(i);
            double t = (double) (target - car[0]) / car[1];
            if (!inRaceStack.isEmpty() && t <= inRaceStack.peek())
                continue;
            else
                inRaceStack.push(t);

        }
        return inRaceStack.size();
    });

    IntCarFleet intCarFleetNeet = ((target, position, speed) -> {
        if (position.length == 1) return 1;
        Stack<Double> stack = new Stack<>();
        int[][] combine = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            combine[i][0] = position[i];
            combine[i][1] = speed[i];
        }

        Arrays.sort(combine, java.util.Comparator.comparingInt(o -> o[0]));
        for (int i = combine.length - 1; i >= 0; i--) {
            double currentTime = (double) (target - combine[i][0]) /
                    combine[i][1];
            if (!stack.isEmpty() && currentTime <= stack.peek()) {
                continue;
            } else {
                stack.push(currentTime);
            }
        }
        return stack.size();
    });

    public void test(IntCarFleet func) {
//        System.out.println("Expected: 3, Actual: " + func.carFleet(12, new int[] {10,8,0,5,3}, new int[] {2,4,1,1,3}));
//        System.out.println("Expected: 1, Actual: " + func.carFleet(10, new int[] {3}, new int[] {3}));
        System.out.println("Expected: 1, Actual: " + func.carFleet(100, new int[] {0,2,4}, new int[] {4,2,1}));
    }

    public static void main(String[] args) {
        CarFleet cf = new CarFleet();
        cf.test(cf.intCarFleetCalc);
        System.out.println();
        cf.test(cf.intCarFleetNeet);
    }
}
