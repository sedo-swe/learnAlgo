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
        c.  PriorityQueue<Integer> heap3 = new PriorityQueue<>(Comparator.reverseOrder());
      * PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
```java
        import java.util.*; 
        import java.io.*;
        import javafx.util.Pair;

        class GFG {
        public static void main(String[] args) {

            // Priority Queue implementing min heap of Pairs 
            // Creating instance of PriorityQueue by passing  
            // Lambda expression as a constructor parameter. 
            PriorityQueue<Pair<Integer, String>> pq =  
                                       new PriorityQueue<>((a, b) -> a.getKey() - b.getKey()); 
                   
            // Adding objects of Pair<K, V> class by passing  
            // keys and values as parameter in Pair constructor 
            pq.add(new Pair<>(8, "fox")); 
            pq.add(new Pair<>(4, "quick")); 
            pq.add(new Pair<>(2, "the")); 
            pq.add(new Pair<>(6, "brown")); 
                  
            // Printing min heap based on the priority 
            while(!pq.isEmpty()){ 
                System.out.print(pq.remove() +" "); 
            }
            // add and remove will throw exception when it can't

           pq.offer(new Pair<>(9, "zebra"));

           // Printing min heap based on the priority 
           while(!pq.isEmpty()){
              System.out.print(pq.poll() +" ");
           }
           // offer and poll will return false & null when it can't
        }
    }
```
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
      5. Merge
         ```java
         Map<String,Integer> map = new HashMap();
         // map.merge(key, value, remappingFunction)
         map.put("Sana", 70);
         int val = map.merge("Sara", 90, (val1, val2) -> val1+val2);    // Put 90 with Sara as there is no Sara in map
         val = results.merge("Sara", 76, (val1, val2) -> val1+val2);    // Put 166 with Sara as a result of 90 (value from map) + 76
         ```
      6. ComputeIfAbsent
         ```java
         // If key already exists, then Mapping Function isn't triggered
         Map<String, Integer> stringLength = new HashMap<>();
         stringLength.put("John", 5);
         assertEquals((long)stringLength.computeIfAbsent("John", s -> s.length()), 5);
         
         // If key doesn't exists, then Mapping Function is triggered
         Map<String, Integer> stringLength = new HashMap<>();
         assertEquals((long)stringLength.computeIfAbsent("John", s -> s.length()), 4);
         assertEquals((long)stringLength.get("John"), 4);
         ```
      7. getOrDefault
         ```java
         Map<String, String> map = new HashMap<>();
         String value = map.getOrDefault("some key", "NOT_FOUND");
         
      * computeIfAbsent vs getOrDefault
        -> They both return when there is no value to the key, but difference is computeIfAbsent will store back the returned value, while getOrDefault doesn't.
         ```java
        static final String NOT_FOUND = "not found";
        Map<String, String> map1 = ...
        String value = map1.getOrDefault("some key", NOT_FOUND);
        //... do some processing
        // now check if our key exists in the Map
        System.out.println(map1.containsKey("some key")); // prints "false"
        
        Map<String, String> map2 = ...
        String value = map2.computeIfAbsent("some key", (k) -> NOT_FOUND);
        //... do some processing
        // now check if our key exists in the Map
        System.out.println(map2.containsKey("some key")); // prints "true"
```
```

3. ***List***
   1. ArrayList
      List<Integer> alist = new ArrayList<>();
      alist.add(node.val);
      alist.get(level);
      alist.set(level, Math.max(alist.get(level), node.val);
          