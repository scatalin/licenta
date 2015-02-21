package algorithms.tst;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TstNodeTest {

    private TstNode tstNode;

    @Before
    public void setUp() throws Exception {
        tstNode = new TstNode('a');
        tstNode.setLeftChild(new TstNode('b'));
        tstNode.setMiddleChild(new TstNode('c'));
        tstNode.setRightChild(new TstNode('d'));
    }

    @After
    public void tearDown() throws Exception {
        tstNode = null;
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(tstNode.toString(), "TstNode{ch=a, Info='', lC=TstNode{ch=b, Info='', lC=null, mC=null, rC=null}, mC=TstNode{ch=c, Info='', lC=null, mC=null, rC=null}, rC=TstNode{ch=d, Info='', lC=null, mC=null, rC=null}}");
    }
}