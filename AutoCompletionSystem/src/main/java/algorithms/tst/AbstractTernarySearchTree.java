package algorithms.tst;

import algorithms.tst.intern.TstNode;
import algorithms.tst.intern.TstPrettyPrinter;

/**
 * Created by Catalin on 2/21/2015 .
 */
public abstract class AbstractTernarySearchTree implements TernarySearchTree {

    protected TstNode root;
    protected int k;
    private TstPrettyPrinter printer;

    public AbstractTernarySearchTree() {
        root = new TstNode();
        printer = new TstPrettyPrinter(root);
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
        printer.setRootNode(root);
        return printer.prettyPrint();
    }

    @Override
    public void setK(int k) {
        this.k = k;
    }

    @Override
    public TstNode getRoot() {
        return root;
    }
}
