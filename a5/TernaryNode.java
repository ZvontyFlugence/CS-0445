package cs445.a5;

public class TernaryNode<T> {
    private T data;
    @SuppressWarnings("unchecked")
    private TernaryNode<T>[] children = (TernaryNode<T>[])new TernaryNode<?>[3];
    private TernaryNode<T> leftChild, middleChild, rightChild;

    public TernaryNode() {
        this(null);
    }

    public TernaryNode(T data) {
        this.data = data;
    }

    public TernaryNode(T data, TernaryNode<T>[] children) {
        this.data = data;
        if(children.length > 0 && children.length <= 3) {
            this.children = children;
        }
    }

    public T getData () {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TernaryNode<T> getLeftChild() {
        return children[0];
    }

    public void setLeftChild(TernaryNode<T> leftChild) {
        children[0] = leftChild;
    }

    public boolean hasLeftChild() {
        return children[0] != null;
    }

    public TernaryNode<T> getMiddleChild() {
        return children[1];
    }

    public void setMiddleChild(TernaryNode<T> middleChild) {
        children[1] = middleChild;
    }

    public boolean hasMiddleChild() {
        return children[1] != null;
    }

    public TernaryNode<T> getRightChild() {
        return children[2];
    }

    public void setRightChild(TernaryNode<T> rightChild) {
        children[2] = rightChild;
    }

    public boolean hasRightChild() {
        return children[2] != null;
    }

    public boolean isLeaf() {
        return (children[0] == null) && (children[1] == null) && (children[2] == null);
    }

    public int getNumberOfNodes() {
        int leftNum = 0;
        int midNum = 0;
        int rightNum = 0;
        if(children[0] != null) {
            leftNum = children[0].getNumberOfNodes();
        }
        if(children[1] != null) {
            midNum = children[1].getNumberOfNodes();
        }
        if(children[2] != null) {
            rightNum = children[2].getNumberOfNodes();
        }
        return 1 + leftNum + midNum + rightNum;
    }

    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(TernaryNode<T> node) {
        int height = 0;
        int leftHeight = 0;
        int middleHeight = 0;
        int rightHeight = 0;
        if(node != null)
            leftHeight = getHeight(node.getLeftChild());
            middleHeight = getHeight(node.getMiddleChild());
            rightHeight = getHeight(node.getRightChild());
            height = 1 + Math.max(Math.max(leftHeight, middleHeight), Math.max(middleHeight, rightHeight));
        return height;
    }

    public TernaryNode<T> copy() {
        TernaryNode<T> newRoot = new TernaryNode<>(data);
        if(children[0] != null) {
            newRoot.setLeftChild(children[0].copy());
        }
        if(children[1] != null) {
            newRoot.setMiddleChild(children[1].copy());
        }
        if(children[2] != null) {
            newRoot.setRightChild(children[2].copy());
        }
        return newRoot;
    }
}
