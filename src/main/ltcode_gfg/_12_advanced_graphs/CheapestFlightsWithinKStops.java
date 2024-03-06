package main.ltcode_gfg._12_advanced_graphs;

import jdk.internal.util.xml.impl.Pair;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops (Medium)
 *      - Bellman-Ford: https://www.youtube.com/watch?v=5eIK3zUdYmE&t=1213s
 *      - Dijkstra: https://www.youtube.com/watch?v=9XybHVqTHcQ
 */

@FunctionalInterface
interface IntCheapestFlightsWithinKStops {
    int findCheapestPrice(int n, int[][] flights, int src, int dst, int k);
}

public class CheapestFlightsWithinKStops {
    /*
        Works, but cannot pass time limit exceeded
        ==> After improving,
        passed, time: O (n^2), space: O (n)
     */
    public IntCheapestFlightsWithinKStops findFlightsBFS = ((n, flights, src, dst, k) -> {
        Map<Integer, List<Integer[]>> flightsMap = new HashMap<>(); // key: source, value: [dest, price]
        boolean dstHasIncomingFlight = false;
        for (int[] flight : flights) {
            flightsMap.computeIfAbsent(flight[0], ignored -> new ArrayList<>()).add(new Integer[]{flight[1], flight[2]});
            if (flight[1] == dst)
                dstHasIncomingFlight = true;
        }

        // Early termination
        if ((!flightsMap.containsKey(src) && src != dst) || !dstHasIncomingFlight) return -1;
        if (src == dst) return 0;

        int minPrice = Integer.MAX_VALUE;
        Queue<int[]> routes = new ArrayDeque<>();   // [city, stops, price]
        routes.offer(new int[]{src, 0, 0});
        while (!routes.isEmpty()) {
            for (int i = routes.size(); i > 0; i--) {
                int[] cur = routes.poll();

                if (cur[0] == dst) {
                    minPrice = Math.min(cur[2], minPrice);
                    continue;
                }
                if (cur[1] > k || !flightsMap.containsKey(cur[0]))
                    continue;
                for (Integer[] neighbor : flightsMap.get(cur[0])) {
                    if (cur[2] + neighbor[1] < minPrice)
                        routes.offer(new int[]{neighbor[0], cur[1] + 1, cur[2] + neighbor[1]});
                }
            }
        }
        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    });

    /*
        Works, but cannot pass time limit exceeded
     */
    public IntCheapestFlightsWithinKStops findFlightsBFS2 = ((n, flights, src, dst, k) -> {
        // Create a map to store flights information
        Map<Integer, List<int[]>> flightsMap = new HashMap<>();
        for (int[] flight : flights) {
            flightsMap.computeIfAbsent(flight[0], key -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        // Use a Queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0, 0}); // [city, stops, price]
        int minPrice = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int city = cur[0];
                int stops = cur[1];
                int price = cur[2];

                if (city == dst) {
                    minPrice = Math.min(minPrice, price);
                    continue;
                }

                if (stops > k || price > minPrice) {
                    continue;
                }

                if (!flightsMap.containsKey(city)) {
                    continue;
                }
                for (int[] neighbor : flightsMap.get(city)) {
                    int nextCity = neighbor[0];
                    int nextPrice = price + neighbor[1];
                    queue.offer(new int[]{nextCity, stops + 1, nextPrice});
                }
            }
        }

        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    });

    /*
        Bellman-Ford
        time: O (E * K) which E is # of edges (connections) * K is k stops, space: O (n)
     */
    public IntCheapestFlightsWithinKStops bellmanFord = ((n, flights, src, dst, k) -> {
        // initialize an array with max value of size n
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        // price from source to source is always 0
        prices[src] = 0;

        for (int i = 0; i <= k; i++) {
            // make a copy of prices
            int[] newPrices = new int[n];
            newPrices = Arrays.copyOf(prices, prices.length);

            // loop through flights
            for (int j = 0; j < flights.length; j++) {
                int s = flights[j][0]; // from
                int d = flights[j][1]; // to
                int p = flights[j][2]; // price

                if (prices[s] == Integer.MAX_VALUE) {
                    continue;
                }

                if (prices[s] + p < newPrices[d]) {
                    newPrices[d] = prices[s] + p;
                }
            }

            // set newPrices to prices
            prices = newPrices;
        }

        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    });

    public IntCheapestFlightsWithinKStops bellmanFordImproved = ((n, flights, src, dst, k) -> {
        int[] costs = new int[n];
        Arrays.fill(costs,Integer.MAX_VALUE);
        costs[src] = 0;
        while((k--)>=0)
        {
            if(Analyze(costs,flights))
                break;
        }
        /*
        for(Integer cst : costs)
            System.out.print(cst+" ");
        */
        return (costs[dst] == Integer.MAX_VALUE)?-1:costs[dst];
    });
    public boolean Analyze(int[] costs,int[][] flights)
    {
        boolean changes = true;
        int[] oldcosts = costs.clone();
        for(int[] flight : flights)
        {
            int frm = flight[0],to = flight[1],cst = flight[2];
            if(oldcosts[frm]<Integer.MAX_VALUE && costs[frm]+cst<costs[to])
            {
                costs[to] = oldcosts[frm]+cst;
                changes = false;
            }
        }
        return changes;
    }


