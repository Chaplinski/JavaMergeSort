import java.io.*;
import java.util.Scanner;

public class OpenFile {

    public static void main(String args[]) throws FileNotFoundException, IOException {

        int bufferSize = 40000000;
        File file = new File("testfile.txt");
        Scanner scan = new Scanner(file);
        String[] buffer = new String[bufferSize];

        String fileContent = "";
        int i = 0;
        while(scan.hasNextLine()){
            buffer[i]= scan.nextLine();
            //fileContent = fileContent.concat(scan.nextLine() + "\n");
            if(i >= bufferSize){
                //mergesort buffer

                //write sorted buffer to disk

                //reset i to 0
                break;
            }

            i++;


        }
        System.out.println(buffer.length);
        System.out.println(buffer[3]);

//        FileWriter writer = new FileWriter("nexfile.txt");
//        writer.write(fileContent);


    }
}
