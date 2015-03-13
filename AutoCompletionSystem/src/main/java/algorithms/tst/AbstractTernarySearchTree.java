package algorithms.tst;

import algorithms.tst.intern.TstNode;
import algorithms.tst.printing.TstPrettyPrinter;
import algorithms.utils.PrettyPrinter;

/**
 * Created by Catalin on 2/21/2015 .
 */
public abstract class AbstractTernarySearchTree implements TernarySearchTree {

    protected TstNode root;
    protected int k;
    private PrettyPrinter printer;

    public AbstractTernarySearchTree() {
        root = null;
        printer = new TstPrettyPrinter();
    }

    public void load(String[] strings) {
        root = null;
        for (String string : strings) {
            insert(string);
        }
    }

    public void additionalLoad(String[] strings) {
        for (String string : strings) {
            insert(string);
        }
    }

    public String print(){
        printer.setTree(root);
        return printer.prettyPrint();
    }

    @Override
    public void setK(int k) {
        this.k = k;
    }
}
