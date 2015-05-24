package input;

import dictionary.Dictionary;
import dictionary.Word;
import dictionary.filters.CharacterFilter;
import dictionary.filters.RomanianCharacterFilter;
import system.Properties;

import java.io.*;

/**
 * Created by Catalin on 3/19/2015 .
 */
public class DictionaryProcessor {
    private final File dictionaryFile;
    private final CharacterFilter filter;
    private Dictionary dictionary;

    public DictionaryProcessor() {
        this(new Dictionary(), Properties.DICTIONARY_FILE_NAME, Properties.DICTIONARY_DIRECTORY);
    }

    public DictionaryProcessor(Dictionary dictionary) {
        this(dictionary, Properties.DICTIONARY_FILE_NAME, Properties.DICTIONARY_DIRECTORY);
    }

    public DictionaryProcessor(Dictionary dictionary, String dictionaryFileName, String dictionaryDirectory) {
        this.dictionary = dictionary;

        File directory = new File(dictionaryDirectory);
        if (!directory.exists() && !directory.isDirectory()) {
            System.out.println("dictionary directory does not exist: " + directory + ";");
        }

        dictionaryFile = new File(dictionaryDirectory + Properties.SYSTEM_PATH_SEPARATOR + dictionaryFileName);
        if (!dictionaryFile.exists() && !dictionaryFile.isFile()) {
            System.out.println("dictionary file does not exist " + dictionaryFile + ";");
        }

        filter = new RomanianCharacterFilter();
    }

    public void readDictionary() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile))) {
            dictionary.clear();

            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split("=");
                if (tokens.length == 2) {
                    dictionary.addDefaultWord(filter.filterCharacters(tokens[0], Properties.DIACRITICS), Integer.parseInt(tokens[1]));
                } else if (tokens.length == 3) {
                    dictionary.addDefaultWord(filter.filterCharacters(tokens[0], Properties.DIACRITICS), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                } else if (tokens.length == 4) {
                    dictionary.addDefaultWord(filter.filterCharacters(tokens[0], Properties.DIACRITICS), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                } else {
                    System.exit(1);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDictionary() {
        try (PrintWriter writer = new PrintWriter(dictionaryFile)) {
            dictionaryFile.delete();
            dictionaryFile.createNewFile();
            int count = 0;
            for (Word word : dictionary.getAlphabeticallyWords()) {
                writer.println(word);
                count++;
                if (count > 100) {
                    writer.flush();
                    count = 0;
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

}
