package algorithms.tst;

import algorithms.tst.intern.TstNode;
import algorithms.tst.printing.TstPrettyPrinter;
import algorithms.utils.PrettyPrinter;
import model.dictionary.Word;

import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public abstract class AbstractTernarySearchTree implements TernarySearchTree {

    protected TstNode root;
    protected int k;

    public AbstractTernarySearchTree() {
        root = null;
    }

    public void load(List<Word> words) {
        root = null;
        for (Word word : words) {
            insert(word.getWord(),word.getFrequency());
        }
    }

    public void additionalLoad(List<Word> words) {
        for (Word word : words) {
            insert(word.getWord(), word.getFrequency());
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
}
