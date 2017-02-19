package com.cleartax.app;

/**
 * Created by VIVEK VERMA on 2/18/2017.
 */
public class ExpressionReducer {
    private String expression;
    private String lhs;
    private String rhs;
    private int expressionStartIndex = -1;
    private int expressionEndIndex = -1;

    ExpressionReducer(String expression) {
        this.expression = expression;
    }

    public String reduceExpression() throws InvalidExpressionException, InvalidOperationException {
        String[] expressionPartitions = expression.split("=");
        if (expressionPartitions.length != 2) {
            throw new InvalidExpressionException("Provided expression is not valid: " + expression);
        }

        lhs = expressionPartitions[0];
        rhs = expressionPartitions[1];
        simplifyVariable();
        rhs = CommonUtils.removeBoundaryParenthesis(rhs);
        return "x" + "=" + rhs;
    }

    private void simplifyVariable() throws InvalidOperationException {
        while (true) {
            if (checkIfNegationIsValid()) {
                // lhs is starting with a '-' sign
                removeNegativeFromLeft();
                moveNegativeToRhs();
            } else if (isRemovableExpressionFound()) {

                if (expressionStartIndex == 0) {
                    moveExpressionToRhs(lhs.substring(0, expressionEndIndex + 1), getInitialExpressionMovementOperation(lhs.charAt(expressionEndIndex + 1)));
                } else {
                    moveExpressionToRhs(lhs.substring(expressionStartIndex), getComplimentaryOperation(lhs.charAt(expressionStartIndex - 1)));
                }

                removeExpressionFromLeft();
            } else {
                break;
            }
        }
    }

    private String getInitialExpressionMovementOperation(char operation) throws InvalidOperationException {
        switch (operation) {
            case '+':
            case '-':
                return "-";
            default:
                return getComplimentaryOperation(operation);
        }
    }

    private String getComplimentaryOperation(char operation) throws InvalidOperationException {
        switch (operation) {
            case '+':
                return "-";
            case '-':
                return "+";
            case '/':
                return "*";
            case '*':
                return "/";
            default:
                throw new InvalidOperationException("Operation type i invalid");
        }
    }

    // Find the next expression which will be moved to rhs
    private boolean isRemovableExpressionFound() {
        boolean isVariablePresentInExPression = false;
        int openParenthesisCount = 0;
        for (int i = 0; i < lhs.length(); i++) {
            char currentCharacter = lhs.charAt(i);
            switch (currentCharacter) {
                case '(':
                    if (openParenthesisCount == 0) {
                        expressionStartIndex = i;
                    }
                    openParenthesisCount++;
                    break;
                case ')':
                    openParenthesisCount--;
                    if (openParenthesisCount == 0) {
                        if (isVariablePresentInExPression) {
                            expressionStartIndex = -1;
                            isVariablePresentInExPression = false;
                        } else {
                            expressionEndIndex = i;
                            return true;
                        }
                    }
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    break;
                case 'x':
                    isVariablePresentInExPression = true;
                    break;
                default:
                    if (openParenthesisCount == 0) {
                        expressionStartIndex = i;
                        expressionEndIndex = i;
                        for (int j = i+1; j < lhs.length(); j++) {
                            if(lhs.charAt(j) >= '0' && lhs.charAt(j) <= '9'){
                                expressionEndIndex++;
                            }else{
                                return true;
                            }
                        }
                        return true;
                    }
            }
        }

        return false;
    }

    // True is lhs is multiple by a '-'
    private boolean checkIfNegationIsValid() {
        if (lhs.charAt(0) == '-' && lhs.charAt(1) == '(') {
            return true;
        }

        return false;
    }

    // This will remove an expression from lhs. This expression has to be added to rhs separately
    private void removeExpressionFromLeft() {

        if (expressionStartIndex == 0) {
            if(lhs.charAt(expressionEndIndex + 1) == '-' )
            {
                lhs = lhs.substring(expressionEndIndex + 1);
            }
            else{
                lhs = lhs.substring(expressionEndIndex + 2);
            }
        } else {
            lhs = lhs.substring(0, expressionStartIndex - 1);
        }

        lhs = CommonUtils.removeBoundaryParenthesis(lhs);
    }

    // This will remove starting '-' from lhs
    private void removeNegativeFromLeft() {

        lhs = lhs.substring(1);
        lhs = CommonUtils.removeBoundaryParenthesis(lhs);
    }

    // This will move substring of lhs to rhs
    private void moveExpressionToRhs(String expression, String operation) {

        rhs = CommonUtils.wrapStringWithParenthesis(rhs + operation + expression);
    }

    // If there is a starting negative sign on lhs, it will be be moved to rhs
    private void moveNegativeToRhs() {

        moveExpressionToRhs("(-1)", "*");
    }
}
