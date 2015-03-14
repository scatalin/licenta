package algorithms.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class FilePrinter {

    public static void printTstToFile(File file, String string){
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
