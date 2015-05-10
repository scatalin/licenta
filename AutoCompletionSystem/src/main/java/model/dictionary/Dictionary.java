package model.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Dictionary {

    private final List<Word> words;
    private String fileName;

    public Dictionary() {
        this("");
    }

    public Dictionary(String fileName) {
        this.fileName = fileName;
        words = new ArrayList<>();
    }

    public void addWord(String word, int weight) {
        words.add(new Word(word, weight));
    }

    public void addWord(String word) {
        integrateWord(new Word(word), 1);
    }

    public void addWord(Word word) {
        integrateWord(word, word.getWeight());
    }

    public void sortDictionaryAlphabetically() {
        Collections.sort(words, new Comparator<Word>() {
            // word comparator
            @Override
            public int compare(Word o1, Word o2) {
                //sortare crescatoare
                return o1.getWord().compareTo(o2.getWord());
            }
        });
    }

    public void sortDictionaryByWeight() {
        Collections.sort(words, new WordFrequencyComparator());
    }

    public List<Word> getWords() {
        return words;
    }

    public void removeNonWords() {
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            if (word.getWord().isEmpty() || word.getWord().equals(" ")) {
                words.remove(word);
                i--;
            }
        }
    }

    public int getNumberOfWords() {
        return words.size();
    }

    public String getFileName() {
        return fileName;
    }

    public String toString() {
        return "d=" + words.size() + " " + words;
    }

    private void integrateWord(Word word, int increment) {
        int index = words.lastIndexOf(word);
        if (index == -1) {
            words.add(word);
            return;
        }
        Word existentWord = words.get(index);
        existentWord.increaseFrequency(increment);
    }

    /**
     * For further use
     *
     */
    @Deprecated
    public List<Word> getWordsWithPrefix(String word) {
        List<Word> toReturn = new ArrayList<>();
        for (Word word1 : words) {
            if (word1.getWord().startsWith(word)) {
                toReturn.add(word1);
            }
        }
        return toReturn;
    }
}

