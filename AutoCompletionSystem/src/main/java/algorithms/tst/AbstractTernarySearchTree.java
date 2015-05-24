package algorithms.tst;

import algorithms.SearchTree;
import algorithms.heap.HeapTreeNode;
import algorithms.heap.MaxHeap;
import algorithms.tst.intern.TstNode;
import algorithms.tst.printing.TstPrettyPrinter;
import algorithms.utils.PrettyPrinter;
import dictionary.Word;
import dictionary.WordFrequencyComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public abstract class AbstractTernarySearchTree implements SearchTree {

    private final MaxHeap<HeapTreeNode> heap;
    private final List<Word> foundSmallWeightWords;
    private final List<Word> foundWords;
    protected TstNode root;
    private int k;
    private boolean toReset;

    AbstractTernarySearchTree() {
        heap = new MaxHeap<>();
        foundSmallWeightWords = new ArrayList<>();
        foundWords = new ArrayList<>();
        k = 5;
    }

    @Override
    public void load(List<Word> words, boolean reset) {
        if (reset) {
            root = null;
        }
        for (Word word : words) {
            insert(word);
        }
    }

    @Override
    public void load(List<Word> words) {
        load(words, false);
    }

    @Override
    public String print() {
        PrettyPrinter printer = new TstPrettyPrinter(root);
        return printer.prettyPrint();
    }

    @Override
    public void setNumberOfSuggestions(int numberOfSuggestions) {
        this.k = numberOfSuggestions;
    }

    @Override
    public void resetCompletion() {
        toReset = true;
    }

    private void resetSearching() {
        heap.clearHeap();
        foundWords.clear();
        foundSmallWeightWords.clear();
        heap.insert(new HeapTreeNode(root, ""));
        toReset = false;
    }

    @Override
    public List<String> getSuggestions(String prefix) {
        if (toReset) {
            resetSearching();
        } else {
            clearInvalidPaths(prefix);
        }
        searchFurther(prefix);
        List<String> suggestions = new ArrayList<>(foundWords.size());
        for (Word word : foundWords) {
            suggestions.add(word.getWord());
        }
        return suggestions;
    }

    private void searchFurther(String prefix) {
        if (heap.peek() == null) {
            return;
        }
        while (!heap.isEmpty() && (foundWords.size() < k)) {
            HeapTreeNode item = heap.delete();
            if (item.getNode() == null) {
                continue;
            }
            int maxWeight = item.getNode().getSubtreesWeight();
            if (!item.getBuiltWord().startsWith(prefix) && !prefix.startsWith(item.getBuiltWord())) {
                continue;
            }
            String word = item.getBuiltWord() + item.getNode().getCharacter();
            if (item.getNode().isEndWord() && word.startsWith(prefix)) {
                //todo add a heap for found words (specific weight stuff). study if necessary
                foundSmallWeightWords.add(new Word(item.getBuiltWord() + item.getNode().getCharacter(), item.getNode().getWordWeight()));
                Collections.sort(foundSmallWeightWords, new WordFrequencyComparator());
                if (foundSmallWeightWords.get(0).getWeight() >= maxWeight) {
                    foundWords.add(foundSmallWeightWords.remove(0));
                }
            }
            //todo prune the search paths
            if (item.getNode().getLeftChild() != null) {
                HeapTreeNode newNode = item.clone();
                newNode.setNode(item.getNode().getLeftChild());
                heap.insert(newNode);
            }
            if (item.getNode().getMiddleChild() != null) {
                HeapTreeNode newNode = item.clone();
                newNode.setNode(item.getNode().getMiddleChild());
                newNode.addCharacter(item.getNode().getCharacter());
                heap.insert(newNode);
            }
            if (item.getNode().getRightChild() != null) {
                HeapTreeNode newNode = item.clone();
                newNode.setNode(item.getNode().getRightChild());
                heap.insert(newNode);
            }
        }
        while ((foundWords.size() < k) && !foundSmallWeightWords.isEmpty()) {
            foundWords.add(foundSmallWeightWords.remove(0));
        }
    }

    private void clearInvalidPaths(String prefix) {
        Iterator<Word> iterator = foundWords.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (!word.getWord().startsWith(prefix)) {
                iterator.remove();
            }
        }
        iterator = foundSmallWeightWords.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (!word.getWord().startsWith(prefix)) {
                iterator.remove();
            }
        }
        Iterator<HeapTreeNode> it = heap.iterator();
        while (it.hasNext()) {
            HeapTreeNode item = it.next();
            if (!item.getBuiltWord().startsWith(prefix) && !prefix.startsWith(item.getBuiltWord())) {
                it.remove();
            }
        }

    }

    @Override
    public void reset() {
        root = null;
    }
}
