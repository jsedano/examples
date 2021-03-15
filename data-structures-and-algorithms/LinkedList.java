public class LinkedList<T extends Comparable<? super T>> {

    private int size;
    private SingleNode<T> head;
    private SingleNode<T> tail;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        if(size == 0) {
            head = new SingleNode<T>(value, null);
            tail = head;
        } else {
            tail.setNextNode(new SingleNode<T>(value, null));
            tail = tail.getNextNode();
        }
        size++;
    }

    public void prepend(T value) {
        if(size == 0) {
            head = new SingleNode<T>(value, null);
            tail = head;
        } else {
            head = new SingleNode<T>(value, head);
        }
        size++;
    }

    private SingleNode<T> getSingleNodeByIndex(int index) {
        if(size <= 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            return head;
        }
        if(index == size - 1 ) {
            return tail;
        }

        SingleNode<T> singleNode = head.getNextNode();
        for(int i = 1; i < index; i++) {
            singleNode = singleNode.getNextNode();
        }
        return singleNode;
    }

    public T get(int index) {
        return getSingleNodeByIndex(index).getValue();
    }

    public void add(T value, int index) {
        if(size <= 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            prepend(value);
        } else if (index == size - 1) {
            add(value);
        } else {
            SingleNode<T> beforeNewNode = getSingleNodeByIndex(index - 1);
            beforeNewNode.setNextNode(new SingleNode<T>(value, beforeNewNode.getNextNode()));
            size++;
        }
    }

    public int indexOf(T value) {
        SingleNode<T> singleNode = head;
        for(int i = 0; i < size; i++) {
            if (singleNode.getValue().compareTo(value) == 0) {
                return i;
            } else {
                singleNode = singleNode.getNextNode();
            }
        }
        return -1;
    }

    public void removeByIndex(int index) {
        if(size <= 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            head = head.getNextNode();
        } else {
            SingleNode<T> singleNode = getSingleNodeByIndex(index - 1);
            singleNode.setNextNode(singleNode.getNextNode().getNextNode());
        }
        size--;
    }

    public void removeByValue(T value) {
        if(size == 0) {
            return;
        }
        SingleNode<T> beforeValue = null;
        SingleNode<T> valueToCompare = head;

        for(int i = 0; i < size; i++) {
            if (valueToCompare.getValue().compareTo(value) == 0) {
                if(i == 0) {
                    head = head.getNextNode();
                } else {
                    beforeValue.setNextNode(valueToCompare.getNextNode());
                    if (i == size - 1) {
                        tail = beforeValue;
                    }
                }
                size--;
                break;
            } else {
                beforeValue = valueToCompare;
                valueToCompare = valueToCompare.getNextNode();
            }
        }
    }


    @Override
    public String toString() {
        String s = "LinkedList { ";
        for(SingleNode i = head; i != null; i = i.getNextNode()) {
            s += "[" + i.getValue() + "]->";
        }
        return s + "null }";
    }

    public static void main(String []args) {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add("uno");
        linkedList.add("dos");
        linkedList.add("tres");
        linkedList.add("catorce");

        linkedList.prepend("before");
        linkedList.prepend("go");
        linkedList.prepend("will");
        linkedList.prepend("this");

        System.out.println(linkedList);

        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(7));
        System.out.println(linkedList.get(6));
        System.out.println(linkedList.indexOf("not found"));
        System.out.println(linkedList.indexOf("catorce"));

        linkedList.removeByIndex(0);
        linkedList.removeByIndex(6);
        linkedList.removeByIndex(3);

        System.out.println(linkedList);

        linkedList.removeByValue("will");
        linkedList.removeByValue("tres");
        linkedList.removeByValue("before");

        System.out.println(linkedList);

        System.out.println(linkedList.tail.getValue());

        linkedList.add("before go", 0);
        linkedList.add("after dos", 2);
        linkedList.add("after go", 2);

        System.out.println(linkedList);


    }
}
