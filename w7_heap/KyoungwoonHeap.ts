class Heap {
    private items: number[] = []

    constructor(items: number[]) {
        this.items = items
        this.makeHeap(items)
    }

    insert(value: number) {
        this.items.push(value)
        this.heapifyUp(this.items.length - 1)
    }

    findMax() {
        if (this.items.length === 0) {
            return null
        }

        return this.items[0]
    }

    deleteMax() {
        if (this.items.length === 0) {
            return null
        }

        const value = this.findMax()

        this.swap(0, this.items.length - 1)
        this.items.pop()
        this.heapifyDown(0)

        return value
    }

    print() {
        console.log(this.items)
    }

    private makeHeap(items: number[]) {
        for (let index = items.length - 1; index >= 0; index -= 1) {
            this.heapifyDown(index)
        }
    }

    private heapifyDown(index: number) {
        while (!this.isLeafNode(index)) {
            const leftIndex = 2 * index + 1
            const rightIndex = 2 * index + 2
            const leftValue = this.items[leftIndex] ?? Number.MIN_SAFE_INTEGER
            const rightValue = this.items[rightIndex] ?? Number.MIN_SAFE_INTEGER

            const maxValue = Math.max(
                this.items[index],
                leftValue,
                rightValue
            )

            if (maxValue === this.items[index]) {
                break
            }


            if (leftValue > rightValue) {
                this.swap(index, leftIndex)
                index = leftIndex
            } else {
                this.swap(index, rightIndex)
                index = rightIndex
            }
        }
    }

    private isLeafNode(index: number) {
        const leftNodeIndex = 2 * index + 1
        return this.items.length <= leftNodeIndex
    }

    private heapifyUp(index: number) {
        let parentIndex = Math.floor((index - 1) / 2)
        while(index > 0 && this.items[parentIndex] < this.items[index]) {
            this.swap(index, parentIndex)
            index = parentIndex
            parentIndex = Math.floor((index - 1) / 2)
        }
    }

    private swap(indexA: number, indexB: number) {
        [this.items[indexA], this.items[indexB]] = [this.items[indexB], this.items[indexA]]
    }
}

const arr = [1, 4, 2, 3]
const heap = new Heap(arr)
heap.print()
heap.insert(5)
heap.insert(0)
heap.print()
heap.deleteMax()
heap.print()
