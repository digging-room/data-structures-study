package week04;

public class TimoNode {

    private final int key;

    private TimoNode next;

    public TimoNode(int key) {
        this.key = key;
    }

    public void changeNext(TimoNode node) {
        this.next = node;
    }

    public TimoNode getNextNode() {
        return next;
    }

    public boolean hasNextNode() {
        return next != null;
    }

    public int getKey() {
        return this.key;
    }
}
