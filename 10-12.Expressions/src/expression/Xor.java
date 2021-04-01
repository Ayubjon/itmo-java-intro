package expression;

public class Xor extends BinaryOperations {
    public Xor(TripleExpression a, TripleExpression b) {
        super(a, b, "^");
    }

    public int evaluate(int a, int b) {
        return a ^ b;
    }
}
