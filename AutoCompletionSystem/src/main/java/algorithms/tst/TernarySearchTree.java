package algorithms.tst;

/**
 * Created by Catalin on 2/21/2015.
 */
public interface TernarySearchTree {

    public void load(String[] strings) ;

    public void additionalLoad(String[] strings) ;

    public boolean search(String s) ;

    public void insert(String s) ;

    public String print();
}
