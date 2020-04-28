import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OpenFile {



    public static void main(String args[]) throws IOException {

//        String inputFile = args[0];
//        long threads = Integer.parseInt(args[1]);
//        long totalBuffer = 8L * 1024 * 1024 * 1024;
//
//        ArrayList<String> fileList = new ArrayList<>();
//
//        long bufferSize = totalBuffer/threads;
//        File file = new File(inputFile);
//        Scanner scan = new Scanner(file);
//        long stringSize = 200;
//        long intBufferSize = bufferSize/stringSize;
//        String[] buffer = new String[(int)intBufferSize];
//
//        int i = 0;
//        int file_num = 1;
//
//        //TODO add multithreading
//        for () {
//            while (scan.hasNextLine()) {
//                buffer[i] = scan.nextLine();
//
//                if ((i >= bufferSize - 1) || (!scan.hasNextLine())) {
//                    //mergesort buffer
////                System.out.println(bufferSize);
//
//                    Task task = new Task();
//                    task.setBuffer(buffer);
//                    task.run();
//                    buffer = task.getBuffer();
//                    System.out.println("Thread " + file_num + " started");
//
//                    String filename = "testwrite" + file_num + ".txt";
//                    //write sorted buffer to disk
//                    Writer output = new BufferedWriter(new FileWriter(filename, true));
//
//                    fileList.add(filename);
//                    for (String s : buffer) {
//                        if (s.compareTo("") != 0) {
//                            output.append(s);
//                            ((BufferedWriter) output).newLine();
//                        }
//                    }
//                    output.close();
//
//                    //since we are incrementing i after this if statement we set it to negative 1,
//                    // so it will be 0 the next time it is used
//                    i = -1;
//                    file_num++;
//                    Arrays.fill(buffer, "");
//
//                    //reset i to 0
//
//                }
//
//                i++;
//
//
//            }
//        }
//        System.out.println("Total Number of output files: " + (file_num -1));
//
//        MergeSort ms = new MergeSort();
//        ms.finalMerge(fileList);

    }
}

//class Task extends Thread {
//    private String[] buffer;
//
//    public void run(){
//        MergeSort ms = new MergeSort();
//        buffer = ms.runClass(buffer);
//
//    }
//
//    public String[] getBuffer(){
//        return buffer;
//    }
//
//    public void setBuffer(String[] inputBuffer){
//        buffer = inputBuffer;
//    }
//}
