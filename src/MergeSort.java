class MergeSort {


    public static void main(String args[]){

        //open input file
        //    chunk <- read next 4 GB in array
        //    sortedChunk <- mergeSort(chunk)
        //    output sortedChunk on disk

        // k-way merge of files


        String[] array = {"Scoop", "Carla", "Shrimp", "Monkeys", "Castaway"};
        System.out.println("Initial Array: ");
        printArray(array);

        array = mergeSort(array);
        System.out.println("Sorted Array: ");
        printArray(array);
    }




    private static void printArray(String[] array){

        for (String s: array){
            System.out.print(s + " ");
        }
        System.out.println();

    }

    private static String[] mergeSort(String[] array) {
        if (array.length <= 1) {
            return array;
        }

        int midpoint = array.length / 2;
        String[] left = new String[midpoint];
        String[] right;

        if (array.length % 2 == 0) {
            right = new String[midpoint];
        } else {
            right = new String[midpoint + 1];
        }

        for (int i = 0; i < midpoint; i++) {
            left[i] = array[i];
        }

        for (int j = 0; j < right.length; j++) {

            right[j] = array[midpoint + j];

        }

        String[] result = new String[array.length];

        left = mergeSort(left);
        right = mergeSort(right);

        result = merge(left, right);

        return result;

    }

    private static String[] merge(String[] left, String[] right){

        String[] result = new String[left.length + right.length];

        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        while(leftPointer < left.length || rightPointer < right.length){

            if(leftPointer < left.length && rightPointer < right.length){

                //                if(left[leftPointer] < right[rightPointer]){
                if(left[leftPointer].compareTo(right[rightPointer]) < 0){
                    result[resultPointer++] = left[leftPointer++];
                } else {
                    result[resultPointer++] = right[rightPointer++];
                }

            }

            else if(leftPointer < left.length){

                result[resultPointer++] = left[leftPointer++];

            }

            else if (rightPointer < right.length){
                result[resultPointer++] = right[rightPointer++];
            }

        }


        return result;

    }


}











