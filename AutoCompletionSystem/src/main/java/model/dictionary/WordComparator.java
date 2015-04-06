package model.dictionary;

import java.util.Comparator;

/**
 * Created by Catalin on 4/6/2015 .
 */
public class WordComparator implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        //sortare crescatoare
        return o1.getWord().compareTo(o2.getWord());
    }

}
