package algorithms.heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gstan on 27.03.2015.
 */
public class MaxHeap<T extends HeapNode> {
    //todo : modify levels in shiftUp and shiftDown
    private List<T> items;
    private CostCalculator cost;

    public MaxHeap() {
        this(false);
    }

    public MaxHeap(boolean calculateCost) {
        items = new ArrayList<>();
        if (calculateCost) {
            cost = new CostCalculator();
        }
    }

    private MaxHeap(List<T> items, CostCalculator cost) {
        this.items = items;
        this.cost = cost;
    }

    public int shiftUp(int k, boolean update) {
        while (k > 0) {
            int p = parent(k);
            T item = items.get(k);
            T parent = items.get(p);

            if (item.compareTo(parent) > 0) {
                //swap
                if ((cost != null) && update) {
                    cost.addModification(items.get(p).getLevel());
                }
                items.set(k, parent);
                items.set(p, item);
                //move up one level
                k = p;
            } else {
                break;
            }
        }
        return k;
    }

    public void insert(T item) {
        int previousLevel = 0;
        if (!items.isEmpty()) {
            previousLevel = items.get(items.size() - 1).getLevel();
        }
        int sum = geometricSum(previousLevel);
        items.add(item);
        items.get(items.size() - 1).setLevel((items.size() > sum) ? (previousLevel + 1) : previousLevel);
        shiftUp(items.size() - 1, false);
    }

    private int geometricSum(int previousLevel) {
        return (int) (Math.pow(2, previousLevel) - 1);
    }

    public int shiftDown(int k, boolean update) {
        int l = leftChild(k);
        while (l < items.size()) {
            int max = l;
            int r = rightChild(k);
            if (r < items.size()) {
                if (items.get(r).compareTo(items.get(l)) > 0) {
                    max++;
                }
            }
            if (items.get(k).compareTo(items.get(max)) < 0) {
                //switch
                if ((cost != null) && update) {
                    cost.addModification(items.get(k).getLevel());
                }
                T temp = items.get(k);
                items.set(k, items.get(max));
                items.set(max, temp);
                k = max;
                l = leftChild(k);
            } else {
                break;
            }
        }
        return k;
    }

    public T delete() {
        if (items.isEmpty()) {
            return null;
        }
        if (items.size() == 1) {
            return items.remove(0);
        }
        T hold = items.get(0);
        items.set(0, items.remove(items.size() - 1));
        items.get(0).setLevel(0);
        shiftDown(0, false);
        return hold;
    }

    public T peek() {
        if (items.size() == 1) {
            return items.get(0);
        }
        return null;
    }

    public void clearHeap() {
        items.clear();
    }

    private int parent(int k) {
        return (k - 1) / 2;
    }

    private int rightChild(int k) {
        return (2 * k) + 2;
    }

    private int leftChild(int k) {
        return (2 * k) + 1;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "AutoCompletionHeap{" +
                "items=" + items +
                '}';
    }

    public Iterator<T> iterator() {
        return items.iterator();
    }

    public List<T> getItems() {
        return items;
    }

    public List<T> duplicateItems() {
        return new ArrayList<>(items);
    }

    public MaxHeap<T> clone(){
        return new MaxHeap<>(duplicateItems(),cost);
    }
}
