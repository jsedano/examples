public class Queue<T> {

    private T[] innerArray;
    private int size;
    private int dequeueIndex;
    private int enqueueIndex;

    public Queue(int capacity) {
        innerArray = (T[]) new Object[capacity];
        size = 0;
        dequeueIndex = 0;
        enqueueIndex = 0;
    }

    /**
    * Dequeues an element from the queue
    * - if queue is empty we raise an exception
    * - take out the element and return it
    * - decrease size
    * - increase dequeue index
    * O(1)
    **/
    public T dequeue() {
        if(size == 0) {
            throw new NullPointerException("empty queue");
        }
        T valueToDequeue = innerArray[dequeueIndex];
        innerArray[dequeueIndex] = null;
        if (dequeueIndex == innerArray.length - 1) {
            dequeueIndex = 0;
        } else {
            dequeueIndex++;
        }
        size--;
        return valueToDequeue;
    }

    /**
    * Enqueues an element from the queue
    * - if queue is full we dont add the element
    * - adds element to the queue at enqueue index
    * - increase size
    * - increase enqueue index
    * O(1)
    **/
    public boolean enqueue(T valueToPush) {
        if(size == innerArray.length) {
            return false;
        }
        innerArray[enqueueIndex] = valueToPush;
        if (enqueueIndex == innerArray.length - 1) {
            enqueueIndex = 0;
        } else {
            enqueueIndex++;
        }
        size++;

        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String []args){
        Queue<Character> queue = new Queue<Character>(5);
        queue.enqueue('h');
        queue.enqueue('h');
        queue.enqueue('e');
        queue.enqueue('l');
        System.out.println("-->" + queue.dequeue());
        queue.enqueue('l');
        queue.enqueue('o');
        queue.enqueue('!');

        while(!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }

        try {
            queue.dequeue();
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
