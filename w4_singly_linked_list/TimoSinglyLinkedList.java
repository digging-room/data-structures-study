package week04;

public class TimoLinkedList {

    private Node head;
    private int size;

    public void pushFront(Node node) {
        if (this.isNotEmpty()) {
            node.changeNext(head);
        }
        changeHead(node);
        increaseSize();
    }

    public void pushBack(Node node) {
        if (this.isEmpty()) {
            changeHead(node);

        } else {
            Node target = head;
            while (target.hasNextNode()) {
                target = target.getNextNode();
            }
            target.changeNext(node);
        }
        increaseSize();
    }

    public Node popFront() {
        if (isEmpty()) throw new IllegalStateException("비어있는 리스트입니다.");

        Node target = head;

        if (head.hasNextNode()) changeHead(head.getNextNode());
        else changeHead(null);

        decreaseSize();
        return target;
    }

    public Node popBack() {
        if (isEmpty()) throw new IllegalStateException("비어있는 리스트입니다.");

        Node prevNode = null;
        Node targetNode = head;

        while (targetNode.hasNextNode()) {
            prevNode = targetNode;
            targetNode = targetNode.getNextNode();
        }
        if (prevNode != null) {
            prevNode.changeNext(null);
        }
        decreaseSize();
        return targetNode;
    }

    public Node search(int key) {
        if (head.getKey() == key) {
            return head;
        } else {
            Node target = head;
            while (target.hasNextNode()) {
                if (target.getKey() == key) {
                    return target;
                }
                target = target.getNextNode();
            }
        }
        return null;
    }

    private boolean isEmpty() {
        return head == null;
    }

    private boolean isNotEmpty() {
        return !isEmpty();
    }

    public int size() {
        return this.size;
    }

    private void increaseSize() {
        this.size++;
    }

    private void decreaseSize() {
        this.size--;
    }

    private void changeHead(Node node) {
        this.head = node;
    }
}
