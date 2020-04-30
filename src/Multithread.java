import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Multithread {

    public static void main(String args[]) throws IOException {

        //get input file and thread count
        String inputFile = args[0];
        long totalThreadCount = Integer.parseInt(args[1]);

        ArrayList<String> fileList = new ArrayList<>();


        //get size of inputFile
        File file = new File(inputFile);
        long fileByteSize = file.length();

        //get total number of strings in inputFile
        long totalStringCount = fileByteSize/100;

        //get number of strings that will be in each individuals threads String[] buffer
//        long stringsPerThread = totalStringCount/totalThreadCount;
//
//        long stringRemainder = totalStringCount % totalThreadCount;
        //create and use Scanner object
        Scanner scan = new Scanner(file);

        long stringRemainder = totalStringCount % totalThreadCount;
        long stringsPerThread = totalStringCount/totalThreadCount;

        for (int i = 0; i < totalThreadCount; i++) {
            long stringsThisThread = stringsPerThread;


            //TODO Why is file 3 getting 13 lines and file 4 only getting 11?
            //for each thread
            if(stringRemainder >= i){
                stringsThisThread = stringsThisThread + 1;
                stringRemainder--;
            }

            int j = 0;
            int file_num = 1;
            //create empty String[] buffer
            String[] buffer = new String[(int) stringsThisThread];

            while (scan.hasNextLine()) {
                buffer[j] = scan.nextLine();

                if ((j >= stringsThisThread - 1) || (!scan.hasNextLine())) {
                    //mergesort buffer

                    Task task = new Task();
                    task.setBuffer(buffer);
                    task.run();
                    buffer = task.getBuffer();

                    int gigs = (int)fileByteSize/1000000000;
                    String filename = "t" + totalThreadCount + "f" + file_num + "s" + gigs + ".txt";
                    //write sorted buffer to disk
                    Writer output = new BufferedWriter(new FileWriter(filename, true));

                    fileList.add(filename);
                    for (String s : buffer) {
                        if (s.compareTo("") != 0) {
                            output.append(s);
                            ((BufferedWriter) output).newLine();
                        }
                    }
                    output.close();

                    //since we are incrementing i after this if statement we set it to negative 1,
                    // so it will be 0 the next time it is used
                    j = -1;
                    file_num++;
                    Arrays.fill(buffer, "");

                    //reset i to 0

                }

                j++;
            }
        }
        MergeSort ms = new MergeSort();
        ms.finalMerge(fileList);
    }

}

class Task extends Thread {
    private String[] buffer;

    public void run() {
        MergeSort ms = new MergeSort();
        buffer = ms.runClass(buffer);

    }

    public String[] getBuffer() {
        return buffer;
    }

    public void setBuffer(String[] inputBuffer) {
        buffer = inputBuffer;
    }
}

