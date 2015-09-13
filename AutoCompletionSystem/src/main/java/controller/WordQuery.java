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

    public List<String> getSuggestions(String prefix) {
        if (searchTree == null) {
            searchTree = ServiceLocator.getCompletionTree();
        }
        if (!prefix.substring(0, prefix.length() - 1).startsWith(lastPrefix) || lastPrefix.isEmpty()) {
            searchTree.resetCompletion();
        }
        lastPrefix = prefix;
        long start = System.currentTimeMillis();
        List<String> results = searchTree.getSuggestions(prefix);
        System.out.println("durata:" + (System.currentTimeMillis() - start));
        return results;
    }
}
