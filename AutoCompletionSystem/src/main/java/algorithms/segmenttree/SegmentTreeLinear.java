package algorithms.segmenttree;

import algorithms.Data;
import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import algorithms.segmenttree.printing.SegmentTreePrettyPrinter;
import algorithms.tst.build.TernarySearchTreeFactory;
import algorithms.utils.PrettyPrinter;
import dictionary.Word;
import system.Properties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/13/2015 .
 */
public class SegmentTreeLinear implements SearchTree {

    private List<SearchTree> trees;
    private Data data;

    public SegmentTreeLinear() {
        int size = Properties.SEGMENT_SIZE;
        trees = new ArrayList<>(size + 1);
        data = SearchTreeFactory.createData();
        for (int i = 0; i < size; i++) {
            trees.add(TernarySearchTreeFactory.createTst());
        }
    }

    @Override
    public void insert(Word word) {
        int position = data.getPosition(word.getWord().substring(0, 1));
        trees.get(position).insert(word);
    }

    @Override
    public void update(String word, int weight) {
        int position = data.getPosition(word.substring(0, 1));
        trees.get(position).update(word, weight);
    }

    @Override
    public void load(List<Word> words, boolean reset) {
        if (reset) {
            resetAll();
        }
        for (Word word : words) {
            insert(word);
        }
    }

    private void resetAll() {
        for (SearchTree tree : trees) {
            tree.reset();
        }
    }

    @Override
    public List<String> getSuggestions(String prefix) {
        int position = data.getPosition(prefix.substring(0, 1));
        return trees.get(position).getSuggestions(prefix);
    }

    @Override
    public void setNumberOfSuggestions(int numberOfSuggestions) {
        for (SearchTree tree : trees) {
            tree.setNumberOfSuggestions(numberOfSuggestions);
        }
    }

    @Override
    public void resetCompletion() {
        for (SearchTree tree : trees) {
            tree.resetCompletion();
        }
    }

    @Override
    public String print() {
        PrettyPrinter printer = new SegmentTreePrettyPrinter();
        return printer.prettyPrint();
    }

    @Override
    public void reset() {
        resetAll();
    }

    public List<SearchTree> getTrees() {
        return trees;
    }
}
