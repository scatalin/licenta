package system;

import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import algorithms.heap.MaxHeap;
import algorithms.utils.FilePrinter;
import dictionary.Dictionary;
import dictionary.Word;
import dictionary.inserting.UserWeightUpdate;
import input.DictionaryProcessor;
import input.FilesProcessor;

import java.util.List;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class AutoCompletionSystemImpl implements AutoCompletionSystem {

    private SearchTree searchTree;
    private Dictionary dictionary;
    private List<String> suggestions;
    private String lastPrefix;
    private Dictionary backup;

    public AutoCompletionSystemImpl() {
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
        filesProcessor.processInputFiles();
    }

    @Override
    public void loadCustomDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void constructSearchTree() {
        searchTree.reset();
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
        suggestions = searchTree.getSuggestions(prefix);
        return suggestions;
    }

    @Override
    public void selectWord(String word) {
        //todo separate this in a job, search the word in dictionary, get weight and update it in the model
        dictionary.setUpdater(new UserWeightUpdate());
        int ceilingWeight = dictionary.getMaximumWeightForWord(word);
        dictionary.updateUserWord(word, Properties.USER_WEIGHT,ceilingWeight / 10);
        Word w = dictionary.getWord(word);
        searchTree.update(word, w.getWeight());
    }

    @Override
    public void print(){
        FilePrinter.printTstToFile(FilePrinter.COMPLETION_TREE_FILE, searchTree.print());
    }

    @Override
    public void saveState() {
        backup = dictionary.clone();
    }

    @Override
    public void printDiff() {
        List<Word> backupWords = backup.asList();
        StringBuilder diff = new StringBuilder();
        for(Word word : backupWords){
            Word w =dictionary.getWord(word.getWord());
            if(!w.toString().equals(word.toString())){
                diff.append("before: ").append(word.toStringCustom()).append(" after: ").append(w.toStringCustom()).append("\n");
            }
        }
        FilePrinter.printTstToFile(FilePrinter.COMPLETION_TREE_FILE, diff.toString());
    }
}
