package algorithms.heap;

/**
 * Created by Catalin on 5/17/2015 .
 */
public interface HeapNode extends Comparable {

    int getLevel();

    void setLevel(int level);
}
