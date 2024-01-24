package main.ltcode.medium;

import main.ltcode.utils.Node;
import java.util.*;

/**
 *  133. Clone Graph (Medium)
 */

@FunctionalInterface
interface IntCloneGraph {
    Node cloneGraph(Node node);
}
public class CloneGraph {
    Map<Node, Node> visited = new HashMap<>();
    IntCloneGraph intCloneGraphDFS = (node -> {
        return dfs(node);
    });

    private Node dfs(Node node) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloned = new Node(node.val);
        visited.put(node, cloned);
        for (Node n : node.neighbors) {
            cloned.neighbors.add(dfs(n));
        }
        return cloned;
    }

    Set<Node> visitedNodes = new HashSet<>();
    public String printNodeToStringDFS(Node node) {
        StringBuffer sb = new StringBuffer();

        if (visitedNodes.contains(node)) {
            return "";
        }
        sb.append("[");
        visitedNodes.add(node);
        int length = sb.length();
        for (int i=0; i<node.neighbors.size(); i++) {
            sb.insert(length+i*2, node.neighbors.get(i).val + ",");
            sb.append(printNodeToStringDFS(node.neighbors.get(i)));
        }
        sb.insert(length+node.neighbors.size()*2-1, "]");
        if (sb.charAt(sb.length()-1) == ',') {
            sb.replace(sb.length() - 1, sb.length(), "");
        }
        return sb.toString();
    }

    IntCloneGraph intCloneGraphBFS = (node -> {
        return bfs(node);
    });


    private Node bfs(Node node) {
        if (node == null) {
            return null;
        }
        Deque<Node> queue = new ArrayDeque<>(Arrays.asList(node));
        Map<Node, Node> clones = new HashMap<>();
        clones.put(node, new Node(node.val));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node n : cur.neighbors) {
                if (!clones.containsKey(n)) {
                    clones.put(n, new Node(n.val));
                    queue.offer(n);
                }
                clones.get(cur).neighbors.add(clones.get(n));
            }
        }
        return clones.get(node);
    }

    public String printNodeToStringBFS(Node node) {
        Deque<Node> queue = new ArrayDeque<>();
        StringBuffer sb = new StringBuffer();
        Set<Node> visited = new HashSet<>();

        // [[2,4],[1,3],[2,4],[1,3]]
        sb.append("[");
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (!visited.contains(cur)) {
                sb.append("[");
                int length = sb.length();
                for (Node n : cur.neighbors) {
                    sb.append(n.val + ",");
                    if (!visited.contains(n)) {
                        queue.offer(n);
                    }
                }
                if (length != sb.length()) {
                    sb.replace(sb.length() - 1, sb.length(), "");
                }
                sb.append("],");
            }
            visited.add(cur);
        }

        if (sb.length() > 4) {
            sb.replace(sb.length() - 1, sb.length(), "");
        }
        sb.append("]");
        return sb.toString();
    }


    public void test(IntCloneGraph func) {
        Node test1_01 = new Node(1);
        Node test1_02 = new Node(2);
        Node test1_03 = new Node(3);
        Node test1_04 = new Node(4);
        test1_01.neighbors.add(test1_02);
        test1_01.neighbors.add(test1_04);
        test1_02.neighbors.add(test1_01);
        test1_02.neighbors.add(test1_03);
        test1_03.neighbors.add(test1_02);
        test1_03.neighbors.add(test1_04);
        test1_04.neighbors.add(test1_01);
        test1_04.neighbors.add(test1_03);
        System.out.println("  printing via DFS");
        System.out.println("  Expected: [[2,4],[1,3],[2,4],[1,3]], Actual (DFS): " + this.printNodeToStringDFS(func.cloneGraph(test1_01)));
        System.out.println("  printing via BFS");
        System.out.println("  Expected: [[2,4],[1,3],[2,4],[1,3]], Actual (BFS): " + this.printNodeToStringBFS(func.cloneGraph(test1_01)));
    }

    public static void main(String[] args) {
        CloneGraph cg = new CloneGraph();
        System.out.println("= DFS =======");
        cg.test(cg.intCloneGraphDFS);
        System.out.println();
        System.out.println("= BFS =======");
        cg.test(cg.intCloneGraphBFS);
    }
}
