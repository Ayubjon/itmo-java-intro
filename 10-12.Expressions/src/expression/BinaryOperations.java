package expression;

import expression.exceptions.EvaluatingException;

import java.util.Objects;

public abstract class BinaryOperations implements TripleExpression {
    private final TripleExpression first;
    private final TripleExpression second;
    private final String operation;

    public BinaryOperations(TripleExpression a, TripleExpression b, String operation) {
        this.first = a;
        this.second = b;
        this.operation = operation;
    }

    abstract protected int evaluate(int a, int b) throws EvaluatingException;

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return evaluate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + first.toString() + " " + operation + " " + second.toString() + ")";
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            BinaryOperations expression = (BinaryOperations) obj;
            return first.equals(expression.first) && second.equals(expression.second);
        }
        return false;
    }

    final public int hashCode() {
        return Objects.hash(first, second, operation);
    }
}