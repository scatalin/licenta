package algorithms.segmenttree.intern;

/**
 * Created by Catalin on 3/14/2015 .
 */
public class Interval {
    public int leftLimit;
    public int rightLimit;

    public Interval(int leftLimit, int rightLimit) {
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    public Interval() {

    }

    @Override
    public String toString() {
        return "[" + leftLimit + "," + rightLimit + "]";
    }
}
