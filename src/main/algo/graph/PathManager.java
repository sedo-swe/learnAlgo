package main.algo.graph;

import java.util.LinkedList;

public class PathManager {

    private LinkedList<LinkedList<Node>> paths = new LinkedList<LinkedList<Node>>();

    public LinkedList<Node> getPathEndingWithNode(Node current) {
        for (int i=0; i<paths.size(); i++) {
            LinkedList<Node> l = paths.get(i);
            if (l.getLast().getValue() == current.getValue()) {
//                System.out.println("Found!!!");
                return l;
            }
        }

        return null;
    }

    public void addNewNodeToPaths(Node current, Node next) {
        LinkedList<Node> l = this.getPathEndingWithNode(current);
        if (l == null) {
            LinkedList<Node> temp1 = new LinkedList<Node>();
            temp1.add(current);
//            temp1.add(next);
            paths.add(temp1);
        }
        else {
//        paths.remove(l);
            LinkedList<Node> temp = (LinkedList<Node>)l.clone();
            temp.add(next);
            paths.add(temp);
        }
    }

    public String printPathAsString(Node current) {
        LinkedList<Node> l = this.getPathEndingWithNode(current);
        String path = "";
        for (Node n : l) {
            path += " " + n.getValue();
        }
        paths.remove(l);

        return path;
    }
}
