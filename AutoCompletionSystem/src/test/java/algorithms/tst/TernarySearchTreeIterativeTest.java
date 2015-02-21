package algorithms.tst;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TernarySearchTreeIterativeTest {

    private TernarySearchTree tst;
    private static String word1 = "asd";
    private static String word2 = "as";
    private static String word3 = "fr";
    private static String word4 = "frt";
    private static String[] words;
    private static String[] words1;
    private static String[] words2;

    @BeforeClass
    public static void setUpClass() throws Exception {
        words = new String[4];
        words[0] = word1;
        words[1] = word2;
        words[2] = word3;
        words[3] = word4;
        words1 = new String[2];
        words1[0] = word1;
        words1[1] = word2;
        words2 = new String[2];
        words2[0] = word3;
        words2[1] = word4;

    }

    @Before
    public void setUp() throws Exception {
        tst = new TernarySearchTreeIterative();
    }

    @Test
    public void testLoad() throws Exception {
        tst.load(words);
        Assert.assertEquals(tst.print(),
                "      l f\n" +
                "         m r\n" +
                "            m t\n" +
                "   a\n" +
                "      m s\n" +
                "         m d\n");
    }

    @Test
    public void testAdditionalLoad() throws Exception {
        tst.load(words);
        String tst1 = tst.print();
        tst.load(words1);
        tst.additionalLoad(words2);
        Assert.assertEquals(tst1, tst.print());
    }

    @Test
    public void testSearchFound() throws Exception {
        tst.load(words);
        Assert.assertTrue(tst.search(word1));
    }

    @Test
    public void testSearchNotFound() throws Exception {
        tst.load(words);
        Assert.assertFalse(tst.search(word1 + "s"));
    }


    @Test
    public void testInsert() throws Exception {
        tst.load(words);
        String tst1 = tst.print();
        tst.insert(word1);
        Assert.assertEquals(tst1,tst.print());
    }

}
