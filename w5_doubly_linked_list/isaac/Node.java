package dataStructure.linkedList.doubly;

public class Node {
    // doubly linked list는 key와 앞뒤 노드의 정보가 필요하다.
    private String key;
    private Node prev;
    private Node next;

    public Node(){}

    public Node(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
