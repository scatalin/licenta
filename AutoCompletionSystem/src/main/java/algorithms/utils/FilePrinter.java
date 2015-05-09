package algorithms.utils;

import system.Properties;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class FilePrinter {

    public static final File TST_FILE = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.TST_OUTPUT_FILE_NAME);
    public static final File SEGMENT_FILE = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.SEGMENT_OUTPUT_FILE_NAME);
    public static final File SEGMENT_TREE_FILE = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.SEGMENT_TREE_OUTPUT_FILE_NAME);

    //todo make more clean this

    public static void printTstToFile(File file, String string) {
        try {
            if (!file.exists()) {
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
