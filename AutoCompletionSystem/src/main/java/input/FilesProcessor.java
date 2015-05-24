package input;

import dictionary.Dictionary;
import dictionary.filters.CharacterFilter;
import dictionary.filters.RomanianCharacterFilter;
import system.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class FilesProcessor {

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private final File inputDir;

    private final DictionaryProcessor dictionary;

    private final CharacterFilter filter;

    public FilesProcessor(DictionaryProcessor dictionary) {
        this(dictionary, Properties.INPUT_FILES_DIRECTORY);
    }

    public FilesProcessor(DictionaryProcessor dictionary, String inputDirectory) {
        this.dictionary = dictionary;
        filter = new RomanianCharacterFilter();
        inputDir = new File(inputDirectory);
        if (!inputDir.exists() && !inputDir.isDirectory()) {
            System.out.println("input files directory does not exist: " + inputDir + ";");
        }
    }

    public int getNumberOfFiles() {
        File[] listFileNames = inputDir.listFiles();
        return (listFileNames != null) ? listFileNames.length : 0;
    }

    public void processInputFiles(){
        processInputFiles(-1,Properties.DIACRITICS);
    }

    public void processInputFiles(int skip){
        processInputFiles(skip,Properties.DIACRITICS);
    }

    public void processInputFiles(int skip, boolean filt) {
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

            System.out.println("reading and process file " + file.getName());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                while (line != null) {
                    String[] words = line.split(WORD_SEPARATION_REGEX);
                    for (String word : words) {
                        dictionary.getDictionary().addDictionaryWord(filter.filterCharacters(word.toLowerCase(), filt));
                    }
                    line = reader.readLine();
                }
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dictionary.saveDictionary();
    }

    public List<Dictionary> createDictionariesFromFiles() {
        return createDictionariesFromFiles(Properties.DIACRITICS);
    }

    public List<Dictionary> createDictionariesFromFiles(boolean filt) {
        List<Dictionary> dictionaries = new ArrayList<>();

        File[] listFileNames = inputDir.listFiles();

        if (listFileNames == null) {
            System.out.println("an exception occurred while reading files from " + inputDir);
            return Collections.emptyList();
        }

        //todo read all input files and hand over to delegates, word processor and phrase processor
        for (File file : listFileNames) {
            Dictionary dictionary = new Dictionary(file.getName());

            System.out.println("read and process file " + file.getName());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                while (line != null) {
                    String[] words = line.split(WORD_SEPARATION_REGEX);
                    for (String word : words) {
                        dictionary.addDictionaryWord(filter.filterCharacters(word.toLowerCase(), filt));
                    }
                    line = reader.readLine();
                }

                reader.close();

                dictionaries.add(dictionary);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dictionaries;
    }
}

