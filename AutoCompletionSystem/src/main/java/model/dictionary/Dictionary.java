package model.dictionary;

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
    private List<Word> words;

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
        words = new ArrayList<Word>();
    }

    public void readDictionary() {
        try {
            words.clear();
            BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile));
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split("=");
                words.add(new Word(tokens[0],Integer.parseInt(tokens[1])));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWord(String word){
        int index = words.lastIndexOf(new Word(word));
        if (index == -1){
            words.add(new Word(word));
            return;
        }
        Word word1 = words.get(index);
        word1.increaseFrequency();
    }

    public void sortDictionary(){
        Collections.sort(words);
    }

    public void saveDictionary() {
        try {
            dictionaryFile.delete();
            dictionaryFile.createNewFile();
            PrintWriter writer = new PrintWriter(dictionaryFile);
            for (Word word : words) {
                writer.println(word);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public void removeNonWords(){
        for(int i = 0; i< words.size(); i++){
            Word word = words.get(i);
            if (word.getWord().equals("") || word.getWord().equals(" ")){
                words.remove(word);
                i--;
            }
        }
    }
}
