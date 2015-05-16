package dictionary;

import algorithms.heap.MaxHeap;
import dictionary.inserting.DefaultDictionaryWeightUpdate;
import dictionary.inserting.WeightUpdate;
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
    private Validator validator;
    private WeightUpdate updater;

    public Dictionary() {
        this("");
    }

    public Dictionary(String fileName) {
        this.fileName = fileName;
        wordsHeap = new MaxHeap<>();
        validator = new LengthLessThanThresholdValidator();
        updater = new DefaultDictionaryWeightUpdate();
    }

    public void addDictionaryWord(String word, int weight) {
        integrateDictionaryWord(word, weight);
    }

    public void addDictionaryWord(String word) {
        addDictionaryWord(word, 1);
    }

    public void addDictionaryWord(Word word) {
        addDictionaryWord(word.getWord(), word.getWeight());
    }

    public List<Word> getWordsSortedByWeight() {
        List<Word> words = wordsHeap.duplicateItems();
        Collections.sort(words, new WordFrequencyComparator());
        return words;
    }

    public List<Word> getAlphabeticallyWords() {
        List<Word> words = wordsHeap.duplicateItems();
        Collections.sort(words, new Comparator<Word>() {
            // word comparator
            @Override
            public int compare(Word o1, Word o2) {
                //sortare crescatoare
                return o1.getWord().compareTo(o2.getWord());
            }
        });
        return words;
    }

    public List<Word> getWords() {
        return wordsHeap.getItems();
    }

    public int getNumberOfWords() {
        return wordsHeap.getItems().size();
    }

    public String getFileName() {
        return fileName;
    }

    public String toString() {
        return "d=" + wordsHeap.getItems().size() + " " + wordsHeap.getItems();
    }

    private void integrateDictionaryWord(String word, int increment) {
        if (!validator.isValid(word)) {
            return;
        }
        Word checkWord = new Word(word);
        int index = wordsHeap.getItems().lastIndexOf(checkWord);
        if (index == -1) {
            wordsHeap.insert(new Word(word, increment));
            return;
        }
        Word existentWord = wordsHeap.getItems().get(index);
        updater.updateWeight(existentWord, increment);
        //todo add a check here for model remaking
        wordsHeap.shiftUp(index);
    }

    public void setUpdater(WeightUpdate updater) {
        this.updater = updater;
    }
}

