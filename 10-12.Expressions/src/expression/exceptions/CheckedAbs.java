package expression.exceptions;

import expression.*;

public class CheckedAbs extends UnaryOperations {
    public CheckedAbs(TripleExpression a) {
        super(a, "abs");
    }

    public int evaluate(int a) throws EvaluatingException {
        if (a == Integer.MIN_VALUE) {
            throw new EvaluatingException("overflow with number " + a);
        }
        if (a < 0) {
            return -a;
        }
        return a;
    }
}
