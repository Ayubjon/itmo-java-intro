package expression.exceptions;

import expression.parser.*;
import expression.*;
import expression.TripleExpression;

import java.util.EnumSet;
import java.util.Set;

public class ExpressionParser implements Parser {
    private String expression;
    private String str;
    private int currentIndex = 0;
    private Token currentToken = Token.BEG;
    private int value = 0;
    private int col_br = 0;
    private int firstBracket = 0;

    private static final Set<Token> operations = EnumSet.of(Token.ADD, Token.DIV,
            Token.MUL, Token.MINUS, Token.SUB);

    private static final Set<String> varsAndFunctions = Set.of("x", "y", "z", "sqrt", "abs");

    private void skipWhiteSpace() {
        while (currentIndex < expression.length() && Character.isWhitespace(expression.charAt(currentIndex))) {
            currentIndex++;
        }
    }

    private void checkOperation(boolean isB) throws ParsingException {
        if (currentToken == Token.ERR) throw new IncorrectExpressionException(' ', 0);
        if (operations.contains(currentToken) || currentToken == Token.OPB || currentToken == Token.BEG)
            throw new ParsingException("Index: " + currentIndex + ". Two operations came together.");
        if (!isB && currentIndex + 2 > expression.length())
            throw new EndException(currentToken);
    }

    private void getNumber(StringBuilder s) throws ParsingException {
        while (currentIndex < expression.length() && Character.isDigit(expression.charAt(currentIndex))) {
            s.append(expression.charAt(currentIndex++));
        }
        try {
            value = Integer.parseInt(s.toString());
        } catch (NumberFormatException e) {
            throw new ParsingException("Index: " + currentIndex + ". The number " + s.toString() + " is too large or too small");
        }
        currentToken = Token.CON;
    }

    private void getVariable() throws ParsingException {
        int leftBorder = currentIndex;
        checkOperand();
        while (currentIndex < expression.length() && Character.isLetter(expression.charAt(currentIndex))) {
            ++currentIndex;
        }
        str = expression.substring(leftBorder, currentIndex);
        if (!varsAndFunctions.contains(str)) {
            throw new UnknownFuncOrVarException(str);
        }
        if (str.equals("sqrt")) {
            currentToken = Token.SQRT;
        } else if (str.equals("abs")) {
            currentToken = Token.ABS;
        } else {
            currentToken = Token.VAR;
        }
        --currentIndex;
    }

    private void checkOperand() throws ParsingException {
        if (currentToken == Token.CLB || currentToken == Token.VAR || currentToken == Token.CON)
            throw new ParsingException("Index: " + currentIndex + ". Forgot to write some operation.");
    }

    private void nextToken() throws ParsingException {
        skipWhiteSpace();
        if (currentIndex >= expression.length()) {
            currentToken = Token.END;
            return;
        }
        char c = expression.charAt(currentIndex);
        switch (c) {
            case '+':
                checkOperation(false);
                currentToken = Token.ADD;
                break;
            case '-':
                if (currentToken == Token.CON || currentToken == Token.VAR || currentToken == Token.CLB) {
                    checkOperation(false);
                    currentToken = Token.SUB;
                } else {
                    StringBuilder s = new StringBuilder("-");
                    currentIndex++;
                    skipWhiteSpace();
                    currentToken = Token.MINUS;
                    if (currentIndex + 1 >
                            expression.length()) {
                        throw new EndException(currentToken);
                    }
                    if (currentIndex < expression.length() && Character.isDigit(expression.charAt(currentIndex))) {
                        getNumber(s);
                        --currentIndex;
                        break;
                    }
                    --currentIndex;
                }
                break;
            case '/':
                checkOperation(false);
                currentToken = Token.DIV;
                break;
            case '*':
                checkOperation(false);
                currentToken = Token.MUL;
                break;
            case '(':
                if (col_br == 0) firstBracket = currentIndex;
                col_br++;
                checkOperand();
                currentToken = Token.OPB;
                break;
            case ')':
                if (col_br <= 0) throw new BracketException("close", currentIndex);
                checkOperation(true);
                col_br--;
                currentToken = Token.CLB;
                break;
            default:
                if (Character.isDigit(c)) {
                    checkOperand();
                    getNumber(new StringBuilder());
                    currentToken = Token.CON;
                    --currentIndex;
                } else if (Character.isLetter(expression.charAt(currentIndex))) {
                    getVariable();
                } else {
                    throw new IncorrectExpressionException(expression.charAt(currentIndex), currentIndex);
                }
        }
        ++currentIndex;
    }

    private TripleExpression unary() throws ParsingException {
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
                res = new CheckedNegate(unary());
                break;
            case OPB:
                res = addSub();
                nextToken();
                break;
            case ABS:
                res = new CheckedAbs(unary());
                break;
            case SQRT:
                res = new CheckedSqrt(unary());
                break;
            default:
                if (currentIndex >= expression.length()) throw new EndException(currentToken);
                throw new IncorrectExpressionException(expression.charAt(currentIndex), currentIndex);
        }
        return res;
    }

    private TripleExpression mulDiv() throws ParsingException {
        TripleExpression res = unary();
        while (true) {
            if (currentToken == Token.MUL) {
                res = new CheckedMultiply(res, unary());
            } else if (currentToken == Token.DIV) {
                res = new CheckedDivide(res, unary());
            } else {
                return res;
            }
        }
    }

    private TripleExpression addSub() throws ParsingException {
        TripleExpression res = mulDiv();
        while (true) {
            if (currentToken == Token.ADD) {
                res = new CheckedAdd(res, mulDiv());
            } else if (currentToken == Token.SUB) {
                res = new CheckedSubtract(res, mulDiv());
            } else {
                return res;
            }
        }
    }

    public TripleExpression parse(final String expression) throws ParsingException {
        assert expression != null : "Expression is null";
        currentIndex = 0;
        this.expression = expression;
        currentToken = Token.ERR;
        col_br = 0;
        TripleExpression t = addSub();
        if (col_br > 0) {
            throw new BracketException("open", firstBracket);
        }
        return t;
    }
}
