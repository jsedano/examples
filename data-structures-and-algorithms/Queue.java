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

    public T dequeue() {
        if(size == 0) {
            throw new NullPointerException("empty stack");
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
        Queue<Character> stack = new Queue<Character>(5);
        stack.enqueue('h');
        stack.enqueue('e');
        stack.enqueue('l');
        stack.enqueue('l');
        stack.enqueue('o');
        stack.enqueue('!');

        while(!stack.isEmpty()){
            System.out.println(stack.dequeue());
        }

        try {
            stack.dequeue();
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
