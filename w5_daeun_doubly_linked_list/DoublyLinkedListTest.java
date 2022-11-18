package grace.datastructuresgroupstudy.week5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoublyLinkedListTest {

  @DisplayName("헤드 노드 앞에 새로운 노드 삽입")
  @Test
  void originPushFront() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node cNode = new Node("c");

    //when
    doublyLinkedList.originPushFront(cNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("a-b-c");
  }

  @DisplayName("splice 연산")
  @Test
  void splice() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node apNode = new Node("ap");
    Node aNode = new Node("a");
    Node anNode = new Node("an");
    Node bpNode = new Node("bp");
    Node bNode = new Node("b");
    Node bnNode = new Node("bn");
    Node xpNode = new Node("xp");
    Node xNode = new Node("x");
    Node xnNode = new Node("xn");


    doublyLinkedList.originPushFront(xnNode);
    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(xpNode);
    doublyLinkedList.originPushFront(bnNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(bpNode);
    doublyLinkedList.originPushFront(anNode);
    doublyLinkedList.originPushFront(aNode);
    doublyLinkedList.originPushFront(apNode);

    //when
    doublyLinkedList.splice(aNode, bNode, xNode);

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("ap-bn-xp-x-a-an-bp-b-xn");
  }

  @DisplayName("노드a 를 노드x 뒤로 이동")
  @Test
  void moveAfter() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node cNode = new Node("c");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(cNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    doublyLinkedList.moveAfter(aNode, xNode);

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("b-c-x-a");
  }

  @DisplayName("노드a 를 노드x 앞으로 이동")
  @Test
  void moveBefore() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node cNode = new Node("c");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(cNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    doublyLinkedList.moveBefore(aNode, xNode);

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("b-c-a-x");
  }

  @DisplayName("key로 새로운 노드를 만들어서 x 뒤에 삽입")
  @Test
  void insertAfter() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    doublyLinkedList.insertAfter(xNode, "y");

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("a-b-x-y");
  }

  @DisplayName("key로 새로운 노드를 만들어서 x 앞에 삽입")
  @Test
  void insertBefore() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    doublyLinkedList.insertBefore(xNode, "y");

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("a-b-y-x");
  }

  @DisplayName("key로 새로운 노드를 만들어서 헤드노드(더미) 다음에 삽입")
  @Test
  void pushFront() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    doublyLinkedList.pushFront("y");

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("y-a-b-x");
  }

  @DisplayName("key로 새로운 노드를 만들어서 헤드노드(더미) 전 (=테일 노드 다음)에 삽입")
  @Test
  void pushBack() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    doublyLinkedList.pushBack( "y");

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("a-b-x-y");
  }

  @DisplayName("탐색")
  @Test
  void search() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    Node searchNode = doublyLinkedList.search("a");

    //then
    assertThat(searchNode.getKey()).isEqualTo("a");
  }

  @DisplayName("삭제")
  @Test
  void remove() {
    //given
    DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    doublyLinkedList.originPushFront(xNode);
    doublyLinkedList.originPushFront(bNode);
    doublyLinkedList.originPushFront(aNode);

    //when
    doublyLinkedList.remove(bNode);

    //then
    assertThat(arrayToStr(doublyLinkedList.toArray())).isEqualTo("a-x");
  }

  private String arrayToStr(String[] keyList) {
    return Arrays.stream(keyList)
        .collect(Collectors.joining("-"));
  }
}