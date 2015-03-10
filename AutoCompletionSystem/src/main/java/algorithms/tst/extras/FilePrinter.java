package algorithms.tst.extras;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class FilePrinter {

    private static final File file = new File("D:\\licenta\\dictionary\\tstoutput.txt");

    public void printTstToFile(File file, String string){
        try {
            if(!file.exists()){
                file.delete();
            }
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(string);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTest(String string){
        try {
            if(!file.exists()){
                file.delete();
            }
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(string);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
