package algorithms.segmenttree.intern;

/**
 * Created by Catalin on 3/14/2015 .
 */
public class Interval {
    int leftLimit = 0;
    int rightLimit = 0;

    @Override
    public String toString() {
        return "[" + leftLimit + "," + rightLimit + "]";
    }
}
