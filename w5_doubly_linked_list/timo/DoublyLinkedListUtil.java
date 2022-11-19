package week05;

public class DoublyLinkedListUtil {
    public static void splice(Node fromNode, Node toNode, Node targetNode) {
        //cut
        Node fromNodePrev = fromNode.getPrev();
        Node toNodeNext = toNode.getNext();
        //ap.next = bn
        //bn.prev = ap
        fromNodePrev.changeNext(toNodeNext);
        toNodeNext.changePrev(fromNodePrev);

        //paste
        Node targetNodeNext = targetNode.getNext();
        //a.prev = x
        //b.next = xn
        fromNode.changePrev(targetNode);
        toNode.changeNext(targetNodeNext);
        //x.next = a
        //xn.prev = b
        targetNode.changeNext(fromNode);
        targetNodeNext.changePrev(toNode);
    }
}
