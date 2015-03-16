package algorithms.utils;

import input.Properties;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class FilePrinter {

    public static File TST_FILE = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.TST_OUTPUT_FILE_NAME);
    public static File SEGMENT_FILE = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.SEGMENT_OUTPUT_FILE_NAME);
    public static File SEGMENT_TREE_FILE = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.SEGMENT_TREE_OUTPUT_FILE_NAME);

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
