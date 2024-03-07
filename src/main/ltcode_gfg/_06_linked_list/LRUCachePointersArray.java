package main.ltcode_gfg._06_linked_list;

class LRUCachePointersArray {
    class Node{
        int key;
        int value;

        Node prev;
        Node next;

        Node(int key, int value){
            this.key= key;
            this.value= value;
        }
    }

    public Node[] map;
    public int count, capacity;
    public Node head, tail;
    public LRUCachePointersArray(int capacity) {
        this.capacity= capacity;
        count= 0;

        map= new Node[10_000+1];

        head= new Node(0,0);
        tail= new Node(0,0);

        head.next= tail;
        tail.prev= head;

        head.prev= null;
        tail.next= null;
    }
    public void deleteNode(Node node){
        node.prev.next= node.next;
        node.next.prev= node.prev;
        return;
    }

    public void addToTail(Node node){
        node.prev= tail.prev;
        node.next= tail;
        node.next.prev= node;
        tail.prev= node;
        return;
    }

    public void addToHead(Node node){
        node.next= head.next;
        node.next.prev= node;
        node.prev= head;
        head.next= node;
        return;
    }

    public int get(int key) {
        if( map[key] != null ){
            Node node= map[key];
            int nodeVal= node.value;
            deleteNode(node);
            addToTail(node);
            return nodeVal;
        }
        else
            return -1;
    }

    public void put(int key, int value) {
        if(map[key] != null){
            Node node= map[key];
            node.value= value;
            deleteNode(node);
            addToTail(node);
        } else {
            Node node= new Node(key,value);
            map[key]= node;
            if(count < capacity){
                count++;
                addToTail(node);
            }
            else {
                map[tail.prev.key]= null;
                deleteNode(tail.prev);
                addToTail(node);
            }
        }

        return;
    }

    public static void main(String[] args) {
        LRUCachePointersArray lc = new LRUCachePointersArray(2);
        /*lc.put(1, 1);
        lc.put(2, 2);
        System.out.println(lc.get(1));
        lc.put(3, 3);
        System.out.println(lc.get(2));
        lc.put(4, 4);
        System.out.println(lc.get(1));
        System.out.println(lc.get(3));
        System.out.println(lc.get(4));*/

        System.out.println(lc.get(2));
        lc.put(2, 6);
        System.out.println(lc.get(1));
        lc.put(1, 5);
        lc.put(1, 2);
        System.out.println(lc.get(1));
        System.out.println(lc.get(2));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */