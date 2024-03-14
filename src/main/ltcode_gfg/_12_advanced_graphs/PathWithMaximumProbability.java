package main.ltcode_gfg._12_advanced_graphs;

import java.util.*;

/**
 * 1514. Path with Maximum Probability (Medium)
 */
@FunctionalInterface
interface IntPathWithMaximumProbability {
    double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node);
}

public class PathWithMaximumProbability {
    IntPathWithMaximumProbability findMaxPath = ((n, edges, succProb, start_node, end_node) -> {
        Map<Integer, List<double[]>> edgesMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            // As edge is undirected, add both ways
            edgesMap.computeIfAbsent(edge[0], ignored -> new ArrayList<>()).add(new double[]{(double) edge[1], succProb[i]});
            edgesMap.computeIfAbsent(edge[1], ignored -> new ArrayList<>()).add(new double[]{(double) edge[0], succProb[i]});
        }
//        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble((double[] value) -> value[1]).reversed());
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1])); // {from, succProb}
        pq.offer(new double[]{start_node, 1.0});

        double[] probToNode = new double[n];
        probToNode[start_node] = 1.0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            if (!edgesMap.containsKey((int) cur[0]))
                continue;
            for (double[] next : edgesMap.get((int) cur[0])) {
                if (cur[1] * next[1] > probToNode[(int) next[0]]) {
                    probToNode[(int) next[0]] = cur[1] * next[1];
                    pq.offer(new double[]{next[0], cur[1] * next[1]});
                }
            }
        }
        return probToNode[end_node];
    });

    /*
        passed, time: O (n * m), n is # of nodes, m is # of edges
     */
    IntPathWithMaximumProbability findMaxPathSol = ((n, edges, succProb, start_node, end_node) -> {
        double[] probToNode = new double[n];
        probToNode[start_node] = 1.0;

        for (int i = 0; i < n - 1; i++) {
            boolean hasUpdate = false;
            for (int j = 0; j < edges.length; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                double pathProb = succProb[j];
                if (probToNode[u] * pathProb > probToNode[v]) {
                    probToNode[v] = probToNode[u] * pathProb;
                    hasUpdate = true;
                }
                if (probToNode[v] * pathProb > probToNode[u]) {
                    probToNode[u] = probToNode[v] * pathProb;
                    hasUpdate = true;
                }
            }
            if (!hasUpdate)
                break;
        }

        return probToNode[end_node];
    });

    public void test(IntPathWithMaximumProbability func) {
        System.out.println("Expected: 0.25000, Actual: " + func.maxProbability(3, new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.2}, 0, 2));
    }

    public static void main(String[] args) {
        PathWithMaximumProbability p = new PathWithMaximumProbability();
        p.test(p.findMaxPath);
    }
}
