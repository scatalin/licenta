package dictionary;

import algorithms.heap.MaxHeap;
import dictionary.validators.LengthLessThanThresholdValidator;
import dictionary.validators.Validator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Dictionary {

    private MaxHeap<Word> wordsHeap;
    private String fileName;

    public Dictionary() {
        this("");
    }

    public Dictionary(String fileName) {
        this.fileName = fileName;
        wordsHeap = new MaxHeap<>();
    }

    public void addDictionaryWord(String word, int weight) {
        integrateWord(word, weight);
    }

    public void addDictionaryWord(String word) {
        addDictionaryWord(word, 1);
    }

    public void addDictionaryWord(Word word) {
        addDictionaryWord(word.getWord(), word.getWeight());
    }

    //todo: this should not exist
    public void sortDictionaryAlphabetically() {
        Collections.sort(wordsHeap.getItems(), new Comparator<Word>() {
            // word comparator
            @Override
            public int compare(Word o1, Word o2) {
                //sortare crescatoare
                return o1.getWord().compareTo(o2.getWord());
            }
        });
    }

    //todo: this should not exist
    public void sortDictionaryByWeight() {
        Collections.sort(wordsHeap.getItems(), new WordFrequencyComparator());
    }

    public List<Word> getWords() {
        return wordsHeap.getItems();
    }

    public void removeNonWords() {
        for (int i = 0; i < wordsHeap.getItems().size(); i++) {
            Word word = wordsHeap.get(i);
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

    private void integrateWord(String word, int increment) {
        if(!isValidWord()){
            return;
        }
        int index = wordsHeap.getItems().lastIndexOf(word);
        if (index == -1) {
            wordsHeap.insert(new Word(word, increment));
            return;
        }
        Word existentWord = wordsHeap.getItems().get(index);
        existentWord.increaseFrequency(increment);
    }

    /**
     * For further use
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

