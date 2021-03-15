public class SingleNode<T extends Comparable<? super T>>{
    private T value;
    private SingleNode<T> nextNode;

    public SingleNode(T value, SingleNode<T> nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNextNode(SingleNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public T getValue() { return value; }

    public  SingleNode<T> getNextNode() { return nextNode; }
}
