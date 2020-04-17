import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class MergeSort {


    public static void main(String args[]){

        //open input file
        //    chunk <- read next 4 GB in array
        //    sortedChunk <- mergeSort(chunk)
        //    output sortedChunk on disk

        // k-way merge of files


        String[] array = args;
        System.out.println("Initial Array: ");
        printArray(array);

        array = mergeSort(array);
        System.out.println("Sorted Array: ");
        printArray(array);
    }

    public String[] runClass(String[] args){
        String[] array = args;
//        System.out.println("Initial Array: ");
//        printArray(array);
//
        array = mergeSort(array);
//        System.out.println("Sorted Array: ");
//        printArray(array);
        return array;
    }




    public static void printArray(String[] array){

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

    protected static void finalMerge(ArrayList<String> fileList){

        long totalBufferSize = 8L * 1024 * 1024 * 1024;
        ArrayList<String[]> bufferList = new ArrayList<>();

        // buffer size divided by the list size, divided by the number of bytes per line of input
        long inputFileBufferSize = totalBufferSize/fileList.size()/100;
        System.out.println(inputFileBufferSize);


        for (int i = 0; i < fileList.size(); i++){
            //for each file create a buffer the size of 1/N * totalBufferSize
            String[] buffer = new String[(int)inputFileBufferSize];

            try {

                //fill each buffer with x number of values from respective file
                File file = new File(fileList.get(i));
                Scanner scan = new Scanner(file);

                while(scan.hasNextLine()) {
                    buffer[i] = scan.nextLine();
                }
                bufferList.add(buffer);


                } catch (IOException io){

            }

        }

        //now all files have their initial buffer
        // we will compare their buffer


        //while input files still have contents

            //while outputBuffer is not full
                //get first value in each input file buffer

                //compare all values and add the i lowest one to the buffer[i]

                //get next value from file that just added

            //when output buffer is full, write buffer to storage

            //empty buffer



//        String[] result = new String[left.length + right.length];
//
//        int leftPointer, rightPointer, resultPointer;
//        leftPointer = rightPointer = resultPointer = 0;
//
//        while(leftPointer < left.length || rightPointer < right.length){
//
//            if(leftPointer < left.length && rightPointer < right.length){
//
//                //                if(left[leftPointer] < right[rightPointer]){
//                if(left[leftPointer].compareTo(right[rightPointer]) < 0){
//                    result[resultPointer++] = left[leftPointer++];
//                } else {
//                    result[resultPointer++] = right[rightPointer++];
//                }
//
//            }
//
//            else if(leftPointer < left.length){
//
//                result[resultPointer++] = left[leftPointer++];
//
//            }
//
//            else if (rightPointer < right.length){
//                result[resultPointer++] = right[rightPointer++];
//            }
//
//        }
    }
}











