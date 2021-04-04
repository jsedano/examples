public class Stack<T> {

    private T[] innerArray;
    private int size;

    public Stack(int capacity) {
        innerArray = (T[]) new Object[capacity];
        size = 0;
    }

    /**
    * returns the top element of the stack and removes it from the stack
    * - if stack is empty we raise an exception
    * - take out the element and return it
    * - decrease size
    * O(1)
    **/
    public T pop() {
        if(size == 0) {
            throw new NullPointerException("empty stack");
        }
        T valueToPop = innerArray[size - 1];
        innerArray[size - 1] = null;
        size--;
        return valueToPop;
    }

    /**
    * pushes a new element on the top of the stack
    * - if stack is full we dont do anything and return false
    * - if we still have capacity we put the new element and return true
    * - increase size (if push was successful)
    * O(1)
    **/
    public boolean push(T valueToPush) {
        if(size == innerArray.length) {
            return false;
        }
        innerArray[size] = valueToPush;
        size++;
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String []args){
        Stack<Character> stack = new Stack<Character>(5);
        stack.push('o');
        stack.push('l');
        stack.push('l');
        stack.push('e');
        stack.push('h');
        stack.push('!');

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        try {
            stack.pop();
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
