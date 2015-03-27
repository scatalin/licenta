package algorithms.heap;

import algorithms.tst.intern.TstNode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by gstan on 27.03.2015.
 */
public class AutoCompletionHeap<T extends TstNode> {

    private List<T> items;

    public AutoCompletionHeap() {
        items = new ArrayList<T>();
    }

    private void shiftUp() {
        int k = items.size() - 1;
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
    }

    public void insert(T item) {
        items.add(item);
        shiftUp();
    }

    private void shiftDown() {
        int k = 0;
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
    }

    public T delete() throws NoSuchElementException {
        if (items.size() == 0) {
            throw new NoSuchElementException();
        }
        if (items.size() == 1) {
            return items.remove(0);
        }
        T hold = items.get(0);
        items.set(0, items.remove(items.size() - 1));
        shiftDown();
        return hold;
    }

    private int parent(int k) {
        return (k - 1) / 2;
    }

    private int rightChild(int k) {
        return 2 * k + 2;
    }

    private int leftChild(int k) {
        return 2 * k + 1;
    }

}
