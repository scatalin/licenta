package dictionary;

import algorithms.SearchTree;
import algorithms.heap.MaxHeap;
import system.ServiceLocator;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class ModelConstructor {

    private SearchTree tree;
    private Dictionary dictionary;

    public ModelConstructor() {
        tree = ServiceLocator.getCompletionTree();
        dictionary = ServiceLocator.getDictionary();
    }

    public void constructModel() {
        tree.reset();
        for (MaxHeap<Word> heap : dictionary.getData()) {
            for (Word word : heap.getItems()) {
                tree.insert(word);
            }
        }
    }
}
