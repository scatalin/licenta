package input;

import model.dictionary.Dictionary;

import java.io.*;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class InputFilesProcessor {

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private String inputDirectory;
    private String processedDirectory;
    private File inputDir;
    private File processedDir;

    private Dictionary dictionary;


    public InputFilesProcessor(Dictionary dictionary) {
        this.dictionary = dictionary;
        inputDirectory = Properties.INPUT_FILES_DIRECTORY;
        processedDirectory = Properties.PROCESSED_FILES_DIRECTORY;
        inputDir = new File(inputDirectory);
        if (!inputDir.exists() && !inputDir.isDirectory()) {
            System.out.println("input files directory does not exist: " + inputDir + ";");
        }
        processedDir = new File(processedDirectory);
        if (!processedDir.exists() && !processedDir.isDirectory()) {
            System.out.println("processed files directory does not exist: " + processedDir + ";");
        }
    }

    public void processInputFiles() {
        dictionary.readDictionary();
        String[] listFileNames = inputDir.list();
        for (String fileName : listFileNames) {
            System.out.println(fileName);
            File file = new File(inputDir + Properties.SYSTEM_PATH_SEPARATOR + fileName);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    String[] words = line.split(WORD_SEPARATION_REGEX);
                    for (String word : words) {
                        dictionary.addWord(word);
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dictionary.sortDictionaryAlphabetically();
        dictionary.removeNonWords();
        dictionary.saveDictionary();
    }


    // read all input files and hand over to delegates, word processor and phrase processor

}

