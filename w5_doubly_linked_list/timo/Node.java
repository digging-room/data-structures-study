package week05;

public class Node {
    private final Integer key;
    private Node prev = this;
    private Node next = this;

    public Node(Integer key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public Node getNext() {
        return next;
    }

    public void changeNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void changePrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getKey());
    }
}
