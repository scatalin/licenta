package algorithms.heap;

/**
 * Created by Catalin on 5/17/2015 .
 */
public interface HeapNode<T> extends Comparable {

    int getLevel();

    void setLevel(int level);

    T clone();
}
