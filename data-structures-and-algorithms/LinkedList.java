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

    /**
    * adds an element at the end of the list
    * - if list is empty add new element as head and tail
    * - set this new node to be the nextNode for the tail
    * - update the tail
    * - increment size
    * O(1) constant time since we have a reference to the tail
    **/
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

    /**
    * adds an element at the beggining of the list
    * - if list is empty add new element as head and tail
    * - set head to be the the nextNode for the new node
    * - update the head
    * - increment size
    * O(1)
    **/
    public void prepend(T value) {
        if(size == 0) {
            head = new SingleNode<T>(value, null);
            tail = head;
        } else {
            head = new SingleNode<T>(value, head);
        }
        size++;
    }

    /**
    * get a reference to a node based on the supplied index
    * - validate if the index is valid
    * - if index is head (0) or tail(size - 1) return that.
    * - iterate nodes until we reach the index we want, return that node
    * O(n) since we possibly need to go from 1 to size - 2
    **/
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

    /**
    * insert a value in a given index shifting all other values one position
    * - validate if the index is valid
    * - if index is head (0) or tail(size - 1) we add or prepend
    * - iterate nodes until we reach the node that is previous to the node we
    *   want to insert (we do this by calling getSingleNodeByIndex)
    * - insert new node as next node of the previous node and add the
    *   next node of the next node of previous node as next node of the new node
    * O(n) since we possibly need to go from 1 to size - 2
    **/
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

    /**
    * returns the index of the first occurence of the value in the list
    * - we iterate comparing the value of the nodes to the supplied value.
    * - if we found the value return 1
    * - if we don't find the value return -1
    * O(n)
    **/
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

    /**
    * removes an element from the list using index
    * - validate if the index is valid
    * - if the index is head, just set head to be head.nextNode
    * - iterate until we get a reference to the previous node we want to remove
    * - set previousNode.nextNode to be previousNode.nextNode.nextNode
    * - decrement size
    * O(n)
    **/
    public void removeByIndex(int index) {
        if(size <= 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            head = head.getNextNode();
        } else {
            SingleNode<T> singleNode = getSingleNodeByIndex(index - 1);
            singleNode.setNextNode(singleNode.getNextNode().getNextNode());
            if (index == size - 1) {
                tail = singleNode;
            }
        }
        size--;
    }

    /**
    * removes the first appereance of an element from the list.
    * - if list is empty dont do nothing
    * - keep a reference to previous node and a reference to current node
    * - if current node value is equals to the value we are looking for then
    *   set previousNode.nextNode to be currentNode.nextNode
    * - decrement size
    * O(n)
    **/
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
