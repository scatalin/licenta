package input;

import dictionary.filters.CharacterFilter;
import dictionary.filters.RomanianCharacterFilter;
import system.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/19/2015 .
 */
public class StopWordsProcessor {
    private final File stopWordsFile;
    private final CharacterFilter filter;

    public StopWordsProcessor() {
        this(Properties.STOP_WORDS_FILE, Properties.DICTIONARY_DIRECTORY);
    }

    public StopWordsProcessor(String stopWordsFileName, String dictionaryDirectory) {

        File directory = new File(dictionaryDirectory);
        if (!directory.exists() && !directory.isDirectory()) {
            System.out.println("dictionary directory does not exist: " + directory + ";");
        }

        stopWordsFile = new File(dictionaryDirectory + Properties.SYSTEM_PATH_SEPARATOR + stopWordsFileName);
        if (!stopWordsFile.exists() && !stopWordsFile.isFile()) {
            System.out.println("dictionary file does not exist " + stopWordsFile + ";");
        }

        filter = new RomanianCharacterFilter();
    }

    public List<String> readFile() {
        List<String> stopWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(stopWordsFile))) {
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                line = line.toLowerCase();
                line = filter.filterCharacters(line, Properties.DIACRITICS);
                stopWords.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopWords;
    }

}
