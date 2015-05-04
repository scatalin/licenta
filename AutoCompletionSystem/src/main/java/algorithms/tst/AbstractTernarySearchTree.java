package algorithms.tst;

import algorithms.SearchTree;
import algorithms.heap.AutoCompletionHeap;
import algorithms.heap.HeapNode;
import algorithms.tst.intern.TstNode;
import algorithms.tst.printing.TstPrettyPrinter;
import algorithms.utils.PrettyPrinter;
import model.dictionary.Word;
import model.dictionary.WordFrequencyComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public abstract class AbstractTernarySearchTree implements SearchTree {

    TstNode root;
    private AutoCompletionHeap<HeapNode> heap;
    private List<Word> foundSmallWeightWords;
    private List<Word> foundWords;
    private int maxWeight;
    private int k;

    AbstractTernarySearchTree() {
        heap = new AutoCompletionHeap<HeapNode>();
        foundSmallWeightWords = new ArrayList<Word>();
        foundWords = new ArrayList<Word>();
        k = 5;
    }

    public void load(List<Word> words, boolean reset) {
        if (reset) {
            root = null;
        }
        for (Word word : words) {
            insert(word);
        }
    }

    public String print() {
        PrettyPrinter printer = new TstPrettyPrinter(root);
        return printer.prettyPrint();
    }

    @Override
    public void setK(int k) {
        this.k = k;
    }

    @Override
    public void resetSearchK() {
        heap.clearHeap();
        foundWords.clear();
        foundSmallWeightWords.clear();
        heap.insert(new HeapNode(root, ""));
    }

    @Override
    public List<Word> getNextTopK(String prefix) {
        clearInvalidPaths(prefix);
        seachFurther(prefix, k);
        return foundWords;
    }

    protected void seachFurther(String prefix, int limit) {
        while (!heap.isEmpty() && foundWords.size() < limit) {
            HeapNode item = heap.delete();
            if (item == null) {
                return;
            }
            if (item.getNode() == null) {
                continue;
            }
            maxWeight = item.getNode().getWeight();
            if (!item.getBuiltWord().startsWith(prefix) && !prefix.startsWith(item.getBuiltWord())) {
                continue;
            }
            String word = item.getBuiltWord() + item.getNode().getCharacter();
            if (item.getNode().isEndWord() && word.startsWith(prefix)) {
                //todo add a heap for found words (specific weight stuff)
                foundSmallWeightWords.add(new Word(item.getBuiltWord() + item.getNode().getCharacter(), item.getNode().getEndWordWeight()));
                Collections.sort(foundSmallWeightWords, new WordFrequencyComparator());
                if (foundSmallWeightWords.get(0).getFrequency() >= maxWeight) {
                    foundWords.add(foundSmallWeightWords.remove(0));
                }
            }
            //todo prune the search paths
            if (item.getNode().getLeftChild() != null) {
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getLeftChild());
                heap.insert(newNode);
            }
            if (item.getNode().getMiddleChild() != null) {
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getMiddleChild());
                newNode.addCharacter(item.getNode().getCharacter());
                heap.insert(newNode);
            }
            if (item.getNode().getRightChild() != null) {
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getRightChild());
                heap.insert(newNode);
            }
        }
        while (foundWords.size() < limit && !foundSmallWeightWords.isEmpty()) {
            foundWords.add(foundSmallWeightWords.remove(0));
        }
    }

    protected void clearInvalidPaths(String prefix) {
        Iterator<Word> iter = foundWords.iterator();
        while (iter.hasNext()) {
            Word word = iter.next();
            if (!word.getWord().startsWith(prefix)) {
                iter.remove();
            }
        }
        iter = foundSmallWeightWords.iterator();
        while (iter.hasNext()) {
            Word word = iter.next();
            if (!word.getWord().startsWith(prefix)) {
                iter.remove();
            }
        }
        heap.clearInvalidPaths(prefix);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public TstNode getRoot() {
        return root;
    }

    @Override
    public void reset() {
        root = null;
    }
}
