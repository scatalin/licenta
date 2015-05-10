package algorithms.heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gstan on 27.03.2015.
 */
public class MaxHeap<T extends Comparable> {

    private List<T> items;

    public MaxHeap() {
        items = new ArrayList<>();
    }

    public int shiftUp(int k) {
        while (k > 0) {
            int p = parent(k);
            T item = items.get(k);
            T parent = items.get(p);

            if (item.compareTo(parent) > 0) {
                //swap
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
        items.add(item);
        shiftUp(items.size() - 1);
    }

    public int shiftDown(int k) {
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
        shiftDown(0);
        return hold;
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
}
