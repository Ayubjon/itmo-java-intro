package expression.exceptions;

import expression.*;

public class CheckedAdd extends BinaryOperations {
    public CheckedAdd(TripleExpression a, TripleExpression b) {
        super(a, b, "+");
    }

    public int evaluate(int a, int b) throws EvaluatingException {
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new EvaluatingException("overflow " + a + "+" + "b");
        }
        if (a < 0 && b < Integer.MIN_VALUE - a) {
            throw new EvaluatingException("overflow " + a + "+" + "b");
        }
        return a + b;
    }
}
