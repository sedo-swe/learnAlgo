package main.ltcode_gfg._12_advanced_graphs;

import java.util.*;

/**
 *  743. Network Delay Time (Medium)
 *      https://github.com/YaokaiYang-assaultmaster/LeetCode/blob/master/LeetcodeAlgorithmQuestions/743.%20Network%20Delay%20Time.md
 *      https://www.geeksforgeeks.org/what-are-the-differences-between-bellman-fords-and-dijkstras-algorithms/
 */
@FunctionalInterface
interface IntNetworkDelayTime {
    int networkDelayTime(int[][] times, int n, int k);
}

class NetworkDelayTime {
    IntNetworkDelayTime dfsSol = ((times, n, k) -> {
        Map<Integer, List<Integer[]>> neighborsMap = new HashMap<>();   // key: source, list of [dest, time]
        for (int[] t : times) {
            neighborsMap.computeIfAbsent(t[0], ignored -> new ArrayList<>()).add(new Integer[]{t[1], t[2]});
        }
        for (int neighbor : neighborsMap.keySet()) {
            Collections.sort(neighborsMap.get(neighbor), (a, b) -> {return a[1] - b[1];});
        }
        Integer[] timeToNodes = new Integer[n+1];
        Arrays.fill(timeToNodes, Integer.MAX_VALUE);
        timeToNodes[0] = 0;
        dfs(k, 0, neighborsMap, timeToNodes);

        int minTime = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (timeToNodes[i] == Integer.MAX_VALUE) return -1;
            minTime = Math.max(minTime, timeToNodes[i]);
        }
        return minTime;
    });

    private void dfs(int node, int elapsedTime, Map<Integer, List<Integer[]>> neighborsMap, Integer[] timeToNodes) {
        if (elapsedTime >= timeToNodes[node]) return;
        timeToNodes[node] = elapsedTime;

        if (neighborsMap.containsKey(node)) {
            for (Integer[] next : neighborsMap.getOrDefault(node, new ArrayList<>())) {
                dfs(next[0], elapsedTime + next[1], neighborsMap, timeToNodes);
            }
        }
    }

    /*
        Use Dijkstra’s Algorithm
            - find the shortest paths from Source to all Vertices
            time: O (E * log V), E: # of edges, V: # of vertices
            space: O (E + V)
     */
    IntNetworkDelayTime dijkstraSol = ((times, n, k) -> {
        int elapsedTime = 0;
        Map<Integer, List<Integer[]>> edgesMap = new HashMap<>();   // key: src, value: Integer[] 0: dest, 1: weight
        for (int[] edge : times) {
            edgesMap.computeIfAbsent(edge[0], ignored -> new ArrayList<>()).add(new Integer[]{edge[1], edge[2]});
        }
        Set<Integer> visitSet = new HashSet<>();
        Integer[] timeToNode = new Integer[n + 1];
        Arrays.fill(timeToNode, Integer.MAX_VALUE);
        PriorityQueue<Integer[]> minQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minQueue.offer(new Integer[]{k, 0});    // e { 2: [1,1], [3,1], 3: [4,1] }

        while (!minQueue.isEmpty()) {   // q: [  ], e: 2
            Integer[] curr = minQueue.poll();   // c: [4,1]
            if (visitSet.contains(curr[0]))
                continue;
            visitSet.add(curr[0]);      // v: {2, 1, 3, 4}, n: []
            elapsedTime = Math.max(elapsedTime, curr[1]);

            for (Integer[] neighbor : edgesMap.getOrDefault(curr[0], new ArrayList<>())) {
                if (!visitSet.contains(neighbor[0]) && (curr[1] + neighbor[1]) < timeToNode[neighbor[0]])
                    minQueue.offer(new Integer[]{neighbor[0], curr[1] + neighbor[1]});
            }
        }
        return visitSet.size() == n ? elapsedTime : -1;
    });

    IntNetworkDelayTime leetSol = ((times, n, k) -> {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] graph = new int[n][n];
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, INF);
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for(int[] time : times){
            graph[time[0] - 1][time[1] - 1] = time[2];
        }
        for(int i = 0; i < n; i++){
            dist[i] = graph[k - 1][i];
        }
        dist[k-1] = 0;
        visited[k - 1] = true;

        for(int i = 0; i < n - 1; i++){
            int min = INF;
            int u = -1;

            for(int j = 0; j < n; j++){
                if(!visited[j] && dist[j] < min){
                    min = dist[j];
                    u = j;
                }
            }

            if(u == -1)
                return -1;

            visited[u] = true;

            for(int j = 0; j < n; j++){
                if(!visited[j] && dist[u] + graph[u][j] < dist[j]){
                    dist[j] = dist[u] + graph[u][j];
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            ans = Math.max(dist[i], ans);
        }

        return ans == INF ? -1 : ans;
    });

    /*
        Bellman Ford Algorithm
        Time: O (n * t), Space: O(n) where t is the length of times
     */
    IntNetworkDelayTime bellmanFordSol = ((times, n, k) -> {
        // initialize an array with max value of size n
        int[] paths = new int[n];
        Arrays.fill(paths, Integer.MAX_VALUE);
        paths[k - 1] = 0;

        for (int i = 0; i < n; i++) {
            // make a copy of paths
            int[] temp = new int[n];
            temp = Arrays.copyOf(paths, paths.length);

            // loop through times
            for (int j = 0; j < times.length; j++) {
                int src = times[j][0]; // source
                int tgt = times[j][1]; // target
                int time = times[j][2]; // time

                if (temp[src - 1] != Integer.MAX_VALUE
                        && temp[src - 1] + time < temp[tgt - 1])
                {
                    temp[tgt - 1] = temp[src - 1] + time;
                }
            }
            // set paths to temp
            paths = temp;
        }

        int result = Integer.MIN_VALUE;
        // calculate max value
        for (int i = 0; i < n; i++) {
            if (paths[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, paths[i]);
        }

        // return result
        return result;
    });

    public void test(IntNetworkDelayTime func) {
        System.out.println("Expected: 2, Actual: " + func.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println("Expected: 1, Actual: " + func.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println("Expected: -1, Actual: " + func.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
        System.out.println("Expected: 2, Actual: " + func.networkDelayTime(new int[][]{{2, 1, 3}, {2, 3, 1}, {3, 1, 1}}, 3, 2));
        System.out.println("Expected: 3, Actual: " + func.networkDelayTime(new int[][]{{2, 1, 3}, {2, 3, 1}}, 3, 2));
    }
    public static void main(String[] args) {
        NetworkDelayTime s = new NetworkDelayTime();
        s.test(s.dfsSol);
        System.out.println();
        s.test(s.dijkstraSol);
    }
}
