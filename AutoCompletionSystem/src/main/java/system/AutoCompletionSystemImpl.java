package system;

import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import algorithms.heap.MaxHeap;
import dictionary.Dictionary;
import dictionary.Word;
import dictionary.inserting.UserWeightUpdate;
import input.DictionaryProcessor;
import input.FilesProcessor;
import system.Properties;
import system.AutoCompletionSystem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class AutoCompletionSystemImpl implements AutoCompletionSystem {

    private SearchTree searchTree;
    private Dictionary dictionary;
    private List<String> sugestions;
    private String lastPrefix;

    AutoCompletionSystemImpl() {
        searchTree = SearchTreeFactory.createCompletionTree();
        dictionary = new Dictionary();
        lastPrefix = "";
    }

    @Override
    public void loadDictionary() {
        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor(dictionary);
        dictionaryProcessor.readDictionary();
    }

    @Override
    public void readDictionary() {
        FilesProcessor filesProcessor = new FilesProcessor(new DictionaryProcessor(dictionary));
        filesProcessor.processInputFiles(-1);
    }

    @Override
    public void loadCustomDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void constructSearchTree() {
        searchTree.load(Collections.<Word>emptyList(),true);
        for (MaxHeap<Word> heap : dictionary.getData()) {
            searchTree.load(heap.getItems(), false);
        }
        searchTree.setNumberOfSuggestions(Properties.AUTOCOMPLETION_SUGGESTION_SIZE);
    }

    @Override
    public void startCompletion() {
        searchTree.resetCompletion();
    }

    @Override
    public List<String> getCompletion(String prefix) {
        if (!prefix.startsWith(lastPrefix)) {
            startCompletion();
        }
        sugestions = searchTree.getSuggestions(prefix);
        return sugestions;
    }

    @Override
    public void selectWord(String word) {
        //todo separate this in a job, search the word in dictionary, get weight and update it in the model
        dictionary.setUpdater(new UserWeightUpdate());
        int previousWeight = dictionary.addDictionaryWord(word);
        if(previousWeight > 0) {
            searchTree.update(word, previousWeight + Properties.USER_WEIGHT);
        }
    }
}
