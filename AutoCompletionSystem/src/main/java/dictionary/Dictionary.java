package dictionary;

import algorithms.Data;
import algorithms.SearchTreeFactory;
import algorithms.heap.MaxHeap;
import dictionary.inserting.DefaultDictionaryWeightUpdate;
import dictionary.inserting.WeightUpdate;

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
    private WeightUpdate updater;
    private List<Word> wordsList;
    private boolean changed;

    public Dictionary() {
        this("");
    }

    public Dictionary(String fileName) {
        this.fileName = fileName;
        updater = new DefaultDictionaryWeightUpdate();
        changed = true;
    }

    private Dictionary(List<MaxHeap<Word>> words, Data data, String fileName, WeightUpdate updater) {
        this.words = words;
        this.data = data;
        this.fileName = fileName;
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
            for (MaxHeap<Word> heap : getHeaps()) {
                wordsList.addAll(heap.duplicateItems());
            }
            changed = false;
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
        return getHeaps();
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
        changed = true;
        Word checkWord = new Word(word);

        int position = getInnerData().getPosition(word);
        List<Word> items = getHeaps().get(position).getItems();

        int index = items.lastIndexOf(checkWord);
        if (index == -1) {
            getHeaps().get(position).insert(new Word(word, defaultW, userW, actW));
            return defaultW;
        }

        int toReturnWeight = updater.updateWeight(items.get(index), defaultW, userW, actW);
        getHeaps().get(position).shiftUp(index, updater.updateModel());
        return toReturnWeight;
    }

    public void setUpdater(WeightUpdate updater) {
        this.updater = updater;
    }

    public void clear() {
        for (MaxHeap<Word> heap : getHeaps()) {
            heap.clearHeap();
        }
    }

    public int getMaximumWeightForWord(String word) {
        int position = getInnerData().getPosition(word);
        return (getHeaps().get(position).getItems().isEmpty() ? 10 : getHeaps().get(position).getItems().get(0).getWeight());
    }

    public void updateUserWord(String word, int userW, int actW) {
        integrateDictionaryWord(word, 0, userW, actW);
    }

    public Word getWord(String word) {
        Word checkWord = new Word(word);

        int position = getInnerData().getPosition(word);
        List<Word> items = getHeaps().get(position).getItems();

        int index = items.lastIndexOf(checkWord);
        if (index == -1) {
            return null;
        }
        return items.get(index);
    }

    public Dictionary clone() {
        List<MaxHeap<Word>> newHeap = new ArrayList<>();
        for (MaxHeap<Word> heap : getHeaps()) {
            newHeap.add(heap.clone());
        }
        return new Dictionary(newHeap, data, fileName, updater);
    }

    private Data getInnerData(){
        if(data == null){
            data = SearchTreeFactory.createData();
        }
        return data;
    }

    private List<MaxHeap<Word>> getHeaps(){
        if(words == null){
            words = new ArrayList<>(getInnerData().getSize());
            for (int i = 0; i < getInnerData().getSize(); i++) {
                words.add(new MaxHeap<>(true));
            }
        }
        return words;
    }
}

