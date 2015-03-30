package algorithms.tst;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("FieldCanBeLocal")
public class TernarySearchTreeRecursiveTest {

    private TernarySearchTree tst;
    private static String word1 = "asd";
    private static String word2 = "as";
    private static String word3 = "fr";
    private static String word4 = "frta";
    private static String word5 = "bbr";
    private static String word6 = "bar";
    private static String word7 = "gz";
    private static String word8 = "chr";
    private static String[] words;
    private static String[] words1;
    private static String[] words2;

    @BeforeClass
    public static void setUpClass() throws Exception {
        words = new String[8];
        words[0] = word3;
        words[1] = word4;
        words[2] = word1;
        words[3] = word2;
        words[4] = word5;
        words[5] = word6;
        words[6] = word7;
        words[7] = word8;
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
    }

    @Ignore
    @Test
    public void testLoad() throws Exception {
//        tst.load(words1);
    }

    @Ignore
    @Test
    public void testAdditionalLoad() throws Exception {
//        tst.load(words);
//        String tst1 = tst.print();
//        tst.load(words1);
//        tst.additionalLoad(words2);
//        Assert.assertEquals(tst1, tst.print());
    }

    @Ignore
    @Test
    public void testSearchFound() throws Exception {
//        tst.load(words);
//        Assert.assertTrue(tst.search(word1));
    }

    @Ignore
    @Test
    public void testSearchNotFound() throws Exception {
//        tst.load(words);
//        Assert.assertFalse(tst.search(word1 + "s"));
    }

    @Ignore
    @Test
    public void testInsert() throws Exception {
//        tst.load(words);
//        String tst1 = tst.print();
//        tst.insert(word1);
//        Assert.assertEquals(tst1,tst.print());
    }

    @Ignore
    @Test
    public void testTopK(){
//        tst.load(words);
//        tst.setK(2);
//        List<String> completions = tst.getNextTopK("a");
    }

    @Test
    public void testSomething(){
        System.out.println("s".startsWith("sa"));
    }

}
