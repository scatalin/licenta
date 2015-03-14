package algorithms.tst.printing;

import algorithms.utils.FilePrinter;
import input.Properties;

import java.io.File;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class TstFilePrinter extends FilePrinter {
    public static File file = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.TST_OUTPUT_FILE_NAME);
}
