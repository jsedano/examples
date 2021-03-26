public class BinaryHeap<T extends Comparable<? super T>> {
    private DynamicArray<T> array;
    private boolean max;

    public BinaryHeap() {
        max = true;
        array = new DynamicArray<T>();
    }

    public BinaryHeap(boolean max) {
        this();
        this.max = max;
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public void add(T value) {
        array.add(value);
        siftUp(array.size() - 1);
    }

    public void swap(int index1, int index2) {
        T tmp = array.get(index1);
        array.put(array.get(index2), index1);
        array.put(tmp, index2);
    }

    public int getLeftChild(int index) {
        return 2 * index + 1;
    }

    public int getRightChild(int index) {
        return 2 * index + 2;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public void siftUp(int index) {
        if(index > 0 && compare(index, getParent(index)) > 0) {
            swap(index, getParent(index));
            siftUp(getParent(index));
        }
    }

    public void siftDown(int parent) {
        int current = parent;
        int leftChild = getLeftChild(parent);
        int rightChild = getRightChild(parent);
        if(leftChild < array.size() && compare(leftChild, parent) > 0) {
            current = leftChild;
        }
        if(rightChild < array.size() && compare(rightChild, current) > 0) {
            current = rightChild;
        }
        if(parent != current) {
            swap(parent, current);
            siftDown(current);
        }
    }

    public T poll() {
        T value = array.get(0);
        swap(0, array.size() - 1);
        array.remove(array.size() - 1);
        siftDown(0);
        return value;
    }

    public int compare(int childIndex, int parentIndex) {
        return array.get(childIndex).compareTo(array.get(parentIndex)) * (max ? 1 : -1);
    }

    public String toString() {
        return array.toString();
    }

    public static void main(String []args){
        BinaryHeap<Integer> maxHeap = new BinaryHeap<Integer>();
        System.out.println(maxHeap.max);
        maxHeap.add(0);
        System.out.println(maxHeap);
        maxHeap.add(1);
        System.out.println(maxHeap);
        maxHeap.add(2);
        System.out.println(maxHeap);
        maxHeap.add(3);
        System.out.println(maxHeap);
        maxHeap.add(4);
        System.out.println(maxHeap);
        maxHeap.add(5);
        System.out.println(maxHeap);
        maxHeap.add(-6);
        System.out.println(maxHeap);
        maxHeap.add(100);
        System.out.println(maxHeap);

        while(!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
            System.out.println(maxHeap);
        }
    }
}
