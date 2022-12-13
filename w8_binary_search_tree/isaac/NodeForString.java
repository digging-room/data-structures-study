package dataStructure.binaryTree;

public class NodeForString {
    private String key;
    private NodeForString parent;
    private NodeForString left;
    private NodeForString right;

    public void preOrder() {
        if (this != null) {
            System.out.print(key);
            if (left != null) {
                left.preOrder();
            }
            if (right != null) {
                right.preOrder();
            }
        } else {
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

    public NodeForString(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public NodeForString getParent() {
        return parent;
    }

    public void setParent(NodeForString parent) {
        this.parent = parent;
    }

    public NodeForString getLeft() {
        return left;
    }

    public void setLeft(NodeForString left) {
        this.left = left;
    }

    public NodeForString getRight() {
        return right;
    }

    public void setRight(NodeForString right) {
        this.right = right;
    }
}
