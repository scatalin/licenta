package input;

import model.dictionary.Dictionary;
import model.dictionary.Word;
import system.Properties;

import java.io.*;

/**
 * Created by Catalin on 3/19/2015 .
 */
public class DictionaryProcessor {
    private final File dictionaryFile;

    private final Dictionary dictionary;

    public DictionaryProcessor(Dictionary dictionary){
        this(dictionary, Properties.DICTIONARY_FILE_NAME, Properties.DICTIONARY_DIRECTORY);
    }

    public DictionaryProcessor(Dictionary dictionary, String dictionaryFileName){
        this(dictionary, dictionaryFileName, Properties.DICTIONARY_DIRECTORY);
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

    }

    public void readDictionary() {
        try {
            dictionary.clear();
            BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile));
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split("=");
                dictionary.addWord(tokens[0], Integer.parseInt(tokens[1]));
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

    public Dictionary getDictionary() {
        return dictionary;
    }
}
