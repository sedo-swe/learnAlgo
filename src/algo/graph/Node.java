package algo.graph;


import java.util.ArrayList;
import java.util.List;

public class Node {
    private int value;
    private boolean visited = false;
    private List<Node> children;

    public Node(int value) {
        this.value = value;
        this.children = null;
    }

    public void addChildren(Node child) {
        if (this.children == null)
            this.children = new ArrayList<Node>();

        this.children.add(child);
    }

    public int getValue() {
        return this.value;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
