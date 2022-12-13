package dataStructure.binaryTree;

public class Node {
    private int key;
    private Node parent;
    private Node left;
    private Node right;

    public void preOrder() {
        System.out.print(key);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
        if (this == null) {
            System.out.println("빈 노드입니다.");
        }
    }

    public void inOrder(){
        if (this != null) {
            if (this.left != null) {
                this.left.inOrder();
            }
            System.out.print(this.key);
            if (this.right != null) {
                this.right.inOrder();
            }
        } else {
            System.out.println("빈 노드입니다.");
        }
    }

    public void postOrder(){
        if (this != null) {
            if (this.left != null) {
                this.left.postOrder();
            }

            if (this.right != null) {
                this.right.postOrder();
            }

            System.out.print(this.key);
        } else {
            System.out.println("빈 노드입니다.");
        }
    }

    public Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
