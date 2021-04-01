package expression.exceptions;

import expression.*;

public class CheckedDivide extends BinaryOperations {
    public CheckedDivide(TripleExpression a, TripleExpression b) {
        super(a, b, "/");
    }

    public int evaluate(int a, int b) throws EvaluatingException {
        if (b == 0) {
            throw new EvaluatingException("division by zero " + a + "/" + b);
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new EvaluatingException("overflow " + a + "/" + b);
        }
        return a / b;
    }
}
