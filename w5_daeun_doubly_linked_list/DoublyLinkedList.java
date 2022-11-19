package grace.datastructuresgroupstudy.week5;

// 원형 양방향 연결 리스트 구현
public class DoublyLinkedList {

  private final Node head =  new Node(null); // 더미 null
  private int size;

  public void splice(Node a, Node b, Node x) {

    Node aPrev = a.getPrev();
    Node bNext = b.getNext();

    // 자르기
    aPrev.changeNext(bNext);
    bNext.changePrev(aPrev);

    // x 뒤에 a~b 삽입
    Node xNext = x.getNext();
    x.changeNext(a);
    a.changePrev(x);
    b.changeNext(xNext);
    xNext.changePrev(b);

  }

  // 노드a 를 노드x 뒤로 이동
  public void moveAfter(Node a, Node x) {
    this.splice(a, a, x);
  }

  // 노드a 를 노드x 앞으로 이동
  public void moveBefore(Node a, Node x) {
    this.splice(a, a, x.getPrev());
  }

  // key로 새로운 노드를 만들어서 x 뒤에 삽입
  public void insertAfter(Node x, Node newNode) {
    this.moveAfter(newNode, x);
    this.size++;
  }

  // key로 새로운 노드를 만들어서 x 전에 삽입
  public void insertBefore(Node x, Node newNode) {
    this.moveBefore(newNode, x);
    this.size++;
  }

  // key로 새로운 노드를 만들어서 헤드노드(더미) 다음에 삽입
  public void pushFront(Node newNode) {
    this.insertAfter(this.head, newNode);
  }

  // key로 새로운 노드를 만들어서 헤드노드(더미) 전 (=테일 노드 다음)에 삽입
  public void pushBack(Node newNode) {
    this.insertBefore(this.head, newNode);
  }

  // 탐색
  public Node search(String key) {
    Node searchNode = this.head;
    if(searchNode.getNext() == this.head) throw new IllegalStateException("빈 리스트입니다.");
    while (searchNode.getNext() != this.head) {
      if(key.equals(searchNode.getKey())) {
        return searchNode;
      }
      searchNode = searchNode.getNext();
    }

    return searchNode;
  }

  // 삭제
  public void remove(Node removeNode) {
    if(removeNode == null || removeNode.getKey() == null) {
      return;
    }
    removeNode.getPrev().changeNext(removeNode.getNext());
    removeNode.getNext().changePrev(removeNode.getPrev());
    this.size--;
  }

  public String[] toArray() {
    int listSize = getSize();
    String[] array = new String[getSize()];
    Node x = head.getNext();
    for(int i=0; i<listSize; i++) {
      array[i] = x.getKey();
      x = x.getNext();
    }
    return array;
  }

  public int getSize() {
    int listSize = 0;
    Node node = this.head.getNext();
    while(node.getKey() != null) {
      listSize++;
      node = node.getNext();
    }
    return listSize;
  }

}
