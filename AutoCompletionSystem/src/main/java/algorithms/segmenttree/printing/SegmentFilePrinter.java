package algorithms.segmenttree.printing;

import algorithms.utils.FilePrinter;
import input.Properties;

import java.io.File;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentFilePrinter extends FilePrinter {
    protected static File file = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.SEGMENT_OUTPUT_FILE_NAME);
}
