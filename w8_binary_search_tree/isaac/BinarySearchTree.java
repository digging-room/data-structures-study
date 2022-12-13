package dataStructure.binaryTree;

public class BinarySearchTree {
    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize(){
        return this.size;
    }

    public void iteratePreOrder(){
        if (root != null) {
            root.preOrder();
        }
    }

    public void iterateInOrder(){
        if (root != null) {
            root.inOrder();
        }
    }

    public void iteratePostOrder(){
        if (root != null) {
            root.postOrder();
        }
    }

    // key값 노드가 있다면 해당노드를, 없다면 노드가 삽입될 부모노드 return
    public Node findLocation(int key){
        if (this.size == 0) {
            return null;
        }
        Node parent = null;
        Node value = this.root;

        while (value != null) {
            if (value.getKey() == key) {
                return value;
            } else if (value.getKey() < key) { // 찾으려는 key값이 더 크면 오른쪽으로
                parent = value;
                value = value.getRight();
            } else { // 찾으려는 key값이 더 작으면 왼쪽으로
                parent = value;
                value = value.getLeft();
            }
        }
        return parent;
    }

    public Node search(int key){
        Node value = findLocation(key);
        if (value != null && value.getKey() == key) {
            return value;
        } else {
            return null;
        }
    }

    public Node insert(int key){
        Node parent = findLocation(key);
        Node value = new Node(key);

        if (parent == null || parent.getKey() != key) {
            if (parent == null) {
                this.root = value;
            } else  {
                if (parent.getKey() >= key) {
                    parent.setLeft(value);
                    value.setParent(parent);
                } else {
                    parent.setRight(value);
                    value.setParent(parent);
                }
            }
        } else {
            System.out.println("key is already exist");
            return parent;
        }
        this.size += 1;
        return value;
    }

    public void deleteNodeByMerging(int key){
        Node x = search(key);
        Node a = x.getLeft();
        Node b = x.getRight();
        Node pt = x.getParent();
        Node c; // x자리를 대체할 노드
        Node m; // 왼쪽 서브트리에서 최대값을 가지는 노드

        // 왼쪽 서브트리가 존재하는 경우
        if (a != null) {
            c = a;
            m = a;
            while (m.getRight() != null) {
                m = m.getRight();
            }

            if (b != null) {
                // 왼쪽 서브트리의 최대값 노드를 b의 부모로 설정
                b.setParent(m);
                m.setRight(b);
            }
        } else { // 왼쪽 서브트리가 존재하지 않는다면, 오른쪽 서브트리로 대체
            c = b;
        }

        // x가 루트노드인 경우
        if (pt == null) {
            this.root = c;
            c.setParent(null);
        } else {
            if (c != null) {
                c.setParent(pt);
            }
            if (pt.getKey() >= c.getKey()) {
                pt.setLeft(c);
            } else {
                pt.setRight(c);
            }
        }
        this.size -= 1;
    }
}
