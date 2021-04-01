package expression;

import expression.exceptions.EvaluatingException;

import java.util.Objects;

public abstract class UnaryOperations implements TripleExpression {
    private final TripleExpression a;
    private final String operation;

    public UnaryOperations(TripleExpression a, String operation) {
        this.a = a;
        this.operation = operation;
    }

    abstract protected int evaluate(int a) throws EvaluatingException;

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return evaluate(a.evaluate(x, y, z));
    }

    public String toString() {
        return operation + "(" + a.toString() + ")";
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            UnaryOperations expression = (UnaryOperations) obj;
            return a.equals(expression.a);
        }
        return false;
    }

    final public int hashCode() {
        return Objects.hash(a, operation);
    }
}