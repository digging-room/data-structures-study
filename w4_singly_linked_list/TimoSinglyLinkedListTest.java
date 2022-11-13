import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TimoSinglyLinkedListTest {

    @DisplayName("제일 앞에 요소 삽입")
    @Test
    void pushFront() {
        //given
        TimoSinglyLinkedList linkedListEmpty = new TimoSinglyLinkedList();
        TimoSinglyLinkedList linkedListAppend = new TimoSinglyLinkedList();
        TimoNode fistNode = new TimoNode(1);
        TimoNode secondNode = new TimoNode(1);

        //when
        linkedListEmpty.pushFront(fistNode);
        linkedListAppend.pushFront(fistNode);
        linkedListAppend.pushFront(secondNode);

        //then
        assertThat(linkedListEmpty.size()).isEqualTo(1);
        assertThat(linkedListAppend.size()).isEqualTo(2);
    }

    @DisplayName("제일 뒤에 요소 삽입")
    @Test
    void pushBack() {
        //given
        TimoSinglyLinkedList linkedListEmpty = new TimoSinglyLinkedList();
        TimoNode firstNode = new TimoNode(1);

        TimoSinglyLinkedList linkedListAppend = new TimoSinglyLinkedList();
        TimoNode secondNode = new TimoNode(2);

        //when
        linkedListEmpty.pushBack(firstNode);
        linkedListAppend.pushBack(firstNode);
        linkedListAppend.pushBack(secondNode);

        //then
        assertThat(linkedListEmpty.size()).isEqualTo(1);
        assertThat(linkedListAppend.size()).isEqualTo(2);
    }

    @DisplayName("제일 첫 요소 제거")
    @Test
    void popFirst() {
        //given
        TimoSinglyLinkedList linkedList = new TimoSinglyLinkedList();
        TimoNode firstNode = new TimoNode(1);
        TimoNode secondNode = new TimoNode(2);
        TimoNode thirdNode = new TimoNode(3);

        linkedList.pushFront(thirdNode);
        linkedList.pushFront(secondNode);
        linkedList.pushFront(firstNode);

        //when
        TimoNode popNode = linkedList.popFront();

        //then
        assertThat(popNode).isEqualTo(firstNode);
    }

    @DisplayName("제일 마지막 요소 제거")
    @Test
    void popLast() {
        //given
        TimoSinglyLinkedList linkedList = new TimoSinglyLinkedList();
        TimoNode firstNode = new TimoNode(1);
        TimoNode secondNode = new TimoNode(2);
        TimoNode thirdNode = new TimoNode(3);

        linkedList.pushBack(firstNode);
        linkedList.pushBack(secondNode);
        linkedList.pushBack(thirdNode);

        //when
        TimoNode popNode = linkedList.popBack();

        //then
        assertThat(popNode).isEqualTo(thirdNode);
        assertThat(linkedList.size()).isEqualTo(2);
    }

    @DisplayName("비어있는 리스트에서 popFirst()를 호출하면 예외 발생")
    @Test
    void popFirstException() {
        //given
        TimoSinglyLinkedList linkedList = new TimoSinglyLinkedList();

        //when & then
        assertThatThrownBy(linkedList::popFront)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("비어있는 리스트입니다.");
        assertThatThrownBy(linkedList::popBack)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("비어있는 리스트입니다.");
    }

    @DisplayName("해당 KEY를 가진 노드 탐색")
    @Test
    void search() {
        //given
        TimoSinglyLinkedList linkedList = new TimoSinglyLinkedList();
        linkedList.pushBack(new TimoNode(1));
        linkedList.pushBack(new TimoNode(2));
        linkedList.pushBack(new TimoNode(3));

        //when
        TimoNode findNode =  linkedList.search(2);

        //then
        assertThat(findNode.getKey()).isEqualTo(2);
        assertThat(linkedList.search(9)).isNull();
    }
}