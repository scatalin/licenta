package algorithms.tst;

import algorithms.heap.AutoCompletionHeap;
import algorithms.heap.HeapNode;
import algorithms.tst.intern.TstNode;
import algorithms.tst.printing.TstPrettyPrinter;
import algorithms.utils.PrettyPrinter;
import model.dictionary.Word;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Catalin on 2/21/2015 .
 */
public abstract class AbstractTernarySearchTree implements TernarySearchTree {

    TstNode root;
    AutoCompletionHeap<HeapNode> heap;
    List<Word> foundWords;
    int k;

    AbstractTernarySearchTree(){
        heap = new AutoCompletionHeap<HeapNode>();
        foundWords = new ArrayList<Word>();
        k = 5;
    }

    public void load(List<Word> words) {
        root = null;
        for (Word word : words) {
            insert(word.getWord(),word.getFrequency());
        }
    }

    public void additionalLoad(List<Word> words) {
        for (Word word : words) {
            insert(word.getWord(), word.getFrequency());
        }
    }

    public String print() {
        PrettyPrinter printer = new TstPrettyPrinter(root);
        return printer.prettyPrint();
    }

    @Override
    public void setK(int k) {
        this.k = k;
    }

    @Override
    public void resetSearchK() {
        heap.clearHeap();
        foundWords.clear();
        heap.insert(new HeapNode(root,""));
    }

    @Override
    public List<Word> getNextTopK(String prefix) {
        clearInvalidPaths(prefix);
        seachFurther(prefix,k);
        return foundWords;
    }

    protected void seachFurther(String prefix, int limit) {
        while (!heap.isEmpty() && foundWords.size() < limit) {
            HeapNode item = heap.delete();
            if(item == null){
                return;
            }
            if(!item.getBuiltWord().startsWith(prefix) && !prefix.startsWith(item.getBuiltWord())){
                continue;
            }
            if(item.getNode().isEndWord() && item.getBuiltWord().startsWith(prefix)){
                foundWords.add(new Word(item.getBuiltWord()+item.getNode().getCharacter(), item.getNode().getEndWordWeight()));
            }
            //todo prune the search paths
            if(item.getNode().getLeftChild() != null){
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getLeftChild());
                heap.insert(newNode);
            }
            if(item.getNode().getMiddleChild() != null){
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getMiddleChild());
                newNode.addCharacter(item.getNode().getCharacter());
                heap.insert(newNode);
            }
            if(item.getNode().getRightChild() != null){
                HeapNode newNode = item.clone();
                newNode.setNode(item.getNode().getRightChild());
                heap.insert(newNode);
            }
        }
    }

    protected void clearInvalidPaths(String prefix) {
        Iterator<Word> iter = foundWords.iterator();
        while (iter.hasNext()) {
            Word word = iter.next();
            if (!word.getWord().startsWith(prefix)) {
                iter.remove();
            }
        }
        heap.clearInvalidPaths(prefix);
    }


}
