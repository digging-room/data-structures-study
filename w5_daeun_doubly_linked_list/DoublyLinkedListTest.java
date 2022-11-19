package grace.datastructuresgroupstudy.week5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {


  @DisplayName("splice 연산")
  @Test
  void splice() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node apNode = new Node("ap");
    Node aNode = new Node("a");
    Node anNode = new Node("an");
    Node bpNode = new Node("bp");
    Node bNode = new Node("b");
    Node bnNode = new Node("bn");
    Node xpNode = new Node("xp");
    Node xNode = new Node("x");
    Node xnNode = new Node("xn");


    DoublyLinkedList.pushFront(xnNode);
    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(xpNode);
    DoublyLinkedList.pushFront(bnNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(bpNode);
    DoublyLinkedList.pushFront(anNode);
    DoublyLinkedList.pushFront(aNode);
    DoublyLinkedList.pushFront(apNode);

    //when
    DoublyLinkedList.splice(aNode, bNode, xNode);

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("ap-bn-xp-x-a-an-bp-b-xn");
  }

  @DisplayName("노드a 를 노드x 뒤로 이동")
  @Test
  void moveAfter() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node cNode = new Node("c");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(cNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    DoublyLinkedList.moveAfter(aNode, xNode);

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("b-c-x-a");
  }

  @DisplayName("노드a 를 노드x 앞으로 이동")
  @Test
  void moveBefore() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node cNode = new Node("c");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(cNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    DoublyLinkedList.moveBefore(aNode, xNode);

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("b-c-a-x");
  }

  @DisplayName("key로 새로운 노드를 만들어서 x 뒤에 삽입")
  @Test
  void insertAfter() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    DoublyLinkedList.insertAfter(xNode, new Node("y"));

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("a-b-x-y");
  }

  @DisplayName("key로 새로운 노드를 만들어서 x 앞에 삽입")
  @Test
  void insertBefore() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    DoublyLinkedList.insertBefore(xNode, new Node("y"));

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("a-b-y-x");
  }

  @DisplayName("key로 새로운 노드를 만들어서 헤드노드(더미) 다음에 삽입")
  @Test
  void pushFront() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    DoublyLinkedList.pushFront(new Node("y"));

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("y-a-b-x");
  }

  @DisplayName("key로 새로운 노드를 만들어서 헤드노드(더미) 전 (=테일 노드 다음)에 삽입")
  @Test
  void pushBack() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    DoublyLinkedList.pushBack( new Node("y"));

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("a-b-x-y");
  }

  @DisplayName("탐색")
  @Test
  void search() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    Node searchNode = DoublyLinkedList.search("a");

    //then
    assertThat(searchNode.getKey()).isEqualTo("a");
  }

  @DisplayName("삭제")
  @Test
  void remove() {
    //given
    DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();
    Node aNode = new Node("a");
    Node bNode = new Node("b");
    Node xNode = new Node("x");

    DoublyLinkedList.pushFront(xNode);
    DoublyLinkedList.pushFront(bNode);
    DoublyLinkedList.pushFront(aNode);

    //when
    DoublyLinkedList.remove(bNode);

    //then
    assertThat(arrayToStr(DoublyLinkedList.toArray())).isEqualTo("a-x");
  }

  private String arrayToStr(String[] keyList) {
    return String.join("-", keyList);
  }
}