package expression.exceptions;

import expression.*;

public class CheckedNegate extends UnaryOperations {
    public CheckedNegate(TripleExpression a) {
        super(a, "-");
    }

    public int evaluate(int a) throws EvaluatingException {
        if (a == Integer.MIN_VALUE) {
            throw new EvaluatingException("overflow with the number -(" + a + ")");
        }
        return (-1) * a;
    }
}
