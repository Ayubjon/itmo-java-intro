package expression;

public class And extends BinaryOperations {
    public And(TripleExpression a, TripleExpression b) {
        super(a, b, "&");
    }

    public int evaluate(int a, int b) {
        return a & b;
    }
}
