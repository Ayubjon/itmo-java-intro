package expression.exceptions;

import expression.*;

public class CheckedSqrt extends UnaryOperations {
    public CheckedSqrt(TripleExpression a) {
        super(a, "sqrt");
    }

    public int evaluate(int a) throws EvaluatingException {
        if (a < 0) {
            throw new EvaluatingException(a + " is negative");
        }
        int left = 0;
        int right = a;
        while (right - left > 0) {
            int mid = (left + right + 1) / 2;
            if (Integer.MAX_VALUE / mid < mid || mid * mid > a) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
