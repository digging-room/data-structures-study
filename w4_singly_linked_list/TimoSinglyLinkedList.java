public class TimoSinglyLinkedList {

    private TimoNode head;
    private int size;

    public void pushFront(TimoNode node) {
        if (this.isNotEmpty()) {
            node.changeNext(head);
        }
        changeHead(node);
        increaseSize();
    }

    public void pushBack(TimoNode node) {
        if (this.isEmpty()) {
            changeHead(node);

        } else {
            TimoNode target = head;
            while (target.hasNextNode()) {
                target = target.getNextNode();
            }
            target.changeNext(node);
        }
        increaseSize();
    }

    public TimoNode popFront() {
        if (isEmpty()) throw new IllegalStateException("비어있는 리스트입니다.");

        TimoNode target = head;

        if (head.hasNextNode()) changeHead(head.getNextNode());
        else changeHead(null);

        decreaseSize();
        return target;
    }

    public TimoNode popBack() {
        if (isEmpty()) throw new IllegalStateException("비어있는 리스트입니다.");

        TimoNode prevNode = null;
        TimoNode targetNode = head;

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

    public TimoNode search(int key) {
        if (head.getKey() == key) {
            return head;
        } else {
            TimoNode target = head;
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

    private void changeHead(TimoNode node) {
        this.head = node;
    }
}
