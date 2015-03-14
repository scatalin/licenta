package algorithms.segmenttree.intern;

/**
 * Created by Catalin on 3/14/2015 .
 */
public class Interval {
    public int leftLimit = 0;
    public int rightLimit = 0;

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
