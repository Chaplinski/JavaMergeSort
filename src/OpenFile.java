import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OpenFile {



    public static void main(String args[]) throws FileNotFoundException, IOException {


        ArrayList<String> fileList = new ArrayList<>();

        int bufferSize = 20;
        File file = new File("testfile2.txt");
        Scanner scan = new Scanner(file);
        String[] buffer = new String[bufferSize];


        String fileContent = "";
        int i = 0;
        int file_num = 1;


        //TODO add multithreading
        while(scan.hasNextLine()){
            buffer[i]= scan.nextLine();

            if((i >= bufferSize - 1) || (!scan.hasNextLine())){
                //mergesort buffer
//                System.out.println(bufferSize);

                MergeSort ms = new MergeSort();
                buffer = ms.runClass(buffer);

                String filename = "testwrite" + file_num + ".txt";
                //write sorted buffer to disk
                Writer output = new BufferedWriter(new FileWriter(filename, true));

                fileList.add(filename);
                for(String s: buffer){
                    if(s.compareTo("") != 0) {
                        output.append(s);
                        ((BufferedWriter) output).newLine();
                    }
                }
                output.close();

                //since we are incrementing i after this if statement we set it to negative 1,
                // so it will be 0 the next time it is used
                i = -1;
                file_num++;
                Arrays.fill(buffer, "");

                //reset i to 0

            }

            i++;


        }
        System.out.println("Total Number of output files: " + (file_num -1));

        // TODO implemented k-way external sort using one thread
        MergeSort ms = new MergeSort();
        ms.finalMerge(fileList);

    }
}
