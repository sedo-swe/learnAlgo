package algo.graph;

import java.util.LinkedList;

public class BFS {
    public void traverseInBFS(Node header) {
        LinkedList<Node> queue = new LinkedList<Node>();
        Node temp = null;
        header.setVisited(true);
        queue.add(header);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.getValue() + " ");
            if (temp.getChildren() != null) {
                for (Node n : temp.getChildren()) {
                    if (!n.getVisited()) {
                        n.setVisited(true);
                        queue.add(n);
                    }
                }
            }
        }
    }

    public String findPathToNodeFirst(Node header, Node destination) {
        String path = "";
        PathManager pm = new PathManager();

        LinkedList<Node> queue = new LinkedList<Node>();
        header.setVisited(true);
        queue.add(header);
        pm.addNewNodeToPaths(header, null);

        Node temp = null;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.getValue() == destination.getValue()) {
                path = pm.printPathAsString(temp);
                break;
            }
            if (temp.getChildren() != null) {
                for (Node n : temp.getChildren()) {
                    if (!n.getVisited()) {
                        n.setVisited(true);
                        queue.add(n);
                        pm.addNewNodeToPaths(temp, n);
                    }
                }
            }
        }

        return path;
    }

    @Deprecated
    public String findPathToNodeAll_old(Node header, Node destination) {
        LinkedList<String> paths = new LinkedList<String>();

        PathManager pm = new PathManager();

        LinkedList<Node> queue = new LinkedList<Node>();
        header.setVisited(true);
        queue.add(header);
        pm.addNewNodeToPaths(header, null);

        Node temp = null;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.getValue() == destination.getValue()) {
                paths.add(pm.printPathAsString(temp));
            }
            if (temp.getChildren() != null) {
                for (Node n : temp.getChildren()) {
                    if (!n.getVisited()) {
                        n.setVisited(true);
                        queue.add(n);
                        pm.addNewNodeToPaths(temp, n);
                    }
                }
            }
        }

        String path = "";
        if (paths.size() > 0) {
            for (String s : paths)
                path += s + "\n";
        }

        return path;
    }


    public boolean isVisited(LinkedList<Node> path, Node current) {
        boolean visited = false;

        for (Node n: path) {
            if (n.getValue() == current.getValue()) {
                visited = true;
                break;
            }
        }

        return visited;
    }

    public String printPath(LinkedList<Node> path) {
        String p = "";
        for (Node n : path)
            p += n.getValue() + " ";

        return p;
    }

    public String findPathToNodeAll(Node header, Node destination) {
        LinkedList<String> paths = new LinkedList<String>();

        LinkedList<LinkedList<Node>> queue = new LinkedList<>();

        LinkedList<Node> path = new LinkedList<>();
        path.add(header);
        queue.add(path);

        Node lastNode = null;
        while (!queue.isEmpty()) {
            path = queue.poll();
            lastNode = path.getLast();

            if (lastNode.getValue() == destination.getValue()) {
                paths.add(this.printPath(path));
            }

            if (lastNode.getChildren() != null) {
                for (Node n : lastNode.getChildren()) {
                    if (!isVisited(path, n)) {
                        LinkedList<Node> newPath = new LinkedList<>(path);
                        newPath.add(n);
                        queue.add(newPath);
                    }
                }
            }
        }

        String pathStr = "";
        if (paths.size() > 0) {
            for (String s : paths)
                pathStr += s + "\n";
        }

        return pathStr;
    }




    public static void main(String[] args) {
        BFS bfs = new BFS();
        TestGraphs tg = new TestGraphs();

        System.out.println("Hello BFS\n");

        System.out.println("==================================");
        System.out.println("= Test Traverse Tree             =");
        System.out.println("==================================");

        System.out.println("\nDefault Tree");
        Node header1 = tg.prepareDefaultTestGraphs();
        bfs.traverseInBFS(header1);

        System.out.println("\nClose1");
        header1 = tg.prepareTestGraphsWithClose1();
        bfs.traverseInBFS(header1);

        System.out.println("\nClose2");
        header1 = tg.prepareTestGraphsWithClose2();
        bfs.traverseInBFS(header1);

        System.out.println("\nComplicate");
        header1 = tg.prepareTestGraphsComplicate();
        bfs.traverseInBFS(header1);


        System.out.println("\n\n");
        System.out.println("==================================");
        System.out.println("= Test Finding a path in Tree    =");
        System.out.println("==================================");

        System.out.println("\nFind path to 8 on default tree");
        header1 = tg.prepareDefaultTestGraphs();
        System.out.println(bfs.findPathToNodeFirst(header1, new Node(8)));

        System.out.println("\nFind path to 6 on close 3");
        header1 = tg.prepareTestGraphsWithClose3();
        System.out.println(bfs.findPathToNodeFirst(header1, new Node(6)));

        System.out.println("\nFind path to 2 on complicate");
        header1 = tg.prepareTestGraphsComplicate();
        System.out.println(bfs.findPathToNodeFirst(header1, new Node(2)));

        System.out.println("\nFind path to 9 on complicate2");
        header1 = tg.prepareTestGraphsComplicate2();
        System.out.println(bfs.findPathToNodeFirst(header1, new Node(9)));


        System.out.println("\n\n");
        System.out.println("==================================");
        System.out.println("= Test Finding all paths in Tree =");
        System.out.println("==================================");

        System.out.println("\nFind paths to 8 on default tree");
        header1 = tg.prepareDefaultTestGraphs();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(8)));

        System.out.println("\nFind paths to 6 on close 3");
        header1 = tg.prepareTestGraphsWithClose3();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(6)));

        System.out.println("\nFind paths to 6 on close 1");
        header1 = tg.prepareTestGraphsWithClose1();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(6)));

        System.out.println("\nFind paths to 6 on simple circle");
        header1 = tg.prepareTestGraphsWithSimpleCircle();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(6)));

        System.out.println("\nFind paths to 6 on close 2");
        header1 = tg.prepareTestGraphsWithClose2();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(6)));

        System.out.println("\nFind paths to 8 on complicate circle");
        header1 = tg.prepareTestGraphWithComplicateCircle();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(8)));

        System.out.println("\nFind paths to 2 on complicate");
        header1 = tg.prepareTestGraphsComplicate();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(2)));

        System.out.println("\nFind paths to 2 on complicate 2");
        header1 = tg.prepareTestGraphsComplicate2();
        System.out.println(bfs.findPathToNodeAll(header1, new Node(9)));

    }
}
