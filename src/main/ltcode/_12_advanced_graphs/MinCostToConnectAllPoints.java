package main.ltcode._12_advanced_graphs;

import java.util.*;

/**
 *  1584. Min Cost to Connect All Points (Medium)
 *      - Prim's algorithm, Minimum Spanning Tree
 */
@FunctionalInterface
interface IntMinCostToConnectAllPoints {
    int minCostConnectPoints(int[][] points);
}

public class MinCostToConnectAllPoints {
    IntMinCostToConnectAllPoints intMinCostToConnectAllPoints = ((points) -> {
        int minCost = 0;
        int[][] distances = new int[points.length][points.length];
        int[][] minPoints = new int[points.length][2];
        int[] minCosts = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            int min = Integer.MAX_VALUE;
            boolean minCheck = false;
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int[] point1 = points[i];
                int[] point2 = points[j];
                int distance = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
                distances[i][j] = distance;
                if (distance < min) {
                    minPoints[i] = point2;
                    System.out.println("i: " + i+ ", j: " + j + ", mP+j (" + minPoints[j][0] + ", " + minPoints[j][1] + "), point2: ("
                    + point2[0] + ", " + point2[1]+")");
                    if (minPoints[j][0] == point1[0] && minPoints[j][1] == point1[1]) {
                        System.out.println("++ "+distance);
                        minCheck = false;
                        break;
                    }
                    minCosts[i] = distance;
                    min = distance;
                    minCheck = true;
                }
            }
            if (minCheck) {
//                System.out.println("+ "+min);
                minCost += min;
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                System.out.print(distances[i][j] + " ");
            }
            System.out.println();
        }
        return minCost;
    });

    IntMinCostToConnectAllPoints exampleOfNestedList = (points -> {
        int minCost = 0;
        int length = points.length;
        System.out.println(length);

        // Build adjacency list
        List<List<int[]>> adj = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            adj.add(i, new ArrayList<>());
        }
        for (int i = 0; i < length; i++) {
            int[] point1 = points[i];
            List<int[]> row = null;
            for (int j = i + 1; j < length; j++) {
                int[] point2 = points[j];
                int dist = Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
                row = adj.get(i);
                row.add(new int[] {dist, j});
                adj.set(i, row);
                row = adj.get(j);
                row.add(new int[] {dist, i});
                adj.set(j, row);
            }
        }
        for (List<int[]> row : adj) {
            for (int[] pair : row) {
                System.out.println("Dist: " + pair[0] + ", point: (" + points[pair[1]][0] + ", " + points[pair[1]][1] + ")");
            }
        }
        return minCost;
    });

    /**
     *  time: O(n^2 log n), space: O(n^2) using PriorityQueue and Set with Prim's algorithm
     */
    IntMinCostToConnectAllPoints solByPrim = (points -> {
        PriorityQueue<int[]> min = new PriorityQueue<>((d1, d2) -> Integer.compare(d1[0], d2[0]));  // Contain int[2] of {dist, point index}
        Set<Integer> visited = new HashSet<>();
        int minCost = 0, length = points.length;
        min.offer(new int[]{0, 0});

        while (visited.size() < length) {
            int[] cur = min.poll();
            int weight = cur[0];
            int curNode = cur[1];

            if (visited.contains(curNode)) {
                continue;
            }
            visited.add(curNode);
            minCost += weight;

            for (int next = 0; next < length; next++) {
                if (!visited.contains(next)) {
                    int nextWeight =
                            Math.abs(points[next][0] - points[curNode][0]) +
                            Math.abs(points[next][1] - points[curNode][1]);
                    min.offer(new int[]{nextWeight, next});
                }
            }
        }
        return minCost;
    });

    /**
     *  Time: O(n^2), Space: O(n)
     */
    IntMinCostToConnectAllPoints intMinCostToConnectAllPointsSol = (points -> {
        int n = points.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int total = 0;

        for(int p = 0; p < n-1; p++){
            int leastDist = Integer.MAX_VALUE;
            int leastPoint = -1;
            for(int p2 = p+1; p2 < n; p2++){
                dist[p2] = Math.min(dist[p2], getDist(points[p],points[p2]));
                if(leastDist > dist[p2]){
                    leastDist = dist[p2];
                    leastPoint = p2;
                }
            }
            int[] temp = points[p+1];
            points[p+1] = points[leastPoint];
            points[leastPoint] = temp;
            int temp2 = dist[p+1];
            dist[p+1] = dist[leastPoint];
            dist[leastPoint] = temp2;
            total += leastDist;
        }
        return total;
    });

    public int getDist(int[] p1, int[] p2){
        return (Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]));
    }


    public void test(IntMinCostToConnectAllPoints func) {
        int[][] points1 = new int[][] {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println("Expected: 20, Actual: " + func.minCostConnectPoints(points1));

        int[][] points2 = new int[][] {{3, 12}, {-2, 5}, {-4, 1}};
        System.out.println("Expected: 18, Actual: " + func.minCostConnectPoints(points2));
    }

    public static void main(String[] args) {
        MinCostToConnectAllPoints minCost = new MinCostToConnectAllPoints();
//        minCost.test(minCost.exampleOfNestedList);
        minCost.test(minCost.solByPrim);
        minCost.test(minCost.intMinCostToConnectAllPointsSol);
    }
}
