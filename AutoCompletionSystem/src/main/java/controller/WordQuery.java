package controller;

import algorithms.SearchTree;
import system.ServiceLocator;

import java.util.List;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class WordQuery {

    private SearchTree searchTree;
    private String lastPrefix;

    public WordQuery() {

        lastPrefix = "";
    }

    public List<String> getSuggestions(String prefix){
        if(searchTree == null){
            searchTree = ServiceLocator.getCompletionTree();
        }
        if (!prefix.startsWith(lastPrefix) || lastPrefix.isEmpty()) {
            searchTree.resetCompletion();
        }
        lastPrefix = prefix;
        return searchTree.getSuggestions(prefix);
    }
}
