package com.cleartax.app;

import java.util.Map;

/**
 * Created by VIVEK VERMA on 2/18/2017.
 */
public class ParsingUtility {

    static String getExpression(Object expressionObject) throws InvalidOperationException{
        Map<String, Object> expressionMap = (Map) expressionObject;
        String lhs = getSimpleExpression(expressionMap.get(Constants.LhsVariable));
        if(lhs.length() > 1){
            lhs = lhs.substring(1, lhs.length()-1);
        }

        return lhs + "=" + ((Double)expressionMap.get(Constants.RhsVariable)).intValue();
    }

    // This is being used to simplify the lhs from input expression
    private static String getSimpleExpression(Object expressionObject) throws InvalidOperationException {

        // This is to handle x
        if (expressionObject instanceof String) {
            return (String) expressionObject;
        }

        if (expressionObject instanceof Double) {
            return Integer.toString(((Double) expressionObject).intValue());
        }

        Map<String, Object> expressionMap = (Map) expressionObject;
        return CommonUtils.wrapStringWithParenthesis(getSimpleExpression(expressionMap.get(Constants.LhsVariable)) +
                getOperationCharacter((String) expressionMap.get(Constants.Operation)) +
                getSimpleExpression(expressionMap.get(Constants.RhsVariable)));
    }

    // Based on operation string, actual operation character will be returned.
    private static String getOperationCharacter(String operation) throws InvalidOperationException {
        // This can be converted to switch with higher version of java
        if (operation.equalsIgnoreCase(Constants.AddOperation))
            return "+";
        else if (operation.equalsIgnoreCase(Constants.SubtractOperation))
            return "-";
        else if (operation.equalsIgnoreCase(Constants.MultiplyOperation))
            return "*";
        else if (operation.equalsIgnoreCase(Constants.DivideOperation))
            return "/";

        throw new InvalidOperationException("Provided operation is not supported");
    }
}
