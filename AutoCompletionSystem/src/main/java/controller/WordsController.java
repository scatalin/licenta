package controller;

import algorithms.SearchTree;
import controller.filter.WordsFilter;
import system.ServiceLocator;
import updating.Updater;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class WordsController {
    private WordsFilter filter;
    private Updater updater;
    private SearchTree tree;

    public WordsController() {
        filter = new WordsFilter();
        updater = Updater.getInstance();
        tree = ServiceLocator.getCompletionTree();
    }

    public void handleWord(String word){
        if(filter.filterWord(word)){
            int newWeight = updater.update(word);
            tree.update(word, newWeight);
        }
    }
}
