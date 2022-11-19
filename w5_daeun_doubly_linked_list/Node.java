package grace.datastructuresgroupstudy.week5;

public class Node {

  private final String key;
  private Node prev;
  private Node next;

  public Node(String key) {
    this.key = key;
    this.prev = this;
    this.next = this;
  }

  public void changePrev(Node node) {
    this.prev = node;
  }

  public void changeNext(Node node) {
    this.next = node;
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

  @Override
  public String toString() {
    return "Node{" +
        "key=" + key +
        ", prevKey=" + (prev != null ? prev.getKey() : "") +
        ", nextKey=" + (next != null ? next.getKey() : "") +
        '}';
  }
}
