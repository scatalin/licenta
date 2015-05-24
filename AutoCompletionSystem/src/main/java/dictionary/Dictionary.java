package dictionary;

import algorithms.Data;
import algorithms.SearchTreeFactory;
import algorithms.heap.MaxHeap;
import dictionary.inserting.DefaultDictionaryWeightUpdate;
import dictionary.inserting.WeightUpdate;
import dictionary.validators.LengthLessThanThresholdValidator;
import dictionary.validators.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Dictionary {

    //todo separate the heap as it is separated in segment tree. necessary for model reconstruction.
    private List<MaxHeap<Word>> words;
    private Data data;
    private String fileName;
    private Validator validator;
    private WeightUpdate updater;
    private List<Word> wordsList;
    private boolean changed;

    public Dictionary() {
        this("");
    }

    public Dictionary(String fileName) {
        this.fileName = fileName;
        data = SearchTreeFactory.createData();
        words = new ArrayList<>(data.getSize());
        for (int i = 0; i < data.getSize(); i++) {
            words.add(new MaxHeap<>(true));
        }
        validator = new LengthLessThanThresholdValidator();
        updater = new DefaultDictionaryWeightUpdate();
        changed = true;
    }

    private Dictionary(List<MaxHeap<Word>> words, Data data, String fileName, Validator validator, WeightUpdate updater) {
        this.words = words;
        this.data = data;
        this.fileName = fileName;
        this.validator = validator;
        this.updater = updater;
        this.wordsList = null;
        this.changed = true;
    }

    public int addDefaultWord(String word, int weight) {
        return integrateDictionaryWord(word, weight, 0, 0);
    }

    public int addDefaultWord(String word, int weight, int userW, int actW) {
        return integrateDictionaryWord(word, weight, userW, actW);
    }

    public int addDefaultWord(String word, int weight, int userW) {
        return integrateDictionaryWord(word, weight, userW, 0);
    }

    public int addDefaultWord(String word) {
        return addDefaultWord(word, 1);
    }

    public int addDefaultWord(Word word) {
        return addDefaultWord(word.getWord(), word.getWeight());
    }

    public List<Word> getWordsSortedByWeight() {
        List<Word> toReturnWords = asList();
        Collections.sort(toReturnWords, new WordFrequencyComparator());
        return toReturnWords;
    }

    public List<Word> asList() {
        if (changed) {
            wordsList = new ArrayList<>();
            for (MaxHeap<Word> heap : words) {
                wordsList.addAll(heap.duplicateItems());
            }
        }
        return wordsList;
    }

    public List<Word> getAlphabeticallyWords() {
        List<Word> toReturnWords = asList();
        Collections.sort(toReturnWords, new Comparator<Word>() {
            // word comparator
            @Override
            public int compare(Word o1, Word o2) {
                //sortare crescatoare
                return o1.getWord().compareTo(o2.getWord());
            }
        });
        return toReturnWords;
    }

    public List<MaxHeap<Word>> getData() {
        return words;
    }

    public int getNumberOfWords() {
        return asList().size();
    }

    public String getFileName() {
        return fileName;
    }

    public String toString() {
        return "d=" + asList().size() + " " + asList();
    }

    private int integrateDictionaryWord(String word, int defaultW, int userW, int actW) {
        if (!validator.isValid(word)) {
            return -1;
        }
        changed = true;
        Word checkWord = new Word(word);

        int position = data.getPosition(word);
        List<Word> items = words.get(position).getItems();

        int index = items.lastIndexOf(checkWord);
        if (index == -1) {
            words.get(position).insert(new Word(word, defaultW, userW, actW));
            return defaultW;
        }

        int toReturnWeight = updater.updateWeight(items.get(index), defaultW, userW, actW);
        words.get(position).shiftUp(index, updater.updateModel());
        return toReturnWeight;
    }

    public void setUpdater(WeightUpdate updater) {
        this.updater = updater;
    }

    public void clear() {
        for (MaxHeap<Word> heap : words) {
            heap.clearHeap();
        }
    }

    public int getMaximumWeightForWord(String word) {
        int position = data.getPosition(word);
        return words.get(position).getItems().get(0).getWeight();
    }

    public void updateUserWord(String word, int userW) {
        integrateDictionaryWord(word, 0, userW, 0);
    }

    public void updateUserWord(String word, int userW, int actW) {
        integrateDictionaryWord(word, 0, userW, actW);
    }

    public Word getWord(String word) {
        if (!validator.isValid(word)) {
            return null;
        }
        Word checkWord = new Word(word);

        int position = data.getPosition(word);
        List<Word> items = words.get(position).getItems();

        int index = items.lastIndexOf(checkWord);
        if (index == -1) {
            return null;
        }
        return items.get(index);
    }

    public Dictionary clone() {
        List<MaxHeap<Word>> newHeap = new ArrayList<>();
        for (MaxHeap<Word> heap : words) {
            newHeap.add(heap.clone());
        }
        return new Dictionary(newHeap, data, fileName, validator, updater);
    }
}

