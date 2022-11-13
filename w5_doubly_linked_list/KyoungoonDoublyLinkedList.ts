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
  public head: DoublyLinkedListNode<Key>
  private size: number

  get length() {
    return this.size
  }

  private get isOnlyHasHeadNode() {
    return this.size === 1
  }

  constructor(key: Key) {
    this.head = new DoublyLinkedListNode(key)
    this.size = 1
  }

  insertAfter(targetNode: DoublyLinkedListNode<Key>, key: Key) {
    if (this.isOnlyHasHeadNode) {
      this.insertSecondNode(key)
      return
    }

    const newNode = new DoublyLinkedListNode(key)

    newNode.prev = targetNode
    newNode.next = targetNode.next
    targetNode.next!.prev = newNode
    targetNode.next = newNode
    this.size += 1
  }

  insertBefore(targetNode: DoublyLinkedListNode<Key>, key: Key) {
    if (this.isOnlyHasHeadNode) {
      this.insertSecondNode(key)
      return
    }

    const newNode = new DoublyLinkedListNode(key)

    newNode.prev = targetNode.prev
    newNode.next = targetNode
    targetNode.prev!.next = newNode
    targetNode.prev = newNode
    this.size += 1
  }

  private insertSecondNode(key: Key) {
    const newNode = new DoublyLinkedListNode(key)
    newNode.prev = this.head
    newNode.next = this.head
    this.head.prev = newNode
    this.head.next = newNode
    this.size += 1

  }

  pushFront(key: Key) {
    if (this.isOnlyHasHeadNode) {
      this.insertSecondNode(key)
      return
    }

    this.insertAfter(this.head, key)
  }

  pushBack(key: Key) {
    if (this.isOnlyHasHeadNode) {
      this.insertSecondNode(key)
      return
    }

    this.insertBefore(this.head, key)
  }

  remove(key: Key) {
    if (this.size === 1) {
      throw new Error(`Can't remove last node`)
    }

    const targetNode = this.search(key)

    if (!targetNode) {
      throw new Error(`There is no node with key is '${key}'`)
    }

    // head 삭제할 경우 새로운 head 지정
    if (this.head.key === key) {
      this.head = this.head.next!
    }

    if (this.size === 2) {
      targetNode.prev!.next = null
      targetNode.prev!.prev = null
      this.size -= 1
      return
    }

    targetNode.prev!.next = targetNode.next
    targetNode.next!.prev = targetNode.prev
    this.size -= 1
  }

  popFront() {
    this.remove(this.head.key)
  }

  popBack() {
    this.remove(this.head.prev?.key as Key)
  }

  search(key: Key) {
    let node = this.head
    for(let i = 0; i < this.size; i += 1) {
      if (node.key === key) {
        return node
      }
      node = node.next!
    }

    return null
  }

  print() {
    let result = []
    let node = this.head
    for(let i = 0; i < this.size; i += 1) {
      result.push(node.key)
      node = node.next!
    }

    console.log(result.join(' > '))
  }
}

const list = new DoublyLinkedList(1)
list.insertAfter(list.head, 2)
list.insertAfter(list.search(2)!, 4)
list.insertBefore(list.search(4)!, 3)
list.pushFront(1.1)
list.pushBack(0)
list.print()
list.remove(1.1)
list.print()
list.popFront()
list.print()
list.popBack()
list.popBack()
list.popBack()
list.popBack()
list.print()
