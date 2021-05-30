public class BinarySearchTree<T extends Comparable<? super T>>  {

    private BinaryNode<T> rootNode;
    private int length;

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean insert(T data) {
        if(rootNode == null) {
            rootNode = new BinaryNode<>(data);
            length++;
            return true;
        }
        return insert(new BinaryNode<T>(data), rootNode);
    }

    private boolean insert(BinaryNode<T> newNode, BinaryNode<T> node) {
        if(newNode.getData().compareTo(node.getData()) < 0) {
            if(node.getLeftChild() == null) {
                node.setLeftChild(newNode);
                length ++;
                return true;
            }
            return insert(newNode, node.getLeftChild());
        }

        if(newNode.getData().compareTo(node.getData()) > 0) {
            if(node.getRightChild() == null) {
                node.setRightChild(newNode);
                length ++;
                return true;
            }
            return insert(newNode, node.getRightChild());
        }

        return false;
    }

    public boolean contains(T data){
        return contains(data, rootNode);
    }

    private boolean contains(T data, BinaryNode<T> node) {
        if(node == null) {
            return false;
        }

        if(data.compareTo(node.getData()) < 0) {
            return contains(data, node.getLeftChild());
        }

        if(data.compareTo(node.getData()) > 0) {
            return contains(data, node.getRightChild());
        }

        return true;
    }

    public boolean remove(T data) {
        int originalLength = length;
        rootNode = remove(data, rootNode);
        return originalLength != length;
    }

    private BinaryNode<T> remove(T data, BinaryNode<T> node) {
        if(node == null) {
            return null;
        }

        if(data.compareTo(node.getData()) < 0) {
            node.setLeftChild(remove(data, node.getLeftChild()));

        } else if(data.compareTo(node.getData()) > 0) {
            node.setRightChild(remove(data, node.getRightChild()));

        } else {
            if(node.getLeftChild() == null) {
                BinaryNode<T> rightChild = node.getRightChild();
                node.setData(null);
                node = null;
                length--;
                return rightChild;
            }
            if(node.getRightChild() == null) {
                BinaryNode<T> leftChild = node.getLeftChild();
                node.setData(null);
                node = null;
                length--;
                return leftChild;
            }
            BinaryNode<T> tmp = getLeftmost(node.getRightChild());
            node.setData(tmp.getData());
            node.setRightChild(remove(tmp.getData(), node.getRightChild()));
        }
        return node;
    }

    private BinaryNode<T> getLeftmost(BinaryNode<T> node) {
        while(node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    public void print() {
        System.out.println("calling print");
        print(rootNode);
        System.out.println("called print");

    }

    private void print(BinaryNode<T> node){
        if(node == null) {
            return;
        }
        print(node.getLeftChild());
        System.out.print(node.getData() + " ");
        print(node.getRightChild());
    }


    public static void main(String args[]) {
        java.util.Random r = new java.util.Random();
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        int length = 1000;

        System.out.println("attempting to randomly fill bst with 0 to " + length);

        while(bst.getLength() != length) {
            int integerBetween0andLenght = r.nextInt(length);
            if(bst.insert(integerBetween0andLenght) && !bst.contains(integerBetween0andLenght)) {
                System.out.println("insertion returned true but int is not on bst");
                System.exit(-1);
            }
        }

        System.out.println("bst filled");

        System.out.println("attempting to randomly empty bst");

        while(!bst.isEmpty()) {
            int integerBetween0andLenght = r.nextInt(length);
            if(bst.remove(integerBetween0andLenght) && bst.contains(integerBetween0andLenght)) {
                System.out.println("remove returned true but int is still on bst");
                System.exit(-1);
            }
        }

        System.out.println("emptied bst");

    }
}
