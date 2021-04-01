package expression.parser;

import expression.*;
import expression.TripleExpression;

public class ExpressionParser implements Parser {
    private String expression;
    private String str;
    private int currentIndex = 0;
    private Token currentToken;
    private int value = 0;

    private void skipWhiteSpace() {
        while (currentIndex < expression.length() && Character.isWhitespace(expression.charAt(currentIndex))) {
            currentIndex++;
        }
    }

    private void nextToken() {
        skipWhiteSpace();
        if (currentIndex >= expression.length()) {
            currentToken = Token.END;
            return;
        }
        char c = expression.charAt(currentIndex);
        switch (expression.charAt(currentIndex)) {
            case '+':
                currentToken = Token.ADD;
                break;
            case '-':
                if (currentToken == Token.CON || currentToken == Token.VAR || currentToken == Token.CLB) {
                    currentToken = Token.SUB;
                } else {
                    currentToken = Token.MINUS;
                }
                break;
            case '/':
                currentToken = Token.DIV;
                break;
            case '*':
                currentToken = Token.MUL;
                break;
            case '(':
                currentToken = Token.OPB;
                break;
            case ')':
                currentToken = Token.CLB;
                break;
            case '&':
                currentToken = Token.AND;
                break;
            case '|':
                currentToken = Token.OR;
                break;
            case '^':
                currentToken = Token.XOR;
                break;
            default:
                if (Character.isDigit(c)) {
                    int leftBorder = currentIndex;
                    while (currentIndex < expression.length() && Character.isDigit(expression.charAt(currentIndex))) {
                        ++currentIndex;
                    }
                    value = Integer.parseUnsignedInt(expression.substring(leftBorder, currentIndex));
                    currentToken = Token.CON;
                    --currentIndex;
                } else if (Character.isLetter(expression.charAt(currentIndex))) {
                    int leftBorder = currentIndex;
                    while (currentIndex < expression.length() && Character.isLetter(expression.charAt(currentIndex))) {
                        ++currentIndex;
                    }
                    str = expression.substring(leftBorder, currentIndex);
                    currentToken = Token.VAR;
                    --currentIndex;
                } else {
                    currentToken = Token.ERR;
                }
        }
        ++currentIndex;
    }

    private TripleExpression unary() {
        nextToken();
        TripleExpression res;

        switch (currentToken) {
            case CON:
                res = new Const(value);
                nextToken();
                break;
            case VAR:
                res = new Variable(str);
                nextToken();
                break;
            case MINUS:
                res = new Minus(unary());
                break;
            case OPB:
                res = or();
                nextToken();
                break;
            default:
                return new Const(0);
        }
        return res;
    }

    private TripleExpression mulDiv() {
        TripleExpression res = unary();
        while (true) {
            if (currentToken == Token.MUL) {
                res = new Multiply(res, unary());
            } else if (currentToken == Token.DIV) {
                res = new Divide(res, unary());
            } else {
                return res;
            }
        }
    }

    private TripleExpression addSub() {
        TripleExpression res = mulDiv();
        while (true) {
            if (currentToken == Token.ADD) {
                res = new Add(res, mulDiv());
            } else if (currentToken == Token.SUB) {
                res = new Subtract(res, mulDiv());
            } else {
                return res;
            }
        }
    }

    private TripleExpression and() {
        TripleExpression res = addSub();
        while (true) {
            if (currentToken == Token.AND) {
                res = new And(res, addSub());
            } else {
                return res;
            }
        }
    }

    private TripleExpression xor() {
        TripleExpression res = and();
        while (true) {
            if (currentToken == Token.XOR) {
                res = new Xor(res, and());
            } else {
                return res;
            }
        }
    }

    private TripleExpression or() {
        TripleExpression res = xor();
        while (true) {
            if (currentToken == Token.OR) {
                res = new Or(res, xor());
            } else {
                return res;
            }
        }
    }

    public TripleExpression parse(final String expression) {
        assert expression != null : "Expression is null";
        currentIndex = 0;
        this.expression = expression;
        currentToken = Token.ERR;
        return or();
    }
}
