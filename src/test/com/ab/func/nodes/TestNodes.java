package test.com.ab.func.nodes;

import junit.framework.TestCase;
import com.ab.func.nodes.*;
import com.ab.func.util.BaseException;

public class TestNodes extends TestCase {
    private int a = 2;
    private int b = 3;
    private int c = 5;

    public void testInt()
    {
        //int node
        IntNode in = new IntNode(a);
        assertEquals(a, in.eval());
    }
    public void testF() throws BaseException
    {
        // f function
        FFunction f = new FFunction(new IntNode(b));
        assertEquals(b*2, f.eval());
    }
    public void testG() throws BaseException
    {
        //g function
        GFunction g = new GFunction(new IntNode(b),new IntNode(c));
        assertEquals(b+c, g.eval());
    }
    public void testExpr() throws BaseException
    {
        //expressions
        BaseNode node;
        
        //
        node = new GFunction(
                new FFunction(
                	new GFunction(
                	    new IntNode(a), 
                		new FFunction(
                		        new IntNode(b)
                		)
                	)
                 ),
                 new IntNode(c)
                )
                ;
        
        assertEquals(((a+(b*2))*2)+c, node.eval() );
    }
}
