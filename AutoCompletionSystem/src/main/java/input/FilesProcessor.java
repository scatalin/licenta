package input;

import model.dictionary.Dictionary;
import system.Properties;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class FilesProcessor {

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private final File inputDir;
    private final String processedDirectory;

    private final DictionaryProcessor dictionary;

    private final FileManager manager;

    public FilesProcessor(DictionaryProcessor dictionary) {
        this(dictionary, Properties.INPUT_FILES_DIRECTORY, Properties.PROCESSED_FILES_DIRECTORY);
    }

    public FilesProcessor(DictionaryProcessor dictionary, String inputDirectory) {
        this(dictionary, inputDirectory, Properties.PROCESSED_FILES_DIRECTORY);
    }

    public FilesProcessor(DictionaryProcessor dictionary, String inputDirectory, String processedDirectory) {
        this.dictionary = dictionary;
        this.processedDirectory = processedDirectory;

        inputDir = new File(inputDirectory);
        if (!inputDir.exists() && !inputDir.isDirectory()) {
            System.out.println("input files directory does not exist: " + inputDir + ";");
        }

        File processedDir = new File(this.processedDirectory);
        if (!processedDir.exists() && !processedDir.isDirectory()) {
            System.out.println("processed files directory does not exist: " + processedDir + ";");
        }

        manager = new FileManager();
    }

    public int getNumberOfFiles() {
        File[] listFileNames = inputDir.listFiles();
        return (listFileNames != null) ? listFileNames.length : 0;
    }

    public void processInputFiles(boolean move, int skip) {
        dictionary.readDictionary();
        File[] listFileNames = inputDir.listFiles();

        if (listFileNames == null) {
            System.out.println("an exception occurred while reading files from " + inputDir);
            return;
        }
        //todo read all input files and hand over to delegates, word processor and phrase processor
        for (int i = 0; i < listFileNames.length; i++) {
            File file = listFileNames[i];

            if (i == skip) {
                continue;
            }

            System.out.println("processing file " + file.getName());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                while (line != null) {
                    String[] words = line.split(WORD_SEPARATION_REGEX);
                    for (String word : words) {
                        if (word.length() > 1) {
                            dictionary.getDictionary().addWord(word.toLowerCase());
                        }
                    }
                    line = reader.readLine();
                }

                reader.close();
                if (move) {
                    manager.moveFile(file, processedDirectory);
                    System.out.println("file " + file.getName() + " was moved to processed directory");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dictionary.getDictionary().removeNonWords();
        dictionary.getDictionary().sortDictionaryAlphabetically();
        dictionary.saveDictionary();
    }

    public List<Dictionary> createDictionariesFromFiles() {
        List<Dictionary> dictionaries = new ArrayList<>();

        File[] listFileNames = inputDir.listFiles();

        if (listFileNames == null) {
            System.out.println("an exception occurred while reading files from " + inputDir);
            return Collections.emptyList();
        }

        //todo read all input files and hand over to delegates, word processor and phrase processor
        for (File file : listFileNames) {
            Dictionary dictionary = new Dictionary(file.getName());

            System.out.println("processing file " + file.getName());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                while (line != null) {
                    String[] words = line.split(WORD_SEPARATION_REGEX);
                    for (String word : words) {
                        if (word.length() > 2) {
                            dictionary.addWord(word.toLowerCase());
                        }
                    }
                    line = reader.readLine();
                }

                reader.close();

                dictionary.removeNonWords();
                dictionaries.add(dictionary);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dictionaries;
    }
}

