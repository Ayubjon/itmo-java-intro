package expression;

public class Add extends BinaryOperations {
    public Add(TripleExpression a, TripleExpression b) {
        super(a, b, "+");
    }

    public int evaluate(int a, int b) {
        return a + b;
    }
}
