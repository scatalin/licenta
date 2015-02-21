package input;

import java.io.File;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class InputFilesProcessor {

    private String inputDirectory;
    private String processedDirectory;
    private File inputDir;
    private File processedDir;


    public InputFilesProcessor() {
        inputDirectory = Properties.INPUT_FILES_DIRECTORY;
        processedDirectory = Properties.PROCESSED_FILES_DIRECTORY;
        inputDir = new File(inputDirectory);
        if (!inputDir.exists() && !inputDir.isDirectory()) {
            System.out.println("input files directory does not exist");
        }
        processedDir = new File(processedDirectory);
        if (!processedDir.exists() && !processedDir.isDirectory()) {
            System.out.println("processed files directory does not exist");
        }
    }

    public void processInputFiles() {
        String[] list = inputDir.list();
        for (String s : list){
            System.out.println(s);
        }
    }


    // read all input files and hand over to delegates, word processor and phrase processor

}
