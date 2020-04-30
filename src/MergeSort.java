import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class MergeSort {


    public static void main(String args[]){

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

    protected static void finalMerge(ArrayList<String> fileList)throws IOException{

        ArrayList<String[]> bufferList = new ArrayList<>();
        int numberOfFiles = fileList.size();
        int[] pointers = new int[numberOfFiles];
        int[] stringCountPerFile = new int[numberOfFiles];
        long sizeOfAllFiles = 0;
        for(int i=0; i < fileList.size(); i++){
            // get file
            File file = new File(fileList.get(i));

            //get number of strings in file
            long fileByteSize = file.length();
            long stringsInFile = fileByteSize/100;
            stringCountPerFile[i] = (int)stringsInFile;

            //add size of this file to running total
            sizeOfAllFiles += fileByteSize;

            //set pointer to 0 for this file's buffer
            pointers[i] = 0;

            String[] buffer = new String[(int)stringsInFile + 1];
            Scanner scan = new Scanner(file);

            //fill buffer
            int j = 0;
            while(scan.hasNextLine()){
                buffer[j] = scan.nextLine();
                j++;
            }
            buffer[(int)stringsInFile] = "■■■■■■■■";

            bufferList.add(buffer);

        }

        final long inputFileStringCount = sizeOfAllFiles/100;
        int currentIteration = 0;
        String[] outputBuffer = new String[(int)inputFileStringCount];

        while(currentIteration < inputFileStringCount) {
            String[] temp = new String[numberOfFiles];

            for (int i = 0; i < numberOfFiles; i++) {
                String[] thisBuffer = bufferList.get(i);
                String nextLine = thisBuffer[pointers[i]];
                temp[i] = nextLine;

            }

            String first = "■■■■■■";
            int indexOfFirst = 0;

            for (int i = 0; i < temp.length; i++) {
                if (first.compareTo(temp[i]) > 0) {
                    first = temp[i];
                    indexOfFirst = i;
                }
            }

            outputBuffer[currentIteration] = temp[indexOfFirst];
            pointers[indexOfFirst]++;

            currentIteration++;
        }

        int gigs = (int)sizeOfAllFiles/1000000000;
        BufferedWriter output = new BufferedWriter(new FileWriter("outputThreads" + numberOfFiles + "size" + gigs + ".txt", true));
        for(int i = 0; i < outputBuffer.length; i++ ){
            output.append(outputBuffer[i]);
            output.newLine();
        }
        output.close();

    }
}



