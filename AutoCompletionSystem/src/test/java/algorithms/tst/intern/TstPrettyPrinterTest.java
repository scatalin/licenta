package algorithms.tst.intern;

import algorithms.tst.TernarySearchTree;
import algorithms.tst.TernarySearchTreeRecursive;
import model.dictionary.Dictionary;
import model.dictionary.Word;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

@SuppressWarnings("FieldCanBeLocal")
public class TstPrettyPrinterTest {

    private TernarySearchTree tst;
    private Dictionary dictionary;
    private static String word1 = "asd";
    private static String word2 = "as";
    private static String word3 = "fr";
    private static String word4 = "frta";
    private static String word5 = "bbr";
    private static String word6 = "bar";
    private static String word7 = "jz";
    private static String word8 = "chr";
    private static String word9 = "ht";
    private static String word10 = "ij";
    private static String word11 = "gz";
    private static String word12 = "jzgh";
    private static String[] words;
    private static String[] words1;
    private static String[] words2;
    private static List<Word> wordsList;

    @BeforeClass
    public static void setUpClass() throws Exception {
        words = new String[12];
        words[0] = word3;
        words[1] = word4;
        words[2] = word1;
        words[3] = word2;
        words[4] = word5;
        words[5] = word6;
        words[6] = word7;
        words[7] = word8;
        words[8] = word9;
        words[9] = word10;
        words[10] = word11;
        words[11] = word12;
        wordsList = new ArrayList<Word>();
        wordsList.add(new Word(word3,30));
        wordsList.add(new Word(word4,28));
        wordsList.add(new Word(word1,26));
        wordsList.add(new Word(word2,24));
        wordsList.add(new Word(word5,22));
        wordsList.add(new Word(word6,20));
        wordsList.add(new Word(word7,17));
        wordsList.add(new Word(word8,15));
        wordsList.add(new Word(word9,13));
        wordsList.add(new Word(word10,10));
        wordsList.add(new Word(word11,6));
        wordsList.add(new Word(word12,18));
        words1 = new String[5];
        words1[0] = word3;
        words1[1] = word4;
        words1[2] = word1;
        words1[3] = word2;
        words1[4] = word5;
        words2 = new String[2];
        words2[0] = word3;
        words2[1] = word4;

    }

    @Before
    public void setUp() throws Exception {
        tst = new TernarySearchTreeRecursive();
        dictionary = new Dictionary();
        for(Word word : wordsList){
            dictionary.addWord(word.getWord(), word.getFrequency());
        }
        dictionary.sortDictionaryByWeight();
    }

    @Test
    public void testLoad() throws Exception {
        tst.load(dictionary.getWords());
        System.out.println(tst.print());
    }

}

