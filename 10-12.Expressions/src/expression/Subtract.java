package expression;

public class Subtract extends BinaryOperations {
    public Subtract(TripleExpression a, TripleExpression b) {
        super(a, b, "-");
    }

    public int evaluate(int a, int b) {
        return a - b;
    }
}
