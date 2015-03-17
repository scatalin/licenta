package input;

import model.dictionary.Dictionary;
import model.dictionary.Word;

import java.io.*;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class InputFilesProcessor {

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private File inputDir;
    private File processedDir;

    private File dictionaryFile;

    private Dictionary dictionary;


    public InputFilesProcessor(Dictionary dictionary) {
        this.dictionary = dictionary;
        String inputDirectory = Properties.INPUT_FILES_DIRECTORY;
        String processedDirectory = Properties.PROCESSED_FILES_DIRECTORY;

        inputDir = new File(inputDirectory);
        if (!inputDir.exists() && !inputDir.isDirectory()) {
            System.out.println("input files directory does not exist: " + inputDir + ";");
        }

        processedDir = new File(processedDirectory);
        if (!processedDir.exists() && !processedDir.isDirectory()) {
            System.out.println("processed files directory does not exist: " + processedDir + ";");
        }

        String dictDirectory = Properties.DICTIONARY_DIRECTORY;
        String fileName = Properties.DICTIONARY_FILE_NAME;

        File directory = new File(dictDirectory);
        if (!directory.exists() && !directory.isDirectory()) {
            System.out.println("dictionary directory does not exist: " + directory + ";");
        }

        dictionaryFile = new File(dictDirectory + Properties.SYSTEM_PATH_SEPARATOR + fileName);
        if (!dictionaryFile.exists() && !dictionaryFile.isFile()) {
            System.out.println("dictionary file does not exist " + dictionaryFile + ";");
        }
    }

    public void processInputFiles() {
        readDictionary();
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
        saveDictionary();
    }

    public void readDictionary() {
        try {
            dictionary.clear();
            BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile));
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split("=");
                dictionary.add(new Word(tokens[0], Integer.parseInt(tokens[1])));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDictionary() {
        try {
            dictionaryFile.delete();
            dictionaryFile.createNewFile();
            PrintWriter writer = new PrintWriter(dictionaryFile);
            for (Word word : dictionary.getWords()) {
                writer.println(word);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read all input files and hand over to delegates, word processor and phrase processor

}

