package expression.exceptions;

import expression.*;

public class CheckedSubtract extends BinaryOperations {
    public CheckedSubtract(TripleExpression a, TripleExpression b) {
        super(a, b, "-");
    }

    public int evaluate(int a, int b) throws EvaluatingException {
        if (a >= 0 && b < 0 && b < a - Integer.MAX_VALUE) {
            throw new EvaluatingException("overflow " + a + "-" + b);
        }
        if (a <= 0 && b > 0 && a < Integer.MIN_VALUE + b) {
            throw new EvaluatingException("overflow " + a + "-" + b);
        }
        return a - b;
    }
}
