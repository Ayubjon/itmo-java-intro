package expression;

public class Divide extends BinaryOperations {
    public Divide(TripleExpression a, TripleExpression b) {
        super(a, b, "/");
    }

    public int evaluate(int a, int b) {
        return a / b;
    }
}
