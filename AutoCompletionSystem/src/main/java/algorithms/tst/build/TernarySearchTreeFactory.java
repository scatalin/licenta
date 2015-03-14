package algorithms.tst.build;

import algorithms.tst.TernarySearchTree;
import algorithms.tst.TernarySearchTreeRecursive;
import model.dictionary.Dictionary;
import model.dictionary.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class TernarySearchTreeFactory {

    public static TernarySearchTree buildTst(Dictionary dictionary) {
        TernarySearchTree tst = new TernarySearchTreeRecursive();
        for(Word word :dictionary.getWords()){
            tst.insert(word.getWord());
        }
        return tst;
    }

    public static TernarySearchTree buildRandomTst(Dictionary dictionary) {
        TernarySearchTree tst = new TernarySearchTreeRecursive();
        List<Word> words = new ArrayList<Word>(dictionary.getWords());
        int middlePosition = words.size()/2;
        tst.insert(words.remove(middlePosition).getWord());
        Random random = new Random();
        while (words.size()>0){
            int position = random.nextInt(words.size());
            tst.insert(words.remove(position).getWord());
        }
        return tst;
    }
}
