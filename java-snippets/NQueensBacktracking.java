import java.util.LinkedHashSet;

class LinkedHashSetWrapper {
    private LinkedHashSet<Integer> innerLinkedHashSet;
    private int[] innerArray;

    public LinkedHashSetWrapper(int capacity){
        innerLinkedHashSet = new LinkedHashSet<Integer>();
        innerArray = new int[capacity];
    }

    public void add(Integer e){
        if(innerLinkedHashSet.add(e)){
            innerArray[innerLinkedHashSet.size()-1] = e;
        }
    }

    public int size(){
        return innerLinkedHashSet.size();
    }

    public boolean contains(Integer e){
        return innerLinkedHashSet.contains(e);
    }

    public int[] copyInnerArray(){
        int copy[] = new int[innerLinkedHashSet.size()];
        System.arraycopy(innerArray, 0, copy, 0, innerLinkedHashSet.size());
        return copy;
    }

    public void removeLastElement(){
        innerLinkedHashSet.remove(innerArray[innerLinkedHashSet.size() - 1]);
    }

    public String toString(){
        String r = "{ ";
        for(int i = 0; i < innerLinkedHashSet.size() - 1; i++){
            r += innerArray[i] + " , ";
        }
        return  r + innerArray[innerLinkedHashSet.size() - 1] + " }";
    }
}

public class NQueensBacktracking{

    static void findSolutions(LinkedHashSetWrapper perm, int n){
        if(perm.size() == n) {
            System.out.println(perm);
            return;
        }
        for(int i = 0; i < n; i++){
            if(!perm.contains(i)) {
                perm.add(i);
                if(isSolution(perm.copyInnerArray())){
                    findSolutions(perm, n);
                }
                perm.removeLastElement();
            }
        }
    }

    static boolean isSolution(int []perm){
        for(int i = 0; i < perm.length - 1; i++) {
            for(int j = i + 1; j < perm.length; j++){
                if(Math.abs(i - j) == Math.abs(perm[i] - perm[j])){
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.out.println("you should call this class with the value of n, for example: java NQueensBacktracking 4");
        }

        findSolutions(new LinkedHashSetWrapper(n), n);
    }

}
