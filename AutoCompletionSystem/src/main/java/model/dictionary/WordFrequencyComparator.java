package model.dictionary;

import java.util.Comparator;

/**
 * Created by Catalin on 3/15/2015 .
 */
public class WordFrequencyComparator implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        //sortare descrescatoare
        return o2.getFrequency() - o1.getFrequency();
    }
}