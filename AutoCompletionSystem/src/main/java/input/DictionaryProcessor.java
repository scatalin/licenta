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
        this.dictionary = dictionary;

        String fileName = Properties.DICTIONARY_FILE_NAME;
        String dictDirectory = Properties.DICTIONARY_DIRECTORY;


        File directory = new File(dictDirectory);
        if (!directory.exists() && !directory.isDirectory()) {
            System.out.println("dictionary directory does not exist: " + directory + ";");
        }

        dictionaryFile = new File(dictDirectory + Properties.SYSTEM_PATH_SEPARATOR + fileName);
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

    public Dictionary getDictionary() {
        return dictionary;
    }
}
