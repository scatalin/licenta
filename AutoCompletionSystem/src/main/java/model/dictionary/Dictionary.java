package model.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Dictionary {

    private String fileName;
    private final List<Word> words;

    public Dictionary(){
        this("");
    }

    public Dictionary(String fileName) {
        this.fileName = fileName;
        words = new ArrayList<Word>();
    }

    public void addWord(String word, int weight) {
        words.add(new Word(word, weight));
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

    public List<Word> getWordsWithPrefix(String word){
        List<Word> toReturn = new ArrayList<Word>();
        for(Word word1: words){
            if(word1.getWord().startsWith(word)){
                toReturn.add(word1);
            }
        }
        return toReturn;
    }

    public void addWord(Word word){
        int index = words.lastIndexOf(word);
        if (index == -1){
            words.add(word);
            return;
        }
        Word existentWord = words.get(index);
        existentWord.increaseFrequency(word.getFrequency());
    }

    public void sortDictionaryAlphabetically(){
        Collections.sort(words, new WordComparator());
    }

    public void sortDictionaryByWeight() {
        Collections.sort(words, new WordFrequencyComparator());
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

    public void clear() {
        words.clear();
    }

    public int getNumberOfWords() {
        return words.size();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String toString(){
        return "d="+words.size()+" "+words.toString();
    }

}

