//usr/bin/env jshell --show-version "$0" "$@"; exit $?

void printArr(int arr[]) {
    Arrays.stream(arr).forEach(v -> System.out.print(String.format("[%d]",v)));
    System.out.println();
}

int[] removeDupes(int arr[]) {
    return Arrays.stream(arr)
        .boxed()
        .collect(Collectors.toSet()).stream()
        .mapToInt(Integer::intValue).toArray();
}

int arr[] = new int[]{9,1,23,10,6,7,5,10,2,4,6,6,4,4,8,9,1,8,9,10,3,4,11,4};


printArr(arr);
int newArr[] = removeDupes(arr);
printArr(newArr);


/exit
