package algorithms.heap;

import model.dictionary.Word;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gstan on 27.03.2015.
 */
public class AutoCompletionHeap<T extends HeapNode> {

    private List<Word> foundWords;
    private List<T> items;

    public AutoCompletionHeap() {
        items = new ArrayList<T>();
        foundWords = new ArrayList<Word>();
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
//        if (item.getNode().isEndWord()) {
//            foundWords.add(new Word(item.getBuiltWord(), item.getNode().getEndWordWeight()));
//        }
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

    public T delete() {
        if (items.size() == 0) {
            return null;
        }
        if (items.size() == 1) {
            return items.remove(0);
        }
        T hold = items.get(0);
        items.set(0, items.remove(items.size() - 1));
        shiftDown();
        return hold;
    }

    public void clearHeap() {
        items.clear();
        foundWords.clear();
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

    public void clearInvalidPaths(String prefix) {
        Iterator<Word> iter = foundWords.iterator();
        while (iter.hasNext()) {
            Word word = iter.next();
            if (!word.getWord().startsWith(prefix)) {
                iter.remove();
            }
        }
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (!item.getBuiltWord().startsWith(prefix) && !prefix.startsWith(item.getBuiltWord())) {
                it.remove();
            }
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Word> getWordList() {
        return foundWords;
    }

    public void seachFurther(String prefix, int limit) {
        while (items.size() > 0 && foundWords.size() < limit) {
            T item = delete();
            if(item == null){
                return;
            }
            if(!item.getBuiltWord().startsWith(prefix) && !prefix.startsWith(item.getBuiltWord())){
                continue;
            }
            if(item.getNode().isEndWord()){
                foundWords.add(new Word(item.getBuiltWord()+item.getNode().getCharacter(), item.getNode().getEndWordWeight()));
            }
            //todo prune the search paths
            if(item.getNode().getLeftChild() != null){
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getLeftChild());
                insert((T) newNode);
            }
            if(item.getNode().getMiddleChild() != null){
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getMiddleChild());
                newNode.addCharacter(item.getNode().getCharacter());
                insert((T) newNode);
            }
            if(item.getNode().getRightChild() != null){
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getRightChild());
                insert((T) newNode);
            }
        }
    }

    @Override
    public String toString() {
        return "AutoCompletionHeap{" +
                "foundWords=" + foundWords +
                ", items=" + items +
                '}';
    }
}
