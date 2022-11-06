// 연결 리스트 노드
class LinkedListNode<Key> {
  public key: Key
  public next: LinkedListNode<any> | null

  constructor(key: Key) {
    this.key = key
    this.next = null
  }

  toString() {
    return this.key
  }
}

// 한방향 연결 리스트
class SinglyLinkedLink<Key> {
  public head: LinkedListNode<Key> | null
  public size: number

  get length() {
    return this.size
  }

  constructor() {
    this.head = null
    this.size = 0
  }

  pushFront(key: Key) {
    const node = new LinkedListNode(key)

    node.next = this.head
    this.head = node
    this.size += 1
  }

  pushBack(key: Key) {
    const node = new LinkedListNode(key)

    if (this.size === 0) {
      this.head = node
      this.size += 1
      return
    }

    let tail = this.head!
    while (tail.next !== null) {
      tail = tail.next
    }

    tail.next = node
    this.size += 1
  }

  popFront() {
    if (this.size === 0) {
      return null
    }

    const node = this.head!

    this.head = node.next
    this.size -= 1

    return node.key
  }

  popBack() {
    if (this.size === 0) {
      return null
    }

    if (this.size === 1) {
      const key  = this.head!.key
      this.head = null
      this.size -= 1
      return key
    }

    // running technique
    let tail = this.head!
    let prev = tail.next

    while(tail.next !== null) {
      prev = tail
      tail = tail.next
    }

    const key = tail.key
    prev!.next = null
    this.size -= 1
    return key
  }

  search(key: Key) {
    let node = this.head
    while(node !== null) {
      if (node.key === key) {
        return node
      } else {
        node = node.next
      }
    }

    return null
  }

  // Generator를 이용해 for..of 구문을 지원하게 함
  // 사용자 정의 iterable
  *[Symbol.iterator]() {
    let node = this.head
    while(node !== null) {
      yield node
      node = node.next
    }
  }
}

const list = new SinglyLinkedLink<number>()
list.pushFront(1)
list.pushBack(2)
list.pushBack(3)
list.pushBack(4)

for (const item of list) {
  console.log(item)
}

console.log(list.head)
console.log(list.popFront(), list.head)
console.log(list.popBack(), list.head)
