package expression.exceptions;

import expression.*;

public class CheckedMultiply extends BinaryOperations {
    public CheckedMultiply(TripleExpression a, TripleExpression b) {
        super(a, b, "*");
    }

    public int evaluate(int a, int b) throws EvaluatingException {
        if (a > 0 && b > 0 && Integer.MAX_VALUE / a < b) {
            throw new EvaluatingException("overflow " + a + "*" + b);
        }
        if (a > 0 && b < 0 && Integer.MIN_VALUE / a > b) {
            throw new EvaluatingException("overflow " + a + "*" + b);
        }
        if (a < 0 && b > 0 && Integer.MIN_VALUE / b > a) {
            throw new EvaluatingException("overflow " + a + "*" + b);
        }
        if (a < 0 && b < 0 && Integer.MAX_VALUE / a > b) {
            throw new EvaluatingException("overflow " + a + "*" + b);
        }
        return a * b;
    }
}
