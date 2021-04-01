package expression;

public class Multiply extends BinaryOperations {
    public Multiply(TripleExpression a, TripleExpression b) {
        super(a, b, "*");
    }

    public int evaluate(int a, int b) {
        return a * b;
    }
}
