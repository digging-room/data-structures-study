class DoublyLinkedListNode<Key> {
  public key: Key
  public prev: DoublyLinkedListNode<Key> | null
  public next: DoublyLinkedListNode<Key> | null

  constructor(key: Key) {
    this.key = key
    this.next = null
    this.prev = null
  }
}

class DoublyLinkedList<Key> {
  public head: DoublyLinkedListNode<Key> = new DoublyLinkedListNode(null as Key)
  private size: number = 0

  get length() {
    return this.size
  }


  /**
   * @description nodeA와 nodeB 사이의 노드를 잘라서 targetNode 다음으로 연결하는 메서드
   * 조건 1. nodeA의 next를 따라가다보면 nodeB가 나온다(a -> ... -> b).
   * 조건 2. nodeA와 nodeB 사이에 head 노드가 없어야 한다.
   */
  splice(
    nodeA: DoublyLinkedListNode<Key>,
    nodeB: DoublyLinkedListNode<Key>,
    targetNode: DoublyLinkedListNode<Key>
  ) {
    if (this.size === 0) {
      throw new Error('spliace must size > 0')
    }

    const aPrev = nodeA.prev // null
    const bNext = nodeB.next // null

    // cutting
    aPrev && (aPrev.next = bNext)
    bNext && (bNext.prev = aPrev)

    const targetNodeNext = targetNode.next

    // splicing
    targetNode.next = nodeA
    nodeA.prev = targetNode
    nodeB.next = targetNodeNext
    targetNodeNext!.prev = nodeB
  }

  moveAfter(
    node: DoublyLinkedListNode<Key>,
    targetNode: DoublyLinkedListNode<Key>
  ) {
    this.splice(node, node, targetNode)
  }

  moveBefore(
    node: DoublyLinkedListNode<Key>,
    targetNode: DoublyLinkedListNode<Key>
  ) {
    this.splice(node, node, targetNode.prev!)
  }

  insertAfter(
    node: DoublyLinkedListNode<Key>,
    key: Key
  ) {
    if (this.size === 0) {
      this.insertFirstNode(key)
      return
    }

    this.moveAfter(new DoublyLinkedListNode(key), node)
    this.size += 1
  }

  insertBefore(
    node: DoublyLinkedListNode<Key>,
    key: Key
  ) {
    if (this.size === 0) {
      this.insertFirstNode(key)
      return
    }

    this.moveBefore(new DoublyLinkedListNode(key), node)
    this.size += 1
  }

  private insertFirstNode(key: Key) {
    const newNode = new DoublyLinkedListNode(key)
    newNode.prev = this.head
    newNode.next = this.head
    this.head.prev = newNode
    this.head.next = newNode
    this.size += 1
  }

  pushFront(key: Key) {
    this.insertAfter(this.head, key)
  }

  pushBack(key: Key) {
    this.insertBefore(this.head, key)
  }

  remove(key: Key) {
    const node = this.search(key)

    if (!node) {
      return
    }

    node.prev!.next = node.next
    node.next!.prev = node.prev
    this.size -= 1
  }

  popFront() {
    if (this.size === 0) {
      return
    }

    this.remove(this.head.next!.key)
  }

  popBack() {
    if (this.size === 0) {
      return
    }

    this.remove(this.head.prev!.key)
  }

  join(list: DoublyLinkedList<Key>) {
    if (list.length === 0) {
      return
    }

    if (this.size === 0) {
      list.head.next!.prev = this.head
      list.head.prev!.next = this.head
      this.head.next = list.head.next
      this.head.prev = list.head.prev
      this.size = list.length
      return
    }

    this.splice(list.head.next!, list.head.prev!, this.head.prev!)
    this.size += list.length
  }

  search(key: Key) {
    if (this.size === 0) {
      return null
    }

    let node = this.head.next!
    while (node !== this.head) {
      if (node.key === key) {
        return node
      } else {
        node = node.next!
      }
    }

    return null
  }

  print() {
    if (this.size === 0) {
      console.log('')
      return
    }

    let result = []
    let node = this.head.next!

    while(node !== this.head) {
      result.push(node.key)
      node = node.next!
    }

    console.log(result.join(' > '))
  }
}

const list = new DoublyLinkedList()
const list2 = new DoublyLinkedList()
list2.pushFront(1)
list2.pushBack(2)
list.pushFront(0)
let node = list2.search(2)
list2.insertAfter(node!, 4)
node = list2.search(4)
list2.insertBefore(node!, 3)
list2.remove(4)
list.join(list2)
list.print()
list.popBack()
list.popFront()
list.print()
