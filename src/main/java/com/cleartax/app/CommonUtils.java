package com.cleartax.app;

/**
 * Created by VIVEK VERMA on 2/18/2017.
 */
public class CommonUtils {

    // This puts opening and closing parenthesis at start and end of expression respectively.
    static String wrapStringWithParenthesis(String input){
        return Constants.OpenParenthesis + input + Constants.CloseParenthesis;
    }

    // This removes the parenthesis combination, if entire expression is inside a parenthesis and that's acting redundant.
    static String removeBoundaryParenthesis(String input) {
        if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')') {
            return input.substring(1, input.length() - 1);
        }

        return input;
    }
}
