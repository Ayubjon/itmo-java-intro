package expression;

public class Or extends BinaryOperations {
    public Or(TripleExpression a, TripleExpression b) {
        super(a, b, "|");
    }

    public int evaluate(int a, int b) {
        return a | b;
    }
}
