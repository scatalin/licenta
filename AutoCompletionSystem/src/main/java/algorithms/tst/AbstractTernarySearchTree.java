package algorithms.tst;

import algorithms.tst.intern.TstNode;
import algorithms.tst.intern.TstPrinter;

/**
 * Created by Catalin on 2/21/2015 .
 */
public abstract class AbstractTernarySearchTree implements TernarySearchTree {

    protected TstNode root;
    protected int k;
    private TstPrinter printer;

    public AbstractTernarySearchTree() {
        root = new TstNode();
        printer = new TstPrinter();
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
        return printer.prettyPrint(root);
    }

    @Override
    public void setK(int k) {
        this.k = k;
    }
}
