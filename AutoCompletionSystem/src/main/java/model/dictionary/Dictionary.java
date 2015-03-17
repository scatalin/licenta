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


    private List<Word> words;

    public Dictionary() {

        words = new ArrayList<Word>();
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

    public void sortDictionaryAlphabetically(){
        Collections.sort(words);
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

    public void add(Word word) {
        words.add(word);
    }
}
