package main.algo.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        this.key = item;
        left = right = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // Add
    private Node addRecursive(Node current, int item) {
        if (current == null) {
            return new Node(item);
        }

        if (current.getKey() > item) {
            current.setLeft(addRecursive(current.getLeft(), item));
        } else if (current.getKey() < item) {
            current.setRight(addRecursive(current.getRight(), item));
        } else {
            return current;
        }

        return current;
    }

    public void add(int item) {
        this.root = addRecursive(this.root, item);
    }

    // Contain
    private boolean containRecursive(Node current, int item) {
        if (current == null) {
            return false;
        }

        if (current.getKey() == item) {
            return true;
        }

        if (current.getKey() > item) {
            return containRecursive(current.getLeft(), item);
        } else {
            return containRecursive(current.getRight(), item);
        }
    }

    public boolean contain(int item) {
        return containRecursive(this.root, item);
    }

    // Delete
    private int findSmallestKey(Node current) {
        return current.getLeft() == null ? current.getKey() : findSmallestKey(current.getLeft());
    }

    private Node deleteRecursive(Node current, int item) {
        // No node to be deleted
        if (current == null) {
            return null;
        }

        // Found node to be deleted
        if (current.getKey() == item) {
            // No children
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            // One child
            if (current.getLeft() == null) {
                return current.getRight();
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            // Two children
            int smallest = findSmallestKey(current.getRight());
            current.setKey(smallest);
            current.setRight(deleteRecursive(current.getRight(), smallest));
            return current;
        }

        // Keep finding
        if (current.getKey() > item) {
            current.setLeft(deleteRecursive(current.getLeft(), item));
        } else {
            current.setRight(deleteRecursive(current.getRight(), item));
        }
        return current;
    }

    public void delete(int item) {
        this.root = deleteRecursive(this.root, item);
    }


    // Traverse PreOrder
    public void traversePreOrder(Node current) {
        if (current != null) {
            System.out.print(current.getKey() + " ");
            traversePreOrder(current.getLeft());
            traversePreOrder(current.getRight());
        }
    }


    // Traverse InOrder
    public void traverseInOrder(Node current) {
        if (current != null) {
            traverseInOrder(current.getLeft());
            System.out.print(current.getKey() + " ");
            traverseInOrder(current.getRight());
        }
    }


    // Traverse PostOrder
    public void traversePostOrder(Node current) {
        if (current != null) {
            traversePostOrder(current.getLeft());
            traversePostOrder(current.getRight());
            System.out.print(current.getKey() + " ");
        }
    }

    // Traverse LevelOrder
    public void traverseLevelOrder() {
        if (this.root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(this.root);

        while (!nodes.isEmpty()) {
            Node current = nodes.remove();

            System.out.print(current.getKey() + " ");

            if (current.getLeft() != null) {
                nodes.add(current.getLeft());
            }
            if (current.getRight() != null) {
                nodes.add(current.getRight());
            }
        }
    }


    // Traverse PreOrder without recursion
    public void traversePreOrderWithoutRecursion() {
        Stack<Node> nodes = new Stack<>();
        Node current = this.root;
        nodes.push(this.root);

        while (current != null && !nodes.isEmpty()) {
            current = nodes.pop();
            System.out.print(current.getKey() + " ");

            if (current.getRight() != null) {
                nodes.push(current.getRight());
            }

            if (current.getLeft() != null) {
                nodes.push(current.getLeft());
            }
        }
    }

    public void traversePreOrderWithoutRecursion2() {
        Stack<Node> nodes = new Stack<>();
        nodes.push(this.root);

        while (!nodes.isEmpty()) {
            Node current = nodes.pop();
            System.out.print(current.getKey() + " ");

            if (current.getRight() != null) {
                nodes.push(current.getRight());
            }

            if (current.getLeft() != null) {
                nodes.push(current.getLeft());
            }
        }
    }

    // Traverse InOrder without recursion
    public void traverseInOrderWithoutRecursion() {
        Stack<Node> nodes = new Stack<>();
        Node current = this.root;

        while (current != null || !nodes.isEmpty()) {
            while (current != null) {
                nodes.push(current);
                current = current.getLeft();
            }

            current = nodes.pop();
            System.out.print(current.getKey() + " ");
            current = current.getRight();
        }
    }


    // Traverse PostOrder without recursion
    // https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
    public void traversePostOrderWithoutRecursion() {
        Stack<Node> nodes = new Stack<>();
        Node prev = this.root;
        Node current = this.root;
        nodes.push(this.root);

        while (current != null && !nodes.isEmpty()) {
            current = nodes.peek();
            boolean hasChild = (current.getLeft() != null || current.getRight() != null);
            boolean isPrevLastChild = (prev == current.getRight() || (prev == current.getLeft() && current.getRight() == null));

            if (!hasChild || isPrevLastChild) {
                current = nodes.pop();
                System.out.print(current.getKey() + " ");
                prev = current;
            } else {
                if (current.getRight() != null) {
                    nodes.push(current.getRight());
                }
                if (current.getLeft() != null) {
                    nodes.push(current.getLeft());
                }
            }
        }
    }

    public static BinaryTree createSampleBinaryTree() {
        BinaryTree bt = new BinaryTree();
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = BinaryTree.createSampleBinaryTree();

        System.out.println(binaryTree.contain(13));
        binaryTree.add(13);
        System.out.println(binaryTree.contain(13));
        binaryTree.delete(13);
        System.out.println(binaryTree.contain(13));

        System.out.println("Traverse LevelOrder");
        binaryTree.traverseLevelOrder();

        System.out.println("\nTraverse PreOrder");
        binaryTree.traversePreOrder(binaryTree.getRoot());
        System.out.println("\nTraverse PreOrder w/o Recursion");
        binaryTree.traversePreOrderWithoutRecursion();
        System.out.println();
        binaryTree.traversePreOrderWithoutRecursion2();

        System.out.println("\nTraverse InOrder");
        binaryTree.traverseInOrder(binaryTree.getRoot());
        System.out.println("\nTraverse InOrder w/o Recursion");
        binaryTree.traverseInOrderWithoutRecursion();

        System.out.println("\nTraverse PostOrder");
        binaryTree.traversePostOrder(binaryTree.getRoot());
        System.out.println("\nTraverse PostOrder w/o Recursion");
        binaryTree.traversePostOrderWithoutRecursion();

        System.out.println("\n=====================================");
        BinaryTree bt1 = new BinaryTree();
        bt1.add(4);
        bt1.add(2);
        bt1.add(1);
        bt1.add(3);
        bt1.add(6);
        bt1.add(5);
        bt1.add(7);
        System.out.println("\nPost order 1");
        bt1.traversePostOrder(bt1.getRoot());

        System.out.println("\nPost order 2");
        bt1.traversePostOrderWithoutRecursion();
    }
}
