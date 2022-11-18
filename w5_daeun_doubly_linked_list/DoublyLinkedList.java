package grace.datastructuresgroupstudy.week5;

// 원형 양방향 연결 리스트 구현
public class DoublyLinkedList {

  private final Node head =  new Node(null); // 더미 null
  private int size;


  public void originPushFront(Node newNode) {
    Node nextNode = this.head.getNext();
    newNode.changePrev(this.head);
    if(size != 0) {
      newNode.changeNext(nextNode);
      nextNode.changePrev(newNode);
    } else {
      newNode.changeNext(this.head);
      this.head.changePrev(newNode);
    }
    this.head.changeNext(newNode);
    this.size++;
  }

  public void splice(Node a, Node b, Node x) {

    Node aPrev = a.getPrev();
    Node bNext = b.getNext();

    // 자르기
    if(aPrev != null && bNext != null) {
      aPrev.changeNext(bNext);
      bNext.changePrev(aPrev);
    }

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
  public void insertAfter(Node x, String key) {
    this.moveAfter(new Node(key), x);
    this.size++;
  }

  // key로 새로운 노드를 만들어서 x 전에 삽입
  public void insertBefore(Node x, String key) {
    this.moveBefore(new Node(key), x);
    this.size++;
  }

  // key로 새로운 노드를 만들어서 헤드노드(더미) 다음에 삽입
  public void pushFront(String key) {
    this.insertAfter(this.head, key);
  }

  // key로 새로운 노드를 만들어서 헤드노드(더미) 전 (=테일 노드 다음)에 삽입
  public void pushBack(String key) {
    this.insertBefore(this.head, key);
  }

  // 탐색
  public Node search(String key) {
    Node searchNode = this.head;
    if(searchNode.getNext() == null) throw new IllegalStateException("빈 리스트입니다.");
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
    String[] array = new String[size];
    Node x = head.getNext();
    for(int i=0; i<size; i++) {
      array[i] = x.getKey();
      x = x.getNext();
    }
    return array;
  }

}
