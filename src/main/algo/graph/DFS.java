package main.algo.graph;

import java.util.LinkedList;

public class DFS {

    public void traverseInDFS(Node header) {
        LinkedList<Node> stack = new LinkedList<Node>();
        Node temp = null;
        header.setVisited(true);
        stack.add(header);
        while (!stack.isEmpty()) {
            temp = stack.poll();
            System.out.print(temp.getValue() + " ");

            if (temp.getChildren() != null) {
                for (Node n : temp.getChildren()) {
                    if (!n.getVisited()) {
                        n.setVisited(true);
                        stack.addFirst(n);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello DFS");

        DFS dfs = new DFS();
        main.algo.graph.TestGraphs tg = new main.algo.graph.TestGraphs();

        System.out.println("Simple");
        Node header1 = tg.prepareDefaultTestGraphs();
        // 1 4 3 7 8 6 2 5
        dfs.traverseInDFS(header1);


        System.out.println("\nWith close");
        Node header2 = tg.prepareTestGraphsWithClose1();
        // 1 3 5 6 2 4
        dfs.traverseInDFS(header2);


        System.out.println("\nWith close2");
        Node header3 = tg.prepareTestGraphsWithClose2();
        // 1 3 5 6 4 2
        dfs.traverseInDFS(header3);


        System.out.println("\nWith complicate");
        Node header4 = tg.prepareTestGraphsComplicate();
        // 2 3 0 1
        dfs.traverseInDFS(header4);
    }
}
