package algorithms.tst;

import algorithms.tst.intern.TstNode;

import java.util.List;

/**
 * Created by Catalin on 2/21/2015.
 */
public interface TernarySearchTree {

    void load(String[] strings);

    void additionalLoad(String[] strings);

    boolean search(String s);

    List<String> getTopK(String prefix);

    void setK(int k);

    void insert(String s);

    String print();

    TstNode getRoot();
}
