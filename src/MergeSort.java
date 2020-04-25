import java.io.*;
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

        long totalBufferSize = 4 * 1024;
        long fileSize = 6 * 1024 ;
        ArrayList<String[]> bufferList = new ArrayList<>();
        ArrayList<Integer> pointerList = new ArrayList<>();
        ArrayList<Integer> totalIterationsList = new ArrayList<>();
        ArrayList<Integer> totalStringsPerFileList = new ArrayList<>();


        // fileSize = N/2
        // numFiles = M / fileSize = M / (N/2)
        // sbs = N / (numFiles + 1) = N / ((M / (N/2)) + 1 )

        // buffer size divided by the list size, divided by the number of bytes per line of input
        //long outputFileBufferSize = totalBufferSize/100;

        long chunkSize = totalBufferSize / fileList.size();
        long numChunks = fileSize / chunkSize;
        long inputFileBufferSize = totalBufferSize / (numChunks + 1);//outputFileBufferSize/fileList.size();
        long stringSize = 200;
        long inputFileBufferStringCount = inputFileBufferSize / stringSize;
        String[] outputBuffer = new String[(int)inputFileBufferStringCount];


        for (int i = 0; i < fileList.size(); i++){
            //TODO find number of strings in each each file and store in a list
            String filename = fileList.get(i);
            long longNumStrings = new File(filename).length()/100;
            int numStrings = (int)longNumStrings;
            totalStringsPerFileList.add(numStrings);

            //for each file create a buffer the size of 1/N * totalBufferSize
            String[] buffer = new String[(int)inputFileBufferStringCount];

            try {

                //fill each buffer with x number of values from respective file
                File file = new File(fileList.get(i));
                Scanner scan = new Scanner(file);

                int j =0;
                while(scan.hasNextLine()) {
                    buffer[j] = scan.nextLine();
                    j++;
                    if (j == inputFileBufferStringCount){
                        break;
                    }
                }
                bufferList.add(buffer);
                pointerList.add(0);
                totalIterationsList.add(1);


            } catch (IOException io){

            }
        }

        // CHECK HERE THE MEMORY CONSUMPTION
        // MUST BE 1.5G

        //TODO get total number of strings in all files
        // and then iterate that many times

        int totalLines = 0;
        for(int i = 0; i < totalStringsPerFileList.size(); i++) {
            totalLines += totalStringsPerFileList.get(i);
        }

        int totalIterations = totalLines/(outputBuffer.length);
        int linesRead = 0;
        while(linesRead < totalLines) {

            //now all files have their initial buffer
            // we will compare their buffer
            String[] temp = new String[fileList.size()];

            //for each buffer, get value chosen by pointer
            for (int i = 0; i < fileList.size(); i++) {

                String[] buffer = bufferList.get(i);
                String fileLine = "■■■■■■■■";

                //TODO pointerList[0] is incrementing to 3, which gives a NullPointerException
                if(pointerList.get(i) <= inputFileBufferStringCount -1) {
                    fileLine = buffer[pointerList.get(i)];
                } else {
                    //TODO refill buffer
                    try {
                        File file = new File(fileList.get(i));
                        Scanner scan = new Scanner(file);
                        int previousIterations = totalIterationsList.get(i);
                        int startingLine = (int)inputFileBufferStringCount * previousIterations;
                        int finishLine = startingLine + (int)inputFileBufferStringCount - 1;
                        int stringsInThisFile = totalStringsPerFileList.get(i);

                        int j = 0;
                        int k = 0;

                        //TODO if file has been iterated over but file's buffer has not been refilled then fill rest of buffer with all block string
                        while (scan.hasNextLine()) {

                            //TODO when file is out of lines then no longer refill buffer.
                            if((j >= startingLine) && (j <= finishLine)) {
                                buffer[k] = scan.nextLine();
                                k++;
                            } else {
                                scan.nextLine();
                            }

                            if (k >= inputFileBufferStringCount) {
                                //get first element in new buffer
//                                fileLine = buffer[0];
                                //reset pointer for this buffer
                                pointerList.set(i, 0);
                                break;
                            }
                            j++;
                        }
                        //TODO while k is less than bufferSize
                        //handles filling rest of buffer at end of files where buffer does not fill completely
                        while(k < inputFileBufferStringCount){
                            fileLine = buffer[0];
                            pointerList.set(i, 0);

                            buffer[k] = "■■■■■■";
                            k++;
                        }
                        totalIterationsList.set(i, previousIterations + 1);
                    } catch (IOException io){

                    }


                }


                temp[i] = fileLine;

            }

            String first = "■■■■■■";

            int indexOfFirst = -2;

            //TODO on iteration 47/48 indexOfFirst is not getting set again in the for loop
            //compare all elements in temp array
            for (int i = 0; i < temp.length; i++) {

                if (first.compareTo(temp[i]) > 0) {
                    first = temp[i];
                    indexOfFirst = i;
                }

            }

            int bufferIndex = linesRead % outputBuffer.length;
            outputBuffer[bufferIndex] = first;

            if((((linesRead + 1)  % outputBuffer.length) == 0) || (linesRead == totalLines - 1)){
                long longFinalBufferOutputIterations = totalLines - (totalIterations * inputFileBufferStringCount);
                int maxIterations = (int)longFinalBufferOutputIterations;

                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter("finaloutput.txt", true));
                    int i =0;
                    for (String s : outputBuffer) {
                        if ((s.compareTo("") != 0)) {
                            output.append(s);
                            ((BufferedWriter) output).newLine();
                            if((linesRead == totalLines - 1) && (i >= maxIterations - 1)){
                                break;

                            }
                        }
                        i++;
                    }
                    //TODO clear outputBuffer?
                    output.close();
                } catch (IOException io) {

                }
            }

            if (linesRead == 47){
                System.out.println(linesRead);
            }

            System.out.println(linesRead);
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











