public class DynamicArray<T> {

    private int size;
    private T[] innerArray;

    public DynamicArray() {
        innerArray = (T[]) new Object[2];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
         if (size == innerArray.length) {
             T newArray[] =  (T[]) new Object[innerArray.length * 2];
             System.arraycopy(innerArray, 0, newArray, 0, innerArray.length);
             innerArray = newArray;
         }
         innerArray[size] = value;
         size++;
    }

    public void addAtIndex(T value, int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == innerArray.length) {
            T newArray[] =  (T[]) new Object[innerArray.length * 2];
            System.arraycopy(innerArray, 0, newArray, 0, innerArray.length);
            innerArray = newArray;
        }

        for(int i = size - 1; i >= index; i--) {
            innerArray[i + 1] = innerArray[i];
        }
        innerArray[index] = value;
        size++;
    }

    public T get(int index){
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return innerArray[index];
    }

    public void put(T value, int index){
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        innerArray[index] = value;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for(int i = index + 1; i <= size; i++) {
            innerArray[i - 1] = innerArray[i];
        }
        size--;
    }

    public String toString() {
        String s = "Array -> ";
        for(int i = 0; i < size; i++) {
            s += "["+ innerArray[i] +"]";
        }
        return s;
    }

    public static void main(String []args) {
        DynamicArray dynamicArray = new DynamicArray<Character>();

        dynamicArray.add('r');
        dynamicArray.add('a');
        dynamicArray.add('d');
        dynamicArray.add('i');
        dynamicArray.add('o');

        System.out.println(dynamicArray);

        dynamicArray.remove(0);
        dynamicArray.remove(3);
        dynamicArray.remove(1);

        System.out.println(dynamicArray);
        dynamicArray.addAtIndex('r', 0);
        dynamicArray.addAtIndex('d', 2);
        dynamicArray.add('o');
        System.out.println(dynamicArray);

        dynamicArray.put('R', 0);
        System.out.println(dynamicArray);

        while(!dynamicArray.isEmpty()) {
            dynamicArray.remove(0);
        }
        System.out.println(dynamicArray);

    }
}
