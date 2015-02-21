package model;

import input.Properties;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Dictionary {

    private final String directoryPath;
    private final String fileName;
    private final File directory;
    private final File dictionaryFile;
    private List<String> words;

    public Dictionary() {
        directoryPath = Properties.DICTIONARY_DIRECTORY;
        fileName = Properties.DICTIONARY_FILE_NAME;
        directory = new File(directoryPath);
        if (!directory.exists() && !directory.isDirectory()) {
            System.out.println("dictionary directory does not exist: " + directory + ";");
        }
        dictionaryFile = new File(directoryPath + "\\" + fileName);
        if (!dictionaryFile.exists() && !dictionaryFile.isFile()) {
            System.out.println("dictionary file does not exist " + dictionaryFile + ";");
        }
        words = new ArrayList<String>();
    }

    public void readDictionary() {
        try {
            words.clear();
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(dictionaryFile));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println(words);
    }

    public void addWord(String word){
        words.add(word);
    }

    public void sortDictionary(){
        Collections.sort(words);
    }

    public void saveDictionary() {
        try {
            dictionaryFile.delete();
            dictionaryFile.createNewFile();
            PrintWriter writer = new PrintWriter(dictionaryFile);
            for (String word : words) {
                writer.println(word);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
