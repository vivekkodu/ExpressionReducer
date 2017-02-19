package com.cleartax.app;

import java.io.IOException;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        IReader reader = new FileReader();
        IParser parser = new JsonParser();
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("File name for expression is not provided.");
            }

            String inputString = reader.read(args[0]);
            Map<String, Object> expressionObject = parser.parse(inputString);
            String expression = ParsingUtility.getExpression(expressionObject);
            System.out.println(expression);
            //ExpressionReducer reducer = new ExpressionReducer("50*((1+(10/2))+((2+x)*4))=100");
            ExpressionReducer reducer = new ExpressionReducer(expression);
            String reduedExpression = reducer.reduceExpression();
            System.out.println(reduedExpression);
            System.out.println("x=" + ExpressionEvaluator.evaluate(reduedExpression));

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        } catch (InvalidExpressionException e) {
            e.printStackTrace();
        }


    }

}
