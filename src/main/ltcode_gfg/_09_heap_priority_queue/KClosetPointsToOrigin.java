package main.ltcode_gfg._09_heap_priority_queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

@FunctionalInterface
interface IntKClosest {
    int[][] KClosest(int[][] points, int k);
}
class KClosestPointsToOrigin {
    IntKClosest intKClosest = ((points, k) -> {
        if (points == null || points.length == 0) {
            return null;
        }

        // Calculate distance of each point to origin & put into priorityQueue
         PriorityQueue<PointToOrg> distances = new PriorityQueue<>(new Comparator<PointToOrg>() {
             @Override
             public int compare(PointToOrg p1, PointToOrg p2) {
                 if (p1.distance > p2.distance)
                     return 1;
                 else if (p1.distance < p2.distance)
                     return -1;
                 else
                     return 0;
             }
         });
        // pattern 1:
        // PriorityQueue<PointToOrg> distances = new PriorityQueue<>(PointToOrg::compareDistance);
        for (int[] point : points) {
            double distance = Math.sqrt(point[0] * point[0] + point[1] * point[1]);
            distances.add(new PointToOrg(point, distance));
        }

        // Make array of int[] to return
        int[][] result = new int[k][2];
        for(int i=0; i<k; i++) {
            result[i] = distances.poll().point;
        }
        return result;
    });


    // O(n log k)
    IntKClosest intKClosestSol = ((points, k) -> {
        if (points == null || points.length == 0) {
            return null;
        }

        // Calculate distance of each point to origin & put into priorityQueue
        PriorityQueue<int[]> distances = new PriorityQueue<>((p1, p2) ->
            Integer.compare(p1[0] * p1[0] + p1[1] * p1[1],  p2[0] * p2[0] + p2[1] * p2[1]));
        for (int[] point : points) {
            distances.add(point);

            // Remove when size exceeds k
            if (distances.size() > k) {
                distances.remove();
            }
        }

        // Make array of int[] to return
        int[][] result = new int[k][2];
        for(int i=0; i<k; i++) {
            result[i] = distances.poll();
        }
        return result;
    });

    IntKClosest intKClosestSolQuickSort = ((points, k) -> {
        sortByDistance(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    });

    public void sortByDistance(int[][] points, int start, int end, int k) {
        if (start >= end) return;
        int right = end;
        int left = start;
        int pivotDistance = getPivotDistance(points, start + (end - start) / 2);
        int[] temp;

        while (left <= right) {
            while (left <= right && getPivotDistance(points, left) < pivotDistance) {
                left++;
            }
            while (left <= right && getPivotDistance(points, right) > pivotDistance) {
                right--;
            }
            if (left <= right) {
                temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                left++;
                right--;
            }
        }
        if (left <= k) {
            sortByDistance(points, left, end, k);
        }
        if (right >= k) {
            sortByDistance(points, start, right, k);
        }
    }

    private int getPivotDistance(int[][] points, int pos) {
        return points[pos][0] * points[pos][0] + points[pos][1] * points[pos][1];
    }


    public void test(IntKClosest func) {
        int[][] test1 = new int[][] {{1,3}, {-2, 2}};
        System.out.println("Expected: [[-2, 2]], Actual: " + this.printIne2DimensionalArray(func.KClosest(test1, 1)));

        int[][] test2 = new int[][] {{3,3}, {5, -1}, {-2, 4}};
        System.out.println("Expected: [[3, 3], [-2, 4]], Actual: " + this.printIne2DimensionalArray(func.KClosest(test2, 2)));
    }

    private String printIne2DimensionalArray(int[][] src) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int[] a : src) {
            sb.append("[" + a[0] + ", " + a[1] + "], ");
        }
        sb.append("]");
        if (sb.length() >= 10) {
            sb.replace(sb.length() - 3, sb.length() - 1, "");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin kc = new KClosestPointsToOrigin();
        kc.test(kc.intKClosest);
        System.out.println("= KSol =======");
        kc.test(kc.intKClosestSol);
        System.out.println("= LSol =======");
        kc.test(kc.intKClosestSolQuickSort);
    }

    class PointToOrg {
        int[] point;
        double distance;

        PointToOrg(int[] point, double distance) {
            this.point = point;
            this.distance = distance;
        }

        /* // pattern 1
        public static int compareDistance(PointToOrg p1, PointToOrg p2) {
            if (p1.distance > p2.distance)
                return 1;
            else if (p1.distance < p2.distance)
                return -1;
            else
                return 0;
        }*/
    }
}
