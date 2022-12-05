package my.study.data_structuress.heap

data class MaxHeapWithList(var array: ArrayList<Int?>) {
    init {
        makeHeap(array)
    }

    private fun makeHeap(array: ArrayList<Int?>): ArrayList<Int?> {
        val lastIndex = array.lastIndex
        for (k in lastIndex downTo 0 step(2)) {
            heapify(k)
        }
        return array
    }

    private fun heapify(index: Int) {
        // 부모 노드를 찾음
        var parentIndex = findParentNodeIndexByChildNode(index)

        // 리프노드에 도달할 때 까지 반복
        while(!isLeafNode(parentIndex) && !isRootNode(index)) {
            val leftChildNodeIndex = findLeftChildNodeIndexByParentNode(parentIndex)
            val rightChildNodeIndex = findRightChildNodeIndexByParentNode(parentIndex)

            // 현재 index가 자식 노드들보다 값이 크다면
            val maxIndexOfValues = maxIndexByValues(parentIndex, leftChildNodeIndex, rightChildNodeIndex)
            if (parentIndex == maxIndexOfValues) {
                break
            }

            // 부모노드를 값이 큰 자식 노드와 자리 변경
            swap(parentIndex, maxIndexOfValues)

            // 새롭게 자식이 된 노드부터 시작
            parentIndex = maxIndexOfValues
        }
    }

    private fun swap(from: Int, to: Int) {
        val temp = array[from]
        array[from] = array[to]
        array[to] = temp
    }

    private fun findLeftChildNodeIndexByParentNode(parentIndex: Int): Int =
        parentIndex * 2 + 1

    private fun findRightChildNodeIndexByParentNode(parentIndex: Int): Int =
        parentIndex * 2 + 2

    private fun findParentNodeIndexByChildNode(childIndex: Int): Int =
        (childIndex - 1) / 2 // 내림 처리로 정수 변환됨

    private fun isLeafNode(index: Int): Boolean {
        val leftChildNodeIndex = findLeftChildNodeIndexByParentNode(index)
        val rightChildNodeIndex = findRightChildNodeIndexByParentNode(index)

        if (array.lastIndex < leftChildNodeIndex && array.lastIndex < rightChildNodeIndex) return true
        if (array.get(leftChildNodeIndex) == null && array.get(rightChildNodeIndex) == null) return true
        return false
    }

    private fun isRootNode(index: Int): Boolean =
        index == 0

    private fun maxIndexByValues(i1: Int, i2:Int, i3:Int): Int {
        val v1 = array[i1]!!
        val v2 = if (array.lastIndex < i2 || array[i2] == null) v1 - 1 else array[i2]!!
        val v3 = if (array.lastIndex < i3 || array[i3] == null) v1 - 1 else array[i3]!!

        if (v1 >= v2 && v1 >= v3) return i1
        if (v2 >= v1 && v2 >= v3) return i2
        return i3
    }
}
