package dataStructure.linkedList.doubly;

public class CircularyDoublyLinkedList {
    private Node head;
    private Integer size;
    public CircularyDoublyLinkedList(){
        this.head = new Node();
        this.size = 0;
    }

    public int getSize(){
        return this.size;
    }

    public Node getHead(){
        return this.head;
    }

    public Node getFirstNode(){
        return this.head.getNext();
    }

    public void splice(Node a, Node b, Node x){
        Node ap, bn, xn;

        if(a.getPrev() == null && a.getNext() == null) {
            ap = x;
            bn = x.getNext();

            ap.setNext(a);
            a.setPrev(ap);
            bn.setPrev(a);
            a.setNext(bn);
        } else {
            ap = a.getPrev();
            bn = b.getNext();
            xn = x.getNext();

            ap.setNext(bn);
            bn.setPrev(ap);
            x.setNext(a);
            a.setPrev(x);
            xn.setPrev(b);
            b.setNext(xn);
        }
    }

    // a를 x노드 뒤로 이동한다.
    public void moveAfter(Node a, Node x){
        splice(a, a, x);
    }

    public void moveBefore(Node a, Node x){
        splice(a, a, x.getPrev());
    }

    public void insertAfter(Node x, String key){
        moveAfter(new Node(key), x);
        this.size++;
    }

    public void insertBefore(Node x, String key){
        moveBefore(new Node(key), x);
        this.size++;
    }

    public void pushFront(String key){
        insertBefore(this.head.getNext(), key);
    }

    public void pushBack(String key){
        if(this.head.getNext() == null) {
            pushFront(key);
        }
        else {
            Node lastNode = this.head;
            while (lastNode.getNext().getKey() != null) {
                lastNode = lastNode.getNext();
            }
            insertAfter(lastNode, key);
        }
    }


    public String popFront(){
        if (isEmpty()) {
            throw new IllegalStateException("비어있는 리스트입니다.");
        }

        Node front = this.head.getNext();
        if (this.size == 1) {
            this.head.setNext(null);
        } else {
            this.head.setNext(front.getNext());
            front.getNext().setPrev(this.head);
        }
        this.size--;
        return front.getKey();
    }

    public String popBack(){
        if (isEmpty()) {
            throw new IllegalStateException("비어있는 리스트입니다.");
        }

        Node last = this.head.getNext();
        String result;
        while (last.getNext().getKey() != null) {
            last = last.getNext();
        }
        result = last.getKey();
        Node lp = last.getPrev();
        lp.setNext(this.head);
        this.head.setPrev(lp);
        this.size--;
        return result;
    }

    public Node search(String key){
        Node node = this.head.getNext();
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public boolean isEmpty(){
        return this.head.getPrev() ==  null && this.head.getNext() == null;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
