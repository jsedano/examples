import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//
// statement
// Dado un string target y un arreglo
// de strings, define un metodo que
// regrese todos los strings del
// arreglo que contengan anagramas
// del string target.
// ejempl:
// target: cow
// arr: {woc, cwo, cat, dog}
// solution: {woc, cwo}
public class Solution {

    public static List filterAnagram(String target, String arr[]){
        List solution = new LinkedList<>();
        String orderedChars[] = new String[arr.length];
        char t[] = target.toCharArray();
        Arrays.sort(t);
        String orderedTarget = new String(t);
        for(int i =0 ; i< arr.length; i++) {
            char tmp[] = arr[i].toCharArray();
            Arrays.sort(tmp);
            orderedChars[i] = new String(tmp);
        }

        for(int i = 0; i< arr.length; i++){
            if(orderedTarget.equals(orderedChars[i])){
                solution.add(arr[i]);
            }
        }

        return solution;

    }

    public static List filterAnagramV2(String target, String arr[]){
        List solution = new LinkedList<>();
        Map <Character, Integer>targetMap= new HashMap<Character, Integer>();
        for(char t : target.toCharArray()) {
            targetMap.put(t,targetMap.getOrDefault(t, 0) +1);
        }

        for(int i =0 ; i< arr.length; i++) {
            Map <Character, Integer> tmpMap= new HashMap<Character, Integer>();
            for(char t : arr[i].toCharArray()) {
                tmpMap.put(t,tmpMap.getOrDefault(t, 0) +1);
            }
            boolean found = false;
            if(targetMap.size() == tmpMap.size()){
                for(Map.Entry e : targetMap.entrySet()){
                    if(tmpMap.containsKey(e.getKey())) {
                        if(tmpMap.get(e.getKey()) == e.getValue()){
                            found = true;
                        } else {
                            found = false;
                            break;
                        }
                    }
                }
                if(found){
                    solution.add(arr[i]);
                }
            }
        }

        return solution;

    }

    public static void main(String []args) {
        filterAnagram("vaca", new String[]{"acav", "vvac", "ccav", "cava"})
                .stream().forEach(System.out::println);


        System.out.println("---------------");

        filterAnagram("vaca", new String[]{"acav", "vvac", "ccav", "cava"})
                .stream().forEach(System.out::println);
    }




}
