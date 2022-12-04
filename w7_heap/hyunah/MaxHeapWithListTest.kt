package my.study.data_structuress.heap

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MaxHeapWithListTest {
    @MethodSource("generateUnsortedHeap")
    @ParameterizedTest
    fun heapSort(unsortedArray: ArrayList<Int?>, expectedMaxHeapSortedArray: ArrayList<Int?>) {
        // when
        val maxHeap = MaxHeapWithList(unsortedArray)

        // then
        assertThat(maxHeap.array).isEqualTo(expectedMaxHeapSortedArray)
    }

    companion object {
        @JvmStatic
        fun generateUnsortedHeap() = listOf(
            // 교수님 예제
            Arguments.of(arrayListOf(2, 8, 6, 1, 10, 15, 3, 12, 11), arrayListOf(15, 12, 6, 11, 10, 2, 3, 1, 8)),

            // 마지막 노드 중 리프가 1개인 경우 null로 표현하지 않고 아예 존재하지 않는 노드
            Arguments.of(arrayListOf(35, 33, 42, 10, 14, 19, 27, 44, 26, 31), arrayListOf(44, 35, 42, 33, 31, 19, 27, 10, 26, 14)), // intArrayOf(44, 42, 35, 33, 31, 19, 27, 10, 26, 14) 두번째랑 세번째 위치가 달라서 오류라고 뜸!

            // 마지막 노드 중 리프가 1개인 경우, null로 표현하여 노드를 두 쌍으로 채워서 제공
            Arguments.of(arrayListOf(35, 33, 42, 10, 14, 19, 27, 44, 26, 31, null), arrayListOf(44, 35, 42, 33, 31, 19, 27, 10, 26, 14, null)),
        )
    }
}
