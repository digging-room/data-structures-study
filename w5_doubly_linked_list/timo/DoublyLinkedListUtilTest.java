package week05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListUtilTest {

    @DisplayName("a, b를 cut하면 aPrev와 bNext가 연결된다")
    @Test
    public void spliceCut() {
        //given
        Node aPrev = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node bNext = new Node(4);

        //(1 <-> 2 <-> 3 <-> 4)
        aPrev.changeNext(a);
        a.changePrev(aPrev);
        a.changeNext(b);
        b.changePrev(a);
        b.changeNext(bNext);
        bNext.changePrev(b);
        bNext.changeNext(aPrev);
        aPrev.changePrev(bNext);

        //when
        Node targetNode = new Node(0);
        DoublyLinkedListUtil.splice(a, b, targetNode);

        //then
        assertThat(aPrev.getNext()).isEqualTo(bNext);
        assertThat(bNext.getPrev()).isEqualTo(aPrev);
    }

    @DisplayName("a, b를 x에 splice하면 x의 next는 a가 되고, xNext의 prev는 b가 된다")
    @Test
    void splicePaste() {
        //given
        Node aPrev = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node bNext = new Node(4);

        //(1 <-> 2 <-> 3 <-> 4)
        aPrev.changeNext(a);
        a.changePrev(aPrev);
        a.changeNext(b);
        b.changePrev(a);
        b.changeNext(bNext);
        bNext.changePrev(b);
        bNext.changeNext(aPrev);
        aPrev.changePrev(bNext);

        Node x = new Node(0);
        Node xNext = new Node(9);
        x.changeNext(xNext);
        x.changePrev(xNext);
        xNext.changePrev(x);
        xNext.changeNext(x);

        //when
        DoublyLinkedListUtil.splice(a, b, x);

        //x <-> a
        assertThat(x.getNext()).isEqualTo(a);
        assertThat(a.getPrev()).isEqualTo(x);

        // b <-> xNext
        assertThat(b.getNext()).isEqualTo(xNext);
        assertThat(xNext.getPrev()).isEqualTo(b);
    }
}