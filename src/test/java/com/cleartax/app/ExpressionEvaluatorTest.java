package com.cleartax.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by VIVEK VERMA on 2/19/2017.
 */
public class ExpressionEvaluatorTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExpressionEvaluatorTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testExpressionEvaluator() {
        assertEquals(22, ExpressionEvaluator.evaluate("10+2*6"));
        assertEquals(2, ExpressionEvaluator.evaluate("(21-1)/10"));
        assertEquals(-3, ExpressionEvaluator.evaluate("(((100/50)-(1+(10/2)))/4)-2"));
    }
}