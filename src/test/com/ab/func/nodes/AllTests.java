package test.com.ab.func.nodes;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.ab.func");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestNodes.class);
        suite.addTestSuite(TestParser.class);
        //$JUnit-END$
        return suite;
    }
}