    /*
        Passed. time: O (n*k) which n is # of cities & k is maximum stops, space: O (n)
     */
    public IntCheapestFlightsWithinKStops dijkstra = ((n, flights, src, dst, k) -> {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        int m = flights.length;
        for(int i = 0; i<m; i++){
            adj.get(flights[i][0]).add(new int[]{flights[i][1], flights[i][2]});
        }

        Queue<int[]> pq = new LinkedList<int[]>();  // stops, node, dist
        pq.add(new int[]{0, src, 0});

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        while(!pq.isEmpty()){
            int[] it = pq.peek();
            pq.remove();
            int stops = it[0];
            int node = it[1];
            int cost = it[2];

            if(stops  > k) continue;
            for(int[] iter: adj.get(node)) {
                int adjNode = iter[0];
                int edw = iter[1];

                if(cost + edw < dist[adjNode] && stops <= k){
                    dist[adjNode] = cost + edw;
                    pq.add(new int[]{stops+1, adjNode, dist[adjNode]});
                }
            }
        }
        if(dist[dst] == Integer.MAX_VALUE) return -1;
        return dist[dst];
    });

    public void test(IntCheapestFlightsWithinKStops func) {
        System.out.println("Expected: 40, Actual: " + func.findCheapestPrice(5, new int[][]{{0,1,100},{0,2,100},{0,3,10},{1,2,100},{1,4,10},{2,1,10},{2,3,100},{2,4,100},{3,2,10},{3,4,100}}, 0, 4, 3));
        System.out.println("Expected: 700, Actual: " + func.findCheapestPrice(4, new int[][]{{0,1,100},{1,2,100}, {2,0,100},{1,3,600},{2,3,200}}, 0, 3, 1));
        System.out.println("Expected: 400, Actual: " + func.findCheapestPrice(4, new int[][]{{0,1,100},{1,2,100}, {2,0,100},{1,3,600},{2,3,200}}, 0, 3, 2));
        System.out.println("Expected: 200, Actual: " + func.findCheapestPrice(4, new int[][]{{0,1,100},{1,2,100}, {0,2,500}}, 0, 2, 1));
        System.out.println("Expected: 500, Actual: " + func.findCheapestPrice(4, new int[][]{{0,1,100},{1,2,100}, {0,2,500}}, 0, 2, 0));
        System.out.println("Expected: 500, Actual: " + func.findCheapestPrice(
                13,
                new int[][]{{11,12,74},{1,8,91},{4,6,13},{7,6,39},{5,12,8},{0,12,54},{8,4,32},{0,11,4},{4,0,91},{11,7,64},{6,3,88},{8,5,80},{11,10,91},{10,0,60},{8,7,92},{12,6,78},{6,2,8},{4,3,54},{3,11,76},{3,12,23},{11,6,79},{6,12,36},{2,11,100},{2,5,49},{7,0,17},{5,8,95},{3,9,98},{8,10,61},{2,12,38},{5,7,58},{9,4,37},{8,6,79},{9,0,1},{2,3,12},{7,10,7},{12,10,52},{7,2,68},{12,2,100},{6,9,53},{7,4,90},{0,5,43},{11,2,52},{11,8,50},{12,4,38},{7,9,94},{2,7,38},{3,7,88},{9,12,20},{12,0,26},{10,5,38},{12,8,50},{0,2,77},{11,0,13},{9,10,76},{2,6,67},{5,6,34},{9,7,62},{5,3,67}},
                10, 1,
                10));
        System.out.println("Expected: 30, Actual: " + func.findCheapestPrice(
                4,
                new int[][]{{0,1, 100},{0,2,10},{2,3,10},{3,1,10}},
                0, 1,
                2));
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops cf = new CheapestFlightsWithinKStops();
        cf.test(cf.findFlightsBFS);
        System.out.println();
//        cf.test(cf.findFlightsBFS2);
//        System.out.println();
        cf.test(cf.bellmanFord);
        System.out.println();
        cf.test(cf.bellmanFordImproved);
        System.out.println();
        cf.test(cf.dijkstra);

        Set<String> t = new HashSet<>();
        t.add(String.format("%d-%d", 1, 0));
        System.out.println(t.contains(String.format("%d-%d", 1, 0)));
        System.out.println(t.contains(String.format("%d-%d", 1, 1)));
        System.out.println(String.format("%d-%d", 1, 1));
    }
}
