package com.cleartax.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.validation.Validator;

/**
 * Unit test for simple App.
 */
public class ExpressionReducerTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExpressionReducerTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testReduceExpression() throws InvalidExpressionException, InvalidOperationException {
        ExpressionReducer reducer = new ExpressionReducer("1+(x*10)=21");
        assertEquals("x=(21-1)/10", reducer.reduceExpression());
        reducer = new ExpressionReducer("50*((1+(10/2))-((2+x)*10))=20");
        assertEquals("x=((((20/50)-(1+(10/2)))*(-1))/10)-2", reducer.reduceExpression());
    }
}
