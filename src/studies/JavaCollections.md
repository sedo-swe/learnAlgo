### Queue Interface ###
1. ***PriorityQueue***
   1. Queue <T> pq = new PriorityQueue<> ();
   2. This can help prioritize important data to make sure it gets through faster.
   3. Each queue’s item has an additional piece of information, namely priority. Unlike a regular queue, the values in the priority queue are removed based on priority instead of the first-in-first-out (FIFO) rule.
   4. Operators:
      * add: adds an item to the queue
      * peek: returns the item in the queue with the highest priority without deleting the node
      * remove: removes and returns the item in the queue with the highest priority
   5. Characteristics of a Priority Queue
      * Every element in a priority queue has a priority value associated with it
      * The element with the higher priority will be moved to the top and removed first
      * If two elements in a priority queue have the same priority value, they’ll be arranged using the FIFO principle
   6. Types of Priority Queue
      * Min-priority queue: in a min-priority queue, a lower priority number is given as a higher priority
      * Max-priority queue: in a max-priority queue, a higher priority number is given as a higher priority
   7. Code examples
      * a.  PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
               @Override
               public int compare(Integer a, Integer b) { return b - a; }
            });

        b.  PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
      * PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
** https://www.baeldung.com/cs/priority-queue
   
### Map Interface ###
1. ***TreeMap***
   1. All elements in the map are sorted by key.

2. ***HashMap***
   1. Usage:
      1. HashMap<K, V> map = new HashMap<>();
      2. Put data
         1. HashMap<String, Integer> map = new HashMap<>();
         2. map.put("S1", 3);
         3. map.put("S2", map.getOrDefault("S2", 0) + 1);
      3. Get data
         1. HashMap<String, Integer> map = new HashMap<>();
         2. map.get("S1");
         3. map.getOrDefault("S2", 0);
      4. Iterate
         1. Using Key Set
         ```java
         HashMap<Integer, Integer> frequency = new HashMap<>();
         Set<Integer> keys = frequency.keySet();
         for (Integer i : keys) {
             order.add(new AbstractMap.SimpleEntry<>(i, frequency.get(i)));
         }
         ```
         2. Using Entry Set
         ```java
         Map<Integer,Integer> map = new HashMap();
         for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
             pq.add(new Node(entry.getKey(), entry.getValue()));
         }
         ```
          