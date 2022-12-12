class BinaryTreeNode {
    key: number
    parent: BinaryTreeNode | null
    left: BinaryTreeNode | null
    right: BinaryTreeNode | null

    constructor(key: number) {
        this.key = key
        this.parent = null
        this.left = null
        this.right = null
    }

    toString() {
        return this.key
    }

    preorder() {
        console.log(this.key)
        this.left?.preorder()
        this.right?.preorder()
    }

    inorder() {
        this.left?.inorder()
        console.log(this.key)
        this.right?.inorder()
    }

    postorder() {
        this.left?.postorder()
        this.right?.postorder()
        console.log(this.key)
    }
}

class BinarySearchTree {
    private root: BinaryTreeNode | null = null
    private size = 0

    get length() {
        return this.size
    }

    // key 값을 가진 노드가 있으면 해당 노드 return, 없으면 삽입될 부모 노드 return
    search(key: number) {
        if (this.size == 0) {
            return null
        }

        let parent = null
        let node = this.root
        while(node !== null) {
            if (node.key === key) {
                return node
            }

            parent = node
            node = node.key < key ? node.right : node.left
        }

        return parent
    }

    insert (key: number) {
        const parent = this.search(key)

        if (parent?.key === key) {
            throw new Error('key is already inserted')
        }

        const node = new BinaryTreeNode(key)

        if (parent === null) {
            this.root = node
            this.size += 1
            return
        }

        const side = parent.key < key ? 'right' : 'left'
        node.parent = parent
        parent[side] = node
        this.size += 1
    }

    deleteByMerging(targetNode: BinaryTreeNode) {
        const { left, right, parent } = targetNode
        const hasLeftNode = left !== null
        const hasRightNode = right !== null
        const isRootNode = parent === null

        let node // targetNode 자리를 대체할 노드
        let biggestNodeInLeft // left에서 가장 큰 노드

        if(hasLeftNode) {
            node = left
            biggestNodeInLeft = left
            while(biggestNodeInLeft.right) {
                biggestNodeInLeft = biggestNodeInLeft.right // 가장 큰 값을 가지는 노드 탐색
            }

            if (hasRightNode) {
                right.parent = biggestNodeInLeft
            }
            biggestNodeInLeft.right = right
        } else {
            node = right
        }

        console.log('delete', parent?.key, node?.key)

        // root
        if (isRootNode) {
            this.root = node
            if (node) {
                node.parent = null
            }

            // targetNode 자리에 대체 노드가 있는 경우
        } else if (hasLeftNode || hasRightNode) {
            node!.parent = parent

            const side = parent.key < node!.key ? 'right' : 'left'
            parent[side] = node

            // targetNode 자리의 대체 노드가 없는 경우
        } else {
            const side = parent.key < targetNode.key ? 'right' : 'left'
            parent[side] = null
        }
        this.size -= 1
    }

    print() {
        this.root?.preorder()
    }
}

const tree = new BinarySearchTree()
tree.insert(4)
tree.insert(1)
tree.insert(5)
tree.insert(2)
tree.deleteByMerging(tree.search(1)!)
tree.print()
