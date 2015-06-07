package algorithms.heap;

/**
 * Created by Catalin on 5/17/2015 .
 */
public class CostCalculator {

    private long cost = 0;

    //todo: finalize this and use it

    public void addModification(int level) {
        cost += (long) (level * 1.5d);
    }

    public long getCost(){
        return cost;
    }
}
