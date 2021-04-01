package expression;

import expression.exceptions.EvaluatingException;

import java.util.Objects;

public class Minus implements TripleExpression {
    private final TripleExpression a;

    public Minus(TripleExpression a) {
        this.a = a;
    }

    public String toString() {
        return "-" + a.toString();
    }

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return (-1) * a.evaluate(x, y, z);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Minus) {
            Minus that = (Minus) obj;
            return this.a == that.a;
        }
        return false;
    }

    final public int hashCode() {
        return Objects.hash(a, getClass());
    }
}
