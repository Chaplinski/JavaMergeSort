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

        long totalBufferSize = 3L * 1024 * 1024 * 1024;
        ArrayList<String[]> bufferList = new ArrayList<>();
        ArrayList<Integer> pointerList = new ArrayList<>();
        ArrayList<Integer> totalIterationsList = new ArrayList<>();



        // buffer size divided by the list size, divided by the number of bytes per line of input
        long outputFileBufferSize = totalBufferSize/100;
        long inputFileBufferSize = outputFileBufferSize/fileList.size();
        String[] outputBuffer = new String[(int)outputFileBufferSize];

        System.out.println(inputFileBufferSize);


        for (int i = 0; i < fileList.size(); i++){
            //for each file create a buffer the size of 1/N * totalBufferSize
            String[] buffer = new String[(int)inputFileBufferSize];

            try {

                //fill each buffer with x number of values from respective file
                File file = new File(fileList.get(i));
                Scanner scan = new Scanner(file);

                int j =0;
                while(scan.hasNextLine()) {
                    buffer[j] = scan.nextLine();
                    j++;
                    if (j == inputFileBufferSize - 1){
                        break;
                    }
                }
                bufferList.add(buffer);
                pointerList.add(0);
                totalIterationsList.add(1);


                } catch (IOException io){

            }


        }

        //TODO get total number of strings in all files
        // and then iterate that many times

        int totalLines = 10000;
        int linesRead = 0;
        while(linesRead < totalLines) {

            //now all files have their initial buffer
            // we will compare their buffer
            String[] temp = new String[fileList.size()];

            //for each buffer, get value chosen by pointer
            for (int i = 0; i < fileList.size(); i++) {

                String[] buffer = bufferList.get(i);
                String fileLine = null;
                if(!buffer[pointerList.get(i)].isEmpty()) {
                    fileLine = buffer[pointerList.get(i)];
                } else {
                    //TODO refill buffer
                    try {
                        File file = new File(fileList.get(i));
                        Scanner scan = new Scanner(file);
                        int previousIterations = totalIterationsList.get(i);
                        int startingLine = (int)inputFileBufferSize * previousIterations;
                        int finishLine = startingLine + (int)inputFileBufferSize - 1;

                        int j = 0;
                        int k = 0;
                        while (scan.hasNextLine()) {
                            if((j > startingLine) && (j < finishLine)) {
                                buffer[k] = scan.nextLine();
                                if (k == inputFileBufferSize - 1) {
                                    break;
                                }
                                k++;
                            }
                            j++;
                        }
                    } catch (IOException io){

                    }


                }


                temp[i] = fileLine;

            }

            String first = "zzzzzz";
            int indexOfFirst = -1;

            //compare all elements in temp array
            for (int i = 0; i < temp.length; i++) {

                if (first.compareTo(temp[i]) > 0) {
                    first = temp[i];
                    indexOfFirst = i;
                }

            }

            int pointer = pointerList.get(indexOfFirst);
            pointer++;
            pointerList.set(indexOfFirst, pointer);

            linesRead++;

        }
//        outputBuffer[0] = first;
//
//        System.out.println(first);

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











