class BinaryNode<T extends Comparable<? super T>> {
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;
    private T data;

    public BinaryNode(T data) {
        this.data = data;
    }

    public void setLeftChild(BinaryNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(BinaryNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeftChild() {
        return leftChild;
    }

    public BinaryNode<T> getRightChild() {
        return rightChild;
    }

    public T getData() {
        return data;
    }
}
