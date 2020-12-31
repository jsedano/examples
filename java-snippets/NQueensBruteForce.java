import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;


public class NQueensBruteForce{

    static String toString(int[] arr){
        String r = "{ ";
        for(int i = 0; i < arr.length - 1; i++){
            r += arr[i] + " , ";
        }
        return  r + arr[arr.length - 1] + " }";
    }

    static void generatePermutations(List <int[]>permList, LinkedHashSet<Integer> perm, int n){
        if(perm.size() == n) {
            //not so pretty way to turn LinkedHashSet into array
            permList.add(perm.stream().mapToInt(Integer::intValue).toArray());
            return;
        }
        for(int i = 0; i < n; i++){
            if(!perm.contains(i)) {
                perm.add(i);
                generatePermutations(permList, perm, n);
                Iterator<Integer> k = perm.iterator();
                for(; k.hasNext(); k.next());//iterate to last element
                k.remove();//remove it
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
            System.out.println("you should call this class with the value of n, for example: java NQueensBruteForce 4");
        }

        var permutations = new LinkedList<int[]>(); // permutations will be saved here

        generatePermutations(permutations, new LinkedHashSet<Integer>(), n);

        for(int []arr : permutations) {
            if(isSolution(arr)) {
                System.out.println(toString(arr));
            }
        }
    }

}
