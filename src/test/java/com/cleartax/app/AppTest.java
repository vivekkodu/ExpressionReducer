package com.cleartax.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testApp() {
        App app = new App();
        String[] args = new String[1];
        args[0] = "D://Temp//input.txt";
        app.main(args);
    }

    public void testComplexQueries(){
        String expression = "50*((1+(10/2))+((2+x)*4))=100";
        String reduedExpression = "";
        try {
            reduedExpression = new ExpressionReducer(expression).reduceExpression();
        } catch (InvalidExpressionException e) {
            e.printStackTrace();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }

        assertEquals(reduedExpression, "x=(((100/50)-(1+(10/2)))/4)-2");
        assertEquals(-3, ExpressionEvaluator.evaluate(reduedExpression));
    }
}
