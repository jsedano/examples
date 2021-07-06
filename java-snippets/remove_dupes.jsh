//usr/bin/env jshell --show-version "$0" "$@"; exit $?

void printArr(int arr[], int length) {
    for(int i = 0; i < length; i++ ) {
        System.out.print(String.format("[ %d ]", arr[i]));
    }
    System.out.println();
}

int removeDupes(int arr[]) {
    int i = 0;
    for(int j = 1; j < arr.length; j++) {
        if(arr[i] != arr[j]) {
            arr[++i] = arr[j];
        }
    }
    return ++i;
}

int arr[] = new int[]{1,1,2,3,4,4,4,4,4,5,6,6,6,7,8,8,9,9,9,10,10,10,11,23};


printArr(arr, arr.length);
int newLength = removeDupes(arr);
printArr(arr, newLength);


/exit
